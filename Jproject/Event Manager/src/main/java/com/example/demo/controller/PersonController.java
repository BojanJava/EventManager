package com.example.demo.controller;

import java.util.List;
import java.util.NoSuchElementException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Person;
import com.example.demo.service.PersonService;

@RestController
@RequestMapping("/person")
public class PersonController {

	@Autowired
	private PersonService personService;
	
	@GetMapping("/getAll")
	public List<Person> list() {
		return personService.listAll();
	}
	
	@PostMapping("/add")
	public String add(@Valid @RequestBody Person person) {
		personService.save(person);
		return "New Person Added";
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Person> get(@PathVariable Integer id) {
		try {
			Person person = personService.get(id);
			return new ResponseEntity<Person>(person, HttpStatus.OK);
		}
		catch (NoSuchElementException e) {
			return new ResponseEntity<Person>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Person> update(@Valid @RequestBody Person person, @PathVariable Integer id) {
		try {
			Person existingPerson = personService.get(id);
			personService.save(person);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		catch (NoSuchElementException e) {
			return new ResponseEntity<Person>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/{id}")
	public String delete(@PathVariable Integer id) {
		personService.delete(id);
		return "Deleted Person with id " + id;
	}
}
