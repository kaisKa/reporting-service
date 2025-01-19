package com.report.Reporting.Service.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.report.Reporting.Service.statistics.Stats;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.serialization.Serializer;
import org.springframework.beans.factory.annotation.Autowired;
;


public class SubmissionSerde extends Serdes.WrapperSerde<Submission> {



    public SubmissionSerde() {

        super(new SubmissionSerializer(), new SubmissionDeserializer());
    }

    public static class SubmissionSerializer implements Serializer<Submission> {

        private final ObjectMapper objectMapper = new ObjectMapper();

        public SubmissionSerializer() {
            objectMapper.registerModule(new JavaTimeModule());
        }

        @Override
        public byte[] serialize(String topic, Submission data) {
            try {
                return objectMapper.writeValueAsBytes(data);
            } catch (Exception e) {
                throw new RuntimeException("Error serializing submission", e);
            }
        }
    }

    public static class SubmissionDeserializer implements Deserializer<Submission> {

        private final ObjectMapper objectMapper = new ObjectMapper();


        public SubmissionDeserializer() {
            objectMapper.registerModule(new JavaTimeModule());
        }
        @Override
        public Submission deserialize(String topic, byte[] data) {
            try {
                var t =  objectMapper.readValue(data, Submission.class);
                return t;
            } catch (Exception e) {
                throw new RuntimeException("Error deserializing submission", e);
            }
        }
    }
}





