/**
 * @author Aleksander Berezowski
 * @author Danielle Jourdain
 * @author Philippa Madill
 * @version 1.6
 * @since 1.0
 */

package edu.ucalgary.ensf409;

public class FoodNeeds {
    private static int fvCalories, grainCalories, proteinCalories, otherCalories, totalCalories;
    private static float fvPercent, grainPercent, proteinPercent, otherPercent;

    /**
     * Default constructor for FoodNeeds. All values are initially set to 0.
     */
    public FoodNeeds(){
        fvCalories = 0;
        grainCalories = 0;
        proteinCalories = 0;
        otherCalories = 0;
        totalCalories = 0;
        fvPercent = 0;
        grainPercent = 0;
        proteinPercent = 0;
        otherPercent = 0;
    }

    /**
     * Helper method to calculate current calorie values for each type of calorie.
     * Updates the calorie member variables to be appropriate percentages of the
     * total calories.
     */
    private static void calculateCalories(){
        fvCalories = (int) (fvPercent * totalCalories * 0.01);
        grainCalories = (int) (grainPercent * totalCalories * 0.01);
        proteinCalories = (int) (proteinPercent * totalCalories * 0.01);
        otherCalories = (int) (otherPercent * totalCalories * 0.01);
    }

    /**
     * Setter method for totalCalories.
     * @param i The new value for totalCalories
     */
    public static void setTotalCalories(int i){
        totalCalories = i;
    }

    /**
     * Setter method for fvPercent.
     * @param i The new percentage of fvPercent.
     */
    public static void setFvPercent(float i){
        fvPercent = i;
    }

    /**
     * Setter method for grainPercent 
     * @param i the new percentage of grainPercent
     */
    public static void setGrainPercent(float i){
        grainPercent = i;
    }

    /**
     * Setter method for proteinPercent.
     * @param i the new percentage of protein calories
     */
    public static void setProteinPercent(float i){
        proteinPercent = i;
    }

    /**
     * Setter for otherPercent
     * @param i the new percentage of otherPercent
     */
    public static void setOtherPercent(float i){
        otherPercent = i;
    }

    /**
     * This method adds the new value to the current value of fvCalories
     * @param i the value to add to fvCalories
     */
    public static void changeFvCalories(int i){
        fvCalories = fvCalories + i;
    }

    /**
     * This method adds the new value to the current value of grainCalories
     * @param i the value to add to grainCalories
     */
    public static void changeGrainCalories(int i){
        grainCalories = grainCalories + i;
    }

    /**
     * This method adds the new value to the current value of proteinCalories
     * @param i the value to add to proteinCalories
     */
    public static void changeProteinCalories(int i){
        proteinCalories = proteinCalories + i;
    }

    /**
     * This method adds the new value to the current value of otherCalories
     * @param i the value to add to otherCalories
     */
    public static void changeOtherCalories(int i){
        otherCalories = otherCalories + i;
    }

    /**
     * Getter method for fvCalories
     * @return the current value of fvCalories
     */
    public static int getFvCalories(){
        calculateCalories();
        return fvCalories;
    }

    /**
     * Getter method for grainCalories
     * @return the current value of grainCalories
     */
    public static int getGrainCalories(){
        calculateCalories();
        return grainCalories;
    }

    /**
     * Getter method for proteinCalories
     * @return the current value of proteinCalories
     */
    public static int getProteinCalories(){
        calculateCalories();
        return proteinCalories;
    }

    /**
     * Getter method for otherCalories
     * @return the current value of otherCalories
     */
    public static int getOtherCalories(){
        calculateCalories();
        return otherCalories;
    }

    /**
     * This method checks if all calorie values are 0.
     * @return True if all calorie values are 0, False otherwise
     */
    public static boolean isSatisfied(){
        if (fvCalories != 0){
            return false;
        } else if (grainCalories != 0){
            return false;
        } else if (proteinCalories != 0){
            return false;
        } else if (otherCalories != 0){
            return false;
        }
        return true;
    }

}
