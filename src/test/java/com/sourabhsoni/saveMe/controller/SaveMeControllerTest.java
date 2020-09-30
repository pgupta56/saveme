package com.sourabhsoni.saveMe.controller;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.google.gson.Gson;
import com.sourabhsoni.saveMe.model.Event;
import com.sourabhsoni.saveMe.service.EventService;

@RunWith(SpringRunner.class)
@WebMvcTest
public class SaveMeControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private EventService eventService;

	@Test
	public void getEvents_returns_List_of_Events() throws Exception {
		ArrayList<Event> events = new Gson().fromJson("[{\"name\":\"Mother-in-law's Birthday\",\"date\":\"4-OCT-2019\"},{\"name\":\"Your Anniversary\",\"date\":\"6-OCT-2019\"}]",ArrayList.class);
		when(this.eventService.getEvents(any())).thenReturn(events);
		this.mockMvc.perform(MockMvcRequestBuilders.get("/v1/events"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("events").isNotEmpty());
	}

	@Test
	public void getEvents_NullEvents_returns_errorMessage() throws Exception {
		when(this.eventService.getEvents(any())).thenReturn(null);
		this.mockMvc.perform(MockMvcRequestBuilders.get("/v1/events"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("errorMessage").value("No events this week"));
	}

	@Test
	public void getEvents_EmptyEvents_returns_errorMessage() throws Exception {
		when(this.eventService.getEvents(any())).thenReturn(new ArrayList<>());
		this.mockMvc.perform(MockMvcRequestBuilders.get("/v1/events"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("errorMessage").value("No events this week"));
	}

}
