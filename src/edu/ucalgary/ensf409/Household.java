/**
 * @author Aleksander Berezowski
 * @author Danielle Jourdain
 * @author Philippa Madill
 * @version 1.0
 * @since 1.0
 */

package edu.ucalgary.ensf409;

import java.util.ArrayList;

public class Household {
    private ArrayList<Object> listOfFamilies = new ArrayList<>();
    private Hamper familyHamper;

    Household(ArrayList<Object> inputFamilies, FoodNeeds needs){
        this.listOfFamilies = inputFamilies;
        this.familyHamper = new Hamper(needs);
    }

    public ArrayList<Object> getListOfFamilies() {
        return this.listOfFamilies;
    }

    public Hamper getFamilyHamper() {
        this.makeHamper();
        return this.familyHamper;
    }

    private void makeHamper(){

    }
}
