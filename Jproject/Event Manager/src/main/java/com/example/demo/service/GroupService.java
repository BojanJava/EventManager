package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Group0;
import com.example.demo.repository.GroupRepository;

@Service
public class GroupService {

	@Autowired
	private GroupRepository groupRepository;
	
	public List<Group0> listAll() {
		return groupRepository.findAll();
	}
	
	public void save(Group0 group) {
		groupRepository.save(group);
	}
	
	public Group0 get(Integer id) {
		return groupRepository.findById(id).get();
	}
	
	public void delete(Integer id) {
		groupRepository.deleteById(id);
	}
	
}
