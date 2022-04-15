/**
 * @author Philippa Madill
 * @version 1.3
 * @since 1.0
 */


package edu.ucalgary.ensf409;

import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;

public class RequestFormTest{
	public ArrayList<int[]> validHousehold = new ArrayList<>();
	public int[] validEntries = {1, 1, 1, 1};
	validHousehold.add(validEntries);
	private ArrayList<int[]> invalidHousehold = new ArrayList<>();
	public int[] tooFewEntries = {1, 1, 1};
	invalidHousehold.add(tooFewEntries);
	private int expectedNumberOfHouseholds = 1;
	
	RequestForm requestForm = new RequestForm(validHousehold);
	
	@Test
		public void testConstructor(){
			
		assertNotNull(requestForm, "Did not correctly create a RequestForm object.");
	}
	
	@Test 
		/**
		Ensure the constructor throws an exception if 
		there are too few arguments entered
		*/
		
		public void testConstructorTooFewArguments(){
			/*
			This is handled by a GUI test condition. 
			Lines 120-136 of RequestFormGUI.
			*/
			
		boolean testResult = false;
		try{
			RequestForm form = new RequestForm(invalidHousehold);
		}
		catch(IllegalArgumentException e){
			testResult = true;
		}
		catch(Exception e){
			assertTrue("Invalid amount of arguments did not throw an Exception", testResult);
		}
	}
		
	@Test 
		public void testConstructorNonNumericArguments(){
		/*
			This is handled by a GUI test condition. 
			Lines 120-136 of RequestFormGUI.
			Additionally, as the constructor parses for
			Integer arguments, any non-numeric argument would be removed
			and the constructor would be called with too few arguments.
			*/
		
	}
		
	
	@Test
		public void testUpdateFoodValues(){
			HashMap<Integer, FoodData> values = new HashMap<Integer, FoodData>();
			values = requestForm.updateFoodValues();
			assertNotNull("updateFoodValues did not properly fill a HashMap of Integer and FoodData objects", values);
		}
    

	
	@Test	
		public void testGetHampers(){
			ArrayList<Hamper> testHamper = requestForm.getHampers();
			assertNotNull("getHampers did not successfully retrieve a Hamper object", testHamper);
	}
		
	@Test
		public void testGetHouseholds(){
			ArrayList<Household> actualHouseholds = requestForm.getHouseholds();
			ArrayList<Household> expectedHouseholds = new ArrayList<Household>();
			int[] addedHousehold = {1, 1, 1, 1};
			expectedHouseholds.add(addedHousehold);
			assertEquals("getHouseholds did not return the expected Households object", actualHouseholds, expectedHouseholds);
	}
		
	@Test
		public void testGetNumberHouseholds(){
			int actualNumberOfHouseholds = requestForm.getNumberHouseholds();
			assertEquals("getNumberHouseholds did not return the proper number of households", actualNumberOfHouseholds, expectedNumberOfHouseholds);
	}
		
}