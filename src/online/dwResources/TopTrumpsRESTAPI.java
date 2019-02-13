package online.dwResources;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.Collection;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import online.configuration.TopTrumpsJSONConfiguration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import model.*;
import controler.*;
import view.*;
import listeners.*;


@Path("/toptrumps") // Resources specified here should be hosted at http://localhost:7777/toptrumps
@Produces(MediaType.APPLICATION_JSON) // This resource returns JSON content
@Consumes(MediaType.APPLICATION_JSON) // This resource can take JSON content as input
/**
 * This is a Dropwizard Resource that specifies what to provide when a user
 * requests a particular URL. In this case, the URLs are associated to the
 * different REST API methods that you will need to expose the game commands
 * to the Web page.
 * 
 * Below are provided some sample methods that illustrate how to create
 * REST API methods in Dropwizard. You will need to replace these with
 * methods that allow a TopTrumps game to be controled from a Web page.
 */
public class TopTrumpsRESTAPI {

	/** A Jackson Object writer. It allows us to turn Java objects
	 * into JSON strings easily. */
	ObjectWriter oWriter = new ObjectMapper().writerWithDefaultPrettyPrinter();
	private String deckFile;
	private static final String DECK_LOCATION = "StarCitizenDeck.txt";
	private int numberofPlayers;
	private int currentRoundNumber = 0;
	private static Deck deck;
	private Player[] players;
	private CardPile communityPile;
	private Game game;
	
	
	/**
	 * Contructor method for the REST API. This is called first. It provides
	 * a TopTrumpsJSONConfiguration from which you can get the location of
	 * the deck file and the number of AI players.
	 * @param conf
	 */
	public TopTrumpsRESTAPI(TopTrumpsJSONConfiguration conf) {
		// ----------------------------------------------------
		// Add relevant initalization here
		// ----------------------------------------------------
		deckFile = conf.getDeckFile();
		numberofPlayers = conf.getNumAIPlayers() + 1;
		
	}
	
	
	// ----------------------------------------------------
	// Add relevant API methods here
	// ----------------------------------------------------
	
	@GET
	@Path("/helloJSONList")
	/**
	 * Here is an example of a simple REST get request that returns a String.
	 * We also illustrate here how we can convert Java objects to JSON strings.
	 * @return - List of words as JSON
	 * @throws IOException
	 */
	public String helloJSONList() throws IOException {
		
		List<String> listOfWords = new ArrayList<String>();
		listOfWords.add("Hello");
		listOfWords.add("World!");
		
		// We can turn arbatory Java objects directly into JSON strings using
		// Jackson seralization, assuming that the Java objects are not too complex.
		String listAsJSONString = oWriter.writeValueAsString(listOfWords);
		
		return listAsJSONString;
	}
	
	@GET
	@Path("/helloWord")
	/**
	 * Here is an example of how to read parameters provided in an HTML Get request.
	 * @param Word - A word
	 * @return - A String
	 * @throws IOException
	 */
	public String helloWord(@QueryParam("Word") String Word) throws IOException {
		return "Hello "+Word;	
		
	
	}
	@GET
	@Path("/startGame")
	/**
	 * Setting up the game when game launched
	 */
	public String startGame() throws IOException{
		game.startGame();
		return deckFile;
		
		
	}
	
	@GET
	@Path("/startARound")
	
	public void startARound() throws IOException{
		Game game = new Game(currentRoundNumber, null);
	    game.startRound();
		
		
		
	}
	@GET
	@Path("/getRoundNumber")
	public String getRoundNumber() throws IOException {
		return ""+currentRoundNumber;
	}
	
	

	@GET
	@Path("/getDeck")
	// Method that will return deck 
	public Deck getDeck()throws IOException{
		Deck deck = new Deck (deckFile);
		
		deck.getShuffledDeck();
		return deck;
	}
	
	
	@GET
	@Path("/getPlayers")
	
	public Player[] getPlayers() throws IOException{
		return players;
	}
	
	
	
	
	@GET
	@Path("/dealCards")
	
	
	private String dealDeck() {
		Deck deck1 = new Deck (deckFile);
		CardPile shuffled = deck1.getShuffledDeck();

		while (shuffled.hasNextCard()) {
			for (Player player : players) {
				if (shuffled.hasNextCard()) {
					player.addCardToBottomOfPile(shuffled.drawCard());
				}
			}
		}
		return null ;
	}
	
	
	
	
	@GET
	@Path("/getRoundWinner")
	
	public Player getRoundWinner() {
		Round round = new Round (null);
		return round.getRoundWinner();
	}
	
	
	
	@GET
	@Path("/gameOver")
	
	public boolean gameOver () {
	
		return game.gameOver();
	}
	
	
	
	@GET
	@Path("/userChoice")
	
	public void userChoice () {
		
		game.userSelection(game.setCategoryChoice(categoryChoice));
	}
	
	
	
	public void setCategoryChoice(int categoryChoice) {
		
		Round round = new Round (null);
		round.setCategoryChoice(categoryChoice);
	}
	
	public int getIndexOfPlayer(Player p) {
		for (int i = 0; i < players.length; i++) {
			if (p == players[i]) {
				return i;
			}
		}
		return -1;
	}
	
	private void createPlayers(int numOfPlayers) {
		// initialised players first player is the human the rest are AI

		players = new Player[numOfPlayers];

		// Make first player the human player.
		if (numOfPlayers > 0) {
			players[0] = new HumanPlayer(null);
		}

		// Make the rest Computer controlled players.
		for (int i = 1; i < numOfPlayers; i++) {
			players[i] = new ComputerPlayer(null);
		}
		
	}
	
	
	///******** Database API methods ********///
	@GET
	@Path("/getTotalGames")
	public String getTotalGames() throws IOException {
		
		int games = 6;
		return ""+games;
	}
	
	@GET
	@Path("/getCompWins")
	public String getCompWins() throws IOException {
		
		int AIwins = 7;
		return ""+AIwins;
	}
	
	@GET
	@Path("/getHumanWins")
	public String getHumanWins() throws IOException {
		
		int humanWins = 8;
		return ""+humanWins;
	}
	
	@GET
	@Path("/getAveDraws")
	public String getAveDraws() throws IOException {
		
		double drawAvg = 9;
		return ""+drawAvg;
	}
	
	@GET
	@Path("/getBigRound")
	public String getBigRound() throws IOException {
		
		int roundMax = 10;
		return ""+roundMax;
	}
	
	@GET
	@Path("/wipeDatabase")
	public void wipeDatabase() throws IOException {
		
	}
	

}

	
	


	
