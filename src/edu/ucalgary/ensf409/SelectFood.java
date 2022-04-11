/**
 * @author Aleksander Berezowski
 * @author Danielle Jourdain
 * @author Philippa Madill
 * @version 1.6
 * @since 1.0
 */

package edu.ucalgary.ensf409;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

public class SelectFood {

    /**
     * Method to calculate the foods that should be in a hamper
     * @param foods Is a hashmap with a key of the identifying integer of
     *              a food (this should be the primary key from the initial
     *              SQL table) and the value is a FoodData object, which
     *              stores all needed values that we could want for a
     *              specific food.
     * @param needs Is a HouseholdNeeds object that contains the calorie
     *              needs for an entire household.
     * @return A hashmap that contains the foods' integer primary key
     * @throws NotEnoughFoodException if not enough food exists
     */
    public HashMap<Integer, FoodData> calculateFoods(HashMap<Integer, FoodData> foods, HouseholdNeeds needs) throws NotEnoughFoodException {
        String[] foodTypes = {"fv", "grain", "protein", "other"};
        HashMap<Integer, FoodData> returnFoods = new HashMap<>();

        // Main algorithm, iterates through food types while checking the best food for that
        // specific food group. Finishes when the needs are satisfied
        int count = 0;
        while(!needs.isSatisfied()){
            for (String type : foodTypes){
                System.out.println();
                System.out.println(" C O U N T: " + count++);
                System.out.println("Foods available:");
                for(Integer i: foods.keySet())
                    System.out.print(i + ", ");
                System.out.println();
                System.out.println("Foods using:");
                for(Integer i: returnFoods.keySet())
                    System.out.print(i + ", ");
                System.out.println();
                System.out.println("Old Needs:");
                System.out.println("\tFV:\t"+needs.getFvCalories());
                System.out.println("\tGrain:\t"+needs.getGrainCalories());
                System.out.println("\tProtein: "+needs.getProteinCalories());
                System.out.println("\tOther:\t"+needs.getOtherCalories());

                // Figure out the best food to add to the hamper
                FoodData foodToAdd = mostEfficientFood(returnFoods, foods, needs, type);
                Integer key = 0;
                for(Integer i: foods.keySet())
                    if(foods.get(i) == foodToAdd)
                        key = i;

                // Add food to return foods that are in the hamper
                returnFoods.put(key, foodToAdd);

                System.out.println("Food To Add:\t"+foodToAdd.getName());
                System.out.println("\t- FV:" + foodToAdd.getFv());
                System.out.println("\t- GR:" + foodToAdd.getGrain());
                System.out.println("\t- PR:" + foodToAdd.getProtein());
                System.out.println("\t- OT:" + foodToAdd.getOther());


                // Update household needs
                needs.changeFvCalories(-1 * foodToAdd.getFv());
                needs.changeGrainCalories(-1 * foodToAdd.getGrain());
                needs.changeProteinCalories(-1 * foodToAdd.getProtein());
                needs.changeOtherCalories(-1 * foodToAdd.getOther());
                System.out.println("New Needs:");
                System.out.println("\tFV:\t"+needs.getFvCalories());
                System.out.println("\tGrain:\t"+needs.getGrainCalories());
                System.out.println("\tProtein: "+needs.getProteinCalories());
                System.out.println("\tOther:\t"+needs.getOtherCalories());

                // Remove food from available foods

                foods.remove(key);
            }
        }

        System.out.println("Returning");

        return returnFoods;
    }

    /**
     * Helper method to find the most efficient food to add to the hamper
     * @param currentReturnFoods
     * @param foods Is a hashmap with a key of the identifying integer of
     *              a food (this should be the primary key from the initial
     *              SQL table) and the value is a FoodData object, which
     *              stores all needed values that we could want for a
     *              specific food.
     * @param needs Is a HouseholdNeeds object that contains the calorie
     *              needs for an entire household.
     * @param currentlyWorkingOn
     * @return
     * @throws NotEnoughFoodException
     */
    private FoodData mostEfficientFood(HashMap<Integer, FoodData> currentReturnFoods, HashMap<Integer, FoodData> foods,
                                  HouseholdNeeds needs, String currentlyWorkingOn) throws NotEnoughFoodException {
        System.out.println("\tmostEfficientFood");
        int currentFoodCalories;
        ArrayList<FoodData> sortedFoods = new ArrayList<>(foods.values());
        int returnValue = 0;

        // Determines which category currently working on
        if(currentlyWorkingOn.equals("fv")){
            System.out.println("\tWorking on FV");
            // Get the calorie values for the current category
            currentFoodCalories = needs.getFvCalories();
            System.out.println("\tCurrent Calories: " + currentFoodCalories);
            // Sort the foods based on the current category as primary, and the inverse of
            // total calories in a food as secondary
            sortedFoods.sort(new Comparator<FoodData>() {
                // Comparator logic
                public int compare(FoodData o1, FoodData o2) {
                    if (o1.getFv() == o2.getFv())
                        // Solves the peanut butter scenario
                        if(o1.getSum() == o2.getSum())
                            return 0;
                        else
                            return o1.getSum() < o2.getSum() ? -1 : 1;
                    return o1.getFv() > o2.getFv() ? -1 : 1;
                }
            });
            System.out.println("\tComparator made");
            // If the first food (ie the top food) has 0 for the category, then know
            // there is no more food
            // Select the food with the highest value that is under the needed calories, unless
            // there is no value under the needed calories, then selects the least food to
            // add
            while(sortedFoods.get(returnValue).getFv() > currentFoodCalories){
                returnValue++;
                if (sortedFoods.get(returnValue).getFv() == 0){
                    returnValue--;
                    break;
                }
            }
            System.out.println("\tReturn value: " + returnValue);
        } else if(currentlyWorkingOn.equals("grain")){
            System.out.println("\tWorking on Grain");
            currentFoodCalories = needs.getGrainCalories();
            System.out.println("\tCurrent Calories: " + currentFoodCalories);
            sortedFoods.sort(new Comparator<FoodData>() {
                public int compare(FoodData o1, FoodData o2) {
                    if (o1.getGrain() == o2.getGrain())
                        if(o1.getSum() == o2.getSum())
                            return 0;
                        else
                            return o1.getSum() < o2.getSum() ? -1 : 1;
                    return o1.getGrain() > o2.getGrain() ? -1 : 1;
                }
            });
            // Select the food with the highest value that is under the needed calories, unless
            // there is no value under the needed calories, then selects the least food to
            // add
            while(sortedFoods.get(returnValue).getGrain() > currentFoodCalories){
                returnValue++;
                if (sortedFoods.get(returnValue).getGrain() == 0){
                    returnValue--;
                    break;
                }
            }
            System.out.println("\tReturn value: " + returnValue);
        } else if(currentlyWorkingOn.equals("protein")){
            System.out.println("\tWorking on Protein");
            currentFoodCalories = needs.getProteinCalories();
            System.out.println("\tCurrent Calories: " + currentFoodCalories);
            sortedFoods.sort(new Comparator<FoodData>() {
                public int compare(FoodData o1, FoodData o2) {
                    if (o1.getProtein() == o2.getProtein())
                        if(o1.getSum() == o2.getSum())
                            return 0;
                        else
                            return o1.getSum() < o2.getSum() ? -1 : 1;
                    return o1.getProtein() > o2.getProtein() ? -1 : 1;
                }
            });
            // Select the food with the highest value that is under the needed calories, unless
            // there is no value under the needed calories, then selects the least food to
            // add
            while(sortedFoods.get(returnValue).getProtein() > currentFoodCalories){
                returnValue++;
                if (sortedFoods.get(returnValue).getProtein() == 0){
                    returnValue--;
                    break;
                }
            }
            System.out.println("\tReturn value: " + returnValue);
        } else {
            System.out.println("\tWorking on other");
            currentFoodCalories = needs.getOtherCalories();
            System.out.println("\tCurrent Calories: " + currentFoodCalories);
            sortedFoods.sort(new Comparator<FoodData>() {
                public int compare(FoodData o1, FoodData o2) {
                    if (o1.getOther() == o2.getOther())
                        if(o1.getSum() == o2.getSum())
                            return 0;
                        else
                            return o1.getSum() < o2.getSum() ? -1 : 1;
                    return o1.getOther() > o2.getOther() ? -1 : 1;
                }
            });
            // If the first food (ie the top food) has 0 for the category, then know
            // there is no more food
            if (sortedFoods.get(returnValue).getOther() == 0){
                throw new NotEnoughFoodException("other", currentFoodCalories);
            }
            // Select the food with the highest value that is under the needed calories, unless
            // there is no value under the needed calories, then selects the least food to
            // add
            while(sortedFoods.get(returnValue).getOther() > currentFoodCalories){
                returnValue++;
                if (sortedFoods.get(returnValue).getOther() == 0){
                    returnValue--;
                    break;
                }
            }
        }
        return sortedFoods.get(returnValue);
    }

    /**
     * Calculate and return the waste calories
     * @param foods Is a hashmap with a key of the identifying integer of
     *              a food (this should be the primary key from the initial
     *              SQL table) and the value is a FoodData object, which
     *              stores all needed values that we could want for a
     *              specific food.
     * @param needs Is a HouseholdNeeds object that contains the calorie
     *              needs for an entire household.
     * @return
     */
    public HashMap<String, Integer> calculateWaste(HashMap<Integer, FoodData> foods, HouseholdNeeds needs){
        int grains = 0;
        int protein = 0;
        int fv = 0;
        int other = 0;
        HashMap<String, Integer> returnMap = new HashMap<>();

        if(needs == null)
            System.out.println("Needs is null");

        if(foods == null)
            System.out.println("Foods is null");

        // Calculate total values for each food category
        for (Integer i : foods.keySet()){
            grains += foods.get(i).getGrain();
            protein += foods.get(i).getProtein();
            fv += foods.get(i).getFv();
            other += foods.get(i).getOther();
        }

        // Assemble the return hashmap
        returnMap.put("grain", grains - needs.getGrainCalories());
        returnMap.put("fv", fv - needs.getFvCalories());
        returnMap.put("other", other - needs.getOtherCalories());
        returnMap.put("protein", protein - needs.getProteinCalories());

        return returnMap;
    }
}
