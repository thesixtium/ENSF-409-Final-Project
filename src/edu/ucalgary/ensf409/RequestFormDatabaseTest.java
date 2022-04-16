/**
 * @author Philippa Madill
 * @version 1.6
 * @since 1.0
 */

package edu.ucalgary.ensf409;

import java.util.*;
import org.junit.*;
import java.sql.*;
import static org.junit.Assert.*;

public class RequestFormDatabaseTest{
	public static RequestFormDatabase requestDB = new RequestFormDatabase("jdbc:mysql://localhost/FOOD_INVENTORY","student","ensf" );	
	@BeforeClass
	public static void setupDatabase(){
	requestDB.initializeConnection();
	requestDB.setClientValues();
	requestDB.setFoodValues();
	requestDB.close();
	}
	
	/**
	This test ensures that caloric values of Client needs are set in a HashMap properly.
	*/
	@Test
	public void testSetClientValues(){
		
		HashMap<String, HashMap<String, Integer>> values = requestDB.getClientValues();
		
		assertNotNull("Client values were not properly set from the database. ", values);
	}

	/**
	This test ensures that caloric values of food are set in a HashMap properly.
	*/
	@Test
	public void testSetFoodValues(){
		HashMap<Integer, FoodData> values = requestDB.getFoodValues();
		
		assertNotNull("Food values were not properly set.", values);
	}
	
	@Test
	public void testRemoveFood(){
		/**
		This is fully database interaction and as such does not need to be tested.
		*/
	}
	
	
	
}