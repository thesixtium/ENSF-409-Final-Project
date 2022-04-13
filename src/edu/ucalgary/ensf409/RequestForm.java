/**
 * @author Aleksander Berezowski
 * @author Danielle Jourdain
 * @author Philippa Madill
 * @version 1.2
 * @since 1.0
 */

package edu.ucalgary.ensf409;

import java.util.*;

public class RequestForm {
    private int numHouseholds;
    private final ArrayList<Household> HOUSEHOLDS;

    /**
     * Constructor for RequestForm object. Creates one or more families from values given
     * through an ArrayList of int arrays.
     * @param families An ArrayList<int[]> where each int[] has length 4 with values
     * corresponding to the number of each type of person in the family.
     */
    public RequestForm(ArrayList<int[]> families) {
        this.numHouseholds = families.size();
        //set the number of households from the number of families.

        ArrayList<Household> temp = new ArrayList<>();

        for(int[] item: families) {
            ArrayList<String> family = new ArrayList<>();
            //create a new ArrayList to hold the current family.

            if(item[0] != 0) {
                //if there is a number other than 0 in the first index, add the
                    //appropriate number of adult females
                for(int i = 0; i < item[0]; i++) {
                    family.add("Adult Female");
                }
            }
            if(item[1] != 0) {
                //if there is a number other than 0 in the second index, add the
                    //appropriate number of adult males
                for(int i = 0; i < item[1]; i++) {
                    family.add("Adult Male");
                }
            }
            if(item[2] != 0) {
                //if there is a number other than 0 in the third index, add the
                    //appropriate number of children over 8
                for(int i = 0; i < item[2]; i++) {
                    family.add("Child Over 8");
                }
            }
            if(item[3] != 0) {
                //if there is a number other than 0 in the fourth index, add the
                    //appropriate number of children under 8
                for(int i = 0; i < item[3]; i++) {
                    family.add("Child Under 8");
                }
            }

            HashMap<String, HashMap<String, Integer>> clientData = RequestFormDatabase.getClientValues();
            HashMap<Integer, FoodData> foodValues = updateFoodValues();
            for(Integer i: foodValues.keySet()) {
                System.out.println(i + "\t" + foodValues.get(i));
            }
            Household newHouse = new Household(clientData, family, foodValues);
            //create a household from the new family
            temp.add(newHouse);
            //add the new household to the list of households
        }

        this.HOUSEHOLDS = temp;
    }

    /**
     * Helper method to get updated food quantities for RequestForm to use.
     * Creates another database connection and gets the updated data from the table.
     * @return The food stored in the database as a HashMap of Integers and FoodData objects
     */
    private HashMap<Integer, FoodData> updateFoodValues() {
        RequestFormDatabase requestForm = new RequestFormDatabase("jdbc:mysql://localhost/FOOD_INVENTORY","student","ensf" );
		requestForm.initializeConnection();
		requestForm.setFoodValues();
		requestForm.setClientValues();

        return RequestFormDatabase.getFoodValues();
    }

    /**
     * This method gets a list of all the hampers that will be created for the RequestForm
     * and returns it in the form of an ArrayList.
     * @return An ArrayList of Hampers containing all of the Hampers that are needed
     * for the current Request Form.
     */
    public ArrayList<Hamper> getHampers() {
        ArrayList<Hamper> result = new ArrayList<>();

        for(Household each: this.HOUSEHOLDS) {
            result.add(each.getFamilyHamper());
            //add the hamper from each family to the result
        }

        return result;
    }

    /**
     * Getter method for Households in RequestForm.
     * @return An ArrayList of Households.
     */
    public ArrayList<Household> getHouseholds() {
        return this.HOUSEHOLDS;
    }

    /**
     * Getter method for number of households.
     * @return An integer representing number of households. 
     */
    public int getNumHouseholds() {
        return this.numHouseholds;
    }
}
