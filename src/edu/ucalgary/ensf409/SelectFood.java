/**
 * @author Aleksander Berezowski
 * @author Dani ???
 * @author Philippa Madill
 * @version 1.3
 * @since 1.0
 */

package edu.ucalgary.ensf409;

import java.util.ArrayList;
import java.util.HashMap;

public class SelectFood {

    /**
     * Builds the list of foods for each hamper
     * @param needs A hashmap of the hamper's needed calories split into
     *              fv, grains, protein, other.
     * @param availableFoods A hashmap of all the available foods, their
     *              calories, and their id.
     * @return An ArrayList of Integers containing each food's ID number
     */
    public static ArrayList<Integer> calculateFoods(HashMap<String, Integer> needs, HashMap<Integer, Object> availableFoods){

    }

    /**
     * Builds the list of foods for each hamper
     * @param foods An ArrayList of the foods in the hamper.
     * @param needs A hashmap of the hamper's needed calories split into
     *              fv, grains, protein, other.
     * @return A HashMap of the waste for each category:
     *              - fv
     *              - grains
     *              - protein
     *              - other
     */
    public static HashMap<String, Integer> calculateWaste(ArrayList<Integer> foods, HashMap<String, Integer> needs){

    }
}
