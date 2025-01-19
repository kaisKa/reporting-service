package com.report.Reporting.Service.reporting;

import com.report.Reporting.Service.statistics.Stats;
import com.report.Reporting.Service.statistics.StatsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/report")
@RequiredArgsConstructor
public class ReportController {

    private final StatsService service;

    @GetMapping("/total-submissions")
    @Operation(description = "Endpoint to get total submission by service ")
    @SecurityRequirement(name = "BearerAuth")
    public List<Stats> getTotalSubmissionsPerService(@RequestParam Long serviceId) {
        return service.getTotalSubmissionsPerService(serviceId);
    }

    @GetMapping("/stats-by-submission-count")
    @Operation(description = "Endpoint to get stats by submission count")
    @SecurityRequirement(name = "BearerAuth")
    public List<Stats> getStatsBySubmissionCount(@RequestParam Integer submissionCount) {
        return service.getStatsBySubmissionCount(submissionCount);
    }

    @GetMapping("/daily-trends")
    @Operation(description = "Endpoint to get daily stats")
    @SecurityRequirement(name = "BearerAuth")
    public List<Stats> getDailyTrends(@Parameter(description = "data format 'e.i 2025-01-19'") @RequestParam String date) {
        return service.getDailyTrends(date);
    }

    @GetMapping("/weekly-trends")
    @Operation(description = "Endpoint to get weekly stats")
    @SecurityRequirement(name = "BearerAuth")
    public List<Stats> getWeeklyTrends(@RequestParam String week) {
        return service.getWeeklyTrends(week);
    }
}
