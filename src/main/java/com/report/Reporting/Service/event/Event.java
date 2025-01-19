package com.report.Reporting.Service.event;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.Map;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Setter
@Getter
@Document(collection = "ServiceUsage")
public class Event {
    @Id
    private BigInteger _id;
    private int serviceId;
    private int customerId;
    private Map<String,String> submittedData;
    private LocalDateTime submissionTimestamp;
}
