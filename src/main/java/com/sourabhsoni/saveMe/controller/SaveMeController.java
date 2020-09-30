package com.sourabhsoni.saveMe.controller;


import java.util.Date;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sourabhsoni.saveMe.model.Event;
import com.sourabhsoni.saveMe.model.EventsResponse;
import com.sourabhsoni.saveMe.service.EventService;

@RestController
@RequestMapping("/v1")
public class SaveMeController {

	private EventService eventService;

	public SaveMeController(EventService eventService) {
		this.eventService = eventService;
	}

	@GetMapping("/events")
	public EventsResponse getEvents(){
		List<Event> events = eventService.getEvents(new Date());
		String errorMessage = "";
		if(events==null || events.isEmpty()){
			errorMessage = "No events this week";
		}
		EventsResponse eventsResponse = new EventsResponse(new Date(),events,errorMessage);
		return eventsResponse;
	}
}
