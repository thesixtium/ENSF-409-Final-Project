/**
 * @author Aleksander Berezowski
 * @author Danielle Jourdain
 * @author Philippa Madill
 * @version 1.2
 * @since 1.0
 */

package edu.ucalgary.ensf409;

import java.io.FileWriter;
import java.util.*;

public class OrderForm {
    private final RequestForm ORDER;

    /**
     * Constructor for OrderForm class. Creates the OrderForm object
     * and writes the RequestForm information to a file.
     * @param formInfo The RequestForm to create a text file order form from.
     */
    public OrderForm(RequestForm formInfo) {
        this.ORDER = formInfo;
    }

    public void createForm(String filename) {
        StringBuilder formResult = new StringBuilder();
        formResult.append("Group 60 Food Bank\nHamper Order Form\n\n");
        formResult.append("Name:\nDate:\n");
        formResult.append("Original Request:\n");
        //add the header info to the result string

        ArrayList<Household> households = this.ORDER.getHouseholds();
        for(int i = 0; i < households.size(); i++) {
            formResult.append("Hamper " + i + ":\t");
            formResult.append(households.get(i).toString() + "\n");
            //add each household to the form
        }
        formResult.append("\n");

        ArrayList<Hamper> hampers = this.ORDER.getHampers();
        Iterator<Hamper> hamperIter = hampers.iterator();
        //get all of the hampers for this order, and begin to iterate over it

        while(hamperIter.hasNext()) {
            Hamper tempHamper = hamperIter.next();
            //get the next Hamper in the list

            HashMap<String, Integer> shortBy = tempHamper.getShortBy();
            //get the calories the hamper is short by. if there is enough 
            //food, this HashMap should be null

            if(shortBy == null) {
                formResult.append(successfulHamperCreation(tempHamper));
                //if there is enough food, add the food to the form
            }
            else {
                formResult.append(unsuccessfulHamperCreation(tempHamper));
                //if there isn't enough, display how much the bank is short by
            }
        }

        writeToFile(formResult.toString(), filename);
        //write the result to the a text file
    }

    private String successfulHamperCreation(Hamper hamper) {
        StringBuilder result = new StringBuilder();
        HashMap<Integer, HashMap<String, Integer>> foodQuantities = hamper.getFoodQuantities();
        
        return null;
    }

    private String unsuccessfulHamperCreation(Hamper hamper) {
        return null;
    }

    private void writeToFile(String message, String filename) {
        
    }
}
