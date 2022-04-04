package edu.ucalgary.ensf409;

public class FoodNeeds {
    private int fvCalories, grainCalories,
            proteinCalories, otherCalories, totalCalories;
    private float fvPercent, grainPercent, proteinPercent, otherPercent;

    public FoodNeeds(){
        this.fvCalories = 0;
        this.grainCalories = 0;
        this.proteinCalories = 0;
        this.otherCalories = 0;
        this.totalCalories = 0;
        this.fvPercent = 0;
        this.grainPercent = 0;
        this.proteinPercent = 0;
        this.otherPercent = 0;
    }

    private void calculateCalories(){
        this.fvCalories = (int) (this.fvPercent * this.totalCalories);
        this.grainCalories = (int) (this.grainPercent * this.totalCalories);
        this.proteinCalories = (int) (this.proteinPercent * this.totalCalories);
        this.otherCalories = (int) (this.otherPercent * this.totalCalories);
    }

    public void setTotalCalories(int i){
        this.totalCalories = i;
    }

    public void setFvPercent(float i){
        this.fvPercent = i;
    }

    public void setGrainPercent(float i){
        this.grainPercent = i;
    }

    public void setProteinPercent(float i){
        this.proteinPercent = i;
    }

    public void setOtherPercent(float i){
        this.otherPercent = i;
    }

    public void changeFvCalories(int i){
        this.fvCalories = this.fvCalories + i;
    }

    public void changeGrainCalories(int i){
        this.grainCalories = this.grainCalories + i;
    }

    public void changeProteinCalories(int i){
        this.proteinCalories = this.proteinCalories + i;
    }

    public void changeOtherCalories(int i){
        this.otherCalories = this.otherCalories + i;
    }

    public int getFvCalories(){
        this.calculateCalories();
        return this.fvCalories;
    }

    public int getGrainCalories(){
        this.calculateCalories();
        return this.grainCalories;
    }

    public int getProteinCalories(){
        this.calculateCalories();
        return this.proteinCalories;
    }

    public int getOtherCalories(){
        this.calculateCalories();
        return this.otherCalories;
    }

    public boolean isSatisfied(){
        if (this.fvCalories != 0){
            return false;
        } else if (this.grainCalories != 0){
            return false;
        } else if (this.proteinCalories != 0){
            return false;
        } else if (this.otherCalories != 0){
            return false;
        }
        return true;
    }

}
