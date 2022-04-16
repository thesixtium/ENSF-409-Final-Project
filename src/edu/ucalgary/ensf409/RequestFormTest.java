/**
 * @author Philippa Madill
 * @version 1.3
 * @since 1.0
 */


package edu.ucalgary.ensf409;

import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;

/**
REFRESH DATABASE BEFORE RUNNING!!
*/


public class RequestFormTest{
	public ArrayList<int[]> validHousehold = new ArrayList<>();
	public int[] validEntries = {1, 1, 1, 1};
	private ArrayList<int[]> invalidHousehold = new ArrayList<>();
	public int[] tooFewEntries = {1, 1, 1};
	private int expectedNumberOfHouseholds = 1;
	private HashMap<String, HashMap<String, Integer>> clientData = new HashMap<String, HashMap<String, Integer>>();
	
	
	@Test
		public void testConstructor(){
			validHousehold.add(validEntries);
			RequestForm requestForm = new RequestForm(validHousehold);
		assertNotNull( "Did not correctly create a RequestForm object.", requestForm);
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
		
	/**
	This test ensures that updateFoodValues fills a HashMap of
	Integer and FoodData objects.
	*/
	@Test
		public void testUpdateFoodValues(){
			RequestForm requestForm = new RequestForm(validHousehold);
			HashMap<Integer, FoodData> values = new HashMap<Integer, FoodData>();
			values = requestForm.updateFoodValues();
			assertNotNull("updateFoodValues did not properly fill a HashMap of Integer and FoodData objects", values);
		}
    
	/**
	This test ensures the getHampers method returns a Hamper object.
	*/
	@Test	
		public void testGetHampers(){
			RequestForm requestForm = new RequestForm(validHousehold);
			ArrayList<Hamper> testHamper = requestForm.getHampers();
			assertNotNull("getHampers did not successfully retrieve a Hamper object", testHamper);
	}
	
	/**
	This test ensures that the getHouseholds method works correctly.
	*/
	@Test
		public void testGetHouseholds() throws NotEnoughFoodException{
			
			clientData = RequestFormDatabase.getClientValues();
			HashMap<Integer, FoodData> foodValues = RequestForm.updateFoodValues();
			validHousehold.add(validEntries);

			RequestForm requestForm = new RequestForm(validHousehold);
			ArrayList<Household> expectedHouseholds = new ArrayList<>();
			ArrayList<String> family = new ArrayList<>();
			family.add("Adult Female");
			family.add("Adult Male");
			family.add("Child Over 8");
			family.add("Child Under 8");

			Household addedHousehold = new Household(clientData, family, foodValues);
			expectedHouseholds.add(addedHousehold);
			ArrayList<Household> actualHouseholds = requestForm.getHouseholds();

			assertEquals("Number of households is incorrect", 1, requestForm.getNumHouseholds());
			//make sure the number of households is correct before continuing

			Household expected = expectedHouseholds.get(0);
			Household actual = actualHouseholds.get(0);
			assertArrayEquals("Families contained in households are not the same", expected.getFamilyList(), actual.getFamilyList());
	}
	
	@Test 
		public void testGetHouseholdsNotNull() throws NotEnoughFoodException {
			clientData = RequestFormDatabase.getClientValues();
			HashMap<Integer, FoodData> foodValues = RequestForm.updateFoodValues();
			RequestForm requestForm = new RequestForm(validHousehold);
			ArrayList<Household> actualHouseholds = requestForm.getHouseholds();
			assertNotNull("getHouseholds created a null Household object", actualHouseholds);
		}
		
	@Test
		public void testGetNumberHouseholds(){
			RequestForm requestForm = new RequestForm(validHousehold);
			/* 
			add one because the number of indexes of the ArrayList of households
			is one less than the amount of entries
			*/
			int actualNumberOfHouseholds = requestForm.getNumHouseholds()+1;
			assertEquals("getNumberHouseholds did not return the proper number of households", expectedNumberOfHouseholds, actualNumberOfHouseholds);
	}
	
	  @BeforeClass
    public static void setupDatabase() {
        RequestFormDatabase database = new RequestFormDatabase("jdbc:mysql://localhost/FOOD_INVENTORY","student","ensf" );
        database.initializeConnection();
		database.setFoodValues();
		database.setClientValues();
		database.close();
    }
		
}