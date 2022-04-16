/**
 * @author Aleksander Berezowski
 * @author Danielle Jourdain
 * @author Philippa Madill
 * @version 1.0
 * @since 1.0
 */

package edu.ucalgary.ensf409;

import org.junit.Test;
import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.Assert.*;

public class HouseholdTest {
    // Test primary constructor
    @Test
    public void testPrimaryConstructor() {
        HashMap<String, HashMap<String, Integer>> clientData = new HashMap<>();
        HashMap<String, Integer> clientValues = new HashMap<>();
        ArrayList<String> familyList = new ArrayList<>();
        HashMap<Integer, FoodData> availableFoods = new HashMap<>();

        clientValues.put("calories", 10);
        clientValues.put("fv", 10);
        clientValues.put("grain", 20);
        clientValues.put("protein", 30);
        clientValues.put("other", 40);

        clientData.put("Adult Male", clientValues);
        clientData.put("Adult Female", clientValues);
        clientData.put("Child under 8", clientValues);
        clientData.put("Child over 8", clientValues);

        familyList.add("Adult Male");
        familyList.add("Adult Male");
        familyList.add("Adult Female");

        availableFoods.put(1, new FoodData("temp1", 400, 1600, 10, 100));
        availableFoods.put(2, new FoodData("temp2", // suffer <3
                300, 100, 100, 1010));
        availableFoods.put(3, new FoodData("temp3",0, 1, 0, 0));
        availableFoods.put(4, new FoodData("temp4",0, 0, 1, 0));
        availableFoods.put(5, new FoodData("temp5",0, 0, 0, 1));
        availableFoods.put(6, new FoodData("temp5",1, 0, 0, 0));

        boolean flag = true;

        try {
            Household testObj = new Household(clientData, familyList, availableFoods);
        } catch (NotEnoughFoodException e){
            flag = false;
        }

        assertTrue("Household object didn't initialize correctly", flag);
    }

    // Test error constructor
    @Test
    public void testErrorConstructor() {
        HashMap<String, HashMap<String, Integer>> clientData = new HashMap<>();
        HashMap<String, Integer> clientValues = new HashMap<>();
        ArrayList<String> familyList = new ArrayList<>();
        HashMap<Integer, FoodData> availableFoods = new HashMap<>();

        clientValues.put("calories", 10);
        clientValues.put("fv", 10);
        clientValues.put("grain", 20);
        clientValues.put("protein", 30);
        clientValues.put("other", 40);

        clientData.put("Adult Male", clientValues);
        clientData.put("Adult Female", clientValues);
        clientData.put("Child under 8", clientValues);
        clientData.put("Child over 8", clientValues);

        familyList.add("Adult Male");
        familyList.add("Adult Male");
        familyList.add("Adult Female");

        availableFoods.put(1, new FoodData("temp1", 400, 1600, 10, 100));

        Household testObj;

        try {
            testObj = new Household(clientData, familyList, availableFoods);
        } catch (NotEnoughFoodException e){
            testObj = new Household(e, familyList);
        }

        assertNotEquals("Household object didn't initialize correctly",
                testObj.getFamilyHamper().getException(), null);
    }

    // Test get Family hamper from primary constructor
    @Test
    public void testGetHamperWithPrimaryConstructor() {
        HashMap<String, HashMap<String, Integer>> clientData = new HashMap<>();
        HashMap<String, Integer> clientValues = new HashMap<>();
        ArrayList<String> familyList = new ArrayList<>();
        HashMap<Integer, FoodData> availableFoods = new HashMap<>();

        clientValues.put("calories", 10);
        clientValues.put("fv", 10);
        clientValues.put("grain", 20);
        clientValues.put("protein", 30);
        clientValues.put("other", 40);

        clientData.put("Adult Male", clientValues);
        clientData.put("Adult Female", clientValues);
        clientData.put("Child under 8", clientValues);
        clientData.put("Child over 8", clientValues);

        familyList.add("Adult Male");
        familyList.add("Adult Male");
        familyList.add("Adult Female");

        availableFoods.put(1, new FoodData("temp1", 400, 1600, 10, 100));
        availableFoods.put(2, new FoodData("temp2", // suffer <3
                300, 100, 100, 1010));
        availableFoods.put(3, new FoodData("temp3",0, 1, 0, 0));
        availableFoods.put(4, new FoodData("temp4",0, 0, 1, 0));
        availableFoods.put(5, new FoodData("temp5",0, 0, 0, 1));
        availableFoods.put(6, new FoodData("temp5",1, 0, 0, 0));

        boolean flag = true;
        Household testObj;

        try {
            testObj = new Household(clientData, familyList, availableFoods);
        } catch (NotEnoughFoodException e){
            flag = false;
            testObj = null;
        }

        assertTrue("Household object didn't initialize correctly", flag);
        assertNotEquals("Household object didn't initialize correctly",
                testObj.getFamilyHamper(), null);
    }

    // Test get Family hamper from error constructor
    @Test
    public void testGetHamperWithErrorConstructor() {
        HashMap<String, HashMap<String, Integer>> clientData = new HashMap<>();
        HashMap<String, Integer> clientValues = new HashMap<>();
        ArrayList<String> familyList = new ArrayList<>();
        HashMap<Integer, FoodData> availableFoods = new HashMap<>();

        clientValues.put("calories", 10);
        clientValues.put("fv", 10);
        clientValues.put("grain", 20);
        clientValues.put("protein", 30);
        clientValues.put("other", 40);

        clientData.put("Adult Male", clientValues);
        clientData.put("Adult Female", clientValues);
        clientData.put("Child under 8", clientValues);
        clientData.put("Child over 8", clientValues);

        familyList.add("Adult Male");
        familyList.add("Adult Male");
        familyList.add("Adult Female");

        availableFoods.put(1, new FoodData("temp1", 400, 1600, 10, 100));
        availableFoods.put(2, new FoodData("temp2", // suffer <3
                300, 100, 100, 1010));
        availableFoods.put(3, new FoodData("temp3",0, 1, 0, 0));
        availableFoods.put(4, new FoodData("temp4",0, 0, 1, 0));
        availableFoods.put(5, new FoodData("temp5",0, 0, 0, 1));
        availableFoods.put(6, new FoodData("temp5",1, 0, 0, 0));

        Household testObj;

        try {
            testObj = new Household(clientData, familyList, availableFoods);
        } catch (NotEnoughFoodException e){
            testObj = new Household(e, familyList);
        }

        assertNotEquals("Household object didn't initialize correctly",
                testObj.getFamilyHamper(), null);
    }
}
