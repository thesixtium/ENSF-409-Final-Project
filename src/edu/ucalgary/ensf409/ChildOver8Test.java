/**
 * @author Aleksander Berezowski
 * @author Danielle Jourdain
 * @author Philippa Madill
 * @version 1.3
 * @since 1.0
 */

package edu.ucalgary.ensf409;

import org.junit.*;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class ChildOver8Test {
    /**
     * Test constructor initializes correctly. All values should be initialized to 0.
     * Note this test may fail even if the constructor works as inteded if the 
     * getter methods are faulty.
     */
    @Test
    public void testConstructor() {
        int expectedFV = 0;
        int expectedGrain = 0;
        int expectedProtein = 0;
        int expectedOther = 0;

        int actualFV = ChildOver8.getFvCalories();
        int actualGrain = ChildOver8.getGrainCalories();
        int actualProtein = ChildOver8.getProteinCalories();
        int actualOther = ChildOver8.getOtherCalories();
        //get values

        assertEquals("expectedFV and actualFV are not equal", expectedFV, actualFV);
        assertEquals("expectedGrain and actualGrain are not equal", expectedGrain, actualGrain);
        assertEquals("expectedProtein and actualProtein are not equal", expectedProtein, actualProtein);
        assertEquals("expectedOther and actualOther are not equal", expectedOther, actualOther);
    }

    /**
     * Test the getter methods when all values have been set to 0.
     */
    @Test
    public void testZerosGetters() {
        int actualFV = ChildOver8.getFvCalories();
        int actualGrain = ChildOver8.getGrainCalories();
        int actualProtein = ChildOver8.getProteinCalories();
        int actualOther = ChildOver8.getOtherCalories();

        assertEquals("expectedFV and actualFV are not equal", 0, actualFV);
        assertEquals("expectedGrain and actualGrain are not equal", 0, actualGrain);
        assertEquals("expectedProtein and actualProtein are not equal", 0, actualProtein);
        assertEquals("expectedOther and actualOther are not equal", 0, actualOther);
    }

    /**
     * Test getter methods when all values are NOT 0.
     */
    @Test
    public void testSettersGetters() {
        ChildOver8.setTotalCalories(100);
        // 10% FV
        // 20% Grain
        // 30% Protein
        // 40% Other
        ChildOver8.setFvPercent(10);
        ChildOver8.setGrainPercent(20);
        ChildOver8.setProteinPercent(30);
        ChildOver8.setOtherPercent(40);

        int expectedFV = 10;
        int expectedGrain = 20;
        int expectedProtein = 30;
        int expectedOther = 40;

        int actualFV = ChildOver8.getFvCalories();
        int actualGrain = ChildOver8.getGrainCalories();
        int actualProtein = ChildOver8.getProteinCalories();
        int actualOther = ChildOver8.getOtherCalories();
        //get all values

        assertEquals("expectedFV and actualFV are not equal", expectedFV, actualFV);
        assertEquals("expectedGrain and actualGrain are not equal", expectedGrain, actualGrain);
        assertEquals("expectedProtein and actualProtein are not equal", expectedProtein, actualProtein);
        assertEquals("expectedOther and actualOther are not equal", expectedOther, actualOther);
    }

    /**
     * Test getter methods a second time with different values a second time.
     */
    @Test
    public void testSettersGetters2() {
        ChildOver8.setTotalCalories(200);
        // 10% FV
        // 20% Grain
        // 30% Protein
        // 40% Other
        ChildOver8.setFvPercent(10);
        ChildOver8.setGrainPercent(20);
        ChildOver8.setProteinPercent(30);
        ChildOver8.setOtherPercent(40);

        int expectedFV = 20;
        int expectedGrain = 40;
        int expectedProtein = 60;
        int expectedOther = 80;

        int actualFV = ChildOver8.getFvCalories();
        int actualGrain = ChildOver8.getGrainCalories();
        int actualProtein = ChildOver8.getProteinCalories();
        int actualOther = ChildOver8.getOtherCalories();
        //get all values

        assertEquals("expectedFV and actualFV are not equal", expectedFV, actualFV);
        assertEquals("expectedGrain and actualGrain are not equal", expectedGrain, actualGrain);
        assertEquals("expectedProtein and actualProtein are not equal", expectedProtein, actualProtein);
        assertEquals("expectedOther and actualOther are not equal", expectedOther, actualOther);
    }

    /**
     * Test if the isSatisfied method returns the correct result when the needs 
     * are not satidfied
     */
    @Test
    public void testIsSatisfied() {
        ChildOver8.setTotalCalories(100);
        // 10% FV
        // 20% Grain
        // 30% Protein
        // 40% Other
        ChildOver8.setFvPercent(10);
        ChildOver8.setGrainPercent(20);
        ChildOver8.setProteinPercent(30);
        ChildOver8.setOtherPercent(40);

        ChildOver8.getFvCalories();
        //call one of the getters to indirectly call the calculateCalories method and ensure
        //all values are correct

        boolean expectedValue = false;
        boolean actualValue = ChildOver8.isSatisfied();

        assertEquals("expectedValue and actualValue are not equal", expectedValue, actualValue);
    }

    /**
     * Test if the isSatisfied method returns the correct result when the needs
     * are satisfied
     */
    @Test
    public void testIsSatisfied2() {
        ChildOver8.setTotalCalories(0);

        ChildOver8.getFvCalories();
        //call one of the getters to indirectly call the calculateCalories method and ensure
        //all values are correct

        boolean expectedValue = true;
        boolean actualValue = ChildOver8.isSatisfied();

        assertEquals("expectedValue and actualValue are not equal", expectedValue, actualValue);
    }

    /**
     * Helper method to reset values before each test. Sets all values to 0 before every test
     */
    @Before
    public void resetValues() {
        FoodNeeds.setFvPercent(0);
        FoodNeeds.setGrainPercent(0);
        FoodNeeds.setOtherPercent(0);
        FoodNeeds.setProteinPercent(0);
        FoodNeeds.setTotalCalories(0);
    }
}
