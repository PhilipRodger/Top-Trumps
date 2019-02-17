package view;

import java.util.ArrayList;
import java.util.Scanner;

import displayers.DisplayComputerTurn;
import displayers.DisplayDatabaseResponce;
import displayers.DisplayDrewRound;
import displayers.DisplayUserLostGame;
import displayers.DisplayUserLostRound;
import displayers.DisplayUserOutOfGame;
import displayers.DisplayUserTurn;
import displayers.DisplayUserWonGame;
import displayers.DisplayUserWonRound;
import listeners.NextCategoryListener;
import listeners.NextRoundListener;
import listeners.StartGameListener;
import listeners.UserSelectionListener;
import listeners.ViewStatisticsListener;
import model.Card;
import model.DatabaseResponse;
import model.Game;
import model.HumanPlayer;
import model.Player;
import model.Round;
import model.TopTrumpsModel;
import online.dwResources.TopTrumpsRESTAPI;

public class OnlineView implements TopTrumpsView {
	private final int numOfPlayers = 5;
	TopTrumpsModel model;
	public boolean autoResolve = false;
	boolean mainMenu = true;

	public OnlineView(TopTrumpsModel model) {
		this.model = model;

		// Model ---[UPDATES]---> View
		// Decisions should be made about how to display certain events to the
		// user:
		model.addDisplayDataBaseResponce(new DisplayDatabaseResponce() {

			@Override
			public void showDatabaseResonce(DatabaseResponse response) {
			}
		});
		model.addDisplayUserWonGame(new DisplayUserWonGame() {
			@Override
			public void showUserWonGame(Game game) {
				// Display to the user they have won!
				System.out.println("You won!");
				System.out.println(String.format("After only %d rounds!\n\n", Round.getRoundNumber()));
				showMainMenu();
				
				RoundObjectToJson response = new RoundObjectToJson();
				response.gameFinished = true;
				response.gameWinnerString = game.getPlayers()[0].getName();
				response.roundNumber = Round.getRoundNumber();
				setResponse(response);
				
				autoResolve = false;

			}
		});
		model.addDisplayUserLostGame(new DisplayUserLostGame() {

			@Override
			public void showUserLostGame(Game game) {
				// Display to the user they have lost!
				System.out.println(String.format("You lost, the winner was %s.", game.getGameWinner().getName()));
				System.out.println(String.format("After only %d rounds!\n\n", Round.getRoundNumber()));
				showMainMenu();
				RoundObjectToJson response = new RoundObjectToJson();
				response.gameFinished = true;
				for(int i = 0; i < game.getPlayers().length; i++) {
					if(game.getPlayers()[i] == game.getGameWinner()) {
						response.gameWinner = i;
						response.gameWinnerString = game.getPlayers()[i].getName();
					}
				}
				response.roundNumber = Round.getRoundNumber();

				setResponse(response);
				autoResolve = false;
			}
		});
		model.addDisplayUserOutOfGame(new DisplayUserOutOfGame() {

			@Override
			public void showUserOutOfGame(Game game) {
				// TODO Display to the user they have been knocked out of the game and that the
				// remaining computer controlled players will auto-resolve the rest of the game!
				if (!autoResolve) {
					System.out.println("You are out of the game, enter anything to resolve the game.");
					System.out.println("This may take a while if you wanted a log file.");
					RoundObjectToJson response = new RoundObjectToJson();
					displayTurnResolutionResponse(game.getCurrentRound(), response);
					setResponse(response);
					autoResolve = true;
				}
			}
		});
		model.addDisplayUserWonRound(new DisplayUserWonRound() {

			@Override
			public void showUserWonRound(Round currentRound) {
				// TODO Display to the user they have won this round.
				if (!autoResolve) {
					showCardsInRound(currentRound);
					System.out.println("You won this round, enter anything to continue.");
					RoundObjectToJson response = new RoundObjectToJson();
					displayTurnResolutionResponse(currentRound, response);
					setResponse(response);
				}
			}
		});
		model.addDisplayUserDrewRound(new DisplayDrewRound() {

			@Override
			public void showUserDrewRound(Round currentRound) {
				// TODO Display to the user that this round was a draw.
				if (!autoResolve) {
					showCardsInRound(currentRound);
					System.out.println("Round Draw, enter anything to continue.");
					RoundObjectToJson response = new RoundObjectToJson();
					displayTurnResolutionResponse(currentRound, response);
					response.roundWinnerIndex = -1;
					response.roundWinnerString = "Draw";
					setResponse(response);
				}
			}
		});
		model.addDisplayUserLostRound(new DisplayUserLostRound() {

			@Override
			public void showUserLostRound(Round currentRound) {
				// TODO Display to the user they have lost this round.
				if (!autoResolve) {
					showCardsInRound(currentRound);
					System.out.println("And the winner of the round is " + currentRound.getRoundWinner().getName());
					System.out.println("You lost this round, enter anything to continue.");

					RoundObjectToJson response = new RoundObjectToJson();
					displayTurnResolutionResponse(currentRound, response);
					setResponse(response);
				}
			}
		});
		model.addDisplayUserTurn(new DisplayUserTurn() {
			@Override
			public void showUserTurn(Round currentRound) {
				// TODO Display to the user that it is their turn and their card.
				if (!autoResolve) {
					System.out.println(String.format("\n\n--- Round %d ---", Round.getRoundNumber()));
					System.out.println("Your Turn.");
					System.out.println("Number of cards in community pile: " + currentRound.getCommunityPileSize());
					showUsersCard(currentRound.getFirstCard());

					RoundObjectToJson response = new RoundObjectToJson();
					response.gameFinished = false;
					response.roundNumber = Round.getRoundNumber();
					for (int i = 0; i < currentRound.getPlayers().length; i++) {
						if (currentRound.getPlayers()[i] == currentRound.getPlayersTurn()) {
							response.playersTurnIndex = i;
							response.playersTurnName = currentRound.getPlayers()[i].getName();
							break;
						}
					}
					response.communityPileSize = currentRound.getCommunityPileSize();
					response.roundHasBeenResolved = false;
					response.playersToJson = new PlayerToJson[currentRound.getPlayers().length];
					for (int i = 0; i < currentRound.getPlayers().length; i++) {
						response.playersToJson[i] = new PlayerToJson();
						response.playersToJson[i].inGame = currentRound.getPlayers()[i].inGame();
						if (currentRound.getPlayers()[i] instanceof HumanPlayer) {
							response.playersToJson[i].humanPlayer = true;
						} else {
							response.playersToJson[i].humanPlayer = false;
						}
						response.playersToJson[i].numberOfCards = currentRound.getPlayers()[i].getNumberOfCards();
						if (currentRound.getPlayers()[i] == currentRound.getPlayersTurn()) {
							response.playersToJson[i].cardName = currentRound.getFirstCard().getName();
							response.playersToJson[i].size = currentRound.getFirstCard().getValue(0);
							response.playersToJson[i].speed = currentRound.getFirstCard().getValue(1);
							response.playersToJson[i].range = currentRound.getFirstCard().getValue(2);
							response.playersToJson[i].firepower = currentRound.getFirstCard().getValue(3);
							response.playersToJson[i].cargo = currentRound.getFirstCard().getValue(4);
						}
					}
					setResponse(response);
				}
			}
		});
		model.addDisplayComputerTurn(new DisplayComputerTurn() {
			public void showComputerTurn(Round currentRound) {
				// TODO Display to the user that it is an opponent's turn and their card
				if (!autoResolve) {
					System.out.println(String.format("\n\n--- Round %d ---", Round.getRoundNumber()));
					System.out.println(currentRound.getFirstCard().getOwner().getName() + " turn.");
					System.out.println("The computer's card is:\n" + currentRound.getFirstCard());
					System.out.println("They chose:" + Card.getCategories()[currentRound.getChosenCategory()]);
					System.out.println("Number of cards in community pile: " + currentRound.getCommunityPileSize());
					System.out.println("Enter anything to resolve the round.");

					RoundObjectToJson response = new RoundObjectToJson();
					response.gameFinished = false;
					response.roundNumber = Round.getRoundNumber();
					for (int i = 0; i < currentRound.getPlayers().length; i++) {
						if (currentRound.getPlayers()[i] == currentRound.getPlayersTurn()) {
							response.playersTurnIndex = i;
							response.playersTurnName = currentRound.getPlayers()[i].getName();
							break;
						}
					}
					response.chosenCategory = Card.getCategories()[currentRound.getChosenCategory()];
					response.communityPileSize = currentRound.getCommunityPileSize();
					response.roundHasBeenResolved = false;
					response.playersToJson = new PlayerToJson[currentRound.getPlayers().length];
					for (int i = 0; i < currentRound.getPlayers().length; i++) {
						response.playersToJson[i] = new PlayerToJson();
						response.playersToJson[i].inGame = currentRound.getPlayers()[i].inGame();
						if (currentRound.getPlayers()[i] instanceof HumanPlayer) {
							response.playersToJson[i].humanPlayer = true;
						} else {
							response.playersToJson[i].humanPlayer = false;
						}
						response.playersToJson[i].numberOfCards = currentRound.getPlayers()[i].getNumberOfCards();
						if (currentRound.getPlayers()[i] == currentRound.getPlayersTurn()) {
							response.playersToJson[i].cardName = currentRound.getFirstCard().getName();
							response.playersToJson[i].size = currentRound.getFirstCard().getValue(0);
							response.playersToJson[i].speed = currentRound.getFirstCard().getValue(1);
							response.playersToJson[i].range = currentRound.getFirstCard().getValue(2);
							response.playersToJson[i].firepower = currentRound.getFirstCard().getValue(3);
							response.playersToJson[i].cargo = currentRound.getFirstCard().getValue(4);
						}
					}
					setResponse(response);

				}
			}
		});

	}
	
	private void displayTurnResolutionResponse(Round currentRound, RoundObjectToJson response) {
		response.gameFinished = false;
		response.roundNumber = Round.getRoundNumber();
		for (int i = 0; i < currentRound.getPlayers().length; i++) {
			if (currentRound.getPlayers()[i] == currentRound.getPlayersTurn()) {
				response.playersTurnIndex = i;
				response.playersTurnName = currentRound.getPlayers()[i].getName();

				break;
			}
		}
		response.communityPileSize = currentRound.getCommunityPileSize();
		response.roundHasBeenResolved = true;
		for (int i = 0; i < currentRound.getPlayers().length; i++) {
			if (currentRound.getPlayers()[i] == currentRound.getRoundWinner()) {
				response.roundWinnerIndex = i;
				response.roundWinnerString = currentRound.getPlayers()[i].getName();
				break;
			}
		
		}
		response.chosenCategory = Card.getCategories()[currentRound.getChosenCategory()];

		response.playersToJson = new PlayerToJson[currentRound.getPlayers().length];
		for (int i = 0; i < currentRound.getPlayers().length; i++) {
			response.playersToJson[i] = new PlayerToJson();
			response.playersToJson[i].inGame = currentRound.getPlayers()[i].inGame();
			if (currentRound.getPlayers()[i] instanceof HumanPlayer) {
				response.playersToJson[i].humanPlayer = true;
			} else {
				response.playersToJson[i].humanPlayer = false;
			}
			response.playersToJson[i].numberOfCards = currentRound.getPlayers()[i].getNumberOfCards();
			for (Card card : currentRound.getListOfCardsInRound()) {
				if(card.getOwner() == currentRound.getPlayers()[i]) {
					response.playersToJson[i].cardName = card.getName();
					response.playersToJson[i].size = card.getValue(0);
					response.playersToJson[i].speed = card.getValue(1);
					response.playersToJson[i].range = card.getValue(2);
					response.playersToJson[i].firepower = card.getValue(3);
					response.playersToJson[i].cargo = card.getValue(4);
					break;
				}
			}
		}
	}

	// Display the card a user drew to prompt them to make a choice.
	private void showUsersCard(Card usersCard) {
		System.out.println(String.format("      %s", usersCard.getName()));
		for (int i = 0; i < Card.getCategories().length; i++) {
			System.out.println(String.format("%d |%-10s: %2d|", i + 1, Card.getCategories()[i], usersCard.getValue(i)));
		}
		System.out.println("Enter the index for the category you want to pick:");
	}

	private void showCardsInRound(Round currentRound) {
		ArrayList<Card> round = currentRound.getListOfCardsInRound();
		System.out.println("Round:");
		for (Card card : round) {

			String ownerName = card.getOwner().getName();
			int numOfCardsInOwnersDeck = card.getOwner().getNumberOfCards();
			String cardName = card.getName();
			String categoryName = Card.getCategories()[currentRound.getChosenCategory()];
			int cardValue = card.getValue(currentRound.getChosenCategory());

			String playerSummaryText = String.format("%s (%d cards)\t| %-15s| %s: %2d |", ownerName,
					numOfCardsInOwnersDeck, cardName, categoryName, cardValue);
			System.out.println(playerSummaryText);
		}
	}

	@Override
	public void showMainMenu() {
		mainMenu = true;
		System.out.println("Welcome to Top Trumps!");
		System.out.println("Do you want to see past results or play a game?");
		System.out.println("\t1: Print Game Statistics");
		System.out.println("\t2: Play game");
		System.out.println("Enter the number for your selection:");
	}

	// should be a endless loop that will continually seek input from system.in
	// It is the User--[via-View]---> Controller part.

	public void run() {
		showMainMenu();
		Scanner s = new Scanner(System.in);
		String input = "";
		// This boolean prevents unintended actions happening by limiting the actions
		// possible to 1 per user input cycle
		boolean actionTakenThisCycle = false;

		while (!input.toLowerCase().equals("quit")) {
			if (!autoResolve) {
				System.out
						.println("...Type 'quit' to exit or 'autoresolve' to complete the current game automatically.");
				input = s.nextLine();
			}

			if (mainMenu && !actionTakenThisCycle) {
				if (input.equals("1")) {
					viewStatisticsListner.viewStatistics();

					actionTakenThisCycle = true;
				}

				if (input.equals("2")) {
					startGameListner.startNewGame(numOfPlayers);

					// resets ability to take another input
					actionTakenThisCycle = true;
					mainMenu = false;
				}
			}

			if (input.toLowerCase().equals("autoresolve") && !autoResolve) {
				autoResolve = true;
				System.out.println("This may take a while if you wanted a log file.");
			}

			if (model.resolveComputerTurnPossible() && !actionTakenThisCycle) {
				nextCatagoryListener.nextCategory();
				actionTakenThisCycle = true;
			}

			if (model.nextTurnPossible() && !actionTakenThisCycle) {
				nextRoundListener.nextRound();
				actionTakenThisCycle = true;
			}

			if (model.resolveUserTurnPossible() && !actionTakenThisCycle) {
				try {
					int categoryChosen = Integer.parseInt(input);
					categoryChosen--; // To match the indexes starting at one.
					if (categoryChosen < Card.getCategories().length && categoryChosen >= 0) {
						// User has selected a category
						userSelectionListner.userSelection(categoryChosen);
						actionTakenThisCycle = true;
					}
				} catch (Exception e) {
					// Not a category index, do nothing
				}
			}

			if (model.resolveUserTurnPossible() && !actionTakenThisCycle && autoResolve) {
				// I got sick of typing and made an auto resolve mode
				userSelectionListner.userSelection(0);
			}

			// TODO More statements calling actions on the Controller, see Controller for
			// things which should be called.
			actionTakenThisCycle = false;
		}
		// User wants to quit
		s.close();
	}

	TopTrumpsRESTAPI rest;

	public void setTopTrumpsRESTAPI(TopTrumpsRESTAPI rest) {
		this.rest = rest;
	}

	public void setResponse(RoundObjectToJson responce) {
		rest.setRoundResponce(responce);
	}

	// Listeners in the command view are not linked to individual elements like the
	// web version.
	public StartGameListener startGameListner;
	public ViewStatisticsListener viewStatisticsListner;
	public NextCategoryListener nextCatagoryListener;
	public UserSelectionListener userSelectionListner;
	public NextRoundListener nextRoundListener;

	// Below methods just add action listeners to the the view allowing separation
	// of the view form the controller.
	@Override
	public void addStartGameListener(StartGameListener listner) {
		this.startGameListner = listner;

	}

	@Override
	public void addViewStatisticsListener(ViewStatisticsListener listner) {
		this.viewStatisticsListner = listner;

	}

	@Override
	public void addNextCategoryListener(NextCategoryListener listner) {
		this.nextCatagoryListener = listner;

	}

	@Override
	public void addUserSelectionListener(UserSelectionListener listner) {
		this.userSelectionListner = listner;

	}

	@Override
	public void addNextRoundListener(NextRoundListener listner) {
		this.nextRoundListener = listner;

	}
}
