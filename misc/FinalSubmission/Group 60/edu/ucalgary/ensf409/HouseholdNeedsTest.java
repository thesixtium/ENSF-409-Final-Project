/**
 * @author Danielle Jourdain
 * @version 1.2
 * @since 1.0
 */

package edu.ucalgary.ensf409;

import org.junit.*;
import static org.junit.Assert.*;

public class HouseholdNeedsTest {
    /**
     * This test checks that the constructor creates an object and that all the calorie values are set to zero.
     * Note that this and most of the other tests may fail even if the tested feature works correctly if
     * the getters do not work.
     */
    @Test
    public void testConstructor() {
        HouseholdNeeds needs = new HouseholdNeeds();

        assertNotNull("HouseholdNeeds constructor did not create an object", needs);
        assertEquals("HouseholdNeeds constructor did not set fvCalories correctly", 0, needs.getFvCalories());
        assertEquals("HouseholdNeeds constructor did not set grainCalories correctly", 0, needs.getGrainCalories());
        assertEquals("HouseholdNeeds constructor did not set proteinCalories correctly", 0, needs.getProteinCalories());
        assertEquals("HouseholdNeeds constructor did not set otherCalories correctly", 0, needs.getOtherCalories());
    }

    /**
     * This test first checks that the changeFvCalories method changes the value of fvCalories 
     * correctly. The second feature that is tested is that the changeFvCalories method adds 
     * the new value to the current value of fvCalories.
     */
    @Test
    public void testChangeFvCalories() {
        HouseholdNeeds needs = new HouseholdNeeds();

        needs.changeFvCalories(15);
        assertEquals("changeFvCalories did not change fvCalories correctly", 15, needs.getFvCalories());

        needs.changeFvCalories(15);
        assertEquals("changeFvCalories did not update fvCalories correctly", 30, needs.getFvCalories());
    }

    /**
     * This test first checks that the changeGrainCalories method changes the value of grainCalories 
     * correctly. The second part tests that when the changeGrainCalories method is called again 
     * the new value is added to the old one instead of overwriting it.
     */
    @Test
    public void testChangeGrainCalories() {
        HouseholdNeeds needs = new HouseholdNeeds();

        needs.changeGrainCalories(20);
        assertEquals("changeGrainCalories did not change grainCalories correctly", 20, needs.getGrainCalories());

        needs.changeGrainCalories(20);
        assertEquals("changeGrainCalories did not update grainCalories correctly", 40, needs.getGrainCalories());
    }

    /**
     * This first tests that the changeProteinCalories method changes the value of proteinCalories 
     * correctly. The second part of this tests that when the method is called again the old 
     * value of proteinCalories is added to the new number, rather than being replaced.
     */
    @Test
    public void testChangeProteinCalories() {
        HouseholdNeeds needs = new HouseholdNeeds();

        needs.changeProteinCalories(60);
        assertEquals("changeProteinCalories did not change proteinCalories correctly", 60, needs.getProteinCalories());

        needs.changeProteinCalories(60);
        assertEquals("changeProteinCalories did not update proteinCalories correctly", 120, needs.getProteinCalories());
    }

    /**
     * Firstly, the changeOtherCalories method is tested to see if it changes the value of
     * otherCalories correctly. Then, the method is called again to see if the old 
     * value of otherCalories is added to the new value, rather than being replaced.
     */
    @Test
    public void testChangeOtherCalories() {
        HouseholdNeeds needs = new HouseholdNeeds();

        needs.changeOtherCalories(45);
        assertEquals("changeOtherCalories did not change otherCalories correctly", 45, needs.getOtherCalories());

        needs.changeOtherCalories(45);
        assertEquals("changeOtherCalories did not udpdate otherCalories correctly", 90, needs.getOtherCalories());
    }

    /**
     * This test checks that the getter for fvCalories works correctly.
     */
    @Test
    public void testGetFvCalories() {
        HouseholdNeeds needs = new HouseholdNeeds();
        assertEquals("getFvCalories did not return the correct value", 0, needs.getFvCalories());
    }

    /**
     * This test checks that the getter for grainCalories works correctly.
     */
    @Test
    public void testGetGrainCalories() {
        HouseholdNeeds needs = new HouseholdNeeds();
        assertEquals("getGrainCalories did not return the correct value", 0, needs.getGrainCalories());
    }

    /**
     * This test checks that the getter for proteinCalories works correctly.
     */
    @Test
    public void testGetProteinCalories() {
        HouseholdNeeds needs = new HouseholdNeeds();
        assertEquals("getProteinCalories did not return the correct value", 0, needs.getProteinCalories());
    }

    /**
     * This test checks that the getter for otherCalories works correctly.
     */
    @Test
    public void testGetOtherCalories() {
        HouseholdNeeds needs = new HouseholdNeeds();
        assertEquals("getOtherCalories did not return the correct value", 0, needs.getOtherCalories());
    }

    /**
     * This test checks that the isSatisfied method returns true when all calorie values are 0.
     */
    @Test
    public void testIsSatisfiedTrue() {
        HouseholdNeeds needs = new HouseholdNeeds();
        assertTrue("isSatisfied did not return true when all needs were satisfied", needs.isSatisfied());
    }

    /**
     * This test checks that the isSatisfied method returns false when one of the calorie values is not 0.
     */
    @Test
    public void testIsSatisfiedFalse() {
        HouseholdNeeds needs = new HouseholdNeeds();
        needs.changeFvCalories(10);
        assertFalse("isSatisfied did not return false when all needs were not satisfied", needs.isSatisfied());
    }

    /**
     * This test checks that the convertToWeekly method multiplies all calorie need values by 7
     * once they have been set.
     */
    @Test
    public void testConvertToWeekly() {
        HouseholdNeeds needs = new HouseholdNeeds();
        needs.changeFvCalories(10);
        needs.changeGrainCalories(15);
        needs.changeProteinCalories(20);
        needs.changeOtherCalories(25);

        needs.convertToWeekly();

        assertEquals("convertToWeekly did not change fvCalories correctly", 70, needs.getFvCalories());
        assertEquals("convertToWeekly did not change grainCalories correctly", 105, needs.getGrainCalories());
        assertEquals("convertToWeekly did not change proteinCalories correctly", 140, needs.getProteinCalories());
        assertEquals("convertToWeekly did not change otherCalories correctly", 175, needs.getOtherCalories());
    }
}
