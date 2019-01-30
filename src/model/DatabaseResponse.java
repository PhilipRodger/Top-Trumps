package model;

public class DatabaseResponse {
	private int totalGamesPlayed;
	private int totalComputerWins;
	private int totalHumanWins;
	private int averageDrawsPerGame;
	private int largestNumberOfRounds;

	public int getTotalGamesPlayed() {
		return totalGamesPlayed;
	}

	public void setTotalGamesPlayed(int totalGamesPlayed) {
		this.totalGamesPlayed = totalGamesPlayed;
	}

	public int getTotalComputerWins() {
		return totalComputerWins;
	}

	public void setTotalComputerWins(int totalComputerWins) {
		this.totalComputerWins = totalComputerWins;
	}

	public int getTotalHumanWins() {
		return totalHumanWins;
	}

	public void setTotalHumanWins(int totalHumanWins) {
		this.totalHumanWins = totalHumanWins;
	}

	public int getAverageDrawsPerGame() {
		return averageDrawsPerGame;
	}

	public void setAverageDrawsPerGame(int averageDrawsPerGame) {
		this.averageDrawsPerGame = averageDrawsPerGame;
	}

	public int getLargestNumberOfRounds() {
		return largestNumberOfRounds;
	}

	public void setLargestNumberOfRounds(int largestNumberOfRounds) {
		this.largestNumberOfRounds = largestNumberOfRounds;
	}

	public String toString() {
		return "Total games played= " + totalGamesPlayed + ", Total Computer Wins= " + totalComputerWins
				+ ", Total Human Wins= " + totalHumanWins + ", Acerage Draws per Game = " + averageDrawsPerGame
				+ ", Largest Number of Rounds = " + largestNumberOfRounds + "]";
	}

}
