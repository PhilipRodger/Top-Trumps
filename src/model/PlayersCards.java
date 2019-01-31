package model;

public class PlayersCards extends CardPile{
	private Player owner;

	@Override
	public void addCard(Card c) {
		c.setOwner(owner); //update the owner of the card whenever a card is added.
		pile.add(c);	
	}

	public PlayersCards(Player owner) {
		this.owner = owner;
	}

}
