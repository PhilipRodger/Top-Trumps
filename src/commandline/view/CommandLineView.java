package commandline.view;

import java.util.ArrayList;
import java.util.Scanner;

import commandline.TopTrumpsView;
import listeners.ShowComputerTurnListener;
import listeners.NextCategoryListener;
import listeners.NextRoundListener;
import listeners.RestartListener;
import listeners.StartGameListener;
import listeners.UserDrewRoundListener;
import listeners.UserLostGameListener;
import listeners.UserLostRoundListener;
import listeners.UserOutOfGameListener;
import listeners.UserSelectionListener;
import listeners.UserTurnListener;
import listeners.UserWonGameListener;
import listeners.UserWonRoundListener;
import listeners.ViewStatisticsListener;
import model.Card;
import model.TopTrumpsModel;

public class CommandLineView implements TopTrumpsView {
	TopTrumpsModel model;
	// Listeners in the command view are not linked to individual elements like the
	// web version.
	StartGameListener startGameListner;
	ViewStatisticsListener viewStatisticsListner;
	NextCategoryListener nextCatagoryListener;
	UserSelectionListener userSelectionListner;
	NextRoundListener nextRoundListener;
	RestartListener restartListner;

	public CommandLineView(TopTrumpsModel model) {
		this.model = model;

		// Model ---[UPDATES]---> View
		// TODO Decisions should be made about how to display certain events to the
		// user:
		model.addUserWonGameListener(new UserWonGameListener() {
			@Override
			public void UserWonGame() {
				// TODO Display to the user they have won!
				System.out.println("You won!");

			}
		});
		model.addUserLostGameListener(new UserLostGameListener() {

			@Override
			public void UserLostGame() {
				// TODO Display to the user they have lost!
				System.out.println("You Lost!");

			}
		});
		model.addUserOutOfGameListener(new UserOutOfGameListener() {

			@Override
			public void UserOutOfGame() {
				// TODO Display to the user they have been knocked out of the game and that the
				// remaining computer controlled players will auto-resolve the rest of the game!
				System.out.println("You are out of the game, enter anything to resolve the game.");

			}
		});
		model.addUserWonRoundListener(new UserWonRoundListener() {

			@Override
			public void UserWonRound() {
				// TODO Display to the user they have won this round.
				showCards();
				System.out.println("You won this round, enter anything to continue.");

			}
		});
		model.addUserDrewRoundListener(new UserDrewRoundListener() {

			@Override
			public void UserDrewRound() {
				// TODO Display to the user that this round was a draw.
				showCards();
				System.out.println("Round Draw, enter anything to continue.");

			}
		});
		model.addUserLostRoundListener(new UserLostRoundListener() {

			@Override
			public void UserLostRound() {
				// TODO Display to the user they have lost this round.
				showCards();
				System.out.println("You lost this round, enter anything to continue.");
			}
		});
		model.addUserTurnListener(new UserTurnListener() {
			@Override
			public void showUserTurn(Card currentCardDrawn) {
				// TODO Display to the user that it is their turn and their card.
				System.out.println("Your Turn.");
				System.out.println(currentCardDrawn);

			}
		});
		model.addShowComputerTurnListener(new ShowComputerTurnListener() {
			public void showComputerTurn(Card computersCard) {
				// TODO Display to the user that it is an opponent's turn and their card
				System.out.println(computersCard.getOwner().getName() + " turn.");
				System.out.println("The computer's card is: " + computersCard);
				System.out.println("They chose:" + model.getCategoryChoice());
				System.out.println("Enter anything to continue.");

			}
		});
	}

	@Override
	public void showMainMenu() {
		System.out.println("Welcome to Top Trumps! Type 'new' to start a new game or 'stats' to view statistics.");

	}
	
	private void showCards() {
		ArrayList<Card> round = model.getRound().getListRepresentation();
		System.out.println("Number of cards in round: " + round.size());
		for (Card card : round) {
			System.out.println(card);
		}
	}

	

	// should be a endless loop that will continually seek input from system.in
	// It is the User--[via-View]---> Controller part.
	@Override
	public void run() {
		showMainMenu();
		Scanner s = new Scanner(System.in);
		String input = "";
		// This boolean prevents unintended actions happening by limiting the actions
		// possible to 1 per user input cycle
		boolean actionTakenThisCycle = false;

		while (!input.toLowerCase().equals("quit")) {
			System.out.println("Type 'quit' to exit.");
			input = s.nextLine();

			if (model.resolveComputerTurnPossible() && !actionTakenThisCycle) {
				nextCatagoryListener.nextCatagory();
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
					// Not a category, do nothing
				}
			}

			if (input.toLowerCase().equals("new") && (startGameListner != null) && model.newGamePossible()
					&& !actionTakenThisCycle) {
				startGameListner.gameStarted();

				// resets ability to take another input
				actionTakenThisCycle = true;
			}

			// TODO More statements calling actions on the Controller, see Controller for
			// things which should be called.
			actionTakenThisCycle = false;
		}
		// User wants to quit
		s.close();
	}

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

	@Override
	public void addRestartListener(RestartListener listner) {
		this.restartListner = listner;

	}
}
