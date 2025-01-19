package com.report.Reporting.Service.reporting;

import com.report.Reporting.Service.common.dto.ApiResponse;
import com.report.Reporting.Service.statistics.Stats;
import com.report.Reporting.Service.statistics.StatsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<ApiResponse> getTotalSubmissionsPerService(@RequestParam Long serviceId) {
        List<Stats> data =  service.getTotalSubmissionsPerService(serviceId);

        ApiResponse resp = ApiResponse.builder()
                .status(HttpStatus.OK)
                .message("Successful")
                .data(data).build();
        return new ResponseEntity<>(resp,
                resp.getStatus());
    }

    @GetMapping("/stats-by-submission-count")
    @Operation(description = "Endpoint to get stats by submission count")
    @SecurityRequirement(name = "BearerAuth")
    public ResponseEntity<ApiResponse> getStatsBySubmissionCount(@RequestParam Integer submissionCount) {
        List<Stats> data = service.getStatsBySubmissionCount(submissionCount);

        ApiResponse resp = ApiResponse.builder()
                .status(HttpStatus.OK)
                .message("Successful")
                .data(data).build();
        return new ResponseEntity<>(resp,
                resp.getStatus());
    }

    @GetMapping("/daily-trends")
    @Operation(description = "Endpoint to get daily stats")
    @SecurityRequirement(name = "BearerAuth")
    public ResponseEntity<ApiResponse> getDailyTrends(@Parameter(description = "date format 'e.i 2025-01-19'") @RequestParam String date) {
        List<Stats> data = service.getDailyTrends(date);

        ApiResponse resp = ApiResponse.builder()
                .status(HttpStatus.OK)
                .message("Successful")
                .data(data).build();
        return new ResponseEntity<>(resp,
                resp.getStatus());
    }

    @GetMapping("/weekly-trends")
    @Operation(description = "Endpoint to get weekly stats")
    @SecurityRequirement(name = "BearerAuth")
    public ResponseEntity<ApiResponse> getWeeklyTrends(@Parameter(description = "week format 'e.i 2025-W2'") @RequestParam String week) {
        List<Stats> data = service.getWeeklyTrends(week);

        ApiResponse resp = ApiResponse.builder()
                .status(HttpStatus.OK)
                .message("Successful")
                .data(data).build();
        return new ResponseEntity<>(resp,
                resp.getStatus());
    }

    @GetMapping("/api/stats/most-frequent-service")
    @Operation(description = "Endpoint to get most frequently used service")
    @SecurityRequirement(name = "BearerAuth")
    public ResponseEntity<ApiResponse> getMostFrequentService() {
        Stats data  = service.getMostFrequentService();


        ApiResponse resp = ApiResponse.builder()
                .status(HttpStatus.OK)
                .message("Successful")
                .data(data).build();
        return new ResponseEntity<>(resp,
                resp.getStatus());
    }
}
