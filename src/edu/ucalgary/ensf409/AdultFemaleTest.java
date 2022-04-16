/**
 * @author Aleksander Berezowski
 * @author Danielle Jourdain
 * @author Philippa Madill
 * @version 1.2
 * @since 1.0
 */

package edu.ucalgary.ensf409;

import org.junit.*;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class AdultFemaleTest {
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

        int actualFV = AdultFemale.getProteinCalories();
        int actualGrain = AdultFemale.getGrainCalories();
        int actualProtein = AdultFemale.getProteinCalories();
        int actualOther = AdultFemale.getOtherCalories();
        //get all values from AdultFemale

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
        int actualFV = AdultFemale.getProteinCalories();
        int actualGrain = AdultFemale.getGrainCalories();
        int actualProtein = AdultFemale.getProteinCalories();
        int actualOther = AdultFemale.getOtherCalories();
        //get all values

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
        AdultFemale.setTotalCalories(100);
        // 10% FV
        // 20% Grain
        // 30% Protein
        // 40% Other
        AdultFemale.setFvPercent(10);
        AdultFemale.setGrainPercent(20);
        AdultFemale.setProteinPercent(30);
        AdultFemale.setOtherPercent(40);

        int expectedFV = 10;
        int expectedGrain = 20;
        int expectedProtein = 30;
        int expectedOther = 40;

        int actualFV = AdultFemale.getFvCalories();
        int actualGrain = AdultFemale.getGrainCalories();
        int actualProtein = AdultFemale.getProteinCalories();
        int actualOther = AdultFemale.getOtherCalories();
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
        AdultFemale.setTotalCalories(200);
        // 10% FV
        // 20% Grain
        // 30% Protein
        // 40% Other
        AdultFemale.setFvPercent(10);
        AdultFemale.setGrainPercent(20);
        AdultFemale.setProteinPercent(30);
        AdultFemale.setOtherPercent(40);

        int expectedFV = 20;
        int expectedGrain = 40;
        int expectedProtein = 60;
        int expectedOther = 80;

        int actualFV = AdultFemale.getFvCalories();
        int actualGrain = AdultFemale.getGrainCalories();
        int actualProtein = AdultFemale.getProteinCalories();
        int actualOther = AdultFemale.getOtherCalories();
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
        AdultFemale.setTotalCalories(100);
        // 10% FV
        // 20% Grain
        // 30% Protein
        // 40% Other
        AdultFemale.setFvPercent(10);
        AdultFemale.setGrainPercent(20);
        AdultFemale.setProteinPercent(30);
        AdultFemale.setOtherPercent(40);

        AdultFemale.getFvCalories();
        //call one of the getters to indirectly call the calculateCalories method and ensure
        //all values are correct

        boolean expectedValue = false;
        boolean actualValue = AdultFemale.isSatisfied();

        assertEquals("expectedValue and actualValue are not equal", expectedValue, actualValue);
    }

    /**
     * Test if the isSatisfied method returns the correct result when the needs
     * are satisfied
     */
    @Test
    public void testIsSatisfied2() {
        AdultFemale.setTotalCalories(0);

        AdultFemale.getFvCalories();
        //call one of the getters to indirectly call the calculateCalories method and ensure
        //all values are correct

        boolean expectedValue = true;
        boolean actualValue = AdultFemale.isSatisfied();

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
