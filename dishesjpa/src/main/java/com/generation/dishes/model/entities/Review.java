package com.generation.dishes.model.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="DishReview")
public class Review
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) 
	private int ID;		
	
	String title, reviewer;
	int vote;
	
	
	@ManyToOne
	@JoinColumn(name="dishid") // CHIAVE ESTERNA SUL DB. NON E' UN CAMPO SU JAVA!
	@JsonIgnore
	Dish dish;
	
	public int getID()
	{
		return ID;
	}
	public void setID(int iD)
	{
		ID = iD;
	}
	public String getTitle()
	{
		return title;
	}
	public void setTitle(String title)
	{
		this.title = title;
	}
	public String getReviewer()
	{
		return reviewer;
	}
	public void setReviewer(String reviewer)
	{
		this.reviewer = reviewer;
	}
	public int getVote()
	{
		return vote;
	}
	public void setVote(int vote)
	{
		this.vote = vote;
	}
	public Dish getDish()
	{
		return dish;
	}
	public void setDish(Dish dish)
	{
		this.dish = dish;
	}
	
	
}
