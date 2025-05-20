package kanji.controller;

/**
 * Entry point for the Kanji application.
 * Initializes the Controller and starts the application.
 */
public class Runner
{
	public static void main (String [] args)
	{
		Controller app = new Controller();
		app.start();
	}
}
