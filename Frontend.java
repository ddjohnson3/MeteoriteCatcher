
import java.io.IOException;
import java.util.List;
import java.util.Scanner;


public class Frontend implements FrontendInterface {
    private boolean commandLoopRunning = false;
    private BackendInterface backend;
    private Scanner scanner;

    public Frontend(BackendInterface backend, Scanner scanner) {
        this.backend = backend;
        this.scanner = scanner;
    }

    public int validateUserIntInput(int min, int max) {
        while (true) {
            String input = scanner.nextLine().trim();
            try {
                int validInt = Integer.parseInt(input);
                if (min <= validInt && validInt <= max) {
                    return validInt;
                }
                System.out.println("Please enter a number between " + min + " and " + max);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid integer");
            }
        }
    }

    public double validateUserDoubleInput(double min, double max) {
        while (true) {
            String input = scanner.nextLine().trim();
            try {
                double validDouble = Double.parseDouble(input);
                if (min <= validDouble && validDouble <= max) {
                    return validDouble;
                }
                System.out.println("Please enter a number between " + min + " and " + max);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number");
            }
        }
    }

    public void startCommandLoop() {
        this.commandLoopRunning = true;
        while (this.commandLoopRunning) {
            mainMenu();
        }
    }

    public void mainMenu() {
        System.out.println("\nMeteorite Analysis Menu");
        System.out.println("1: Load Data File");
        System.out.println("2: Find Highest Mass Meteorites");
        System.out.println("3: Find Meteors by Mass");
        System.out.println("4: Exit");
        
        int input = validateUserIntInput(1, 4);
        switch (input) {
            case 1 -> loadDataFileMenu();
            case 2 -> loadHighestMassMeteoritesMenu();
            case 3 -> loadMeteoritesInRangeMenu();
            case 4 -> exitApp();
        }
    }

    public void loadHighestMassMeteoritesMenu() {
        System.out.println("\nHighest Mass Meteorites:");
        List<MeteoriteObjectInterface> meteorites = backend.getMaxMassMeteorites();
        
        if (meteorites == null || meteorites.isEmpty()) {
            System.out.println("No meteorites found. Please load data first.");
        } else {
            for (MeteoriteObjectInterface meteorite : meteorites) {
                System.out.printf("%s: %.2f kg%n", meteorite.getName(), meteorite.getMass());
            }
        }
        
        System.out.println("\nEnter 1 to return to main menu");
        validateUserIntInput(1, 1);
    }

    public void loadDataFileMenu() {
        System.out.println("\nEnter the name of the data file:");
        String filename = scanner.nextLine().trim();
        
        try {
            backend.readFile(filename);
            System.out.println("File '" + filename + "' loaded successfully");
        } catch (IOException e) {
            System.out.println("Error loading file: " + e.getMessage());
        }
        
        System.out.println("\nEnter 1 to return to main menu");
        validateUserIntInput(1, 1);
    }

    public void loadMeteoritesInRangeMenu() {
        System.out.println("\nEnter lower bound mass (kg):");
        double lower = validateUserDoubleInput(0.0, Double.MAX_VALUE);
        
        System.out.println("Enter upper bound mass (kg):");
        double upper = validateUserDoubleInput(lower, Double.MAX_VALUE);

        List<MeteoriteObjectInterface> meteorites = backend.getMeteoritesBetween(lower, upper);
        
        if (meteorites == null || meteorites.isEmpty()) {
            System.out.println("No meteorites found in this range");
        } else {
            System.out.printf("\nMeteorites between %.2f and %.2f kg:%n", lower, upper);
            for (MeteoriteObjectInterface meteorite : meteorites) {
                System.out.printf("%s: %.2f kg%n", meteorite.getName(), meteorite.getMass());
            }
        }
        
        System.out.println("\nEnter 1 to return to main menu");
        validateUserIntInput(1, 1);
    }

    @Override
    public void exitApp() {
        this.commandLoopRunning = false;
    }
}


