/**
 * @author Philippa Madill
 * @version 1.0
 * @since 1.0
 */

package edu.ucalgary.ensf409;

import org.junit.*;
import static org.junit.Assert.*;

public class SelectFoodTest{
	private int[] calorieValues = {100, 200, 300, 400};
        Hamper testHamper = new Hamper(calorieValues);
	
	@Test
	public void testNotEnoughFoodException(){
		/*
		calculateFoods, set the Hashmap to have a negative value or an absurdly high
		value. Check if the NotEnoughFoodException is thrown
		*/
	}
	
	@Test
	public void testCalculateFoods(){
		//basically just need to ensure it returns a Hashmap, right?
		HashMap<int, int> testMap = new HashMap<int, int>testHamper.calculateFoods();
		assertNotNull(testMap, "calculateFoods did not create a HashMap.");
	}
	
	@Test
	public void testMostEfficientFood(){
		//the SelectFood object.mostEfficientFood assertNotNull basically
		int testEfficiency = testHamper.mostEfficientFood();
		assertNotNull(testEfficiency, "mostEfficientFood did not return an integer.");
	}
	
	@Test
	public void testCalculateWaste(){
		//Ensure it is indeed returning an int[].
		int[] testWaste = testHamper.calculateWaste();
		assertNotNull(testWaste, "calculateWaste did not correctly return an int[].")
	}
	
}