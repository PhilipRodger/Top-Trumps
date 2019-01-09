package online.dwResources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import online.dwViews.GameScreenView;
import online.dwViews.SelectionScreenView;
import online.dwViews.StatisticsView;

@Path("/toptrumps") // Resources specified here should be hosted at http://localhost:7777/toptrumps
@Produces(MediaType.TEXT_HTML) // This resource returns HTML content

/**
 * This is a Dropwizard Resource that specifies what to provide when a user
 * requests a particular URL. In this case, the URLs are associated to the
 * different HTML/Javascript Web pages for the TopTrumps Application. 
 * @author richardm
 *
 */
public class GameWebPagesResource {

	@GET
	@Path("/")
	/**
	 * The selection screen for choosing whether to see past game statistics or
	 * play a game. Hosted at 'http://localhost:7777/toptrumps/'
	 * @return
	 */
    public SelectionScreenView getSelectionScreen() {
        return new SelectionScreenView();
    }
	
	@GET
	@Path("/game")
	/**
	 * The Web page within which the user can play a game of TopTrumps.
	 * Hosted at 'http://localhost:7777/toptrumps/game'
	 * @return
	 */
    public GameScreenView getGameScreen() {
        return new GameScreenView();
    }
	
	@GET
	@Path("/stats")
	/**
	 * The Web page that shows past game statistics
	 * Hosted at 'http://localhost:7777/toptrumps/stats'
	 * @return
	 */
    public StatisticsView getStatisticsView() {
        return new StatisticsView();
    }
	
}
