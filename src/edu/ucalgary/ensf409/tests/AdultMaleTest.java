/**
 * @author Aleksander Berezowski
 * @version 1.1
 * @since 1.0
 */

package edu.ucalgary.ensf409;

import org.junit.Test;

import java.util.HashMap;

public class AdultMaleTest {
    AdultMale testClient = new AdultMale();

    @Test
    public void testSetNeeds(){
        HashMap<String, Integer> testNeeds = new HashMap<>();
        testNeeds.put("fv", 10);
        testNeeds.put("grain", 20);
        testNeeds.put("protein", 30);
        testNeeds.put("other", 40);

        testClient.setNeeds(testNeeds);
    }

    @Test
    public void testFVNeeds(){
        assertEquals("Did not return correct fv needs", 10, testClient.FVNeeds());
    }

    @Test
    public void testGrainNeeds(){
        assertEquals("Did not return correct grain needs", 20, testClient.grainNeeds());
    }

    @Test
    public void testProteinNeeds(){
        assertEquals("Did not return correct protein needs", 30, testClient.proteinNeeds());
    }

    @Test
    public void testOtherNeeds(){
        assertEquals("Did not return correct other needs", 40, testClient.otherNeeds());
    }

    @Test
    public void testNeedsAreStatic(){
        AdultMale testClient2 = new AdultMale();
        assertEquals("Did not return correct fv needs", 10, testClient2.FVNeeds());
        assertEquals("Did not return correct grain needs", 20, testClient2.grainNeeds());
        assertEquals("Did not return correct protein needs", 30, testClient2.proteinNeeds());
        assertEquals("Did not return correct other needs", 40, testClient2.otherNeeds());
    }
}
