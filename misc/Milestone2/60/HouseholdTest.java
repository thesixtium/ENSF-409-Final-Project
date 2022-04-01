/**
 * @author Aleksander Berezowski
 * @version 134
 * @since 1.0
 */

package edu.ucalgary.ensf409;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.ArrayList;

public class HouseholdTest {

    private ArrayList<Object> testObjs = new ArrayList<>();
    private Household testObj = HouseholdTest(testObjs);

    @Test
    public void testHouseholdInit(){
        assertNotEquals("Initialized object is null", testObj, null);
    }

    @Test
    public void testGetFamily(){
        ArrayList<Object> expectedFamily = new ArrayList<>();
        ArrayList<Object> testFamily = testObj.getFamily();

        assertEquals("getFamily does not return correct array list", expectedFamily, testFamily);
    }

    @Test
    public void testGetFamilyHamper(){
        Hamper expectedHamper = new Hamper();
        Hamper testHamper = testObj.getFamilyHamper();

        assertEquals("getFamilyHamper does not return correct array list", expectedHamper, testHamper);
    }

    @Test
    public void testSetFamily(){
        ArrayList<Object> expectedFamily = new ArrayList<>();
        ArrayList<Object> testFamily = testObj.setFamily(expectedFamily).getFamily();

        assertEquals("setFamily does not return correct array list", expectedFamily, testFamily);
    }

    @Test
    public void testSetFamilyHamper(){
        Hamper expectedHamper = new Hamper();
        Hamper testHamper = testObj.setFamilyHamper(expectedHamper).getFamilyHamper();

        assertEquals("getFamilyHamper does not return correct array list", expectedHamper, testHamper);
    }


}
