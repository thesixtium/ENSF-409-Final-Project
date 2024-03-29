/**

 * @author Aleksander Berezowski
 * @author Danielle Jourdain
 * @author Philippa Madill
 * @version 1.0
 * @since 1.0
 */
 
package edu.ucalgary.ensf409;

import org.junit.*;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class FoodNeedsTest {

    // Test constructor initializes correctly
    @Test
    public void testConstructor() {
        int expectedFV = 0;
        int expectedGrain = 0;
        int expectedProtein = 0;
        int expectedOther = 0;

        int actualFV = FoodNeeds.getProteinCalories();
        int actualGrain = FoodNeeds.getGrainCalories();
        int actualProtein = FoodNeeds.getProteinCalories();
        int actualOther = FoodNeeds.getOtherCalories();

        assertEquals("expectedFV and actualFV are not equal", expectedFV, actualFV);
        assertEquals("expectedGrain and actualGrain are not equal", expectedGrain, actualGrain);
        assertEquals("expectedProtein and actualProtein are not equal", expectedProtein, actualProtein);
        assertEquals("expectedOther and actualOther are not equal", expectedOther, actualOther);
    }

    // Test getters with all 0's
    @Test
    public void testZerosGetters() {
        int actualFV = FoodNeeds.getProteinCalories();
        int actualGrain = FoodNeeds.getGrainCalories();
        int actualProtein = FoodNeeds.getProteinCalories();
        int actualOther = FoodNeeds.getOtherCalories();

        assertEquals("expectedFV and actualFV are not equal", 0, actualFV);
        assertEquals("expectedGrain and actualGrain are not equal", 0, actualGrain);
        assertEquals("expectedProtein and actualProtein are not equal", 0, actualProtein);
        assertEquals("expectedOther and actualOther are not equal", 0, actualOther);
    }

    // Test all setters and getters
    @Test
    public void testSettersGetters() {
        FoodNeeds.setTotalCalories(100);
        FoodNeeds.setFvPercent(10);
        FoodNeeds.setGrainPercent(20);
        FoodNeeds.setProteinPercent(30);
        FoodNeeds.setOtherPercent(40);

        int expectedFV = 10;
        int expectedGrain = 20;
        int expectedProtein = 30;
        int expectedOther = 40;

        int actualFV = FoodNeeds.getFvCalories();
        int actualGrain = FoodNeeds.getGrainCalories();
        int actualProtein = FoodNeeds.getProteinCalories();
        int actualOther = FoodNeeds.getOtherCalories();

        assertEquals("expectedFV and actualFV are not equal", expectedFV, actualFV);
        assertEquals("expectedGrain and actualGrain are not equal", expectedGrain, actualGrain);
        assertEquals("expectedProtein and actualProtein are not equal", expectedProtein, actualProtein);
        assertEquals("expectedOther and actualOther are not equal", expectedOther, actualOther);
    }

    // Test all setters and getters again with different numbers
    @Test
    public void testSettersGetters2() {
        FoodNeeds.setTotalCalories(200);
        FoodNeeds.setFvPercent(10);
        FoodNeeds.setGrainPercent(20);
        FoodNeeds.setProteinPercent(30);
        FoodNeeds.setOtherPercent(40);

        int expectedFV = 20;
        int expectedGrain = 40;
        int expectedProtein = 60;
        int expectedOther = 80;

        int actualFV = FoodNeeds.getFvCalories();
        int actualGrain = FoodNeeds.getGrainCalories();
        int actualProtein = FoodNeeds.getProteinCalories();
        int actualOther = FoodNeeds.getOtherCalories();

        assertEquals("expectedFV and actualFV are not equal", expectedFV, actualFV);
        assertEquals("expectedGrain and actualGrain are not equal", expectedGrain, actualGrain);
        assertEquals("expectedProtein and actualProtein are not equal", expectedProtein, actualProtein);
        assertEquals("expectedOther and actualOther are not equal", expectedOther, actualOther);
    }

    // Test isSatisfied
    @Test
    public void testIsSatisfied() {
        FoodNeeds.setTotalCalories(100);
        FoodNeeds.setFvPercent(10);
        FoodNeeds.setGrainPercent(20);
        FoodNeeds.setProteinPercent(30);
        FoodNeeds.setOtherPercent(40);

        FoodNeeds.getFvCalories();
        /*
		call one of the getters to indirectly call the calculateCalories method and ensure
        all values are correct
		*/

        boolean expectedValue = false;
        boolean actualValue = FoodNeeds.isSatisfied();

        assertEquals("expectedValue and actualValue are not equal", expectedValue, actualValue);
    }

    // Test isSatisfied again with different numbers
    @Test
    public void testIsSatisfied2() {
        FoodNeeds.setTotalCalories(0);
        FoodNeeds.getFvCalories();
        /*
		call one of the getters to indirectly call the calculateCalories method and ensure
        all values are correct
		*/

        boolean expectedValue = true;
        boolean actualValue = FoodNeeds.isSatisfied();

        assertEquals("expectedValue and actualValue are not equal", expectedValue, actualValue);
    }

    /**
     * setup method to reset all values before a test. 
     * especially important for the test checking the getters with all values 0
     */
    @Before
    public void resetValues() {
        FoodNeeds.setFvPercent(0);
        FoodNeeds.setGrainPercent(0);
        FoodNeeds.setOtherPercent(0);
        FoodNeeds.setProteinPercent(0);
        FoodNeeds.setTotalCalories(10);
    }
}
