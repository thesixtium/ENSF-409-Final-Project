/**
 * @author Aleksander Berezowski
 * @author Danielle Jourdain
 * @author Philippa Madill
 * @version 1.4
 * @since 1.0
 */

package edu.ucalgary.ensf409;

public class HouseholdNeeds {
    private int fvCalories, grainCalories, proteinCalories, otherCalories;

    public HouseholdNeeds(){
        fvCalories = 0;
        grainCalories = 0;
        proteinCalories = 0;
        otherCalories = 0;
    }
	/**
	This method changes the amount of calories from fruit and 
	veggies required by the household.
	*/

    public void changeFvCalories(int i){
        fvCalories = fvCalories + i;
    }
	
	/**
	This method changes the amount of calories from grains 
	required by the household.
	*/
    public void changeGrainCalories(int i){
        grainCalories = grainCalories + i;
    }

	/**
	This method changes the amount of calories from protein
	required by the household.
	*/
    public void changeProteinCalories(int i){
        proteinCalories = proteinCalories + i;
    }

	/**
	This method changes the amount of calories from Other
	required by the household.
	*/
    public void changeOtherCalories(int i){
        otherCalories = otherCalories + i;
    }
	
	/**
	This method converts the daily client needs provided
	from the database into weekly needs as is required for 
	creating a weekly hamper.
	*/
    public void convertToWeekly(){
        this.fvCalories *= 7;
        this.grainCalories *= 7;
        this.proteinCalories *= 7;
        this.otherCalories *= 7;
    }

	/**
	This method returns the amount of calories from fruit and 
	veggies required in the household.
	*/
    public int getFvCalories(){
        return fvCalories;
    }

	/**
	This method returns the amount of calories from  
	grains required in the household.
	*/
    public int getGrainCalories(){
        return grainCalories;
    }

	/**
	This method returns the amount of calories from
	protein required in the household.
	*/
    public int getProteinCalories(){
        return proteinCalories;
    }
	
	/**
	This method returns the amount of calories from 
	other required in the household.
	*/
    public int getOtherCalories(){
        return otherCalories;
    }

	/**
	This method checks if the needs of the household have been satisfied.
	Satisfaction occurs if the calories required in all categories are 
	less than 0.
	*/
    public boolean isSatisfied(){
        if (fvCalories > 0){
            return false;
        } else if (grainCalories > 0){
            return false;
        } else if (proteinCalories > 0){
            return false;
        } else if (otherCalories > 0){
            return false;
        }
        return true;
    }

}
