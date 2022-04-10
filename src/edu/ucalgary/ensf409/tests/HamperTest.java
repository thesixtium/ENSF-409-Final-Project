/**
 * @author Danielle Jourdain
 * @version 1.6
 * @since 1.0
 */

package edu.ucalgary.ensf409;

import org.junit.*;
import static org.junit.Assert.*;
import java.util.ArrayList;

public class HamperTest {
    private int[] calorieValues = {100, 200, 300, 400};
    
    /**
     * Hamper(int[]) is called to create a Hamper object.
     */
    @Test
    public void testConstructor() {
        Hamper test = new Hamper(calorieValues);

        assertNotNull(test, "Hamper constructor did not correctly create a Hamper obejct.");
    }

    /**
     * Test that the getCalorieNeeds method returns an integer array
     */
    @Test
    public void testGetFoodQuantities() {
        Hamper test = new Hamper(calorieValues);
        int[] result = test.getCalorieNeeds();

        assertNotNull(result, "getCalorieNeeds did not return a value.");
    }

    /**
     * Test that getWaste method returns an integer array
     */
    @Test
    public void testGetWaste() {
        Hamper test = new Hamper(calorieValues);
        int[] result = test.getWaste();

        assertNotNull(result, "getCalorieNeeds did not return a value.");
    }

    /**
     * Test that getFoodQuantities returns an ArrayList of integers
     */
    @Test
    public void testGetCalorieNeeds() {
        Hamper test = new Hamper(calorieValues);
        ArrayList<Integer> result = test.getFoodQuantities();

        assertNotNull(result, "getCalorieNeeds did not return a value.");
    }
}
