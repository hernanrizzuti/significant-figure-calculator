package com.rizzutih.significantfigurecalculator.exercises;

import java.util.Arrays;

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
                 return false;
             }
            }
        }
        return true;
    }

    public boolean isUniqueChar(final String str){

        if(str.length() > 128){
            return false;
        }

        boolean[] char_set = new boolean[128];
        for (int i=0; i < str.length(); i++){
            int val = str.charAt(i);
            if (char_set[val]){
                return false;
            }
            char_set[val] = true;
        }
        return true;
    }

    public String sort(final String string){
        char[] char_set = string.toCharArray();
        Arrays.sort(char_set);

        return new String(char_set);
    }

    public boolean permutation(final String compare,
                               final String compareTo){
        if (compare.length()!= compareTo.length()){
            return false;
        }
        return sort(compare).equals(sort(compareTo));

    }


    public String urlify(final char[] url, final int trueLength) {
        int spaceCount =0;
        int i =0;
        int index;
        for (i=0;i<trueLength;i++){
           char character = url[i];
           if(character == ' '){
               spaceCount++;
           }

        }

        return null;
    }
}
