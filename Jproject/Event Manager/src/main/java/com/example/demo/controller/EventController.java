package com.example.demo.controller;

import java.sql.SQLIntegrityConstraintViolationException;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Event0;
import com.example.demo.service.EventService;

@RestController
@RequestMapping("/event")
public class EventController {

	@Autowired
	private EventService eventService;

	@GetMapping("/getAll")
	public List<Event0> list() {
		return eventService.listAll();
	}
	
	@PostMapping("/add")
	public String add(@RequestBody Event0 event) {
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
	public ResponseEntity<Event0> update(@RequestBody Event0 event, @PathVariable Integer id) {
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
	
	@ExceptionHandler(SQLIntegrityConstraintViolationException.class)
	public ResponseEntity<String> handleConstraint(SQLIntegrityConstraintViolationException ex) {
		
		return new ResponseEntity<String>("Event name already exists", HttpStatus.BAD_REQUEST);	
	}
}
