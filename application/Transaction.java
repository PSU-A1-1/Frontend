package application;

import java.util.Date;

public class Transaction {
	
	private CardHolder cardHolder;
	private int beers;
	private int drinks;
	private Date dateAndTime;
	
	public Transaction(CardHolder cardHolder) {
		this.cardHolder = cardHolder;
		beers = cardHolder.getBeers();
		drinks = cardHolder.getDrinks();
		dateAndTime = new Date();
	}
	
	public void addBeer() {
		beers--;	
	}
	
	public void addDrink() {
		drinks--;
	}
	
	public void reset() {
		beers = cardHolder.getBeers();
		drinks = cardHolder.getDrinks();
	}
	
	public Date getDate() {
		return dateAndTime;
	}
	
	
}
