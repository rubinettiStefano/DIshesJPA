package com.generation.dishes.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.generation.dishes.model.entities.Review;

public interface ReviewRepository extends JpaRepository<Review,Integer>
{

}
