/**
 * @author Aleksander Berezowski
 * @author Danielle Jourdain
 * @author Philippa Madill
 * @version 1.0
 * @since 1.3
 */

package edu.ucalgary.ensf409;

public class FoodData {
    private int fv, grain, protein, other;

    public FoodData(){
        this.fv = 0;
        this.grain = 0;
        this.protein = 0;
        this.other = 0;
    }

    public void setFV(int i){
        this.fv = i;
    }

    public int getFv(){
        return this.fv;
    }

    public void setGrain(int i){
        this.grain = i;
    }

    public int getGrain(){
        return this.grain;
    }

    public void setProtein(int i){
        this.protein = i;
    }

    public int getProtein(){
        return this.protein;
    }

    public void setOther(int i){
        this.other = i;
    }

    public int getOther(){
        return this.other;
    }
}
