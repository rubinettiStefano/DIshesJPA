package com.generation.dishes.model.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;


//io mi aspetto che JPA sia in grado di leggere e scrivere in maniera automatica
// questa roba sul DB
//Gli oggetti di classe Dish sono MANAGED da JPA (gestiti)
@Entity
public class Dish
{
	//Segnalo quale campo è la chiave PRIMARIA, OBBLIGATORIA
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) //Dico che l'ID è generato automaticamente
	private int ID;									  // e come, OPZIONALE	
	
	private String name;
	private int carbs,fats,proteins;
	
	//1 a N - 1 Dish  N Review
	// one to many : annotation da mettere sopra la lista del lato 1
	// mappedBy : campo di collegamento nel lato n (riferimento)
	// nella classe Review c'è un campo dish. mappedBy=dish => campo dish in Review
	// cascade=CascadeType.PERSIST => cambiamenti a questa entità portano a cambiamenti in Review
	// se salvo Dish salvo tutte le sue Review!
	// cascade = POLITICA DI PROPAGAZIONE. Cosa faccio se salvo Dish?
	// PERSIST = salvo anche le review collegate!
	@OneToMany(mappedBy = "dish", cascade=CascadeType.PERSIST)
	private List<Review> reviews = new ArrayList<Review>();
	
	
	public boolean addReview(Review review)
	{
		review.dish=this;
		//review.dishID = ID;
		return reviews.add(review);
	}
	
	public int getID()
	{
		return ID;
	}
	public void setID(int iD)
	{
		ID = iD;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public int getCarbs()
	{
		return carbs;
	}
	public void setCarbs(int carbs)
	{
		this.carbs = carbs;
	}
	public int getFats()
	{
		return fats;
	}
	public void setFats(int fats)
	{
		this.fats = fats;
	}
	public int getProteins()
	{
		return proteins;
	}
	public void setProteins(int proteins)
	{
		this.proteins = proteins;
	}

	public List<Review> getReviews()
	{
		return reviews;
	}

	public void setReviews(List<Review> reviews)
	{
		this.reviews = reviews;
	}
	
	
}