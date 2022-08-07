package com.example.demo.controller;

import java.util.List;
import java.util.NoSuchElementException;

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

import com.example.demo.model.Group0;
import com.example.demo.service.GroupService;

@RestController
@RequestMapping("/group")
public class GroupController {

	@Autowired
	private GroupService groupService;
	
	@GetMapping("/getAll")
	public List<Group0> list() {
		return groupService.listAll();
	}
	@PostMapping("/add")
	public String add(@RequestBody Group0 group) {
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
	public ResponseEntity<Group0> update(@RequestBody Group0 group, @PathVariable Integer id) {
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
}
