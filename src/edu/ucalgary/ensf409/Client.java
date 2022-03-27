/**
 * @author Aleksander Berezowski
 * @author Danielle Jourdain
 * @author Philippa Madill
 * @version 1.0
 * @since 1.0
 */

package edu.ucalgary.ensf409;

import java.util.HashMap;

public interface Client {
    HashMap<String, Integer> CALORIES_AND_PERCENT_NEEDS = new HashMap<>();
    void setNeeds(int[] calorie_needs);
    int FVNeeds();
    int grainNeeds();
    int proteinNeeds();
    int otherNeeds();
    String toString();
}