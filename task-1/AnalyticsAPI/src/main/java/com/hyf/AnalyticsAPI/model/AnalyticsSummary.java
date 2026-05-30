package com.hyf.AnalyticsAPI.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;

@Getter
@AllArgsConstructor
public class AnalyticsSummary {
    private int totalRecords;
    private int uniqueSessions;
    private Map<String, Integer> eventTotals;

}