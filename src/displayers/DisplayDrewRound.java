package displayers;

import model.Round;

public interface DisplayDrewRound {
	/**
	 * To be implemented and attached to the model via the view. This is called when
	 * the model has determined that the round has resulted in a draw.
	 * 
	 * @param currentRound contains methods and data for the display to update.
	 */
	public void showUserDrewRound(Round currentRound);
}
