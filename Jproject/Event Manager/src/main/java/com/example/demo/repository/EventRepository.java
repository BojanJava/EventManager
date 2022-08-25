package com.example.demo.repository;

import java.time.LocalDate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.model.Category1;
import com.example.demo.model.Event0;
import com.example.demo.model.Person;

public interface EventRepository extends JpaRepository<Event0, Integer>{

	@Query("SELECT e FROM Event0 e WHERE e.category.name=:name")
	List<Event0> findEventByCategory(@Param("name") String name);

	@Query("SELECT e FROM Event0 e WHERE e.date=:date")
	List<Event0> findEventByDate(@Param("date") LocalDateTime date);
	
//	@Query("SELECT e FROM Event0 e WHERE :person IN e.people")
//	List<Event0> findEventByPerson(@Param("person") Person person);


}
