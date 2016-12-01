package Ancheta017.java;

/**
 * This class will search maximum valueTag in all inputs
 */
public class Finder {
    public static int findMaximumValueTag(int[] input) {
        int maxElement = -100;
        for(int i : input){
            if ( i > maxElement){
                maxElement = i;
            }
        }
        return maxElement;
    }
}

/**
 * Name: ANCHETA, Jesus Jr
 * Course: JAC444SAA
 * Project: Assignment2_3
 * Date: 11/07/2016
 */