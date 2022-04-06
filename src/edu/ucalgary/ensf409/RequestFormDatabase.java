/**
 * @author Aleksander Berezowski
 * @author Danielle Jourdain
 * @author Philippa Madill
 * @version 1.0
 * @since 1.0
 */

package edu.ucalgary.ensf409;

import java.sql.*;
import java.util.*

public class RequestFormDatabase{
	public final String DBURL;
	public final String USERNAME;
	public final String PASSWORD;
	
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
	
	/*public int selectAllGrainNeeds(String tableName){
		
	}
	
	public int selectAllFVNeeds(String tableName){
		
	}
	
	public int selectAllProteinNeeds(String tableName){
		
	}
	
	public int selectAllOtherNeeds(String tableName){
		
	}
	
	public int selectAllCalorieNeeds(String tableName){
		
	}*/
	
	public HashMap<String, HashMap<String, Integer>> setClientValues(String tableName){
		HashMap<String, HashMap<String, Integer>> clientValues = new HashMap<String, HashMap<String, Integer>>;
		
		try{
			Statement myStmt = dbConnect.createStatement();
			results = myStmt.executeQuery("SELECT * FROM " + tableName);
			while(results.next()){
				clientValues.put(results.getString("Client"));
			}
		}
	}
	
}