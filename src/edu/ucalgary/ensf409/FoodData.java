/**
 * @author Aleksander Berezowski
 * @author Danielle Jourdain
 * @author Philippa Madill
 * @version 1.3
 * @since 1.0
 */

package edu.ucalgary.ensf409;

public class FoodData {
    private int fv, grain, protein, other;
    private final String NAME;

    /**
     * Constuctor for FoodData that initializes values.
     * @param name The name of the food to be added
     * @param fv The number of fruit & veggie calories
     * @param grain The number of grain calories
     * @param protein The number of protein calories
     * @param other The number of other calories
     */
    public FoodData(String name, int fv, int grain, int protein, int other){
        this.fv = fv;
        this.grain = grain;
        this.protein = protein;
        this.other = other;
        this.NAME = name;
    }

    /**
     * Secondary constructor for FoodData. All calorie values will be initialized
     * to 0.
     * @param name The name of the food to be added.
     */
    public FoodData(String name) {
        this.fv = 0;
        this.grain = 0;
        this.protein = 0;
        this.other = 0;
        this.NAME = name;
    }

    /**
     * Setter method for fruit & veggie calories.
     * @param i The new value for fv.
     */
    public void setFV(int i){
        this.fv = i;
    }

    /**
     * Getter method for fruit & veggie calories.
     * @return The current integer value of fv
     */
    public int getFv(){
        return this.fv;
    }

    /**
     * Setter method for grain calories.
     * @param i The new value for grain.
     */
    public void setGrain(int i){
        this.grain = i;
    }

    /**
     * Getter method for grain calories.
     * @return The current integer value of grain
     */
    public int getGrain(){
        return this.grain;
    }

    /**
     * Setter method for protein calories.
     * @param i The new value for protein.
     */
    public void setProtein(int i){
        this.protein = i;
    }

    /**
     * Getter method for protein calories.
     * @return The current integer value of protein 
     */
    public int getProtein(){
        return this.protein;
    }

    /**
     * Setter method for other calories.
     * @param i The new value for other
     */
    public void setOther(int i){
        this.other = i;
    }

    /**
     * Getter method for other calories.
     * @return The 
     */
    public int getOther(){
        return this.other;
    }

    /**
     * Getter method for the name of the food.
     * @return The name of the food as a String
     */
    public String getName(){
        return this.NAME;
    }

    /**
     * This method calculates and returns the sum of all the individual 
     * calorie values.
     * @return The sum of all 4 calorie types
     */
    public int getSum(){
        return this.fv + this.grain + this.protein + this.other;
    }

}
