/**
 * @author Aleksander Berezowski
 * @author Danielle Jourdain
 * @author Philippa Madill
 * @version 1.6
 * @since 1.0
 */

package edu.ucalgary.ensf409;

import java.util.*;

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
    public static HashMap<Integer, FoodData> calculateFoods(HashMap<Integer, FoodData> foods, HouseholdNeeds needs) throws NotEnoughFoodException {
        String[] foodTypes = {"fv", "grain", "protein", "other"};
        HashMap<Integer, FoodData> returnFoods = new HashMap<>();
        HouseholdNeeds wasteNeeds = new HouseholdNeeds();
        wasteNeeds.changeFvCalories(needs.getFvCalories());
        wasteNeeds.changeGrainCalories(needs.getGrainCalories());
        wasteNeeds.changeProteinCalories(needs.getProteinCalories());
        wasteNeeds.changeOtherCalories(needs.getOtherCalories());

        // Main algorithm, iterates through food types while checking the best food for that
        // specific food group. Finishes when the needs are satisfied
        while(!needs.isSatisfied()){
            for (String type : foodTypes){
                if(needs.getFvCalories() <= 0 && type.equals("fv"))
                    continue;
                if(needs.getGrainCalories() <= 0 && type.equals("grain"))
                    continue;
                if(needs.getProteinCalories() <= 0 && type.equals("protein"))
                    continue;
                if(needs.getOtherCalories() <= 0 && type.equals("other"))
                    continue;

                // Figure out the best food to add to the hamper
                FoodData foodToAdd = mostEfficientFood(returnFoods, foods, needs, type);
                Integer key = 0;
                for(Integer i: foods.keySet())
                    if(foods.get(i) == foodToAdd)
                        key = i;

                // Add food to return foods that are in the hamper
                returnFoods.put(key, foodToAdd);

                // Update household needs
                needs.changeFvCalories(-1 * foodToAdd.getFv());
                needs.changeGrainCalories(-1 * foodToAdd.getGrain());
                needs.changeProteinCalories(-1 * foodToAdd.getProtein());
                needs.changeOtherCalories(-1 * foodToAdd.getOther());

                // Remove food from available foods
                foods.remove(key);
            }
        }

        /*boolean flag = true;
        while(flag){
            Integer remove = -1;
            HashMap<String, Integer> waste = calculateWaste(returnFoods, wasteNeeds);
            for(Integer i : returnFoods.keySet()) {
                if (returnFoods.get(i).getFv() > waste.get("fv") &&
                        returnFoods.get(i).getGrain() > waste.get("grain") &&
                        returnFoods.get(i).getProtein() > waste.get("protein") &&
                        returnFoods.get(i).getOther() > waste.get("other")) {
                    remove = i;
                }
            }
                if(remove != -1){
                    returnFoods.remove(remove);
                } else
                    flag = false;
        }*/

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
    private static FoodData mostEfficientFood(HashMap<Integer, FoodData> currentReturnFoods, HashMap<Integer, FoodData> foods,
                                  HouseholdNeeds needs, String currentlyWorkingOn) throws NotEnoughFoodException {
        int currentFoodCalories;
        ArrayList<FoodData> sortedFoods = new ArrayList<>(foods.values());
        int returnValue = 0;

        // Determines which category currently working on
        if(currentlyWorkingOn.equals("fv")){
            // Get the calorie values for the current category
            currentFoodCalories = needs.getFvCalories();
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

            if(sortedFoods.size() == 0) {
                throw new NotEnoughFoodException("fv", currentFoodCalories);
            }
            // If the first food (ie the top food) has 0 for the category, then know
            // there is no more food
            // Select the food with the highest value that is under the needed calories, unless
            // there is no value under the needed calories, then selects the least food to
            // add
            while(sortedFoods.get(returnValue).getFv() > currentFoodCalories){
                returnValue++;
                try {
                    if (sortedFoods.get(returnValue).getFv() == 0) {
                        returnValue--;
                        break;
                    }
                } catch (Exception e){
                    throw new NotEnoughFoodException("fv", currentFoodCalories);
                }
            }
        } else if(currentlyWorkingOn.equals("grain")){
            currentFoodCalories = needs.getGrainCalories();
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

            if(sortedFoods.size() == 0) {
                throw new NotEnoughFoodException("grain", currentFoodCalories);
            }

            // Select the food with the highest value that is under the needed calories, unless
            // there is no value under the needed calories, then selects the least food to
            // add
            while(sortedFoods.get(returnValue).getGrain() > currentFoodCalories){
                returnValue++;
                try {
                    if (sortedFoods.get(returnValue).getGrain() == 0) {
                        returnValue--;
                        break;
                    }
                } catch (Exception e){
                    throw new NotEnoughFoodException("grain", currentFoodCalories);
                }
            }
        } else if(currentlyWorkingOn.equals("protein")){
            currentFoodCalories = needs.getProteinCalories();
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

            if(sortedFoods.size() == 0) {
                throw new NotEnoughFoodException("protein", currentFoodCalories);
            }

            // Select the food with the highest value that is under the needed calories, unless
            // there is no value under the needed calories, then selects the least food to
            // add
            while(sortedFoods.get(returnValue).getProtein() > currentFoodCalories){
                returnValue++;
                try {
                    if (sortedFoods.get(returnValue).getProtein() == 0) {
                        returnValue--;
                        break;
                    }
                } catch (Exception e){
                    throw new NotEnoughFoodException("protein", currentFoodCalories);
                }
            }
        } else {
            currentFoodCalories = needs.getOtherCalories();
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
                try {
                    if (sortedFoods.get(returnValue).getOther() == 0) {
                        returnValue--;
                        break;
                    }
                } catch (Exception e){
                    throw new NotEnoughFoodException("other", currentFoodCalories);
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
    public static HashMap<String, Integer> calculateWaste(HashMap<Integer, FoodData> foods, HouseholdNeeds needs){
        int grains = 0;
        int protein = 0;
        int fv = 0;
        int other = 0;
        HashMap<String, Integer> returnMap = new HashMap<>();

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
