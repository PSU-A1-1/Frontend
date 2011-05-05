package application;

public class Main {

	public static void main(String[] args) {
		
		DBConnection DB = new DBConnection(); 
		CardHolder cardHolder = DB.getCardHolder(29);
		//CardHolder cardHolder = new CardHolder(29, "Anna Sofie Holmgaard", "Didriksen", 12, 11, 1);
		System.out.println(cardHolder.getName());
		Transaction tra = new Transaction(cardHolder);
		System.out.println(tra.getName());
		tra.addBeer();
		tra.addBeer();
		tra.addBeer();
		tra.addDrink();
		System.out.println(tra.getNewBeers());
		DB.sendTransaction(tra);
	}

}
