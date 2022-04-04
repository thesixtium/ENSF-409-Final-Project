/**
 * @author Aleksander Berezowski
 * @author Danielle Jourdain
 * @author Philippa Madill
 * @version 1.3
 * @since 1.0
 */

package edu.ucalgary.ensf409;

import java.util.HashMap;

public class Hamper extends SelectFood{
    private FoodNeeds calorieNeeds;
    private HashMap<String, Integer> wasteAmount;
    private HashMap<Integer, FoodData> hamperFoods;
    private HashMap<Integer, FoodData> avaliableFoods = new HashMap<>();

    public Hamper(FoodNeeds calorieNeeds, HashMap<Integer, FoodData> avaliableFoods){
        this.calorieNeeds = calorieNeeds;
        this.avaliableFoods.putAll(avaliableFoods);

        this.hamperFoods = calculateFoods(this.avaliableFoods, this.calorieNeeds);
        this.wasteAmount = calculateWaste(this.hamperFoods, this.calorieNeeds);
    }

    public HashMap<String, Integer> getWaste(){
        return this.wasteAmount;
    }

    public HashMap<Integer, FoodData> getFoodQuantities(){
        return this.hamperFoods;
    }

    public FoodNeeds getCalorieNeeds() {
        return this.calorieNeeds;
    }
}
