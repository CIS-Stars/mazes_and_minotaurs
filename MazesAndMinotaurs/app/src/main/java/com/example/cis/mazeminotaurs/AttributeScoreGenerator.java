package com.example.cis.mazeminotaurs;

import java.util.Random;

/**
 * Created by Thorin Schmidt on 3/18/2017.
 */

public class AttributeScoreGenerator {
    //random number generator
    Random dieRoller = new Random();

    //private methods
    private AttributeScore nextScore(){
        int rollOne = dieRoller.nextInt(6)+1;
        int rollTwo = dieRoller.nextInt(6)+1;

        return new AttributeScore(rollOne+rollTwo+6);
    }

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
