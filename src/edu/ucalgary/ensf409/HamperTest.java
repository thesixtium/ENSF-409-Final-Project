/**
 * @author Danielle Jourdain
 * @version 1.6
 * @since 1.0
 */

package edu.ucalgary.ensf409;

import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;

public class HamperTest {
    HouseholdNeeds needs;
    HashMap<Integer, FoodData> availableFoods;

    /**
     * Tests that the constructor creates an object
     */
    @Test
    public void testConstructor() {
        Hamper hamper = new Hamper(needs, availableFoods);

        assertNotNull("Hamper constructor did not create an object.", hamper);
    }

    /**
     * Tests that the Hamper containts the correct food item when called with
     * enough food for the HouseholdNeeds
     */
    @Test 
    public void testGetFoodQuantities() {
        Hamper hamper = new Hamper(needs, availableFoods);
        HashMap<Integer, FoodData> result = hamper.getFoodQuantities();

        HashMap<Integer, FoodData> expected = new HashMap<>();
        FoodData pizza = new FoodData("Frozen pizza, pepperoni", 680, 49, 219, 267);
        expected.put(68, pizza);

        assertEquals("Hamper did not contain the correct number of items.", 1, result.size());
        assertEquals("Hamper did not contain expected food values", expected, result);
    }

    /**
     * Tests that the getWaste method returns the correct result
     * when there is enough food.
     */
    @Test   
    public void testGetWaste() {
        Hamper hamper = new Hamper(needs, availableFoods);
        HashMap<String, Integer> result = hamper.getWaste();

        boolean correct = true;
        for(Integer i: result.values()) {
            if(i != 0) {
                correct = false;
            }
        }

        assertTrue("getWaste did not return the correct waste values.", correct);
    }

    /**
     * tests that the getCalorieNeeds method returns the same HouseholdNeeds object
     * that was stored when the hamper was created.
     */
    @Test   
    public void testGetCalorieNeeds() {
        Hamper hamper = new Hamper(needs, availableFoods);
        HouseholdNeeds actual = hamper.getCalorieNeeds();

        assertEquals("getCalorieNeeds did not return the correct result.", this.needs, actual);
    }

    /**
     * This test checks the value of the getShortBy method when there
     * is enough food.
     */
    @Test   
    public void testGetShortByEnoughFood() {
        Hamper hamper = new Hamper(needs, availableFoods);
        FoodData actual = hamper.getShortBy();

        FoodData expected = new FoodData("Short By");
        assertEquals("getShortBy did not return correct result", expected, actual);
    }

    /**
     * This tests the value of the getShortBy method when there is not 
     * enough food.
     */
    @Test  
    public void testGetShortByNotEnoughFood() {
        availableFoods.clear();
        FoodData shortItem = new FoodData("Short pizza", 600, 49, 219, 267);
        availableFoods.put(1, shortItem);

        Hamper hamper = new Hamper(needs, availableFoods);

        FoodData actual = hamper.getShortBy();

        FoodData expected = new FoodData("Short By", 680, 49, 219, 267);
        assertEquals("getShortBy did not return correct result", expected, actual);
    }

    /**
     * Tests that the available foods from the hamper matches the
     * values that were given upon constructing the object
     */
    @Test 
    public void testGetAvailableFoods() {
        Hamper hamper = new Hamper(needs, availableFoods);
        HashMap<Integer, FoodData> actual = hamper.getAvailableFoods();

        assertEquals("getAvailableFoods did not return correct result", this.availableFoods, actual);
    }

    /**
     * Tests that the boolean shortBy returns the correct value
     * when there is enough food.
     */
    @Test 
    public void testGetEnoughFoodGood() {
        Hamper hamper = new Hamper(needs, availableFoods);
        boolean result = hamper.getEnoughFood();
        assertTrue("getEnoughFood did not return correct result", result);
    }

    /**
     * tests that the boolean shortBy has the right value when there is
     * not enough food.
     */
    @Test 
    public void testGetEnoughFoodBad() {
        availableFoods.clear();

        Hamper hamper = new Hamper(needs, availableFoods);
        boolean result = hamper.getEnoughFood();
        assertFalse("getEnoughFood did not return correct result", result);
    }

    /**
     * Helper method to setup connection to the database before running tests.
     */
    @BeforeClass
    public static void setupDatabase() {
        RequestFormDatabase database = new RequestFormDatabase("jdbc:mysql://localhost/FOOD_INVENTORY","student","ensf" );
        database.initializeConnection();
		database.setFoodValues();
		database.setClientValues();
		database.close();
    }

    @Before
    public void setup() {
        this.availableFoods = RequestFormDatabase.getFoodValues();
        this.needs = new HouseholdNeeds();
        this.needs.changeFvCalories(680);
        this.needs.changeGrainCalories(49);
        this.needs.changeProteinCalories(219);
        this.needs.changeOtherCalories(267);
        //setup the HouseholdNeeds obejct 
        //these calorie needs line up exactly to item 68 in the database
            //to simplify testing
    }

    /*
    private int[] calorieValues = {100, 200, 300, 400};
    
    /*
     * Hamper(int[]) is called to create a Hamper object.
     
    @Test
    public void testConstructor() {
        Hamper test = new Hamper(calorieValues);

        assertNotNull(test, "Hamper constructor did not correctly create a Hamper obejct.");
    }

    /**
     * Test that the getCalorieNeeds method returns an integer array
     
    @Test
    public void testGetFoodQuantities() {
        Hamper test = new Hamper(calorieValues);
        int[] result = test.getCalorieNeeds();

        assertNotNull(result, "getCalorieNeeds did not return a value.");
    }

    /**
     * Test that getWaste method returns an integer array
     
    @Test
    public void testGetWaste() {
        Hamper test = new Hamper(calorieValues);
        int[] result = test.getWaste();

        assertNotNull(result, "getCalorieNeeds did not return a value.");
    }

    /**
     * Test that getFoodQuantities returns an ArrayList of integers
     
    @Test
    public void testGetCalorieNeeds() {
        Hamper test = new Hamper(calorieValues);
        ArrayList<Integer> result = test.getFoodQuantities();

        assertNotNull(result, "getCalorieNeeds did not return a value.");
    }
    */
}
