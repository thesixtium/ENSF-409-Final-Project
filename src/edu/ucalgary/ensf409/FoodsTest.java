/**
 * @author Danielle Jourdain
 * @version 1.0
 * @since 1.0
 */

package edu.ucalgary.ensf409;

import org.junit.*;
import static org.junit.Assert.*;

public class FoodsTest {

    /**
     * Adds food to the inventory, then retrieves one of them based on ID
     */
    @Test
    public void testGetFoodGood() {
        int[] calories = {1, 2, 3, 4};
        Foods.setFood(1, "Apple", calories);
        Foods.setFood(2, "Candy Bar", calories);

        int[] actual = Foods.getFood(1);
        int[] expected = {1, 2, 3, 4};

        assertEquals("getFood did not return the expected value.", actual, expected);
    }

    /**
     * Adds food to the inventory, then attempts to retrieve a food with a non-existent ID.
     */
    @Test 
    public void testGetFoodBad() {
        int[] calories = {1, 2, 3, 4};
        Foods.setFood(1, "Apple", calories);
        Foods.setFood(2, "Candy Bar", calories);

        int[] actual = Foods.getFood(3);

        assertNull(actual, "getFood did not return a null int array when the given ID did not exist");
    }
}
