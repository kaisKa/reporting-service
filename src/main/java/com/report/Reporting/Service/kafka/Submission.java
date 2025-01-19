package com.report.Reporting.Service.kafka;

import lombok.*;
import java.time.LocalDateTime;
import java.util.Map;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Getter
@Setter
public class Submission {


    private Long id;
    private Long serviceId;
    private Long customerId;
    private Map<String, String> submittedData;
    private LocalDateTime submissionTimestamp;
    private Customer customer;


    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    static class Customer {


        private Long id;
        private String firstName;
        private String lastName;
        private String email;
        private String password;


    }
}