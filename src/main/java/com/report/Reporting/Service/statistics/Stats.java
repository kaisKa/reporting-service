package com.report.Reporting.Service.statistics;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Setter
@Getter
@Document(collection = "ServiceUsage")
public class Stats {

    @Id
    private BigInteger _id;
    private Long serviceId;
    private int submissionCount;
    private LocalDateTime lastSubmissionTimestamp;
    private Map<String, Integer> dailySubmissionCounts = new HashMap<>();
    private Map<String, Integer> weeklySubmissionCounts = new HashMap<>();
}
