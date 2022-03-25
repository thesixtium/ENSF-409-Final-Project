/**
 * @author Aleksander Berezowski
 * @author Dani ???
 * @author Philippa Madill
 * @version 1.0
 * @since 1.0
 */

package edu.ucalgary.ensf409;

public interface Client {
    void setNeeds(int[] calorie_needs);
    int FVNeeds();
    int grainNeeds();
    int proteinNeeds();
    int otherNeeds();
    String toString();
}