package com.report.Reporting.Service.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.report.Reporting.Service.statistics.Stats;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.serialization.Serializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;
//
//public class StatsSerde extends Serdes.WrapperSerde<Stats> {
//
//
//
//    public StatsSerde() {
//
//        super(new StatsSerializer(), new StatsDeserializer());
//    }
//
//    public static class StatsSerializer implements Serializer<Stats> {
//
//
//        private final ObjectMapper objectMapper = new ObjectMapper();
//
//        public StatsSerializer() {
//            objectMapper.registerModule(new JavaTimeModule());
//        }
//
//        @Override
//        public byte[] serialize(String topic, Stats data) {
//            try {
//                return objectMapper.writeValueAsBytes(data);
//            } catch (Exception e) {
//                throw new RuntimeException("Error serializing Stats", e);
//            }
//        }
//    }
//
//    public static class StatsDeserializer implements Deserializer<Stats> {
//
//        private final ObjectMapper objectMapper = new ObjectMapper();
//
//
//        public StatsDeserializer() {
//            objectMapper.registerModule(new JavaTimeModule());
//        }
//        @Override
//        public Stats deserialize(String topic, byte[] data) {
//            try {
//                var t =  objectMapper.readValue(data, Stats.class);
//                return t;
//            } catch (Exception e) {
//                throw new RuntimeException("Error deserializing Stats", e);
//            }
//        }
//    }
//}


public class StatsSerde extends Serdes.WrapperSerde<Stats> {
    public StatsSerde() {
        super(new JsonSerializer<>(), new JsonDeserializer<>(Stats.class));
    }
}