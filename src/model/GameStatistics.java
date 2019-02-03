package model;

public class GameStatistics {

	private int numOfDraws;
	private Player winner;
	private int numOfRounds;
	private Player[] players;
	
	public GameStatistics(Player[] players) {
		numOfDraws = 0;
		winner = null;
		numOfRounds = 0;
		this.players = players;
	}
	
	public int getNumOfDraws() {
		return numOfDraws;
	}
	public void incrementDrawCounter() {
		numOfDraws++;
	}
	

	public Player[] getPlayerArray() {
		return players;
	}
	

	public Player getWinner() {
		return winner;
	}
	public void setWinner(Player winner) {
		this.winner = winner;
	}
	
	public int getNumOfRounds() {
		return numOfRounds;
	}
	public void incrementRoundCounter() {
		numOfRounds++;
	}

}
