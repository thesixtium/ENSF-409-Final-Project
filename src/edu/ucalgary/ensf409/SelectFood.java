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
import java.util.List;

public class SelectFood {

    public HashMap<Integer, FoodData> calculateFoods(HashMap<Integer, FoodData> foods, HouseholdNeeds needs) throws NotEnoughFoodException {
        String[] foodTypes = {"fv", "grain", "protein", "other"};
        HashMap<Integer, FoodData> returnFoods = new HashMap<>();
        while(!needs.isSatisfied()){
            for (String type : foodTypes){
                Integer foodToAdd = mostEfficientFood(returnFoods, foods, needs, type);
                returnFoods.put(foodToAdd, foods.get(foodToAdd));
                needs.changeFvCalories(-1 * foods.get(foodToAdd).getFv());
                needs.changeGrainCalories(-1 * foods.get(foodToAdd).getGrain());
                needs.changeProteinCalories(-1 * foods.get(foodToAdd).getProtein());
                needs.changeOtherCalories(-1 * foods.get(foodToAdd).getOther());
                foods.remove(foodToAdd);
            }
        }

        return returnFoods;
    }

    private int mostEfficientFood(HashMap<Integer, FoodData> currentReturnFoods, HashMap<Integer, FoodData> foods,
                                  HouseholdNeeds needs, String currentlyWorkingOn) throws NotEnoughFoodException {
        int currentFoodCalories;
        ArrayList<FoodData> sortedFoods = new ArrayList<>(foods.values());
        int returnValue = 0;

        if(currentlyWorkingOn.equals("fv")){
            currentFoodCalories = needs.getFvCalories();
            sortedFoods.sort(new Comparator<FoodData>() {
                public int compare(FoodData o1, FoodData o2) {
                    if (o1.getFv() == o2.getFv())
                        if(o1.getSum() == o2.getSum())
                            return 0;
                        else
                            return o1.getSum() < o2.getSum() ? -1 : 1;
                    return o1.getFv() < o2.getFv() ? -1 : 1;
                }
            });
            if (sortedFoods.get(returnValue).getFv() == 0){
                throw new NotEnoughFoodException("fv", currentFoodCalories);
            }
            while(sortedFoods.get(returnValue).getFv() > currentFoodCalories){
                returnValue++;
                if (sortedFoods.get(returnValue).getFv() == 0){
                    returnValue--;
                    break;
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
                    return o1.getGrain() < o2.getGrain() ? -1 : 1;
                }
            });
            if (sortedFoods.get(returnValue).getGrain() == 0){
                throw new NotEnoughFoodException("grain", currentFoodCalories);
            }
            while(sortedFoods.get(returnValue).getGrain() > currentFoodCalories){
                returnValue++;
                if (sortedFoods.get(returnValue).getGrain() == 0){
                    returnValue--;
                    break;
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
                    return o1.getProtein() < o2.getProtein() ? -1 : 1;
                }
            });
            if (sortedFoods.get(returnValue).getProtein() == 0){
                throw new NotEnoughFoodException("protein", currentFoodCalories);
            }
            while(sortedFoods.get(returnValue).getProtein() > currentFoodCalories){
                returnValue++;
                if (sortedFoods.get(returnValue).getProtein() == 0){
                    returnValue--;
                    break;
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
                    return o1.getOther() < o2.getOther() ? -1 : 1;
                }
            });
            if (sortedFoods.get(returnValue).getOther() == 0){
                throw new NotEnoughFoodException("other", currentFoodCalories);
            }
            while(sortedFoods.get(returnValue).getOther() > currentFoodCalories){
                returnValue++;
                if (sortedFoods.get(returnValue).getOther() == 0){
                    returnValue--;
                    break;
                }
            }
        }

        for (Integer i : currentReturnFoods.keySet()){
            if (sortedFoods.get(returnValue) == currentReturnFoods.get(i)){
                return i;
            }
        }

        return -1;
    }

    public HashMap<String, Integer> calculateWaste(HashMap<Integer, FoodData> foods, HouseholdNeeds needs){
        int grains = 0;
        int protein = 0;
        int fv = 0;
        int other = 0;
        HashMap<String, Integer> returnMap = new HashMap<>();

        for (Integer i : foods.keySet()){
            grains += foods.get(i).getGrain();
            protein += foods.get(i).getProtein();
            fv += foods.get(i).getFv();
            other += foods.get(i).getOther();
        }

        returnMap.put("grain", grains - needs.getGrainCalories());
        returnMap.put("fv", fv - needs.getFvCalories());
        returnMap.put("other", other - needs.getOtherCalories());
        returnMap.put("protein", protein - needs.getProteinCalories());

        return returnMap;
    }
}
