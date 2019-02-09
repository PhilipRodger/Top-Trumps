package controler;

import listeners.NextCategoryListener;
import listeners.NextRoundListener;
import listeners.StartGameListener;
import listeners.UserSelectionListener;
import listeners.ViewStatisticsListener;
import model.TopTrumpsModel;
import view.TopTrumpsView;

/**
 * The controller takes actions from the User. Then triggers the model to update
 * to reflect the action. 
 * 
 * User ---[Interacts]---->Controller---[Manipulates]----> Model
 */
public class TopTrumpsContoller {
	TopTrumpsModel model;
	TopTrumpsView view;

	/**
	 * The controller takes a model and a view so that it can update the model when
	 * the view is interacted with.
	 * @param model
	 * @param view
	 */

	public TopTrumpsContoller(TopTrumpsModel model, TopTrumpsView view) {
		this.model = model;
		this.view = view;

		// The methods bellow may be triggered by the user via their view. The methods
		// update the model in response to the chosen action.
		view.addStartGameListener(new StartGameListener() {
			/**
			 * This will be called when the user has selected to play a game instead of
			 * viewing statistics. It causes the model to start up a new game.
			 */
			@Override
			public void startNewGame(int numOfPlayers) {
				model.playNewGame(numOfPlayers);

			}
		});

		view.addViewStatisticsListener(new ViewStatisticsListener() {
			/**
			 * This will be called when the user has selected to view statistics instead of
			 * playing a game. It causes the model to get info from the database about past
			 * games.
			 */
			@Override
			public void viewStatistics() {
				model.viewStatistics();
			}
		});

		view.addNextCategoryListener(new NextCategoryListener() {
			/**
			 * This will be called when it is not the user's turn, in place of asking the
			 * user to select a category of their card. It provides a stopping point so that
			 * the non-user round does not instantly resolve. Before this action happens the
			 * user can see their card from the round, and after the action the AI will
			 * chose their category and the round will resolve.
			 */
			@Override
			public void nextCategory() {
				if (model.resolveComputerTurnPossible()) {
					model.nextCategory();
				}
			}
		});

		view.addUserSelectionListener(new UserSelectionListener() {
			/**
			 * This will be called during the game when the user has selected a
			 * category/attribute on their turn. It triggers the model to be updated in
			 * response. The model will run up till the end of the current round.
			 *
			 * @param selectedCatagory is the index of the category the user has chosen
			 */
			@Override
			public void userSelection(int selectedCategory) {
				if (model.resolveUserTurnPossible()) {
					model.userSelection(selectedCategory);
				}

			}
		});

		view.addNextRoundListener(new NextRoundListener() {
			/**
			 * This will be called after the end of a round and the user is ready for the
			 * next round to start.
			 */
			@Override
			public void nextRound() {
				model.startRound();

			}
		});
	}
}
