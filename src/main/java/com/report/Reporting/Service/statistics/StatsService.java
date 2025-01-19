package com.report.Reporting.Service.statistics;

import com.report.Reporting.Service.event.Event;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatsService {

    private final StatsRepository repository;

    public StatsService(StatsRepository repository) {
        this.repository = repository;
    }

    public Stats save(Stats stats){
        return repository.save(stats);
    }
    public List<Stats> getALl(){
        return repository.findAll();
    }

    public List<Stats> getByServiceId(Long id){
        return repository.findByServiceId(id);
    }

    public List<Stats> getTotalSubmissionsPerService(Long serviceId) {
        return repository.findByServiceId(serviceId);
    }

    public List<Stats> getStatsBySubmissionCount(Integer submissionCount) {
        return repository.findBySubmissionCount(submissionCount);
    }
    public List<Stats> getDailyTrends(String date) {
        return repository.findByDailySubmissionCountsContainingKey(date);
    }

    public List<Stats> getWeeklyTrends(String week) {
        return repository.findByWeeklySubmissionCountsContainingKey(week);
    }


}
