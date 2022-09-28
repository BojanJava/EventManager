package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Event0;
import com.example.demo.model.Person;
import com.example.demo.repository.EventRepository;
import com.example.demo.repository.PersonRepository;

@Service
public class EventService {

	@Autowired
	private EventRepository eventRepository;
	@Autowired
	private PersonRepository personRepository;
	
	public List<Event0> listAll() {
		return eventRepository.findAll();
	}
	
	public void save(Event0 event) {
		eventRepository.save(event);
	}
	
	public Event0 get(Integer id) {
		return eventRepository.findById(id).get();
	}
	
	public void delete(Integer id) {
		eventRepository.deleteById(id);
	}
	
	public List<Event0> getEventByCategory(String name) {
		return eventRepository.findEventByCategory(name);
	}
	
	public List<Event0> getEventByPerson(Integer personId) {
		Person person = personRepository.findById(personId).get();
		return eventRepository.findEventByPerson(person);
	}
	
	
}
