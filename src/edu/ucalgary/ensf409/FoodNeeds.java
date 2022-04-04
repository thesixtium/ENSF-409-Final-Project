package edu.ucalgary.ensf409;

public class FoodNeeds {
    private static int fvCalories, grainCalories,
            proteinCalories, otherCalories, totalCalories;
    private static float fvPercent, grainPercent, proteinPercent, otherPercent;

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

    private static void calculateCalories(){
        fvCalories = (int) (fvPercent * totalCalories);
        grainCalories = (int) (grainPercent * totalCalories);
        proteinCalories = (int) (proteinPercent * totalCalories);
        otherCalories = (int) (otherPercent * totalCalories);
    }

    public static void setTotalCalories(int i){
        totalCalories = i;
    }

    public static void setFvPercent(float i){
        fvPercent = i;
    }

    public static void setGrainPercent(float i){
        grainPercent = i;
    }

    public static void setProteinPercent(float i){
        proteinPercent = i;
    }

    public static void setOtherPercent(float i){
        otherPercent = i;
    }

    public static int getFvCalories(){
        calculateCalories();
        return fvCalories;
    }

    public static int getGrainCalories(){
        calculateCalories();
        return grainCalories;
    }

    public static int getProteinCalories(){
        calculateCalories();
        return proteinCalories;
    }

    public static int getOtherCalories(){
        calculateCalories();
        return otherCalories;
    }

}
