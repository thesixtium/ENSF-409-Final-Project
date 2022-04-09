/**
 * @author Aleksander Berezowski
 * @author Danielle Jourdain
 * @author Philippa Madill
 * @version 1.2
 * @since 1.0
 */

package edu.ucalgary.ensf409;
import java.util.*;
import java.util.ArrayList;

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

        for(int[] item: families) {
            ArrayList<Object> family = new ArrayList<>();
            //create a new ArrayList to hold the current family.

            if(item[0] != 0) {
                //if there is a number other than 0 in the first index, add the
                    //appropriate number of adult females
                for(int i = 0; i < item[0]; i++) {
                    family.add(new AdultFemale());
                }
            }
            if(item[1] != 0) {
                //if there is a number other than 0 in the second index, add the
                    //appropriate number of adult males
                for(int i = 0; i < item[1]; i++) {
                    family.add(new AdultMale());
                }
            }
            if(item[2] != 0) {
                //if there is a number other than 0 in the third index, add the
                    //appropriate number of children over 8
                for(int i = 0; i < item[2]; i++) {
                    family.add(new ChildOver8());
                }
            }
            if(item[3] != 0) {
                //if there is a number other than 0 in the fourth index, add the
                    //appropriate number of children under 8
                for(int i = 0; i < item[3]; i++) {
                    family.add(new ChildUnder8());
                }
            }

            Household newHouse = new Household(family);
            //create a household from the new family
            this.households.add(newHouse);
            //add the new household to the list of households
        }
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
