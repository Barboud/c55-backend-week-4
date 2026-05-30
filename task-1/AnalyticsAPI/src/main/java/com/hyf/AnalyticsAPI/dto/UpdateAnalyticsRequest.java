package com.hyf.AnalyticsAPI.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateAnalyticsRequest {

    @NotBlank
    private String eventType;

    @NotBlank
    private String eventSource;

    @NotBlank
    private String sessionId;
}