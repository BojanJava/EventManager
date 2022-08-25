package com.example.demo.controller;

//import java.util.List;
//
//import java.util.NoSuchElementException;
//
//import javax.validation.Valid;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.example.demo.model.Category0;
//import com.example.demo.model.Person;
//import com.example.demo.service.CategoryService;
//
//@RestController
//@RequestMapping("/category")
//public class CategoryController {
//
//	@Autowired
//	private CategoryService categoryService;
//	
//	@GetMapping("/getAll")
//	public List<Category0> list() {
//		return categoryService.listAll();
//	}
//	
//	@PostMapping("/add")
//	public String add(@RequestBody Category0 category) {
//		categoryService.save(category);
//		return "New Category Added";
//	}
//	
//	@GetMapping("/{id}")
//	public ResponseEntity<Category0> get(@PathVariable Integer id) {
//		try {
//			Category0 category = categoryService.get(id);
//			return new ResponseEntity<Category0>(category, HttpStatus.OK);
//		}
//		catch (NoSuchElementException e) {
//			return new ResponseEntity<Category0>(HttpStatus.NOT_FOUND);
//		}
//	}
//	
//	@PutMapping("/{id}")
//	public ResponseEntity<Category0> update(@RequestBody Category0 category, @PathVariable Integer id) {
//		try {
//			Category0 existingCategory = categoryService.get(id);
//			categoryService.save(category);
//			return new ResponseEntity<Category0>(HttpStatus.OK);
//		}
//		catch (NoSuchElementException e) {
//			return new ResponseEntity<Category0>(HttpStatus.NOT_FOUND);
//		}
//	}
//	
//	@DeleteMapping("/{id}")
//	public String delete(@PathVariable Integer id) {
//		categoryService.delete(id);
//		return "Deleted Category with id " + id;
//	}
//}
