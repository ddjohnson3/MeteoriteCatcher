import static org.junit.Assert.assertTrue; 
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.List;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

public class FrontendDeveloperTests {
    
    private IterableMultiKeySortedCollectionInterface redBlackTree;
    private FrontendInterface frontend;
    private Backend backend;
    //private BackendPlaceHolder backendPlaceHolder;
       
    public FrontendDeveloperTests() {
	
	redBlackTree = new IterableMultiKeyRBT<MeteoriteObjectInterface>();
	this.backend = new Backend(redBlackTree);
	//this.backendPlaceHolder = new BackendPlaceHolder(redBlackTree);
	this.frontend = new Frontend(backend, new Scanner(System.in));
    }
    


    
     
    @Test
    public void testLoadMeteoritesInRangeMenu() {
        TextUITester tester = new TextUITester("5\n10\n");
        frontend.loadMeteoritesInRangeMenu();
        tester.run();
        String output = tester.checkOutput();
        assertTrue(output.contains("please enter your lower bound") && output.contains("please enter your upper bound"));
    }
    
    
    @Test
    public void testUserIntInputInvalidInput() {
        TextUITester tester = new TextUITester("11");
        tester.run();
        int code = frontend.validateUserIntInput(8, 10);
        String output = tester.checkOutput();

        assertTrue(output.contains("Your integer is not within the range: [8, 10]"));
    }

    @Test
    public void testUserIntInputValidInput() {
        TextUITester tester = new TextUITester("9");
        tester.run();
        int test= frontend.validateUserIntInput(8, 10);

        String output = tester.checkOutput();
        
        assertTrue(output.contains("Welcome to the run program.") && output.contains("9"));
    }

    @Test
    public void testUserDoubleInputInvalidInput() {
        TextUITester tester = new TextUITester("11");
        
        frontend.validateUserDoubleInput(1.0, 10.0);
        tester.run();
        String output = tester.checkOutput();

        assertTrue(output.contains("Your input was not a double"));
    }

   
    @Test
    public void testLoadDataFileMenu() throws IOException {
        TextUITester tester = new TextUITester("meteorites.csv\n1\n");
        frontend.loadDataFileMenu();
        tester.run();
        
        String output = tester.checkOutput();
        assertTrue(output.contains("enter the name of the data file") && output.contains("no file found"));
    }
    
    
    
        
    ///Integration tests///
    
    @Test
    public void testLoadDataFileMenuIntegration() {
        TextUITester tester = new TextUITester("meteorites.csv\n1\n");
        frontend.loadDataFileMenu();
        tester.run();
        String output = tester.checkOutput();
        assertTrue(output.contains("fileFound"));
    }

    @Test
    public void testLoadMeteoritesInRangeMenuIntegration() {
        TextUITester tester = new TextUITester("5\n10\n");
        frontend.loadMeteoritesInRangeMenu();
        tester.run();
        String output = tester.checkOutput();
        assertTrue(output.contains("please enter your lower bound") && output.contains("please enter your upper bound"));
    }

    
    //Partners Backend Tests
    

    @Test
    public void testGetMaxMassMeteorites() {	
        IterableMultiKeySortedCollectionInterface<MeteoriteObjectInterface> redBlackTree = new IterableMultiKeyRBT<>();
        BackendInterface backend = new Backend(redBlackTree);
        backend.readFile("meteorites.csv");
        List<MeteoriteObjectInterface> result = backend.getMaxMassMeteorites();
        assertEquals(1, result.size());
    }

}
