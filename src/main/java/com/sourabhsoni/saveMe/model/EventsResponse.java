package com.sourabhsoni.saveMe.model;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EventsResponse {

	private Date date;
	private List<Event> events;
	private String errorMessage;
}
