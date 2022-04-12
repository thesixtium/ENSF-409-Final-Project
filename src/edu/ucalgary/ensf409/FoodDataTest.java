/**
 * @author Danielle Jourdain
 * @version 1.7
 * @since 1.0
 */

package edu.ucalgary.ensf409;

import org.junit.*;
import static org.junit.Assert.*;

public class FoodDataTest {
    /**
     * This test creates a FoodData object using the 4-argument constructor and checks if
     * all values were set correctly. This test may fail if the getters are faulty even if
     * the constructor works.
     */
    @Test  
    public void test4ArgConstructor() {
        String name = "test item";
        FoodData testData = new FoodData(name, 1, 2, 3, 4);
        
        int actualFV = testData.getFv();
        int actualGrain = testData.getGrain();
        int actualOther = testData.getOther();
        int actualProtein = testData.getProtein();
        String actualName = testData.getName();

        assertNotNull("FoodData 4 argument constructor did not create an object.", testData);
        assertEquals("FoodData 4 argument constructor did not set name correctly.", name, actualName);
        assertEquals("FoodData 4 argument constructor did not set FV value correctly.", 1, actualFV);
        assertEquals("FoodData 4 argument constructor did not set Grain value correctly.", 2, actualGrain);
        assertEquals("FoodData 4 argument constructor did not set Protein value correctly.", 3, actualProtein);
        assertEquals("FoodData 4 argument constructor did not set Other value correctly.", 4, actualOther);
    }

    /**
     * This test creates a FoodData object using the 4-argument constructor and checks if
     * all values were set correctly. This test may also fail if getter methodss are faulty.
     */
    @Test 
    public void test1ArgConstructor() {
        String name = "test item";
        FoodData testData = new FoodData(name);
        
        int actualFV = testData.getFv();
        int actualGrain = testData.getGrain();
        int actualOther = testData.getOther();
        int actualProtein = testData.getProtein();
        String actualName = testData.getName();

        assertNotNull("FoodData 4 argument constructor did not create an object.", testData);
        assertEquals("FoodData 4 argument constructor did not set name correctly.", name, actualName);
        assertEquals("FoodData 4 argument constructor did not set FV value correctly.", 0, actualFV);
        assertEquals("FoodData 4 argument constructor did not set Grain value correctly.", 0, actualGrain);
        assertEquals("FoodData 4 argument constructor did not set Protein value correctly.", 0, actualProtein);
        assertEquals("FoodData 4 argument constructor did not set Other value correctly.", 0, actualOther);
    }

    /**
     * This test checks if the getFV method returns the correct value.
     */
    @Test
    public void testGetFV() {
        String name = "FV item";
        FoodData testData = new FoodData(name, 100, 0, 0 , 0);

        int actualFV = testData.getFv();

        assertEquals("getFV method did not return expected value.", 100, actualFV);
    }

    /**
     * This test checks if the setter for FV works correctly. Note this
     * test may fail if the getter is faulty.
     */
    @Test  
    public void testSetFV() {
        String name = "FV item";
        FoodData testData = new FoodData(name);

        testData.setFV(50);
        int actualFV = testData.getFv();

        assertEquals("setFV method did not return expected value.", 50, actualFV);
    }

    /**
     * This test checks if the getGrain method returns the correct value.
     */
    @Test
    public void testGetGrain() {
        String name = "Grain item";
        FoodData testData = new FoodData(name, 0, 200, 0 , 0);

        int actualGrain = testData.getGrain();

        assertEquals("getGrain method did not return expected value.", 200, actualGrain);
    }

    /**
     * This test checks if the setter for Grain works correctly. Note this
     * test may fail if the getter is faulty.
     */
    @Test  
    public void testSetGrain() {
        String name = "Grain item";
        FoodData testData = new FoodData(name);

        testData.setGrain(60);
        int actualgrain = testData.getGrain();

        assertEquals("setGrain method did not return expected value.", 60, actualgrain);
    }

    /**
     * This test checks if the getProtein method returns the correct value.
     */
    @Test
    public void testGetProtein() {
        String name = "Protein item";
        FoodData testData = new FoodData(name, 0, 0, 300 , 0);

        int actualProtein = testData.getProtein();

        assertEquals("getProtein method did not return expected value.", 300, actualProtein);
    }

    /**
     * This test checks if the setter for Protein works correctly. Note this
     * test may fail if the getter is faulty.
     */
    @Test  
    public void testSetProtein() {
        String name = "Protein item";
        FoodData testData = new FoodData(name);

        testData.setProtein(70);
        int actualProtein = testData.getProtein();

        assertEquals("setProtein method did not return expected value.", 70, actualProtein);
    }

    /**
     * This test checks if the getOther method returns the correct value.
     */
    @Test
    public void testGetOther() {
        String name = "Other item";
        FoodData testData = new FoodData(name, 0, 0, 0 , 400);

        int acutalOther = testData.getOther();

        assertEquals("getOther method did not return expected value.", 400, acutalOther);
    }

    /**
     * This test checks if the setter for Other works correctly. Note this
     * test may fail if the getter is faulty.
     */
    @Test  
    public void testSetOther() {
        String name = "Other item";
        FoodData testData = new FoodData(name);

        testData.setOther(80);
        int actualOther = testData.getOther();

        assertEquals("setOther method did not return expected value.", 80, actualOther);

    }

    /**
     * This test checks if the getName method returns the correct value.
     */
    @Test
    public void testGetName() {
        String name = "Named item";
        FoodData testData = new FoodData(name);

        String actualName = testData.getName();

        assertEquals("getName method did not return expected value.", name, actualName);

    }

    /**
     * This test checks if the getSum method returns the correct value.
     */
    @Test  
    public void testGetSum() {
        String name = "Sum item";
        FoodData testData = new FoodData(name, 100, 200, 300, 400);

        int expectedSum = 100 + 200 + 300 + 400;
        int actualSum = testData.getSum();

        assertEquals("getSum method did not return expected value.", expectedSum, actualSum);
    }
}
