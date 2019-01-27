package model;

import listeners.UserTurnListener;

public class HumanPlayer extends Player{

	UserTurnListener userTurnListener;
	
	public HumanPlayer(TopTrumpsModel model) {
		super(model);
	}	
	
	
	public void addUserTurnListener(UserTurnListener listner) {
		this.userTurnListener = listner;
	}

	@Override
	protected void showCard() {
		if(userTurnListener != null) {
			model.setResolveUserTurnPossible(true);
			userTurnListener.showUserTurn();
		}	
	}
}
