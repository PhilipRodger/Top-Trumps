package view;

public class RoundObjectToJson {
	// Game Data
	public boolean gameFinished;
	public int gameWinner;
	public int communityPileSize;
	
	//Round Data
	public boolean roundHasBeenResolved;
	public int roundNumber;
	public int playersTurnIndex;
	public String playersTurnName; 
	public String chosenCategory;
	
	//Only filled up when Round has resolved
	public int roundWinnerIndex; // -1 if draw
	public String roundWinnerString;

	
	// Player Data
	public PlayerToJson playersToJson[];
}
