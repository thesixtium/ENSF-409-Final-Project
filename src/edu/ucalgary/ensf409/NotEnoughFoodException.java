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

    public String getType() {
        return type;
    }

    public int getAmount() {
        return amount;
    }

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
