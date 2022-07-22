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
import com.generation.dishes.model.entities.Review;
import com.generation.dishes.model.repositories.DishRepository;
import com.generation.dishes.model.repositories.ReviewRepository;

@RestController
public class ReviewController
{
	
	@Autowired
	ReviewRepository reviewRepository;
	@Autowired
	DishRepository dishRepository;
	
	@GetMapping("/reviews")
	public List<Review> getAll()
	{
		return reviewRepository.findAll();
	}
	
	@GetMapping("/reviews/{id}")
	//Una response che avrà un Object JASONIZZATO come BODY, nel nostro caso un Review o una String
	public      ResponseEntity<Object> getOne(@PathVariable int id)
	{
		Optional<Review> reviewOptional = reviewRepository.findById(id);
		return 	reviewOptional.isPresent() 							? 
				ResponseEntity.ok(reviewOptional.get())				:
				ResponseEntity.status(HttpStatus.NOT_FOUND).body("");
		
	}
	
	@DeleteMapping("/reviews/{id}")
	public ResponseEntity<Object> delete(@PathVariable int id)
	{
		Optional<Review> reviewOptional = reviewRepository.findById(id);
		
		if(reviewOptional.isPresent())
		{
			reviewRepository.deleteById(id);
			return ResponseEntity.ok(reviewOptional.get());
		}
		else
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("");
	}
	
	// Review è una entità'correlata e DIPENDENTE di Dish
	// per essere precisi, Fra dish e review c'è un rapporto di composizione sensu strictu
	// dish è l'entità' principale, o "intero"
	// review è l'entità' subordinata, o "dipendente"
	// aggiungere review da sola non ha senso. Io aggiungerò review nel contesto di un dish
	
	
	// POST /dishes/1/reviews => Aggiungi una recensione al PIATTO UNO
	
	@PostMapping("/dishes/{dishid}/reviews")
	public ResponseEntity<Object> insert(@PathVariable int dishid,@RequestBody Review review)
	{
		//Controlli da aggiungere
		Dish parent = dishRepository.findById(dishid).get();
		parent.addReview(review);
		Review insertedReview = reviewRepository.save(review);
		return ResponseEntity.ok(parent);
	}
	
	@PutMapping("/reviews/{id}")
	public ResponseEntity<Object> update
	(
		@PathVariable int id,
		@RequestBody Review review
	)
	{
		review.setID(id);
		Optional<Review> reviewOptional = reviewRepository.findById(id);
		//L'operazione in grado di fare Insert o Update in base al fatto che l'entità
		//esista o no viene detta UPSERT (o save)
		
		
		if(reviewOptional.isPresent())
		{
			Dish parent = reviewOptional.get().getDish();
			parent.addReview(review);
			Review modified = reviewRepository.save(review);
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
				res = "Error creating or updating Review entity. Could not deserialize data, could not rebuild object. Please check the value of the fields.";
			break;
			default:
				res = "Generic exception: "+e.getMessage();
		}
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
		
	}
}
