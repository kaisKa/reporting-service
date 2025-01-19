package com.report.Reporting.Service.kafka;

import com.report.Reporting.Service.statistics.Stats;
import com.report.Reporting.Service.statistics.StatsService;
import lombok.extern.log4j.Log4j2;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.utils.Bytes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KTable;
import org.apache.kafka.streams.kstream.Materialized;
import org.apache.kafka.streams.state.KeyValueStore;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class Processor {

    private final StatsService usageService;

    private String inputTopic = "submissions";

    public Processor(StatsService usageService) {
        this.usageService = usageService;
    }


    @Bean
    public KStream<String, Submission> process(StreamsBuilder streamsBuilder) {
        // create a stream on the topic to consume from
        KStream<String, Submission> submissionKStream = streamsBuilder.stream(inputTopic);

//        // in case we want to store single submission event
//        submissionKStream.foreach((key, value) -> storeEvent(parseSubmission(value)));

        Materialized<String, Stats, KeyValueStore<Bytes, byte[]>> materialized = Materialized
                .<String, Stats, KeyValueStore<Bytes, byte[]>>as("aggregated-store")  // Explicitly define all generics
                .withKeySerde(Serdes.String())       // Serde for keys
                .withValueSerde(new StatsSerde());  // Custom Serde for values
        // aggregate data
        KTable<String, Stats> aggregates = submissionKStream
                .peek((key, value) -> {
                    log.info("Consumed event: " + value);  // Check if timestamp is in the event before aggregation
                })
                .groupByKey()
                .aggregate(
                        Stats::new,
                        (key, value, aggregate) -> {
                            log.info("In Aggregation - Submission Timestamp: " + value.getSubmissionTimestamp());
                            return updateAggregate(aggregate, value);
                        },
                        materialized
                );

        aggregates.toStream().foreach((serviceId, aggregate) -> usageService.save(aggregate));

        return submissionKStream;

    }

    private Stats updateAggregate(Stats aggregate, Submission submission) {
        aggregate.setServiceId(submission.getServiceId());
        aggregate.setSubmissionCount(aggregate.getSubmissionCount() + 1);
        aggregate.setLastSubmissionTimestamp(submission.getSubmissionTimestamp());

        //store the daily KPI
        String dayKey = submission.getSubmissionTimestamp().toLocalDate().toString();
        aggregate.getDailySubmissionCounts().merge(dayKey, 1, Integer::sum);

        //Store the weekly kpi
        String weekKey = submission.getSubmissionTimestamp().getYear() + "-W" + submission.getSubmissionTimestamp().getDayOfYear() / 7;
        aggregate.getWeeklySubmissionCounts().merge(weekKey, 1, Integer::sum);

        return aggregate;

    }

//    private void storeEvent(Event submission) {
//        ServiceEvent event = new ServiceEvent();
//        event.setId(UUID.randomUUID().toString()); // Generate a unique ID
//        event.setServiceId(submission.getServiceId());
//        event.setCustomerId(submission.getCustomerId());
//        event.setTimestamp(submission.getTimestamp());
//        event.setSubmittedData(submission.getSubmittedData());
//        event.setCustomerDetails(submission.getCustomerDetails());
//
//        eventRepository.save(event);
//    }

}
