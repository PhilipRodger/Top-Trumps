package model;

import java.awt.List;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Deque;

public class CardPile {
	protected Deque<Card> pile = new ArrayDeque<Card>();

	public void addCard(Card c) {
		pile.add(c);
	}

	public Card drawCard() {
		return pile.remove();
	}

	public boolean hasNextCard() {
		if (pile.isEmpty()) {
			return false;

		}
		return true;

	}
	
	public ArrayList<Card> getListRepresentation(){
		return new ArrayList<>(pile);
	}

	public int size() {
		return pile.size();
	}
}
