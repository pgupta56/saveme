package com.sourabhsoni.saveMe.service;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.time.DateUtils;

import com.sourabhsoni.saveMe.model.Event;
import com.sourabhsoni.saveMe.repository.EventRepository;

public class EventService {


	private EventRepository eventRepository;

	public EventService(EventRepository eventRepository) {
		this.eventRepository = eventRepository;
	}

	public List<Event> getEvents(Date date){
		return eventRepository.findAllByDateBetween(date, DateUtils.addDays(date,7));
	}
}
