/**
 * @author Aleksander Berezowski
 * @author Danielle Jourdain
 * @author Philippa Madill
 * @version 1.4
 * @since 1.0
 */

package edu.ucalgary.ensf409;

import java.util.HashMap;

public class Household {
    String[] familyList; // Stores the list of family members in a given household
    Hamper familyHamper; // The hamper that goes to that specific household

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
                     String[] familyList,
                     HashMap<Integer, FoodData> availableFoods){
        initializeClients(clientData);

        // Set the internal variables
        this.familyList = familyList;
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
        AdultMale.setTotalCalories(client.get("Total Calories"));
        AdultMale.setFvPercent(client.get("FV Percent"));
        AdultMale.setGrainPercent(client.get("Grain Percent"));
        AdultMale.setProteinPercent(client.get("Protein Percent"));
        AdultMale.setOtherPercent(client.get("Other Percent"));

        client = clientData.get("Adult Female");
        AdultFemale.setTotalCalories(client.get("Total Calories"));
        AdultFemale.setFvPercent(client.get("FV Percent"));
        AdultFemale.setGrainPercent(client.get("Grain Percent"));
        AdultFemale.setProteinPercent(client.get("Protein Percent"));
        AdultFemale.setOtherPercent(client.get("Other Percent"));

        client = clientData.get("Child Over 8");
        ChildOver8.setTotalCalories(client.get("Total Calories"));
        ChildOver8.setFvPercent(client.get("FV Percent"));
        ChildOver8.setGrainPercent(client.get("Grain Percent"));
        ChildOver8.setProteinPercent(client.get("Protein Percent"));
        ChildOver8.setOtherPercent(client.get("Other Percent"));

        client = clientData.get("Child Under 8");
        ChildUnder8.setTotalCalories(client.get("Total Calories"));
        ChildUnder8.setFvPercent(client.get("FV Percent"));
        ChildUnder8.setGrainPercent(client.get("Grain Percent"));
        ChildUnder8.setProteinPercent(client.get("Protein Percent"));
        ChildUnder8.setOtherPercent(client.get("Other Percent"));
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
