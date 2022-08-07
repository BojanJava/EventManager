package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person { 
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotEmpty
	@Size(min = 2, message = "First name should have at least 2 characters")
	@Size(max = 20, message = "First name should have less than 20 characters")
	private String firstName;
	
	@NotEmpty
	@Size(min = 2, message = "Last name should have at least 2 characters")
	@Size(max = 20, message = "Last name should have less than 20 characters")
	private String lastName;
	
	@NotEmpty
	@Email
	private String email;

	
	public Person(Integer id, String firstName, String lastName, String email) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}
	    
}		 





