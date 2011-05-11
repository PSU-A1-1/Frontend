package model;


public class CardHolder {
	
	private int ID;
	private String first_name;
	private String surname;
	private int beers;
	private int drinks;
	private int active;


	public CardHolder(int ID, String first_name, String surname, int beers, int drinks, int active) {
		
	
			
			this.ID = ID;
			this.beers = beers;
			this.drinks = drinks;
			this.active = active;
			
			if(first_name == null) {
				this.first_name = "Guest";
				this.surname = "";
			} else {
				this.first_name = first_name;
				this.surname = surname;
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
		return first_name + " " + surname;
	}
	
	public boolean isGuest() {
	    return first_name == "Guest";
	   }
	
	public boolean isActive() {
		return active == 1;
	}
	
}
