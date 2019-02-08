package model;

import java.util.ArrayList;
import java.util.Random;

import displayers.DisplayComputerTurn;
import displayers.DisplayUserDrewRound;
import displayers.DisplayUserLostRound;
import displayers.DisplayUserTurn;
import displayers.DisplayUserWonRound;

public class Round {
	// Round Specific Variables
	private int nextPlayer;
	private Card firstCard;
	private CardPile roundPile;
	private int chosenCategory;
	private Player roundWinner;
	private Game game;

	public Round(Game game) {
		this.game = game;
		roundPile = new CardPile();
	}

	// Interactions to progress round
	public void startRound(Player playersTurn) {
		// A single round in a game!
		System.out.println("started Round");
		game.setNextTurnPossible(false);

		// The player's who's turn this is should pick up their card.
		firstCard = playersTurn.playersDrawPhase();
		playersTurn.showCard(this);

		// Community pile should persist between rounds in case of a draw.
		game.addCommunityCard(firstCard);
		roundPile.addCard(firstCard);
	}

	public void resolveRound() {
		// Actions that happen after a player has made their choice of category for the
		// round and contributed to the communal pile.

		// A loop over all other players in the game.
		nextPlayer = game.getPlayersTurn();
		incrementNextPlayer();
		while (nextPlayer != game.getPlayersTurn()) {
			if (game.getPlayers()[nextPlayer].inGame()) {
				// The player is in the game so they should draw a card and add to the communal
				// and round pile.

				Card drawnCard = game.getPlayers()[nextPlayer].drawCard();
				game.addCommunityCard(drawnCard);
				roundPile.addCard(drawnCard);
			}

			incrementNextPlayer();
		}

		// All player's cards on pile, need to work out who won.
		winnerOfRound();

		// If a draw do nothing with community pile, and next player will stay the same.
		displayTurnResolution();

		if (roundWinner != null) {
			// If not a draw then add the communal pile to the winners deck
			for (Player player : game.getPlayers()) {
				if (roundWinner == player) {
					player.addCardPileToBottom(game.getCommunityPile());
					game.setPlayersTurn(game.getIndexOfPlayer(roundWinner)); // the winner of a round should be next
				}
			}
		} else {
			game.incrementNumOfDraws();
		}
	}

	// Get info about a round.
	public Card getFirstCard() {
		return firstCard;
	}

	public void setCategoryChoice(int chosenCategory) {
		this.chosenCategory = chosenCategory;
	}

	public String getCategoryChoiceString() {
		return Card.getCategories()[chosenCategory];
	}

	public CardPile getRound() {
		return roundPile;
	}

	public int getChosenCategory() {
		return chosenCategory;
	}

	public Player getRoundWinner() {
		return roundWinner;
	}

	public ArrayList<Card> getListOfCardsInRound() {
		return roundPile.getListRepresentation();
	}

	public int getCommunityPileSize() {
		return game.getCommunityPileSize();
	}

	// Private Helper functions
	private void winnerOfRound() {
		// sets the round winner to the one who has won the round or null if it was a
		// draw.
		int maxSeen = -1;
		Player winner = null;
		ArrayList<Card> roundPileCopy = roundPile.getListRepresentation();

		for (int i = 0; i < roundPileCopy.size(); i++) {
			Card toCompare = roundPileCopy.get(i);
			if (toCompare.getValue(chosenCategory) > maxSeen) {
				maxSeen = toCompare.getValue(chosenCategory);
				winner = toCompare.getOwner();
			} else if (toCompare.getValue(chosenCategory) == maxSeen) {
				winner = null;
			}
		}
		roundWinner = winner;
	}

	private void incrementNextPlayer() {
		nextPlayer = (nextPlayer + 1) % game.getPlayers().length;
	}

	private void displayTurnResolution() {
		// Communicates to the view what the result of the round was.

		if (game.gameOver()) {
			game.displayGameOverScreen();
		} else {
			// What to do if game is still ongoing:

			// Work out if it was a win, loss, or draw for the round
			game.setNextTurnPossible(true);
			if (userWonRound()) {
				displayUserWonRound.showUserWonRound(this);

			} else if (game.userOutOfGame()) {
				game.displayUserOutOfGameScreen();
				
			} else if (userLostRound()) {
				
				displayUserLostRound.showUserLostRound(this);
				
			} else {
				// draw round
				displayUserDrewRound.showUserDrewRound(this);
			}
		}
	}

	private boolean userWonRound() {
		if (roundWinner instanceof HumanPlayer) {
			return true;
		} else {
			return false;
		}
	}

	private boolean userLostRound() {
		if (roundWinner instanceof ComputerPlayer) {
			return true;
		} else {
			return false;
		}
	}

	// Adds displayers to update the view.
	private DisplayUserWonRound displayUserWonRound;
	private DisplayUserDrewRound displayUserDrewRound;
	private DisplayUserLostRound displayUserLostRound;

	public void addDisplayUserWonRound(DisplayUserWonRound displayer) {
		this.displayUserWonRound = displayer;
	}

	public void addDisplayUserDrewRound(DisplayUserDrewRound displayer) {
		this.displayUserDrewRound = displayer;
	}

	public void addDisplayUserLostRound(DisplayUserLostRound displayer) {
		this.displayUserLostRound = displayer;
	}
}
