package com.hyf.AnalyticsAPI.controller;

import com.hyf.AnalyticsAPI.dto.CreateAnalyticsRequest;
import com.hyf.AnalyticsAPI.dto.UpdateAnalyticsRequest;
import com.hyf.AnalyticsAPI.model.AnalyticsRecord;
import com.hyf.AnalyticsAPI.model.AnalyticsSummary;
import com.hyf.AnalyticsAPI.service.AnalyticsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;


@RestController
@RequestMapping("/analytics")
@RequiredArgsConstructor // To create constructor for me with all fields (1 field)
public class AnalyticsController {

    private final AnalyticsService analyticsService;


    @PostMapping
    public AnalyticsRecord create(@Valid @RequestBody CreateAnalyticsRequest request) {
        return analyticsService.create(request);
    }

    @GetMapping
    public List<AnalyticsRecord> getAll(
            @RequestParam(required = false) String eventType,
            @RequestParam(required = false) String eventSource,
            @RequestParam(required = false) LocalDateTime start,
            @RequestParam(required = false) LocalDateTime end ) {
        return analyticsService.getAll(
                eventType,
                eventSource,
                start,
                end
        );
    }

    @GetMapping("/{id}")
    public AnalyticsRecord getById(@PathVariable String id) {
        return analyticsService.getById(id);
    }

    @PutMapping("/{id}")
    public AnalyticsRecord update(
            @PathVariable String id,
            @Valid @RequestBody UpdateAnalyticsRequest request) {

        return analyticsService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        analyticsService.delete(id);
    }

    @GetMapping("/summary")
    public AnalyticsSummary getSummary() {
        return analyticsService.getSummary();
    }
}