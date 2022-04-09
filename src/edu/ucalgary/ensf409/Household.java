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
    String[] familyList;
    Hamper familyHamper;

    public Household(HashMap<String, HashMap<String, Integer>> clientData,
                     String[] familyList,
                     HashMap<Integer, FoodData> avaliableFoods){
        initializeClients(clientData);
        this.familyList = familyList;
        this.familyHamper = new Hamper(calculateNeeds(), avaliableFoods);
    }

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

    public Hamper getFamilyHamper() {
        return familyHamper;
    }

    public String[] getFamilyList() {
        return familyList;
    }
}
