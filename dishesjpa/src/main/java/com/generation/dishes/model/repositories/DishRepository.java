package com.generation.dishes.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.generation.dishes.model.entities.Dish;

              //Ereditarietà tra INTERFACCE
public interface DishRepository extends JpaRepository<Dish,Integer>
{													//Tipo Entità,Tipo ID
	
	//QUI NON LO VEDETE, MA C'È TUTTO IL CRUD PER I DISH
}
