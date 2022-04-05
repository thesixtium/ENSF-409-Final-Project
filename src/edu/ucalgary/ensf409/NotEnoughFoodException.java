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
    NotEnoughFoodException(String type){
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
