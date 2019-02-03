package model;

import displayers.DisplayUserTurn;

public class HumanPlayer extends Player{

	DisplayUserTurn userTurnListener;
	
	public HumanPlayer(Game game) {
		super(game);
		name = "User(You)";
	}	
	
	
	public void addDisplayUserTurn(DisplayUserTurn listner) {
		this.userTurnListener = listner;
	}

	@Override
	protected void showCard(Round currentRound) {
		if(userTurnListener != null) {
			game.setResolveUserTurnPossible(true);
			userTurnListener.showUserTurn(currentRound);
		}	
	}
}
