package edu.ucalgary.ensf409;

public interface Client {
    void setNeeds(int[] calorie_needs);
    int FVNeeds();
    int grainNeeds();
    int proteinNeeds();
    int otherNeeds();
    String toString();
}