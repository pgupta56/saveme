package com.sourabhsoni.saveMe.repository;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.time.DateUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.sourabhsoni.saveMe.model.Event;

@RunWith(SpringRunner.class)
@DataJpaTest
public class EventRepositoryTests {

	@Autowired
	private EventRepository eventRepository;

	@Autowired
	private TestEntityManager testEntityManager;

	@Test
	public void findByDate_returns_events_till_date(){
		testEntityManager.persistFlushFind(new Event(new Date("3-OCT-2020"),"Wife's Birthday"));
		testEntityManager.persistFlushFind(new Event(new Date("19-OCT-2020"),"Anniversary"));
		Date currentDate = new Date();
		List<Event> events = eventRepository.findAllByDateBetween(currentDate, DateUtils.addDays(currentDate,7));
		Assert.assertTrue(events.size()==1);
	}
}
