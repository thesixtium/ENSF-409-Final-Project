/**
 * @author Aleksander Berezowski
 * @author Danielle Jourdain
 * @author Philippa Madill
 * @version 1.0
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
	public static HashMap<String, HashMap<String, Integer>> foodValues
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
	
	public HashMap<String, HashMap<String, Integer>> getClientValues(){
		return this.clientValues;
	}
	
	public void setClientValues(String tableName){
		clientValues = new HashMap<String, HashMap<String, Integer>>();
		HashMap<String, Integer> temp = new HashMap<String, Integer>();
		try{
			Statement myStmt = dbConnect.createStatement();
			results = myStmt.executeQuery("SELECT * FROM " + tableName);
			while(results.next()){
				temp.put("Grains", results.getInt("WholeGrains"));
				temp.put("FV", results.getInt("FruitVeggies"));
				temp.put("Protein", results.getInt("Protein"));
				temp.put("Other", results.getInt("Other"));
				temp.put("Calories", results.getInt("Calories"));
				clientValues.put(results.getString("Client"), temp);
			}
			myStmt.close();
		}catch(SQLException ex){
			ex.printStackTrace();
		}
	}
	
	public HashMap<String, HashMap<String, Integer>> getFoodValues(){
		return this.foodValues;
	}
	public void setFoodValues(String tableName){
		foodValues = new HashMap<String, HashMap<String, Integer>>;
		HashMap<String, Integer> temp = new HashMap<String, Integer>;
		try{
			Statement myStmt = dbConnect.createStatement();
			results = myStmt.executeQuery("SELECT * FROM " + tableName);
			while(results.next()){
				temp.put("Grains", results.getInt("GrainContent"));
				temp.put("FV", results.getInt("FVContent"));
				temp.put("Protein", results.getInt("ProContent"));
				temp.put("Other", results.getInt("Other"));
				temp.put("Calories", results.getInt("Calories"));
				foodValues.put(results.getString("Name"), temp);
			}
			myStmt.close();
		}catch(SQLException ex){
			ex.printStackTrace();
		}
	}
	
	  public void close() {

        try {
            results.close();
            dbConnect.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}