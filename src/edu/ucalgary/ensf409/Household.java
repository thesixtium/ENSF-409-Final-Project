/**
 * @author Aleksander Berezowski
 * @author Danielle Jourdain
 * @author Philippa Madill
 * @version 1.4
 * @since 1.0
 */

package edu.ucalgary.ensf409;

import java.util.ArrayList;
import java.util.HashMap;

public class Household {
    private String[] familyList; // Stores the list of family members in a given household
    private Hamper familyHamper; // The hamper that goes to that specific household

    /**
     * Backup Constructor for Household class
     * @param exception Is the error
     * @param familyList Is an array of Strings specifying the family. The possible
     *                   Strings that can be included in this array is "Adult Male",
     *                   "Adult Female", "Child Under 8", and "Child Over 8".
     */
    public Household(NotEnoughFoodException exception, ArrayList<String> familyList){
        this.familyList = familyList.toArray(new String[0]);
        this.familyHamper = new Hamper(exception);
    }

    /**
     * Constructor for Household class
     * @param clientData Is a hashmap with a String key identifying the client name,
     *                   and the value being a secondary hashmap. For the secondary
     *                   hashmap the String key is the name of the food category
     *                   (ie. other) and the value being the integer value.
     * @param familyList Is an array of Strings specifying the family. The possible
     *                   Strings that can be included in this array is "Adult Male",
     *                   "Adult Female", "Child Under 8", and "Child Over 8".
     * @param availableFoods Is a hashmap with a key of the identifying integer of
     *                       a food (this should be the primary key from the initial
     *                       SQL table) and the value is a FoodData object, which
     *                       stores all needed values that we could want for a
     *                       specific food.
     */
    public Household(HashMap<String, HashMap<String, Integer>> clientData,
                     ArrayList<String> familyList,
                     HashMap<Integer, FoodData> availableFoods) throws NotEnoughFoodException {
        initializeClients(clientData);

        // Set the internal variables
        this.familyList = familyList.toArray(new String[0]);
        this.familyHamper = new Hamper(calculateNeeds(), availableFoods);
    }

    /**
     * Method to initialize all the static clients so that we can use them to
     * pull values for each category (ei. other).
     *
     * @param clientData Is a hashmap with a String key identifying the client name,
     *                   and the value being a secondary hashmap. For the secondary
     *                   hashmap the String key is the name of the food category
     *                   (ie. other) and the value being the integer value.
     */
    private void initializeClients(HashMap<String, HashMap<String, Integer>> clientData){
        HashMap<String, Integer> client = clientData.get("Adult Male");

        AdultMale.setTotalCalories(client.get("calories"));
        AdultMale.setFvPercent(client.get("fv"));
        AdultMale.setGrainPercent(client.get("grain"));
        AdultMale.setProteinPercent(client.get("protein"));
        AdultMale.setOtherPercent(client.get("other"));

        client = clientData.get("Adult Female");
        AdultFemale.setTotalCalories(client.get("calories"));
        AdultFemale.setFvPercent(client.get("fv"));
        AdultFemale.setGrainPercent(client.get("grain"));
        AdultFemale.setProteinPercent(client.get("protein"));
        AdultFemale.setOtherPercent(client.get("other"));

        client = clientData.get("Child over 8");
        ChildOver8.setTotalCalories(client.get("calories"));
        ChildOver8.setFvPercent(client.get("fv"));
        ChildOver8.setGrainPercent(client.get("grain"));
        ChildOver8.setProteinPercent(client.get("protein"));
        ChildOver8.setOtherPercent(client.get("other"));

        client = clientData.get("Child under 8");
        ChildUnder8.setTotalCalories(client.get("calories"));
        ChildUnder8.setFvPercent(client.get("fv"));
        ChildUnder8.setGrainPercent(client.get("grain"));
        ChildUnder8.setProteinPercent(client.get("protein"));
        ChildUnder8.setOtherPercent(client.get("other"));
    }

    /**
     * Method to calculate the needs of a household given a String array.
     *
     * @return A HouseholdNeeds object that is then passed to a Hamper initializer.
     */
    private HouseholdNeeds calculateNeeds(){
        HouseholdNeeds returnNeeds = new HouseholdNeeds();
        for(String i : familyList){
            if (i.equals("Adult Male")){
                returnNeeds.changeFvCalories(AdultMale.getFvCalories());
                returnNeeds.changeGrainCalories(AdultMale.getGrainCalories());
                returnNeeds.changeProteinCalories(AdultMale.getProteinCalories());
                returnNeeds.changeOtherCalories(AdultMale.getOtherCalories());
            } else if (i.equals("Adult Female")){
                returnNeeds.changeFvCalories(AdultFemale.getFvCalories());
                returnNeeds.changeGrainCalories(AdultFemale.getGrainCalories());
                returnNeeds.changeProteinCalories(AdultFemale.getProteinCalories());
                returnNeeds.changeOtherCalories(AdultFemale.getOtherCalories());
            } else if (i.equals("Child Over 8")){
                returnNeeds.changeFvCalories(ChildOver8.getFvCalories());
                returnNeeds.changeGrainCalories(ChildOver8.getGrainCalories());
                returnNeeds.changeProteinCalories(ChildOver8.getProteinCalories());
                returnNeeds.changeOtherCalories(ChildOver8.getOtherCalories());
            } else {
                returnNeeds.changeFvCalories(ChildUnder8.getFvCalories());
                returnNeeds.changeGrainCalories(ChildUnder8.getGrainCalories());
                returnNeeds.changeProteinCalories(ChildUnder8.getProteinCalories());
                returnNeeds.changeOtherCalories(ChildUnder8.getOtherCalories());
            }
        }
        return returnNeeds;
    }

    /**
     * Getter to return a household's Hamper
     * @return Hamper object
     */
    public Hamper getFamilyHamper() {
        return familyHamper;
    }

    /**
     * Getter to return a household's family list as a String array
     * @return String[] familyList
     */
    public String[] getFamilyList() {
        return familyList;
    }
}
