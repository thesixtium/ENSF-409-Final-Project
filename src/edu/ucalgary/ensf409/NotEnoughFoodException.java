/**
 * @author Aleksander Berezowski
 * @author Danielle Jourdain
 * @author Philippa Madill
 * @version 1.0
 * @since 1.0
 */

package edu.ucalgary.ensf409;

public class NotEnoughFoodException extends Exception{
    String type;
    int amount;
    NotEnoughFoodException(String type, int amount){
        this.type = type.toLowerCase();
        this.amount = amount;
    }
	/**
	This method retrieves the type of calories that is resulting in the exception.
	*/
    public String getType() {
        return type;
    }
	/**
	This method returns the amount of calories of the type that the food inventory 
	is short of.
	*/
    public int getAmount() {
        return amount;
    }
	/**
	This method converts the type supplied to English with proper grammatical structure.
	*/
    public String getGoodType(){
        if (type.equals("fv"))
            return "Fruits and Veggies";
        else if (type.equals("grains") || type.equals("grain"))
            return "Grain";
        else if (type.equals("protein"))
            return "Protein";
        else
            return "Other";
    }
}
