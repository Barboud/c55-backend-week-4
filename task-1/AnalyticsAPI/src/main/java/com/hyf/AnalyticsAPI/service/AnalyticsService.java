package com.hyf.AnalyticsAPI.service;

import com.hyf.AnalyticsAPI.dto.CreateAnalyticsRequest;
import com.hyf.AnalyticsAPI.dto.UpdateAnalyticsRequest;
import com.hyf.AnalyticsAPI.exception.RecordNotFoundException;
import com.hyf.AnalyticsAPI.model.AnalyticsRecord;
import com.hyf.AnalyticsAPI.model.AnalyticsSummary;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AnalyticsService {

    private final List<AnalyticsRecord> records = new ArrayList<>();
    private int counter = 1; // just to make my app simple


    // Add some data to test
    public AnalyticsService() {

        AnalyticsRecord r1 = new AnalyticsRecord();
        r1.setTraceId("1");
        r1.setTimestamp(LocalDateTime.now().minusDays(2));
        r1.setEventType("LOGIN");
        r1.setEventSource("WEB");
        r1.setSessionId("SESSION-1");

        AnalyticsRecord r2 = new AnalyticsRecord();
        r2.setTraceId("2");
        r2.setTimestamp(LocalDateTime.now().minusDays(1));
        r2.setEventType("LOGIN");
        r2.setEventSource("MOBILE");
        r2.setSessionId("SESSION-2");

        AnalyticsRecord r3 = new AnalyticsRecord();
        r3.setTraceId("3");
        r3.setTimestamp(LocalDateTime.now().minusHours(10));
        r3.setEventType("PURCHASE");
        r3.setEventSource("WEB");
        r3.setSessionId("SESSION-1");

        AnalyticsRecord r4 = new AnalyticsRecord();
        r4.setTraceId("4");
        r4.setTimestamp(LocalDateTime.now().minusHours(5));
        r4.setEventType("LOGOUT");
        r4.setEventSource("WEB");
        r4.setSessionId("SESSION-3");

        AnalyticsRecord r5 = new AnalyticsRecord();
        r5.setTraceId("5");
        r5.setTimestamp(LocalDateTime.now().minusMinutes(30));
        r5.setEventType("PURCHASE");
        r5.setEventSource("MOBILE");
        r5.setSessionId("SESSION-2");

        records.add(r1);
        records.add(r2);
        records.add(r3);
        records.add(r4);
        records.add(r5);

        counter = 6;
    }

    public AnalyticsRecord create(CreateAnalyticsRequest request) {
        AnalyticsRecord record = new AnalyticsRecord();

        record.setTraceId(String.valueOf(counter));

        counter++;

        record.setTimestamp(LocalDateTime.now());
        record.setEventType(request.getEventType());
        record.setEventSource(request.getEventSource());
        record.setSessionId(request.getSessionId());

        records.add(record);

        return record;
    }

    public List<AnalyticsRecord> getAll(
            String eventType,
            String eventSource,
            LocalDateTime start,
            LocalDateTime end) {
        return records.stream()
                .filter(record -> eventType == null || record.getEventType().equalsIgnoreCase(eventType))
                .filter(record -> eventSource == null || record.getEventSource().equalsIgnoreCase(eventSource))
                .filter(record -> start == null || !record.getTimestamp().isBefore(start))
                .filter(record -> end == null || !record.getTimestamp().isAfter(end))
                .toList();
    }

    public AnalyticsRecord getById(String id) {

        return records.stream()
                .filter(record -> record.getTraceId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RecordNotFoundException("Record not found"));
    }

    public AnalyticsRecord update(
            String id,
            UpdateAnalyticsRequest request) {

        AnalyticsRecord record = getById(id);

        record.setEventType(request.getEventType());
        record.setEventSource(request.getEventSource());
        record.setSessionId(request.getSessionId());

        return record;
    }

    public void delete(String id) {
        AnalyticsRecord record = getById(id);
        records.remove(record);
    }

    public AnalyticsSummary getSummary() {

        Map<String, Integer> eventTotals =
                records.stream()
                        .collect(Collectors.groupingBy(
                                AnalyticsRecord::getEventType,
                                Collectors.summingInt(record -> 1)
                        ));

        int uniqueSessions = (int) records.stream()
                .map(AnalyticsRecord::getSessionId)
                .distinct()
                .count();

        return new AnalyticsSummary(
                records.size(),
                uniqueSessions,
                eventTotals
        );
    }
}