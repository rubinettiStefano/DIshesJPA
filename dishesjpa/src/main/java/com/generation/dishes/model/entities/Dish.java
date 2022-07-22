package com.generation.dishes.model.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


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
	
	
}