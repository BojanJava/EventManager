package com.example.demo.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Group0 {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@NotEmpty
	@Size(min = 2, message = "Name should have at least 2 characters")
	@Size(max = 20, message = "Name should have less than 20 characters")
	@Column(unique = true)
	private String name;
	@ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE})
	@JoinTable(name = "person_group_map",
			   joinColumns = @JoinColumn(name = "group_id"),
			   inverseJoinColumns = @JoinColumn(name = "person_id"))
	private List<Person> people = new ArrayList<>();
	
	public Group0(Integer id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public void addPersonToGroup(Person person) {
		people.add(person);	
	}   
	public void removePersonFromGroup(Person person) {
		people.remove(person);	
	}   
}
