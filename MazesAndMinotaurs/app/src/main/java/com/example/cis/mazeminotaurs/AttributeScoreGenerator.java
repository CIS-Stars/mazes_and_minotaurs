package com.example.cis.mazeminotaurs;

import java.util.Random;

/**
 * This is a helper class for generating a valid set of AttributeScores.
 * @author Thorin Schmidt on 3/18/2017.
 */

public class AttributeScoreGenerator {
    /**
     * An instance of Random for generating numbers.
     */
    Random dieRoller = new Random();

    /**
     * A helper method for generating a new score value.
     *
     * @return a score value between 8 - 18
     */
    private AttributeScore nextScore(){
        int rollOne = dieRoller.nextInt(6)+1;
        int rollTwo = dieRoller.nextInt(6)+1;

        return new AttributeScore(rollOne+rollTwo+6);
    }

    /**
     * Returns an array of AttributeScores that are valid according to the rules
     * of Mazes and Minotaurs.
     * @return a valid array of AttributeScores.
     */
    public AttributeScore[] nextValidSet(){

        AttributeScore[] attributes = new AttributeScore[6];
        boolean isNotValid = true;
        int totalOfScores;
        int numberOfGoodOrBetterScores;

        do{
            totalOfScores = 0;
            numberOfGoodOrBetterScores = 0;

            for(int i = 0; i < attributes.length; i++){
                attributes[i] = nextScore();
                totalOfScores += attributes[i].getScore();
                if(attributes[i].getScore() > 12){
                    numberOfGoodOrBetterScores += 1;
                }
            }

            if(totalOfScores > 74 && numberOfGoodOrBetterScores > 1){
                isNotValid = false;
            }

        }while(isNotValid);

        return attributes;
    }


}
