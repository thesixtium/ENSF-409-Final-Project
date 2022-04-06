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
        this.type = type;
        this.amount = amount;
    }

    public String getType() {
        return type;
    }
}
