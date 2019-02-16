package online.dwResources;
import displayers.*;
import model.*;
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

import com.fasterxml.jackson.core.JsonProcessingException;
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
	private static final String DECK_LOCATION = "StarCitizenDeck.txt";
	private int currentRoundNumber = 0;
	private int numOfPlayers;
	private static Deck deck;
	private Player play;
	private Player[] players;
	private GameStatistics stats;
	private Game game;
	private Database db;
	private Round round;
	private TopTrumpsModel model;
	private CommandLineView command;
	private Card card;
	
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
	
	public void startGame() { 
		game.startGame();
	
	}
	
	@GET
	@Path("/startRound")
	
	public void startRound() throws IOException{
	    game.startRound();
	      
	}
		
	@GET
	@Path("/resolveRound")	
	public void resolveRound() throws IOException{
		round.resolveRound();
	}
	
	@GET
	@Path("/getRoundNumber")
	public String getRoundNumber() throws IOException {
		
		String roundNumber = oWriter.writeValueAsString (stats.getNumOfRounds());
		return roundNumber;
		
	}
	
	

	@GET
	@Path("/getDeck")
	// Method that will return deck 
	public Deck getDeck()throws IOException{
		deck.getShuffledDeck();
		return deck;
	}
	
	
	@GET
	@Path("/getPlayers")
	
	public Player[] getPlayers() throws IOException{
		return players;
		
		
		
	}
	
	
	@GET
	@Path("/activePlayer")
	public String activePlayer () throws IOException{
		String activePlayer = oWriter.writeValueAsString(game.getPlayersTurn());
		return activePlayer;
	
	}
	
	@GET
	@Path("/getRoundWinner")
	
	public String getRoundWinner() throws IOException {
		String roundwinner = oWriter.writeValueAsString(round.getRoundWinner());
		
		return roundwinner;
		
	}
	
	
	@GET
	@Path("communityPileSize/")
	
	public String communityPileSize () throws IOException {
		
		String comPileSize = oWriter.writeValueAsString (game.getCommunityPile().size());
		
		return comPileSize;
	
	
	}
	
	
	@GET
	@Path("getCardName/")
	
	public String getCardName () throws IOException {
		
		String cardName = oWriter.writeValueAsString (card.getName());
		
		
		return cardName;
		
		
		
		
	}
	
	
	
	@GET
	@Path("categorySelection/")
	
	public String categorySelection () throws IOException {
		
		String categorySelection = oWriter.writeValueAsString (round.getChosenCategory());
		
		return categorySelection;
	}
	
	@GET
	@Path("createPlayers/")
	
	public void createPlayers () {
		
		game.createPlayers(numOfPlayers);
	}
	
	@GET
	@Path("numOfPlayers/")
	public String numOfPlayers() throws IOException {
		return oWriter.writeValueAsString(players.length);
	
	}
	
	@GET
	@Path("getplayerName/")
	
	public String playerName () throws IOException {
	
		String pname = oWriter.writeValueAsString(play.getName());
		
		return pname;
	}
	
	@GET
	@Path("getCardValues/")
	
	public String getCardValues (int i) throws IOException  {
		
		String cardvals = oWriter.writeValueAsString (card.getValue(i));
		
		return cardvals;
	}
	
	@GET
	@Path("getCategoryNames/")
	
	public String categoryNames () throws IOException {
		
		String catName = oWriter.writeValueAsString (Card.getCategories());
		return catName;
		
		
	}	
	
	
	///***** relevant methods ******///
	
	
	
	///******** Database API methods ********///
	@GET

	@Path("/totalGames")
	/**
	 * Get total games played from the database
	 * @return total games played
	 * @throws IOException
	 */
	public String totalGames() throws IOException {
		Database db = new Database();
		db.connectToDB();
		DatabaseResponse response = db.getDatabaseStats();
		int totalGames = response.getTotalGamesPlayed();
		String stringTotalGames = String.valueOf(totalGames);
		String asJSONString = oWriter.writeValueAsString(stringTotalGames);
		db.disconnectDB();
		return asJSONString;
	}
	
	@GET
	@Path("/humanWins")
	/**
	 * Get total games played from the database
	 * @return total games played
	 * @throws IOException
	 */
	public String humanWins() throws IOException {
		Database db = new Database();
		db.connectToDB();
		DatabaseResponse response = db.getDatabaseStats();
		int humanWins = response.getTotalHumanWins();
		String stringHumanWins = String.valueOf(humanWins);
		String asJSONString = oWriter.writeValueAsString(stringHumanWins);
		db.disconnectDB();
		return asJSONString;
	}
	
	@GET
	@Path("/AIWins")
	/**
	 * Get total games played from the database
	 * @return total games played
	 * @throws IOException
	 */
	public String AIWins() throws IOException {
		Database db = new Database();
		db.connectToDB();
		DatabaseResponse response = db.getDatabaseStats();
		int aiWins = response.getTotalComputerWins();
		String stringAIWins = String.valueOf(aiWins);
		String asJSONString = oWriter.writeValueAsString(stringAIWins);
		db.disconnectDB();
		return asJSONString;
	}
	
	@GET
	@Path("/averageDraws")
	/**
	 * Get average draws per game played from the database
	 * @return total games played
	 * @throws IOException
	 */
	public String averageDraws() throws IOException {
		Database db = new Database();
		db.connectToDB();
		DatabaseResponse response = db.getDatabaseStats();
		int averageDraws = response.getAverageDrawsPerGame();
		String stringAverageDraws = String.valueOf(averageDraws);
		String asJSONString = oWriter.writeValueAsString(stringAverageDraws);
		db.disconnectDB();
		return asJSONString;
	}
	
	
	
	@GET
	@Path("/longestGame")
	/**
	 * Get total games played from the database
	 * @return total games played
	 * @throws IOException
	 */
	public String longestGame() throws IOException {
		Database db = new Database();
		db.connectToDB();
		DatabaseResponse response = db.getDatabaseStats();
		int longestGame = response.getLargestNumberOfRounds();
		String stringLongestGame = String.valueOf(longestGame);
		String asJSONString = oWriter.writeValueAsString(stringLongestGame);
		db.disconnectDB();
		return asJSONString;
	}
	
	
	
}

	
	


	
