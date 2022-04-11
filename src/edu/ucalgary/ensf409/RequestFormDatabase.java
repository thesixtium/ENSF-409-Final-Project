/**
 * @author Aleksander Berezowski
 * @author Danielle Jourdain
 * @author Philippa Madill
 * @version 1.4
 * @since 1.0
 */

package edu.ucalgary.ensf409;

import java.sql.*;
import java.util.*;

public class RequestFormDatabase{
	public final String DBURL;
	public final String USERNAME;
	public final String PASSWORD;
	public static HashMap<String, HashMap<String, Integer>> clientValues;
	public static HashMap<Integer, FoodData> foodValues;
	private Connection dbConnect;
	private ResultSet results;
	
	public RequestFormDatabase(String url, String username, String pw){
		this.DBURL = url;
		
		this.USERNAME = username;
		this.PASSWORD = pw;
		
	}
	
	public void initializeConnection(){
		try{
			dbConnect = DriverManager.getConnection(getDburl(), getUsername(), getPassword());
		} catch (SQLException e){
			e.printStackTrace();
		}
	}
	
	String getDburl(){
		return this.DBURL;
	}
	String getUsername(){
		return this.USERNAME;
	}
	String getPassword(){
		return this.PASSWORD;
	}
	
	/**
	This method returns the sorted caloric requirements of different 
	types of clients so they can be used by other classes.
	
	@return clientValues	The caloric requirements of each type of 
							client sorted and available for use by 
							other classes and methods.
	*/
	
	public static HashMap<String, HashMap<String, Integer>> getClientValues(){
		return clientValues;
	}
	
	/**
	This method accesses the DAILY_CLIENT_NEEDS table in the database
	to retreive the daily needed caloric values of each type of client
	and sort them accordingly so that these values can be used elsewhere
	*/
	
	public void setClientValues(){
		clientValues = new HashMap<String, HashMap<String, Integer>>();

		HashMap<String, Integer> temp = new HashMap<String, Integer>();
		try{
			Statement myStmt = dbConnect.createStatement();
			results = myStmt.executeQuery("SELECT * FROM DAILY_CLIENT_NEEDS");
			while(results.next()){
				temp.put("grain", results.getInt("WholeGrains"));
				temp.put("fv", results.getInt("FruitVeggies"));
				temp.put("protein", results.getInt("Protein"));
				temp.put("other", results.getInt("Other"));
				temp.put("calories", results.getInt("Calories"));
				clientValues.put(results.getString("Client"), temp);
			}
			myStmt.close();
		}catch(SQLException ex){
			ex.printStackTrace();
		}
	}
	
	/**
	This method retreives the HashMap of the caloric values 
	of available foods so this information can be used elsewhere.
	
	@return foodValues	A HashMap of integer and FoodData objects that 
						can be accessed elsewhere in the package.
	*/
	
	public static HashMap<Integer, FoodData> getFoodValues(){
		return foodValues;
	}
	
	/**
	This method takes the caloric values of the available foods in 
	the database and puts them in a HashMap so they're more easily 
	accessible by other classes and methods
	*/
	public void setFoodValues(){
		foodValues = new HashMap<Integer, FoodData>();
		//FoodData temp = new FoodData<>();
		try{
			Statement myStmt = dbConnect.createStatement();
			results = myStmt.executeQuery("SELECT * FROM AVAILABLE_FOOD");
			while(results.next()){
				int calories = results.getInt("Calories");
				int grainVal = ((double) (results.getInt("GrainContent") / 100)) / calories;
				int fvVal = ((double) (results.getInt("FVContent") / 100)) / calories;
				int proteinVal = ((double) (results.getInt("ProContent") / 100)) / calories;
				int otherVal = ((double) (results.getInt("Other") / 100)) / calories;
				String name = results.getString("Name");
				int idNum = results.getInt("ItemID");

				FoodData temp = new FoodData(name, fvVal, grainVal, proteinVal, otherVal);
				foodValues.put(idNum, temp);


				/*
				temp.put("grain", results.getInt("GrainContent"));
				temp.put("fv", results.getInt("FVContent"));
				temp.put("protein", results.getInt("ProContent"));
				temp.put("other", results.getInt("Other"));
				temp.put("calories", results.getInt("Calories"));
				foodValues.put(results.getString("Name"), temp);
				*/
			}
			myStmt.close();
		}catch(SQLException ex){
			ex.printStackTrace();
		}
	}
	
	/**
	This method, when provided an integer key, will remove the 
	food entry in the database associated with that key.
	
	@param key Is an integer corresponding to the ItemID
				of any particular food in the database.
	*/
	
	public void removeFood(int key){
		
		 try {
            String query = "DELETE FROM AVAILABLE_FOOD WHERE ItemID = ?";
            PreparedStatement myStmt = dbConnect.prepareStatement(query);
            myStmt.setString(1, Integer.toString(key));

            myStmt.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
		
	}
	
	/**
	 * This method closes the connection to the database.
	 */
	
	  public void close() {
        try {
            results.close();
            dbConnect.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}