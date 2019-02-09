package displayers;

import model.Game;

public interface DisplayUserWonGame {
	/**
	 * To be implemented and attached to the model via the view. Called by the model
	 * when the user has won the game.
	 * 
	 * @param game Contains methods and data for the display to update.
	 */
	public void showUserWonGame(Game game);
}
