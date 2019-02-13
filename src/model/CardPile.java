package model;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

/**
 * General class for non specific piles of cards.
 */
public class CardPile {
	protected static Deque<Card> pile = new ArrayDeque<Card>();

	public void addCard(Card c) {
		pile.add(c);
	}

	public Card drawCard() {
		return pile.remove();
	}

	public boolean hasNextCard() {
		if (pile.isEmpty())  return false;
		return true;
	}
	
	public ArrayList<Card> getListRepresentation(){
		return new ArrayList<>(pile);
	}

	public int size() {
		return pile.size();
	}

	
}
