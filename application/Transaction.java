package application;

public class Transaction {
    
    private CardHolder cardHolder;
    private int boughtBeers;
    private int boughtDrinks;
    
    public Transaction(CardHolder cardHolder) {
        this.cardHolder = cardHolder;
        boughtBeers = 0;
        boughtDrinks = 0;
    }
    
    public void addBeer() {
        if (boughtBeers < cardHolder.getBeers()) boughtBeers++;
        else System.out.println("NO MOAR BEERS!");
    }
    
    public void addDrink() {
        if (boughtDrinks < cardHolder.getDrinks()) boughtDrinks++;
        else System.out.println("NO MOAR DRINKS!");
    }
    
    public int getBoughtBeers() {
        return boughtBeers;
    }
    
    public int getBoughtDrinks() {
        return boughtDrinks;
    }
    
    public int getNewBeers() {
        return (cardHolder.getBeers() - boughtBeers);
    }
    
    public int getNewDrinks() {
        return (cardHolder.getDrinks() - boughtDrinks);
    }
    
    public void reset() {
        boughtBeers = 0;
        boughtDrinks = 0;
    }
    
    public int getID() {
        return cardHolder.getID();
    }
    
    public String getName() {
        return cardHolder.getName();
    }
       
    public boolean isGuest() {
        return cardHolder.isGuest();
    }
}
