package com.example.demo;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.enumeration.EventEnum;
import com.example.demo.model.Event0;
import com.example.demo.model.Group0;
import com.example.demo.model.Person;
import com.example.demo.repository.EventRepository;
import com.example.demo.repository.GroupRepository;

@SpringBootApplication
public class EventManagerApplication implements CommandLineRunner {

	@Autowired
	private GroupRepository groupRepository;
	@Autowired
	private EventRepository eventRepository;


	
	public static void main(String[] args) {
		SpringApplication.run(EventManagerApplication.class, args);
		}
	@Override
	public void run(String... args) throws Exception {

		Person person1 = new Person(1, "John", "Way", "jway@java.com");
		Person person2 = new Person(2, "Maria", "Anderson", "amaria@java.com");
		Person person3 = new Person(3, "Boris", "Smith", "bsmith@java.com");
		Person person4 = new Person(4, "Sandra", "Petrov", "sandrap@java.com");
		Person person5 = new Person(5, "Katarina", "Andrescu", "akatarina@java.com");
		Person person6 = new Person(6, "Carlos", "Torres", "ctorres@java.com");
		
		Group0 group1 = new Group0(1, "group1");
		Group0 group2 = new Group0(2, "group2");
		
		LocalDateTime date1 = LocalDateTime.of(2022, 8, 25, 18, 00);
		
		Event0 event1 = new Event0(1, "Concert", "Iron Maiden", 
								   date1, 
								   "Savski nasip 1111, 11070 Beograd",
								   20, EventEnum.SCHEDULED);
		event1.getPeople().add(person1);
		
		eventRepository.save(event1);
		
		group1.getPeople().add(person1);
		group1.getPeople().add(person2);
		group1.getPeople().add(person3);
		group2.getPeople().add(person4);
		group2.getPeople().add(person5);
		group2.getPeople().add(person6);
		
		groupRepository.save(group1);
		groupRepository.save(group2);
		
	}	
	
}
