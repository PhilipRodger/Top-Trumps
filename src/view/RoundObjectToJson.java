package view;

/**
 * The reason for this class is that during development the round objects and
 * player objects were too complex to convert to JSON directly, instead this
 * simple class if filled with state of the game to be transmitted via the
 * RESTAPI
 */
public class RoundObjectToJson {
	// Game Data
	public boolean gameFinished;
	public int gameWinner;
	public String gameWinnerString;
	public int communityPileSize;

	// Round Data
	public boolean roundHasBeenResolved;
	public int roundNumber;
	public int playersTurnIndex;
	public String playersTurnName;
	public String chosenCategory;

	// Only filled up when Round has resolved
	public int roundWinnerIndex; // -1 if draw
	public String roundWinnerString;

	// Player Data
	public PlayerToJson playersToJson[];
}
