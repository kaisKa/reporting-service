package com.report.Reporting.Service.statistics;

import com.report.Reporting.Service.event.Event;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

@Repository
public interface StatsRepository extends MongoRepository<Stats, BigInteger> {

    // Total number of submissions per service (simplified query for exact match)
    List<Stats> findByServiceId(Long serviceId);

    // Find Stats based on submissionCount
    List<Stats> findBySubmissionCount(int submissionCount);

    // Custom query to fetch daily submission counts by a specific date
//    @Query("{'dailySubmissionCounts': {$exists: true, $in: [?0]}}")
    @Query("{'dailySubmissionCounts.?0': {$exists: true}}")
    List<Stats> findByDailySubmissionCountsContainingKey(String date);

    // Custom query for weekly submission counts
    @Query("{'weeklySubmissionCounts.?0': {$exists: true}}")
    List<Stats> findByWeeklySubmissionCountsContainingKey(String week);

    @Query("{}") // Find all documents
    List<Stats> findTopServiceBySubmissionCount(PageRequest pageRequest);
}
