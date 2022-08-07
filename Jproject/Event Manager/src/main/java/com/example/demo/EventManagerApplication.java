package com.example.demo;

import org.springframework.boot.CommandLineRunner;



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.demo.model.Person;
import com.example.demo.repository.PersonRepository;

@SpringBootApplication
public class EventManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(EventManagerApplication.class, args);
		
		}	
	
  @Bean
  public CommandLineRunner run(PersonRepository personRepository) {
	  return (args) -> {
	    personRepository.save(new Person(1, "John", "Way", "jway@java.com"));
	    personRepository.save(new Person(2, "Maria", "Anderson", "amaria@java.com"));
	    personRepository.save(new Person(3, "Boris", "Smith", "bsmith@java.com"));
	    personRepository.save(new Person(4, "Sandra", "Petrov", "sandrap@java.com"));
	    personRepository.save(new Person(5, "Katarina", "Andrescu", "akatarina@java.com"));
	    personRepository.save(new Person(6, "Carlos", "Torres", "ctorres@java.com"));
	    };
	 }
  
  
}
