package model;

public class HumanPlayer extends Player{
	RequestDecision request = null;

	@Override
	public int chooseCatagory(Card c) {
		if (request != null) {
			return request.makeDecision(c);
		}
		return 0;
	}
	
	public void setRequestDecision(RequestDecision request) {
		this.request = request;
	}
}
