package commandline.view;

import java.util.ArrayList;
import java.util.Scanner;

import commandline.TopTrumpsView;
import displayers.DisplayComputerTurn;
import displayers.DisplayDatabaseResponce;
import displayers.DisplayUserDrewRound;
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
import model.Round;
import model.TopTrumpsModel;

public class CommandLineView implements TopTrumpsView {
	private final int numOfPlayers = 5;
	TopTrumpsModel model;
	boolean autoResolve = false;


	public CommandLineView(TopTrumpsModel model) {
		this.model = model;

		// Model ---[UPDATES]---> View
		// TODO Decisions should be made about how to display certain events to the
		// user:
		model.addDisplayDataBaseResponce(new DisplayDatabaseResponce() {
			
			@Override
			public void showDatabaseResonce(DatabaseResponse response) {
				System.out.println("Game Stats!:");
				System.out.println("Total games played: " + response.getTotalGamesPlayed());
				System.out.println("Total games users won: " + response.getTotalHumanWins());
				System.out.println("Total games computers won: " + response.getTotalComputerWins());
				System.out.println("Average draws per game: " + response.getAverageDrawsPerGame());
				System.out.println("Largest Number of rounds in a game: " + response.getLargestNumberOfRounds());
				
			}
		});
		model.addDisplayUserWonGame(new DisplayUserWonGame() {
			@Override
			public void showUserWonGame(Game game) {
				// TODO Display to the user they have won!
				System.out.println("You won!");
				autoResolve = false;

			}
		});
		model.addDisplayUserLostGame(new DisplayUserLostGame() {

			@Override
			public void showUserLostGame(Game game) {
				// TODO Display to the user they have lost!
				System.out.println("You Lost!");
				autoResolve = false;
			}
		});
		model.addDisplayUserOutOfGame(new DisplayUserOutOfGame() {

			@Override
			public void showUserOutOfGame(Game game) {
				// TODO Display to the user they have been knocked out of the game and that the
				// remaining computer controlled players will auto-resolve the rest of the game!
				System.out.println("You are out of the game, enter anything to resolve the game.");
				autoResolve = true;
			}
		});
		model.addDisplayUserOutOfGame(new DisplayUserWonRound() {

			@Override
			public void showUserWonRound(Round currentRound) {
				// TODO Display to the user they have won this round.
				showCardsInRound(currentRound);
				System.out.println("You won this round, enter anything to continue.");

			}
		});
		model.addDisplayUserDrewRound(new DisplayUserDrewRound() {

			@Override
			public void showUserDrewRound(Round currentRound) {
				// TODO Display to the user that this round was a draw.
				if (!autoResolve) {
					showCardsInRound(currentRound);
					System.out.println("Round Draw, enter anything to continue.");	
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
				}
			}
		});
		model.addDisplayUserTurn(new DisplayUserTurn() {
			@Override
			public void showUserTurn(Round currentRound) {
				// TODO Display to the user that it is their turn and their card.
				System.out.println("Your Turn.");
				System.out.println("Number of cards in community pile: " + currentRound.getCommunityPileSize());
				System.out.println(currentRound.getFirstCard());

			}
		});
		model.addDisplayComputerTurn(new DisplayComputerTurn() {
			public void showComputerTurn(Round currentRound) {
				// TODO Display to the user that it is an opponent's turn and their card
				System.out.println(currentRound.getFirstCard().getOwner().getName() + " turn.");
				System.out.println("The computer's card is: " + currentRound.getFirstCard());
				System.out.println("They chose:" + Card.getCategories()[currentRound.getChosenCategory()]);
				System.out.println("Number of cards in community pile: " + currentRound.getCommunityPileSize());
				System.out.println("Enter anything to continue.");

			}
		});
		
	}

	@Override
	public void showMainMenu() {
		System.out.println("Welcome to Top Trumps! Type 'new' to start a new game or 'stats' to view statistics.");

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

			String playerSummaryText = String.format("%s (%d cards): \t %s  %s %d", ownerName, numOfCardsInOwnersDeck,
					cardName, categoryName, cardValue);
			System.out.println(playerSummaryText);
		}
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
			if(!autoResolve) {
				System.out.println("Type 'quit' to exit.");
				input = s.nextLine();	
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
					if (categoryChosen < Card.getCategories().length && categoryChosen >= 0) {
						// User has selected a category
						userSelectionListner.userSelection(categoryChosen);
						actionTakenThisCycle = true;
					}
				} catch (Exception e) {
					// Not a category index, do nothing
				}
			}

			if (input.toLowerCase().equals("new") && (startGameListner != null)
					&& !actionTakenThisCycle) {
				startGameListner.startNewGame(numOfPlayers);

				// resets ability to take another input
				actionTakenThisCycle = true;
			}
			
			if(input.toLowerCase().equals("stats") && !actionTakenThisCycle){
				viewStatisticsListner.viewStatistics();
				
				actionTakenThisCycle = true;
			}

			// TODO More statements calling actions on the Controller, see Controller for
			// things which should be called.
			actionTakenThisCycle = false;
		}
		// User wants to quit
		s.close();
	}

	
	// Listeners in the command view are not linked to individual elements like the
	// web version.
	StartGameListener startGameListner;
	ViewStatisticsListener viewStatisticsListner;
	NextCategoryListener nextCatagoryListener;
	UserSelectionListener userSelectionListner;
	NextRoundListener nextRoundListener;
	
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
