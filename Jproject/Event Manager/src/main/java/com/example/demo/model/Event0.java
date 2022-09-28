package com.example.demo.model;

import java.time.LocalDateTime;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.example.demo.enumeration.EventEnum;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Event0 {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@NotEmpty
	@Size(min = 2, message = "Title should have at least 2 characters")
	@Size(max = 30, message = "Title should have less than 30 characters")
	@Column(unique = true)
	private String title;
	private String description;
	@Embedded
	private Category1 category;
	private LocalDateTime date;
	@NotEmpty
	@Size(min = 2, message = "Location should have at least 2 characters")
	private String location;
	private int capacity;
	@Enumerated(EnumType.STRING)
	private EventEnum status;
	@ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE})
	@JoinTable(name = "person_event_map",
			   joinColumns = @JoinColumn(name = "event_id"),
			   inverseJoinColumns = @JoinColumn(name = "person_id"))
	private List<Person> people = new ArrayList<>();
	
	public Event0(Integer id, String title, String description,
				  Category1 category,
			      LocalDateTime date, String location, 
			      Integer capacity, EventEnum status ) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.category = category;
		this.date = date;
		this.location = location;
		this.capacity = capacity;
		this.status = status;
	}
	
	public void addPersonToEvent(Person person) {
		if(people.size() < capacity) {
		people.add(person);	
		}
		else {
		
		}
	}
	
	public void removePersonFromEvent(Person person) {
		people.remove(person);	
	}
	
}
