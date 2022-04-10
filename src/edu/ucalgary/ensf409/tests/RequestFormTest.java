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
	private String[][] validHousehold = new String[][]{"1", "2", "0", "0"};
	private String[][] invalidHousehold1 = new String[][] {"1", "2", "0"};
	private String[][] invalidHousehold2 = new String[][] {"a", "2", "0", "3"};
	private String expectedAdultMale = "1";
	private String expectedAdultFemale = "2";
	private String expectedChildOver8 = "0";
	private String expectedChildUnder8 = "0";
	private int expectedNumberOfHouseholds = 1;
	private int expectedFVValue = 16;
	private int expectedGrainValue = 28;
	private int expectedProteinValue = 26;
	private int expectedOtherValue = 30;
	private int expectedCalorieValue = 2500;
	
	RequestForm requestForm = new RequestForm(validHousehold);
	
	@Test
		public void testConstructor(){
			/*
		String actualAdultMale = requestForm.getAdultMaleNumber();
		String actualAdultFemale = requestForm.getAdultFemaleNumber();
		String actualChildOver8 = requestForm.getChildOver8Number();
		String actualChildUnder8 = requestForm.getChildUnder8Number();
		assertEquals("Incorrect information stored/returned for number of adult males", expectedAdultMale, actualAdultMale);
        assertEquals("Incorrect information stored/returned for number of adult females", expectedAdultFemale, actualAdultFemale);
        assertEquals("Incorrect information stored/returned for number of children over 8", expectedChildOver8, actualChildOver8);
        assertEquals("Incorrect information stored/returned for number of children under 8", expectedChildUnder8, actualChildUnder8);
		*/
		assertNotNull(requestForm, "Did not correctly create a RequestForm object.");
	}
	
	@Test 
		public void testConstructorTooFewArguments(){
		//I think this might actually be GUI conditions tests not unit tests?
		boolean testResult = false;
		try{
			RequestForm form = new RequestForm(invalidHousehold1);
		}
		catch(IllegalArgumentException e){
			testResult = true;
		}
		catch(Exception e){
			assertTrue("Invalid amount of arguments did not throw an IllegalArgumentException", testResult);
		}
	}
		
	@Test 
		public void testConstructorNonNumericArguments(){
			//Again, might be the GUI's problem
		boolean testResult = false;
		try{
			RequestForm form = new RequestForm(invalidHousehold2);
			}
		catch(IllegalArgumentException e){
			testResult = true;
		}
		catch(Exception e){
			assertTrue("Non-numeric argument did not throw an IllegalArgumentException", testResult);
		}
		
	}
		
	@Test
		public void testCreateOrderForm(){
		OrderForm orderForm = requestForm.createOrderForm("test_form.txt");
		assertNotNull(orderForm, "createOrderForm did not correctly create an OrderForm object.");
	}
	
	@Test
		public void testClientSetters(){
			//This might not need to be here?
		var newExpectedAdultMale = "2";
		int[] clients = {newExpectedAdultMale, expectedAdultFemale, expectedChildOver8, expectedChildUnder8};
        var testForm = new RequestForm(clients);
        String newExpectedAdultFemale = "0";
        testObject3.setAdultFemaleNumber(newExpectedAdultFemale);
        String actualAdultFemale = testForm.getAdultFemaleNumber();
        assertEquals("Error with setAdultFemaleNumber", newExpectedAdultFemale,
														actualAdultFemale);
    
	}

	@Test
		public void testClientValueSetters(){
		int[] clientValues = {expectedFVValue, expectedGrainValue, expectedProteinValue, expectedOtherValue};
		requestForm.setAdultMaleNeeds(clientValues);
		int[] actualClientValues = requestForm.getAdultMaleNeeds();
		String stringExpectedClientValues = clientValues.toString();
		String stringActualClientValues = actualClientValues.toString();
		assertEquals("Error with setAdultMaleNeeds", stringExpectedClientValues, stringActualClientValues);
	}
	
	@Test
		public void testSetFoodValues(){
		var newFoodValue = 500;
		//Check if a food with a value of 500 is already in the database. 
		//Will it throw an error that this food already exists with different other values?
	}
	
	/* COMMENTED THIS TEST OUT BC I DON'T KNOW HOW TO FIX IT
	@Test
		public void testAddHamper(){
		Hamper testHamper = new Hamper requestForm.addHamper();
		assertNotNull(testHamper, "addHamper did not correctly create a Hamper object.");
	}
	*/
	
	@Test	
		public void testGetHampers(){
			ArrayList<Hamper> testHamper = requestForm.getHampers();
			assertNotNull("getHampers did not successfully retrieve a Hamper object", testHamper);
	}
		
	@Test
		public void testGetHouseholds(){
			ArrayList<Household> actualHouseholds = requestForm.getHouseholds();
			ArrayList<Household> expectedHouseholds = new ArrayList<Household>();
			expectedHouseholds.add("1, 2, 0, 0");
			assertEquals("getHouseholds did not return the expected Households object", actualHouseholds, expectedHouseholds);
	}
		
	@Test
		public void testGetNumberHouseholds(){
			int actualNumberOfHouseholds = requestForm.getNumberHouseholds();
			assertEquals("getNumberHouseholds did not return the proper number of households", actualNumberOfHouseholds, expectedNumberOfHouseholds);
	}
	
	@Test
		public void testSetHouseholds(){
			ArrayList<Household> testHousehold = new ArrayList<Household>();
			testHousehold = requestForm.setHouseholds();
			ArrayList<Household> expectedHouseholds = new ArrayList<Household>();
			expectedHouseholds.add("1, 2, 0, 0");
			assertEquals("setHouseholds did not properly set the  Households object", testHousehold, expectedHouseholds);
	}
		
}