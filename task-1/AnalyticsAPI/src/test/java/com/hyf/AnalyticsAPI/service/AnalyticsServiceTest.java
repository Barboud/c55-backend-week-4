package com.hyf.AnalyticsAPI.service;

import com.hyf.AnalyticsAPI.dto.CreateAnalyticsRequest;
import com.hyf.AnalyticsAPI.model.AnalyticsRecord;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AnalyticsServiceTest {

	@Test
	void shouldCreateRecord() {

		AnalyticsService service = new AnalyticsService();

		CreateAnalyticsRequest request =
				new CreateAnalyticsRequest();

		request.setEventType("LOGIN");
		request.setEventSource("WEB");
		request.setSessionId("ABC123");

		AnalyticsRecord record =
				service.create(request);

		assertEquals(
				"LOGIN",
				record.getEventType()
		);
	}
}