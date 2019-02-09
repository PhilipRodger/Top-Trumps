package displayers;

import model.Game;

public interface DisplayUserOutOfGame {
	/**
	 * To be implemented and attached to the model via the view. Called by the model
	 * when the user has run out of cards and out of the game.
	 * 
	 * @param game Contains methods and data for the display to update.
	 */
	public void showUserOutOfGame(Game game);

}
