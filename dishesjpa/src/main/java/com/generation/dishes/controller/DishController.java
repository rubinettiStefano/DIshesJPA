package com.generation.dishes.controller;

import java.util.List;
import java.util.Optional;

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
import org.springframework.web.bind.annotation.RestController;

import com.generation.dishes.model.entities.Dish;
import com.generation.dishes.model.repositories.DishRepository;

@RestController
public class DishController
{

	@Autowired
	DishRepository repository;
	
	@GetMapping("/dishes")
	public List<Dish> getAll()
	{
		return repository.findAll();
	}
	
	@GetMapping("/dishes/{id}")
	//Una response che avrà un Object JASONIZZATO come BODY, nel nostro caso un Dish o una String
	public      ResponseEntity<Object> getOne(@PathVariable int id)
	{
		Optional<Dish> dishOptional = repository.findById(id);
		return 	dishOptional.isPresent() 							? 
				ResponseEntity.ok(dishOptional.get())				:
				ResponseEntity.status(HttpStatus.NOT_FOUND).body("");
		
	}
	
	@DeleteMapping("/dishes/{id}")
	public ResponseEntity<Object> delete(@PathVariable int id)
	{
		Optional<Dish> dishOptional = repository.findById(id);
		
		if(dishOptional.isPresent())
		{
			Dish toDelete = dishOptional.get();
			if(toDelete.getReviews().size()==0)
			{
				repository.deleteById(id);
				return ResponseEntity.ok(dishOptional.get());
			}
			else
				return ResponseEntity.status(HttpStatus.FORBIDDEN).body("");
		}
		else
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("");
	}
	
	@PostMapping("/dishes")
	public ResponseEntity<Object> insert(@RequestBody Dish dish)
	{
		Dish insertedDish = repository.save(dish);
		return ResponseEntity.ok(insertedDish);
	}
	
	@PutMapping("/dishes/{id}")
	public ResponseEntity<Object> update
	(
		@PathVariable int id,
		@RequestBody Dish dish
	)
	{
		
		Optional<Dish> dishOptional = repository.findById(id);
		//L'operazione in grado di fare Insert o Update in base al fatto che l'entità
		//esista o no viene detta UPSERT (o save)
		
		dish.setID(id);
		
		if(dishOptional.isPresent())
		{
			Dish modified = repository.save(dish);
			return ResponseEntity.ok(modified);
		}
		else
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("");
		
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> exceptionHandler(Exception e)
	{
		// Io sono Spring Boot, con sotto Spring MVC, con sotto le Servlet
		String exceptionClassName = e.getClass().getSimpleName();
		
		String res;
		switch(exceptionClassName)
		{
			case "HttpMessageNotReadableException":
				res = "Error creating or updating Dish entity. Could not deserialize data, could not rebuild object. Please check the value of the fields.";
			break;
			default:
				res = "Generic exception: "+e.getMessage();
		}
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
		
	}
}
