package application;

import java.util.*;

public class CardHolder extends Observable {
	
	private int ID;
	private String first_name;
	private String surname;
	private int beers;
	private int drinks;


	public CardHolder(int ID, String first_name, String surname, int beers, int drinks, boolean active) {
		
		if(!active) {
			System.out.println("Card's not active, 'n stuff.");

		} else {
			
			this.ID = ID;
			this.beers = beers;
			this.drinks = drinks;
			
			if(first_name == null) {
				first_name = "Guest";
				surname = "";
			} else {
				this.first_name = first_name;
				this.surname = surname;
			}
		}
	}
	
	public int getID() {		
		return ID;
	}
	
	public int getBeers() {		
		return beers;
	}

	  	
	public int getDrinks() {
		return drinks;
	}

	public String getName() {
		return first_name + surname;
	}
	
}