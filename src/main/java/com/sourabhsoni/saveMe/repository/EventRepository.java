package com.sourabhsoni.saveMe.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.sourabhsoni.saveMe.model.Event;


public interface EventRepository extends CrudRepository<Event,String> {

	List<Event> findAllByDateBetween(Date dateStart, Date dateEnd);

}
