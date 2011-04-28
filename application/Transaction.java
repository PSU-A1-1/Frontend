package application;

public class Transaction {
	
	private CardHolder cardHolder;
	private int beers;
	private int drinks;
	
	public Transaction(CardHolder cardHolder) {
		this.cardHolder = cardHolder;
		beers = cardHolder.getBeers();
		drinks = cardHolder.getDrinks();
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
	
	
}
