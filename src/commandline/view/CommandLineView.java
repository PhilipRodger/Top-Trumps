package commandline.view;

import java.util.Scanner;

import commandline.TopTrumpsView;
import listeners.ComputerTurnListener;
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
		// TODO  Decisions should be made about how to display certain events to the user: 
		model.addUserWonGameListener(new UserWonGameListener() {
			@Override
			public void UserWonGame() {
				// TODO Display to the user they have won!

			}
		});
		model.addUserLostGameListener(new UserLostGameListener() {

			@Override
			public void UserLostGame() {
				// TODO Display to the user they have lost!

			}
		});
		model.addUserOutOfGameListener(new UserOutOfGameListener() {

			@Override
			public void UserOutOfGame() {
				// TODO Display to the user they have been knocked out of the game and that the
				// remaining computer controlled players will auto-resolve the rest of the game!

			}
		});
		model.addUserWonRoundListener(new UserWonRoundListener() {

			@Override
			public void UserWonRound() {
				// TODO Display to the user they have won this round.

			}
		});
		model.addUserDrewRoundListener(new UserDrewRoundListener() {

			@Override
			public void UserDrewRound() {
				// TODO Display to the user that this round was a draw.

			}
		});
		model.addUserLostRoundListener(new UserLostRoundListener() {

			@Override
			public void UserLostRound() {
				// TODO Display to the user they have lost this round.

			}
		});
		model.addUserTurnListener(new UserTurnListener() {
			@Override
			public void showUserTurn() {
				// TODO Display to the user that it is their turn and their card.

			}
		});
		model.addComputerTurnListener(new ComputerTurnListener() {
			public void showComputerTurn() {
				// TODO Display to the user that it is an opponent's turn and their card

			}
		});
	}
	
	@Override
	public void showMainMenu() {
		System.out.println("Welcome to Top Trumps! Type 'new' to start a new game or 'stats' to view statistics.");

	}
	

	// should be a endless loop that will continually seek input from system.in
	// It is the User--[via-View]---> Controller part. 
	@Override
	public void run() {
		showMainMenu();
		Scanner s = new Scanner(System.in);
		String input = "";

		while (!input.toLowerCase().equals("quit")) {
			System.out.println("Type 'quit' to exit.");
			input = s.nextLine();

			if (input.toLowerCase().equals("new") && (startGameListner != null) && model.newGamePossible()) {
				startGameListner.gameStarted();
			}
			//TODO More statements calling actions on the Contoller, see Controller for things which should be called.
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
