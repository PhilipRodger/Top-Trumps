package model;

import java.util.Queue;

public class CardPile {
	private Queue<Card> pile;
	
	public void addCard(Card c) {
		addCard(c);
	}
	
	public Card drawCard() {
		return pile.remove();
	}
	
	public boolean hasNextCard() {
		//TODO: return true if there is a next card on the pile or false if not.
		return true;
	}
	
	public int size() {
		return pile.size();
	}
}
