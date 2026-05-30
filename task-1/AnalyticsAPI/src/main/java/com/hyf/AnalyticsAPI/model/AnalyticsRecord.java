package com.hyf.AnalyticsAPI.model;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AnalyticsRecord {
    private String traceId;
    private LocalDateTime timestamp;
    private String eventType;
    private String eventSource;
    private String sessionId;
}