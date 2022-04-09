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
    private HouseholdNeeds calorieNeeds;
    private FoodData shortBy = null;
    private HashMap<String, Integer> wasteAmount;
    private HashMap<Integer, FoodData> hamperFoods;
    private HashMap<Integer, FoodData> avaliableFoods = new HashMap<>();
    private boolean enoughFood = true;

    public Hamper(HouseholdNeeds calorieNeeds, HashMap<Integer, FoodData> avaliableFoods) {
        this.calorieNeeds = calorieNeeds;
        this.avaliableFoods.putAll(avaliableFoods);

        try {
            this.hamperFoods = calculateFoods(this.avaliableFoods, this.calorieNeeds);
        } catch (NotEnoughFoodException e){
            enoughFood = false;
            this.shortBy = new FoodData("Short By");
            if(e.type.equals("fv"))
                this.shortBy.setFV(e.amount);
            else if(e.type.equals("protein"))
                this.shortBy.setProtein(e.amount);
            else if(e.type.equals("grain"))
                this.shortBy.setGrain(e.amount);
            else
                this.shortBy.setOther(e.amount);
        }

        this.wasteAmount = calculateWaste(this.hamperFoods, this.calorieNeeds);
    }

    public HashMap<String, Integer> getWaste(){
        return this.wasteAmount;
    }

    public HashMap<Integer, FoodData> getFoodQuantities(){
        return this.hamperFoods;
    }

    public HouseholdNeeds getCalorieNeeds() {
        return this.calorieNeeds;
    }

    public boolean getEnoughFood(){
        return this.enoughFood;
    }

    public FoodData getShortBy() {
        return this.shortBy;
    }
}
