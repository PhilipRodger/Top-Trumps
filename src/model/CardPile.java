package model;

import java.awt.List;
import java.util.ArrayDeque;
import java.util.Deque;


public class CardPile {
	
	
	
	private Deque<Card> pile = new ArrayDeque<Card>();;
	private Deque<Card> playerPile = new ArrayDeque<Card>();
	private Deque<Card> cpu1Pile = new ArrayDeque<Card>();
	private Deque<Card> cpu2Pile = new ArrayDeque<Card>();
	private Deque<Card> cpu3Pile = new ArrayDeque<Card>();
	private Deque<Card> cpu4Pile = new ArrayDeque<Card>();
	private Deck deck;
	
	
	
	
	public void addCard(Card c) {
		pile.add(c);
	}
	
	public Card drawCard() {
		return pile.remove();
	}
	
	public boolean hasNextCard(Deque<Card> pile) {
		while (pile.peek () != null) {
			
			return true;
		}
		
		{
		return false;
		}
		//TODO: return true if there is a next card on the pile or false if not.
		
	}
	public int size() {
		return pile.size();
	}

	public void dealCards(int numberOfPlayers) {

		pile = deck.getShuffledDeck();
		System.out.println(pile.toString());
		while (!pile.isEmpty()) {
			if (pile.isEmpty()) {
				break;
			}
			playerPile.addFirst(pile.pollFirst());
			if (pile.isEmpty()) {
				break;
			}
			cpu1Pile.addFirst(pile.pollFirst());
			if (pile.isEmpty()) {
				break;
			}
			if (numberOfPlayers> 1) {
				cpu2Pile.addFirst(pile.pollFirst());
				if (pile.isEmpty()) {
					break;
				}
				if (numberOfPlayers > 2) {
					cpu3Pile.addFirst(pile.pollFirst());
					if (pile.isEmpty()) {
						break;
					}
					if (numberOfPlayers > 3) {
						cpu4Pile.addFirst(pile.pollFirst());
						if (pile.isEmpty()) {
							break;
						}
					}
				}
			}
		}
}

}
