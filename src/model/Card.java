package model;

public class Card {
	//Same for all the cards:
	private static String[] categories; //Contains the names of categories/attributes for all the cards.

	public static String[] getCategories() {
		return categories;
	}
	
	public static void setCategories(String[] categories) {
		Card.categories = categories;
	}
	
	//Properties of a single card:
	private String name;  
	private int[] values; //Contains the values of a card.
	private Player owner; //Holds a reference to the current owner of this card.

	public Card(String name, int[] values) {
		this.name = name;
		this.values = values;
		owner = null;
	}

	public Player getOwner() {
		return owner;
	}

	public void setOwner(Player owner) {
		this.owner = owner;
	}

	public String getName() {
		return name;
	}
	
	public int getValue(int i) {
		//gives back the value a category i, where i is the index of the property.
		return values[i];
	}

}
