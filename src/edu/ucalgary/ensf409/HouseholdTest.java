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
        FoodData temp = new FoodData("temp",
                100, 100, 100, 100);

        clientValues.put("calories", 10);
        clientValues.put("fv", 10);
        clientValues.put("grain", 20);
        clientValues.put("protein", 30);
        clientValues.put("other", 40);

        clientData.put("Adult Male", clientValues);
        clientData.put("Adult Female", clientValues);
        clientData.put("Child Under 8", clientValues);
        clientData.put("Child Over 8", clientValues);

        familyList.add("Adult Male");
        familyList.add("Adult Male");
        familyList.add("Adult Female");

        availableFoods.put(1, temp);
        availableFoods.put(2, temp);
        availableFoods.put(3, temp);

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
        HashMap<Integer, FoodData> availableFoods = new HashMap<>();
        ArrayList<String> familyList = new ArrayList<>();
        FoodData temp = new FoodData("temp",
                1, 1, 1, 1);

        clientValues.put("calories", 1000);
        clientValues.put("fv", 10);
        clientValues.put("grain", 20);
        clientValues.put("protein", 30);
        clientValues.put("other", 40);

        clientData.put("Adult Male", clientValues);
        clientData.put("Adult Female", clientValues);
        clientData.put("Child Under 8", clientValues);
        clientData.put("Child Over 8", clientValues);

        familyList.add("Adult Male");
        familyList.add("Adult Male");
        familyList.add("Adult Female");

        availableFoods.put(1, temp);
        availableFoods.put(2, temp);
        availableFoods.put(3, temp);

        Household testObj;

        try {
            testObj = new Household(clientData, familyList, availableFoods);
        } catch (NotEnoughFoodException e){
            testObj = new Household(e, familyList);
        }

        assertNotEquals("Household object didn't initialize correctly",
                testObj.getFamilyHamper().getException(),
                null);
    }

    // Test get Family hamper from primary constructor
    @Test
    public void testGetHamperWithPrimaryConstructor() {
        HashMap<String, HashMap<String, Integer>> clientData = new HashMap<>();
        HashMap<String, Integer> clientValues = new HashMap<>();
        ArrayList<String> familyList = new ArrayList<>();
        HashMap<Integer, FoodData> availableFoods = new HashMap<>();
        FoodData temp = new FoodData("temp",
                100, 100, 100, 100);

        clientValues.put("calories", 10);
        clientValues.put("fv", 10);
        clientValues.put("grain", 20);
        clientValues.put("protein", 30);
        clientValues.put("other", 40);

        clientData.put("Adult Male", clientValues);
        clientData.put("Adult Female", clientValues);
        clientData.put("Child Under 8", clientValues);
        clientData.put("Child Over 8", clientValues);

        familyList.add("Adult Male");
        familyList.add("Adult Male");
        familyList.add("Adult Female");

        availableFoods.put(1, temp);
        availableFoods.put(2, temp);
        availableFoods.put(3, temp);

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
                testObj.getFamilyHamper(),
                null);
    }

    // Test get Family hamper from error constructor
    @Test
    public void testGetHamperWithErrorConstructor() {
        HashMap<String, HashMap<String, Integer>> clientData = new HashMap<>();
        HashMap<String, Integer> clientValues = new HashMap<>();
        HashMap<Integer, FoodData> availableFoods = new HashMap<>();
        ArrayList<String> familyList = new ArrayList<>();
        FoodData temp = new FoodData("temp",
                1, 1, 1, 1);

        clientValues.put("calories", 1000);
        clientValues.put("fv", 10);
        clientValues.put("grain", 20);
        clientValues.put("protein", 30);
        clientValues.put("other", 40);

        clientData.put("Adult Male", clientValues);
        clientData.put("Adult Female", clientValues);
        clientData.put("Child Under 8", clientValues);
        clientData.put("Child Over 8", clientValues);

        familyList.add("Adult Male");
        familyList.add("Adult Male");
        familyList.add("Adult Female");

        availableFoods.put(1, temp);
        availableFoods.put(2, temp);
        availableFoods.put(3, temp);

        Household testObj;

        try {
            testObj = new Household(clientData, familyList, availableFoods);
        } catch (NotEnoughFoodException e){
            testObj = new Household(e, familyList);
        }

        assertNotEquals("Household object didn't initialize correctly",
                testObj.getFamilyHamper(),
                null);
    }
}
