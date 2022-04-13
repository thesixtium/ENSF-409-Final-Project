/**
 * @author Aleksander Berezowski
 * @author Danielle Jourdain
 * @author Philippa Madill
 * @version 1.7
 * @since 1.0
 */

package edu.ucalgary.ensf409;

import java.io.*;
import java.util.*;

public class OrderForm {
    private final RequestForm ORDER;
    private  String filename;

    /**
     * Constructor for OrderForm class. Creates the OrderForm object
     * and writes the RequestForm information to a file.
     * @param formInfo The RequestForm to create a text file order form from.
     */
    public OrderForm(RequestForm formInfo) {
        this.ORDER = formInfo;
        //set the RequestForm object

        this.filename = "orderForm";
        File testFile = new File(this.filename + ".txt");
        int count = 1;
        String temp = this.filename;

        while(testFile.isFile()) {
            //if the File orderForm.txt already exists, see if orderForm1.txt exists
            //if that exists, try orderForm2.txt, and so on until a valid filename is found
            temp = this.filename + Integer.toString(count);
            testFile = new File(temp + ".txt");
            count++;
        }

        this.filename = temp + ".txt";
        //set the final filename with the .txt extension
    }

    /**
     * This method creates a text file containing the information for all of the
     * households and hampers in the current order.
     */
    public void createForm() {
        StringBuilder formResult = new StringBuilder();
        formResult.append("The Peanut Butter Scenario Food Bank\nHamper Order Form\n\n");
        formResult.append("Name:\nDate:\n\n");
        formResult.append("Original Request:\n");
        //add the header info to the result string

        ArrayList<Household> households = this.ORDER.getHouseholds();
        Iterator<Household> houseIter = households.iterator();
        int counter = 1;
        while(houseIter.hasNext()) {
            //iterate through the households, printing the info about each
            //on a new line

            Household curHouse = houseIter.next();
            formResult.append("Household " + counter + ": ");
            formResult.append(countHousehold(curHouse) + "\n");
            counter++;
        }

        ArrayList<Hamper> hampers = this.ORDER.getHampers();
        Iterator<Hamper> hamperIter = hampers.iterator();
        counter = 1;
        while(hamperIter.hasNext()) {
            //iterate through the hampers 
            formResult.append("\nHamper " + counter + " items:\n");
            Hamper tempHamper = hamperIter.next();
            
            if(tempHamper.getException() == null) {
                formResult.append(successfulHamperCreation(tempHamper));
                //if the hamper creation was successful, add that info to the result
            }
            else {
                formResult.append(unsuccessfulHamperCreation(tempHamper));
                //otherwise add what the hamper was short by to the result
            }
            formResult.append("\n");
			counter++;
        }


        formResult.delete(formResult.length() - 2, formResult.length());
        //remove the extra newline characters, then write the contents to the file
        writeToFile(formResult.toString());
    }

    /**
     * This method counts the number of each type of family member.
     * @param house The household to count members for.
     * @return A string containing all of the information for the household
     */
    private String countHousehold(Household house) {
        int[] values = new int[4];
        String[] family = house.getFamilyList();

        for(String a: family) {
            if(a.equals("Adult Female")) {
                values[0]++;
            }
            else if(a.equals("Adult Male")) {
                values[1]++;
            }
            else if(a.equals("Child Over 8")) {
                values[2]++;
            }
            else if(a.equals("Child Under 8")) {
                values[3]++;
            }
            //iterate through family and count amount of each type of person
        }

        StringBuilder result = new StringBuilder();
        if(values[0] != 0) {
            result.append(values[0] + " Adult Female, ");
        }
        if(values[1] != 0) {
            result.append(values[1] + " Adult Male, ");
        }
        if(values[2] != 0) {
            result.append(values[2] + " Child over 8, ");
        }
        if(values[3] != 0) {
            result.append(values[3] + " Child under 8, ");
        }
        //if there is at least one member of each type add it to a StringBuilder

        if(result.length() >= 2) {
            result.delete(result.length() - 2, result.length());
            //delete the comma and space at the end of the line
        }

        return result.toString();
    }

    /**
     * This method creates a string contiaining the info for a hamper that was
     * successfully created.
     * @param hamper The hamper to get information for.
     * @return A string containing the hamper information.
     */
    private String successfulHamperCreation(Hamper hamper) {
        StringBuilder result = new StringBuilder();
        HashMap<Integer, FoodData> foods = hamper.getFoodQuantities();

        for(Map.Entry<Integer, FoodData> entry: foods.entrySet()) {
            //iterate through the HashMap of the foods
            int id = entry.getKey();
            String foodName = entry.getValue().getName();
            result.append(id + "\t" + foodName + "\n");
            //add the food ID number and food name to the result
        }

        return result.toString();
    }

    /**
     * This method creates a string containing the info for a hamper that was
     * unsuccessfullly created.
     * @param hamper The hamper to get informtaion for.
     * @return A string containing what the hamper is short by.
     */
    private String unsuccessfulHamperCreation(Hamper hamper) {
        StringBuilder result = new StringBuilder();
        NotEnoughFoodException shortBy = hamper.getException();

        result.append("Unable to create a hamper. Short by at least ")
                .append(shortBy.getAmount())
                .append(" ")
                .append(shortBy.getGoodType())
                .append(" calories.\n");

        return result.toString();
    }

    /**
     * This method writes a message to a text file.
     * @param message The message to write to the file.
     */
    private void writeToFile(String message) {
        try {
            FileWriter writer = new FileWriter(filename);
            writer.write(message);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Getter method for filename variable.
     * @return value of filename
     */
    public String getFilename() {
        return this.filename;
    }
}
