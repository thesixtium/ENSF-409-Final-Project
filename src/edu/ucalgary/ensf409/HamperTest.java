/**
 * @author Danielle Jourdain
 * @version 1.10
 * @since 1.0
 */

package edu.ucalgary.ensf409;

import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;

/* NOTE: PLEASE RESET THE DATABASE BEFORE RUNNING THESE TESTS */

public class HamperTest {
    /**
     * This tests if the constructor creates a Hamper object as needed
     */
    @Test 
    public void testConstructor() throws NotEnoughFoodException {
        HouseholdNeeds hn = initHouseholdNeeds();
        HashMap<Integer, FoodData> availableFood = updateFoodValues();
        Hamper hamper = new Hamper(hn, availableFood);

        assertNotNull("Hamper constructor did not create an object", hamper);
    }

    /**
     * This tests if the getWaste method returns a HashMap of the correct size
     */
    @Test
    public void testGetWaste() throws NotEnoughFoodException {
        HouseholdNeeds hn = initHouseholdNeeds();
        HashMap<Integer, FoodData> availableFood = updateFoodValues();
        Hamper hamper = new Hamper(hn, availableFood);
        HashMap<String, Integer> waste = hamper.getWaste();

        assertNotNull("getWaste did not return an object", waste);
        assertEquals("getWaste did not create a HashMap of the right size", 4, waste.size());
    }

    /**
     * This tests if the getFoodQuantities method returns an object.
     * The specific values cannot be tested since they will change depeding on what
     * order the tests are run, and how recently the database was reset.
     */
    @Test
    public void testGetFoodQuantities() throws NotEnoughFoodException {
        HouseholdNeeds hn = initHouseholdNeeds();
        HashMap<Integer, FoodData> availableFood = updateFoodValues();
        Hamper hamper = new Hamper(hn, availableFood);
        HashMap<Integer, FoodData> foods = hamper.getFoodQuantities();

        assertNotNull("getFoodQuantities did not return an object", foods);
    }

    /**
     * This tests if the getCalorieNeeds method returns the expected HouseholdNeeds object.
     */
    @Test
    public void testGetCalorieNeeds() throws NotEnoughFoodException {
        HouseholdNeeds hn = initHouseholdNeeds();
        HashMap<Integer, FoodData> availableFood = updateFoodValues();
        Hamper hamper = new Hamper(hn, availableFood);
        HouseholdNeeds actual = hamper.getCalorieNeeds();

        assertEquals("getCalorieNeeds did not return the expected object", hn, actual);
    }

    /**
     * This tests if the boolean from getEnoughFood is true when there is enough food
     * to create a hamper
     */
    @Test   
    public void testGetEnoughFood() throws NotEnoughFoodException {
        HouseholdNeeds hn = initHouseholdNeeds();
        HashMap<Integer, FoodData> availableFood = updateFoodValues();
        Hamper hamper = new Hamper(hn, availableFood);
        boolean result = hamper.getEnoughFood();

        assertTrue("getEnoughFood did not return true", result);
    }

    /**
     * This tests that the shortBy field is null when there is enough food
     * to create a hamper.
     */
    @Test
    public void testGetShortBy() throws NotEnoughFoodException {
        HouseholdNeeds hn = initHouseholdNeeds();
        HashMap<Integer, FoodData> availableFood = updateFoodValues();
        Hamper hamper = new Hamper(hn, availableFood);
        FoodData shortBy = hamper.getShortBy();

        assertNull("getShortBy returned an object even though there was enough food", shortBy);
    }

    /**
     * This tests that the getAvailableFoods method returns a HashMap. Again, the specifics cannot
     * be tested since values are updated after every Hamper creation.
     */
    @Test
    public void testGetAvailableFoods() throws NotEnoughFoodException {
        HouseholdNeeds hn = initHouseholdNeeds();
        HashMap<Integer, FoodData> availableFood = updateFoodValues();
        Hamper hamper = new Hamper(hn, availableFood);
        HashMap<Integer, FoodData> actual = hamper.getAvailableFoods();
        availableFood = updateFoodValues();

        assertNotNull("getAvailableFoods did not return an object", actual);
    }

    /**
     * Helper method to set up a database connection.
     */
    @BeforeClass
    public static void setupDatabase() {
        RequestFormDatabase database = new RequestFormDatabase("jdbc:mysql://localhost/FOOD_INVENTORY","student","ensf" );
        database.initializeConnection();
		database.setFoodValues();
		database.setClientValues();
		database.close();
    }

    /**
     * Helper method to update the stored food values after being used
     * @return
     */
    private HashMap<Integer, FoodData> updateFoodValues() {
        RequestFormDatabase requestForm = new RequestFormDatabase("jdbc:mysql://localhost/FOOD_INVENTORY","student","ensf" );
		requestForm.initializeConnection();
		requestForm.setFoodValues();
		requestForm.setClientValues();

        return RequestFormDatabase.getFoodValues();
    }
    
    /**
     * Helper method to set the HouseholdNeeds
     */
    public static HouseholdNeeds initHouseholdNeeds() {
        HouseholdNeeds hn = new HouseholdNeeds();
        hn.changeFvCalories(100);
        hn.changeGrainCalories(100);
        hn.changeProteinCalories(100);
        hn.changeOtherCalories(100);
        return hn;
    }
}
