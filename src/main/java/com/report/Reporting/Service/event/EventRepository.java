package com.report.Reporting.Service.event;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

@Repository
public interface EventRepository extends MongoRepository<Event, BigInteger> {



    List<Event> findByServiceId(Long id);

    List<Event> findByCustomerId(Long id);
}
