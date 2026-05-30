package com.hyf.AnalyticsAPI.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
public class CreateAnalyticsRequest {

    @NotBlank
    private String eventType;

    @NotBlank
    private String eventSource;

    @NotBlank
    private String sessionId;
}