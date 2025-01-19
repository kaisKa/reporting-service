package com.report.Reporting.Service.reporting;

import com.report.Reporting.Service.statistics.Stats;
import com.report.Reporting.Service.statistics.StatsService;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/report")
public class ReportController {

    private final StatsService service;

    public ReportController(StatsService service) {
        this.service = service;
    }

    @GetMapping("/total-submissions")
    public List<Stats> getTotalSubmissionsPerService(@RequestParam Long serviceId) {
        return service.getTotalSubmissionsPerService(serviceId);
    }

    @GetMapping("/stats-by-submission-count")
    public List<Stats> getStatsBySubmissionCount(@RequestParam Integer submissionCount) {
        return service.getStatsBySubmissionCount(submissionCount);
    }

    @GetMapping("/daily-trends")
    public List<Stats> getDailyTrends(@Parameter(description = "data format 'e.i 2025-01-19'") @RequestParam String date) {
        return service.getDailyTrends(date);
    }

    @GetMapping("/weekly-trends")
    public List<Stats> getWeeklyTrends(@RequestParam String week) {
        return service.getWeeklyTrends(week);
    }
}
