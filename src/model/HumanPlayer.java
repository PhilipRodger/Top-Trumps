package model;

import listeners.UserTurnListener;

public class HumanPlayer extends Player{

	UserTurnListener userTurnListener;
	
	public HumanPlayer(TopTrumpsModel model) {
		super(model);
		name = "User(You)";
	}	
	
	
	public void addUserTurnListener(UserTurnListener listner) {
		this.userTurnListener = listner;
	}

	@Override
	protected void showCard() {
		if(userTurnListener != null) {
			model.setResolveUserTurnPossible(true);
			userTurnListener.showUserTurn(currentCardDrawn);
		}	
	}
}
