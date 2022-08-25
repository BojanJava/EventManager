package com.example.demo.controller;

import java.sql.SQLIntegrityConstraintViolationException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Category1;
import com.example.demo.model.Event0;
import com.example.demo.model.Group0;
import com.example.demo.model.Person;
import com.example.demo.repository.EventRepository;
import com.example.demo.repository.GroupRepository;
import com.example.demo.repository.PersonRepository;
import com.example.demo.service.EventService;

@RestController
@RequestMapping("/event")
public class EventController  {

	@Autowired
	private EventService eventService;
	@Autowired
	private EventRepository eventRepository;
	@Autowired
	private PersonRepository personRepository;


	@GetMapping("/getAll")
	public List<Event0> list() {
		return eventService.listAll();
	}
	
	@PostMapping("/add")
	public String add(@Valid @RequestBody Event0 event) {
		eventService.save(event);
		return "New Event Added";
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Event0> get(@PathVariable Integer id) {
		try {
			Event0 event = eventService.get(id);
			return new ResponseEntity<Event0>(event, HttpStatus.OK);
		}
		catch (NoSuchElementException e) {
			return new ResponseEntity<Event0>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Event0> update(@Valid @RequestBody Event0 event, @PathVariable Integer id) {
		try {
			Event0 existingEvent = eventService.get(id);
			eventService.save(event);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		catch (NoSuchElementException e) {
			return new ResponseEntity<Event0>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/{id}")
	public String delete(@PathVariable Integer id) {
		eventService.delete(id);
		return "Deleted Event with id " + id;
	}
	
	@PutMapping("/{eventId}/addPerson/{personId}")
	public Event0 addPersonToEvent(@PathVariable Integer eventId, @PathVariable Integer personId) {
		Event0 event = eventRepository.findById(eventId).get();
		Person person = personRepository.findById(personId).get();
		event.addPersonToEvent(person);
		return eventRepository.save(event);
	}
	
	@PutMapping("/{eventId}/removePerson/{personId}")
	public Event0 removePersonFromEvent(@PathVariable Integer eventId, @PathVariable Integer personId) {
		Event0 event = eventRepository.findById(eventId).get();
		Person person = personRepository.findById(personId).get();
		event.removePersonFromEvent(person);
		return eventRepository.save(event);
	}
	
	
	@GetMapping("/categoryName")
	public List<Event0> getEventByCategory(@RequestParam("name") String name) {
		return eventService.getEventByCategory(name);
	}
	
	@GetMapping("/eventDate")
	public List<Event0> getEventByDate(@RequestParam("date") 
									   @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime date) {
		return eventService.getEventByDate(date);
	}

//	@GetMapping("/eventPerson")
//	public List<Event0> getEventByPerson(@RequestParam("person") Integer personId) {
//		return eventService.getEventByPerson(personId);
//	}
	
	
	
	
	
	@ExceptionHandler(SQLIntegrityConstraintViolationException.class)
	public ResponseEntity<String> handleConstraint(SQLIntegrityConstraintViolationException ex) {
		
		return new ResponseEntity<String>("Event name already exists", HttpStatus.BAD_REQUEST);	
	}
}
