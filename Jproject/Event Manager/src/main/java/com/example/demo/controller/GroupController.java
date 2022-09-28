package com.example.demo.controller;

import java.sql.SQLIntegrityConstraintViolationException;

import java.util.List;
import java.util.NoSuchElementException;

import javax.validation.Valid;

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

import com.example.demo.model.Group0;
import com.example.demo.model.Person;
import com.example.demo.repository.GroupRepository;
import com.example.demo.repository.PersonRepository;
import com.example.demo.service.GroupService;

@RestController
@RequestMapping("/group")
public class GroupController {

	@Autowired
	private GroupService groupService;
	@Autowired
	private GroupRepository groupRepository;
	@Autowired
	private PersonRepository personRepository;
	
	@GetMapping("/getAll")
	public List<Group0> list() {
		return groupService.listAll();
	}
	
	@PostMapping("/add")
	public String add(@Valid @RequestBody Group0 group) {
		groupService.save(group);
		return "New Group Added";
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Group0>get(@PathVariable Integer id) {
		try {
			Group0 group = groupService.get(id);
			return new ResponseEntity<Group0>(group, HttpStatus.OK);
		}
		catch (NoSuchElementException e) {
			return new ResponseEntity<Group0>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Group0> update(@Valid @RequestBody Group0 group, @PathVariable Integer id) {
		try {
			Group0 existingGroup = groupService.get(id);
			groupService.save(group);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		catch (NoSuchElementException e) {
			return new ResponseEntity<Group0>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/{id}")
	public String delete(@PathVariable Integer id) {
		groupService.delete(id);
		return "Deleted Group with id " + id;
	}
	
	@PutMapping("/{groupId}/addPerson/{personId}")
	public Group0 addPersonToGroup(@PathVariable Integer groupId, @PathVariable Integer personId) {
		Group0 group = groupRepository.findById(groupId).get();
		Person person = personRepository.findById(personId).get();
		group.addPersonToGroup(person);
		return groupRepository.save(group);
	}
	
	@PutMapping("/{groupId}/removePerson/{personId}")
	public Group0 removePersonFromGroup(@PathVariable Integer groupId, @PathVariable Integer personId) {
		Group0 group = groupRepository.findById(groupId).get();
		Person person = personRepository.findById(personId).get();
		group.removePersonFromGroup(person);
		return groupRepository.save(group);
	}
	
	
	@ExceptionHandler(SQLIntegrityConstraintViolationException.class)
	public ResponseEntity<String> handleConstraint(SQLIntegrityConstraintViolationException ex) {
		
		return new ResponseEntity<String>("Group name already exists", HttpStatus.BAD_REQUEST);	
	}
}

