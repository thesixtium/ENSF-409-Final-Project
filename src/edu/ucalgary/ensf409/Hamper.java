package edu.ucalgary.ensf409;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.Map;

public class Hamper extends SelectFood{
    private HashMap<String, Integer> calorieNeeds = new HashMap<>();
    private HashMap<String, Integer> wasteAmount;
    private ArrayList<Integer> hamperFoods;
    private HashMap<Integer, Object> avaliableFoods = new HashMap<>();

    public Hamper(HashMap<String, Integer> calorieNeeds, HashMap<Integer, Object> avaliableFoods){
        this.calorieNeeds.putAll(calorieNeeds);
        this.avaliableFoods.putAll(avaliableFoods);
        this.hamperFoods = calculateFoods(this.calorieNeeds, this.avaliableFoods);
        this.wasteAmount = calculateWaste(this.hamperFoods, this.calorieNeeds);
    }

    public HashMap<String, Integer> getWaste(){
        return this.wasteAmount;
    }

    public ArrayList<Integer> getFoodQuantities(){
        return this.hamperFoods;
    }

    public HashMap<String, Integer> getCalorieNeeds() {
        return this.calorieNeeds;
    }
}
