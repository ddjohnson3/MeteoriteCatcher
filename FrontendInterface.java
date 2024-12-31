import java.util.Scanner;

public interface FrontendInterface {
	
	
	public int validateUserIntInput(int min, int max);
	
	/**
	 * Contains the scanner scan line
	 * input must start at 1, or 0.
	 * Checks for doubles
	 * @param min minimum integer from the menu, must be
	 * @param max maximum integer from the menu
	 * @return If valid, it returns the user's input which can be cast to the correct data type
	 * If invalid, provides specific information about what went wrong
	 */
	public double validateUserDoubleInput(double min, double max);
	
	/**
	 * Starts printing the menu's of the command loop to the console
	 */
	public void startCommandLoop();
	
	/**
	 * Creates the main menu of the command loop.
	 */
	public void mainMenu();
	
	/**
	 * Loads the sub-menu for select a data file
	 */
	public void loadDataFileMenu();
	
	/**
	 * Loads the sub-menu for selecting the highest mass meteorites.
	 */
	public void loadHighestMassMeteoritesMenu();
	
	/**
	 * Loads the sub-menu for selecting meteorites in a specified range. 
	 */
	public void loadMeteoritesInRangeMenu();
	
	/**
	 * Terminates the command loop
	 */
	public void exitApp();	
}
