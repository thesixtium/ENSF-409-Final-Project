package edu.ucalgary.ensf409;

public class HouseholdNeeds {
    private int fvCalories, grainCalories, proteinCalories, otherCalories, totalCalories;

    public HouseholdNeeds(){
        fvCalories = 0;
        grainCalories = 0;
        proteinCalories = 0;
        otherCalories = 0;
        totalCalories = 0;
    }

    public void changeFvCalories(int i){
        fvCalories = fvCalories + i;
    }

    public void changeGrainCalories(int i){
        grainCalories = grainCalories + i;
    }

    public void changeProteinCalories(int i){
        proteinCalories = proteinCalories + i;
    }

    public void changeOtherCalories(int i){
        otherCalories = otherCalories + i;
    }

    public int getFvCalories(){
        return fvCalories;
    }

    public int getGrainCalories(){
        return grainCalories;
    }

    public int getProteinCalories(){
        return proteinCalories;
    }

    public int getOtherCalories(){
        return otherCalories;
    }

    public boolean isSatisfied(){
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
