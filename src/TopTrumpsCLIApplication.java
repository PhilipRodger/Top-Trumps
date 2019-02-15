

import controler.TopTrumpsContoller;
import model.LogFile;
import model.TopTrumpsModel;
import view.CommandLineView;

/**
 * Top Trumps command line application
 */
public class TopTrumpsCLIApplication {
	final static int numberOfPlayers = 5;

	/**
	 * This main method is called by TopTrumps.java when the user specifies that they want to run in
	 * command line mode. The contents of args[0] is whether we should write game logs to a file.
 	 * @param args
	 */
	public static void main(String[] args) {

		boolean writeGameLogsToFile = false; // Should we write game logs to file?
		if (args[0].equalsIgnoreCase("true")) writeGameLogsToFile=true; // Command line selection
		
		TopTrumpsModel model = new TopTrumpsModel(writeGameLogsToFile);
		CommandLineView view = new CommandLineView(model);
		new TopTrumpsContoller(model, view);
		view.run();
		}
}
