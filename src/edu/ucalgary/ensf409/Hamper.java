/**
 * @author Aleksander Berezowski
 * @author Danielle Jourdain
 * @author Philippa Madill
 * @version 1.0
 * @since 1.0
 */

package edu.ucalgary.ensf409;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.Map;

public class Hamper extends SelectFood{
    private HashMap<String, Integer> calorieNeeds = new HashMap<>();
    private HashMap<String, Integer> wasteAmount;
    private HashMap<Integer, HashMap<String, Integer>> hamperFoods;
    private HashMap<Integer, HashMap<String, Integer>> avaliableFoods = new HashMap<>();

    public Hamper(HashMap<String, Integer> calorieNeeds, HashMap<Integer, HashMap<String, Integer>> avaliableFoods){
        this.calorieNeeds.putAll(calorieNeeds);
        this.avaliableFoods.putAll(avaliableFoods);
        this.hamperFoods = calculateFoods(this.calorieNeeds, this.avaliableFoods);
        this.wasteAmount = calculateWaste(this.hamperFoods, this.calorieNeeds);
    }

    public HashMap<String, Integer> getWaste(){
        return this.wasteAmount;
    }

    public HashMap<Integer, HashMap<String, Integer>> getFoodQuantities(){
        return this.hamperFoods;
    }

    public HashMap<String, Integer> getCalorieNeeds() {
        return this.calorieNeeds;
    }
}
