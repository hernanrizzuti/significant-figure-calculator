package com.rizzutih.significantfigurecalculator.exercises;

/**
 * Created by h.rizzuti on 22/06/2018.
 */
public class Ch1ArrayAndStrings {

    public boolean isUnique(final String text){
        char prev;
        char curr;
        for (int i=0; i<text.length();i++){
            prev = text.charAt(i);
            for (int j=i+1;j<text.length();j++){
             curr = text.charAt(j);
             if(prev==curr){
                 return true;
             }
            }
        }
        return false;
    }
}
