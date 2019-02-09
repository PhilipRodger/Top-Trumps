package displayers;

import model.Round;

public interface DisplayUserWonRound {
	/**
	 * To be implemented and attached to the model via the view. Called by the model
	 * when the user has won a round.
	 * 
	 * @param currentRound Contains methods and data for the display to update.
	 */
	public void showUserWonRound(Round currentRound);

}
