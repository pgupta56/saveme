package com.sourabhsoni.saveMe.service;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.time.DateUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.google.gson.Gson;
import com.sourabhsoni.saveMe.model.Event;
import com.sourabhsoni.saveMe.repository.EventRepository;

@RunWith(MockitoJUnitRunner.class)
public class EventServiceTests {

	@Mock
	private EventRepository eventRepository;

	private EventService eventService;

	@Before
	public void setUp(){
		eventService =  new EventService(eventRepository);
	}

	@Test
	public void getEvents_returns_listOfEvents(){
		//Arrange
		ArrayList<Event> events = new Gson().fromJson("[{\"name\":\"Mother-in-law's Birthday\",\"date\":\"4-OCT-2019\"},{\"name\":\"Your Anniversary\",\"date\":\"6-OCT-2019\"}]",ArrayList.class);
		when(eventRepository.findAllByDateBetween(any(),any())).thenReturn(events);
		//Act
		List<Event> eventList = eventService.getEvents(new Date());
		//Assert
		Assert.assertNotNull(eventList);
	}

	@Test
	public void findAllByDateBetween_isCalled_with_date_and_datePlus7(){
		Date curentDate = new Date();
		List<Event> eventList = eventService.getEvents(curentDate);
		verify(eventRepository,times(1)).findAllByDateBetween(curentDate, DateUtils.addDays(curentDate,7));
	}

}
