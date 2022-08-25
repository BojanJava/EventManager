package com.example.demo;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.enumeration.EventEnum;
import com.example.demo.model.Category1;
import com.example.demo.model.Event0;
import com.example.demo.model.Group0;
import com.example.demo.model.Person;
import com.example.demo.repository.EventRepository;
import com.example.demo.repository.GroupRepository;
import com.example.demo.repository.PersonRepository;

@SpringBootApplication
public class EventManagerApplication implements CommandLineRunner {

	@Autowired
	private PersonRepository personRepository;
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
		
		LocalDateTime date1 = LocalDateTime.of(2022, 8, 26, 18, 00);
		
		Category1 category = new Category1();
		category.setName("Entertainment");
		
		Event0 event1 = new Event0(1, "Concert", "Rock concert",
								   category, date1, 
								   "Oak street 1111, New York",
								   5, EventEnum.SCHEDULED);
		
		eventRepository.save(event1);
		
		groupRepository.save(group1);
		groupRepository.save(group2);
		
		personRepository.save(person1);
		personRepository.save(person2);
		personRepository.save(person3);
		personRepository.save(person4);
		personRepository.save(person5);
		personRepository.save(person6);
	}	
	
}
