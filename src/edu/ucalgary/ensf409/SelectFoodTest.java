/**
 * @author Philippa Madill
 * @version 1.3
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
		
		Hamper testHamper1 = new Hamper({60000, 3000, 99999, 1000000})
		boolean testResult = false;
		try{
			testHamper1.calculateFoods()
			}
		catch(NotEnoughFoodException e){
			testResult = true;
		}
		catch(Exception e){
			assertTrue("NotEnoughFoodException was not thrown despite unobtainable calorie values.", testResult);
		}
		
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