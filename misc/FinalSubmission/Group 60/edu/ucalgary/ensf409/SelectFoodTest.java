/**
 * @author Philippa Madill
 * @version 1.6
 * @since 1.0
 */

package edu.ucalgary.ensf409;

import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;

public class SelectFoodTest{
		int[] calorieValues = {100, 200, 300, 400};
	    HouseholdNeeds hn = new HouseholdNeeds();
        HashMap<Integer, FoodData> availableFood = updateFoodValues();
		
		@BeforeClass
    public static void setupDatabase() {
        RequestFormDatabase database = new RequestFormDatabase("jdbc:mysql://localhost/FOOD_INVENTORY","student","ensf" );
        database.initializeConnection();
		database.setFoodValues();
		database.setClientValues();
		database.close();
    }
	
	/**
	Test to ensure that the calculateFoods method will throw
	a NotEnoughFoodException if given calorie values that are unobtainable.
	*/
	
	@Test
	public void testNotEnoughFoodException(){
		HouseholdNeeds needs = new HouseholdNeeds();
        needs.changeFvCalories(60000);
        needs.changeGrainCalories(3000);
        needs.changeProteinCalories(99999);
        needs.changeOtherCalories(1000000);
		HashMap<Integer, FoodData> availableFood = updateFoodValues();
		boolean testResult = false;
		try{
			SelectFood.calculateFoods(availableFood, needs);
		}
		catch(NotEnoughFoodException e){
			testResult = true;
		}
		catch(Exception e){
			assertTrue("NotEnoughFoodException was not thrown despite unobtainable calorie values.", testResult);
		}
		
	}
	
	
	/**
	This test ensures that calculateFoods generates a HashMap correctly.
	*/
	
	@Test
	public void testCalculateFoods() throws NotEnoughFoodException{
		reset();
		HashMap<Integer, FoodData> testMap = SelectFood.calculateFoods(availableFood, hn);
		assertNotNull("calculateFoods did not create a HashMap.", testMap);
	}
	
	@Test
	public void testMostEfficientFood(){
		/*
		Does not need to be tested as mostly database interaction.
		*/
		
	}
	
	@Test
	public void testCalculateWaste() throws NotEnoughFoodException{
		//Ensure it is indeed returning a HashMap of String and Integer Objects.
		reset();
		HashMap<Integer, FoodData> foods = SelectFood.calculateFoods(availableFood, hn);
		HashMap<String, Integer> testWaste = SelectFood.calculateWaste(foods, hn);
		assertNotNull("calculateWaste did not correctly return a HashMap of String and Integer objects.", testWaste);
	}
	
	/**
	helper method to make accessing food values easier..
	*/
	private HashMap<Integer, FoodData> updateFoodValues() {
        RequestFormDatabase requestForm = new RequestFormDatabase("jdbc:mysql://localhost/FOOD_INVENTORY","student","ensf" );
		requestForm.initializeConnection();
		requestForm.setFoodValues();
		requestForm.setClientValues();

        return RequestFormDatabase.getFoodValues();
    }
	private HouseholdNeeds reset(){
		hn.changeFvCalories(100);
        hn.changeGrainCalories(100);
        hn.changeProteinCalories(100);
        hn.changeOtherCalories(100);
		return hn;
	}
	
}