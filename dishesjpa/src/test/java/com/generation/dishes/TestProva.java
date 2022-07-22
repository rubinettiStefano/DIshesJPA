package com.generation.dishes;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.generation.dishes.model.entities.Dish;
import com.generation.dishes.model.repositories.DishRepository;

@SpringBootTest
class TestProva 
{

	@Autowired
	DishRepository repository;
	@Test
	void dishReadTest() 
	{
		Dish dish =  repository.findById(1).get();
		//Dish dish = database.getDish(1);
		
		System.out.println("-\n-\n-\n-\n-\n-\n"+dish.getName()+"\n-\n-\n-\n-\n-\n-");
	}
	
	@Test
	void contextLoads() {
	}

	
}
