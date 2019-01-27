package model;

public class Card {

	private String description;
	private int size;
	private int speed;
	private int range;
	private int firepower;
	private int cargo;

	public Card(String d, int si, int sp, int r, int f, int c) {

		this.setDescription(d);
		this.setSize(si);
		this.setSpeed(sp);
		this.setRange(r);
		this.setFirepower(f);
		this.setCargo(c);

	}

	// Same for all the cards:
	private static String[] categories; // Contains the names of categories/attributes for all the cards.

	public static String[] getCategories() {
		return categories;
	}

	public static void setCategories(String[] categories) {
		Card.categories = categories;
	}

	// Properties of a single card:
	private String name;
	private int[] values; // Contains the values of a card.
	private Player owner; // Holds a reference to the current owner of this card.

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
		// gives back the value a category i, where i is the index of the property.
		return values[i];
	}

	
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String d) {
		description = d;
	}

	public int getSize() {
		return size;
	}
	
	public void setSize(int si) {
		size = si;
	}

	public int getSpeed() {
		return speed;
	}
	
	public void setSpeed(int sp) {
		speed = sp;
	}

	public int getRange() {
		return range;
	}

	
	public void setRange(int r) {
		range = r;
	}

	public int getFirepower() {
		return firepower;
	}

	
	public void setFirepower(int f) {
		firepower = f;
	}

	public int getCargo() {
		return cargo;
	}
	
	public void setCargo(int c) {
		cargo = c;
	}

	
	

	

	

	
	
	

}
