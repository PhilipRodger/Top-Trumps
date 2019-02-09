package displayers;

import model.Game;

public interface DisplayUserLostGame {
	/** To be implemented and attached to the model via the view. Called by the model when User has lost the game.
	 * @param game Contains methods and data for the display to update.
	 */
	public void showUserLostGame(Game game);

}
