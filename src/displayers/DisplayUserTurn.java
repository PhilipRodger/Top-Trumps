package displayers;

import model.Round;

public interface DisplayUserTurn {
	/**
	 * To be implemented and attached to the model via the view. Displays the drawn
	 * card to a user and prompts them to select a category.
	 * 
	 * @param currentRound contains methods and data for the display to update.
	 */
	public void showUserTurn(Round currentRound);

}
