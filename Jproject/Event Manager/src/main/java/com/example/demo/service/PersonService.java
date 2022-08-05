package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Person;
import com.example.demo.repository.PersonRepository;

@Service
public class PersonService {

	@Autowired
	private PersonRepository personRepository;
	
	public List<Person> listAll() {
		return personRepository.findAll(); 
	}
	
	public void save(Person person) {
		personRepository.save(person);
	}
	
	public Person get(Integer id) {
		return personRepository.findById(id).get();
	}
	
	public void delete(Integer id) {
		personRepository.deleteById(id);
	}
}
