/**
 * @author Aleksander Berezowski
 * @author Danielle Jourdain
 * @author Philippa Madill
 * @version 1.5
 * @since 1.0
 */

package edu.ucalgary.ensf409;

import java.util.HashMap;

public class Hamper extends SelectFood{
    private HouseholdNeeds calorieNeeds;
    private FoodData shortBy = null;
    private HashMap<String, Integer> wasteAmount;
    private HashMap<Integer, FoodData> hamperFoods;
    private HashMap<Integer, FoodData> availableFoods = new HashMap<>();
    private boolean enoughFood = true;
    NotEnoughFoodException exception;

    public Hamper(NotEnoughFoodException exception){
        this.exception = exception;
    }

    public NotEnoughFoodException getException() {
        return this.exception;
    }

    /**
     *
     * @param calorieNeeds Is a HouseholdNeeds object that contains the calorie
     *                     needs for an entire household.
     * @param availableFoods Is a hashmap with a key of the identifying integer of
     *                       a food (this should be the primary key from the initial
     *                       SQL table) and the value is a FoodData object, which
     *                       stores all needed values that we could want for a
     *                       specific food.
     */
    public Hamper(HouseholdNeeds calorieNeeds, HashMap<Integer, FoodData> availableFoods) throws NotEnoughFoodException {
        this.calorieNeeds = calorieNeeds;
        this.availableFoods.putAll(availableFoods);
        
        this.calorieNeeds.convertToWeekly();

        // Try to calculate the foods for a Hamper
        // Throws an exception if impossible due to not enough foods
        this.hamperFoods = calculateFoods(this.availableFoods, this.calorieNeeds);

        // Auto-calculate the waste amount so all calcs are done in the constructor
        this.wasteAmount = calculateWaste(this.hamperFoods, this.calorieNeeds);
        deleteFoods();
    }
    
    /**
     * Helper method to remove food from the database.
     * Creates a new database connection to remove the food.
     */
    private void deleteFoods() {
        RequestFormDatabase requestForm = new RequestFormDatabase("jdbc:mysql://localhost/FOOD_INVENTORY","student","ensf" );
		requestForm.initializeConnection();
		requestForm.setFoodValues();
		requestForm.setClientValues();
        if(this.hamperFoods == null) {
            return;
        }

        for(Integer i: this.hamperFoods.keySet()) {
            requestForm.removeFood(i);
        }

		requestForm.close();
    }
    

    /**
     * Getter for the waste amount
     * @return A hashmap with a String key value of each type of food, and an integer
     *          the amount of waste for the corresponding category.
     */
    public HashMap<String, Integer> getWaste(){
        return this.wasteAmount;
    }

    /**
     * Getter for the food for a hamper
     * @return A hashmap with an integer key value for each food, and a
     *          FoodData object for each corresponding food
     */
    public HashMap<Integer, FoodData> getFoodQuantities(){
        return this.hamperFoods;
    }

    /**
     * Getter for the calorie needs for a Household
     * @return A HouseholdNeeds object
     */
    public HouseholdNeeds getCalorieNeeds() {
        return this.calorieNeeds;
    }

    /**
     * Getter to see if there was enough food
     * @return True if there is enough good, and False if there isn't
     */
    public boolean getEnoughFood(){
        return this.enoughFood;
    }

    /**
     * Getter for the amount each category is short by
     * @return A FoodData object of how much each category is short by
     */
    public FoodData getShortBy() {
        return this.shortBy;
    }
	
	/**
	This method retrieves the foods available for selection by a hamper.
	*/
    public HashMap<Integer, FoodData> getAvailableFoods() {
        return availableFoods;
    }
}
