package com.example.cis.mazeminotaurs;

/**
 * Created by tschmidt on 3/18/2017.
 */

public class AttributeScore {

    //instance variables
    int score;
    int modifier;
    String description;

    //private methods

    /**
     * updates the modifier based on current value of score.
     * this method should be called by the constructor, and by any method that modifies
     * the score.  The modifier should never be set individually.
     *
     * just for kicks, the official score description is also set.  probably no space for it in
     * the app, but better to have it than not.
     *
     *  modifiers are applied as follows:
     *  score      mod     description
     *   1-2       -4       Abysmal
     *   3-4       -3       Pathetic
     *   5-6       -2       Inferior
     *   7-8       -1       Poor
     *   9-12       0       Average
     *  13-14       1       Good
     *  15-16       2       Superior
     *  17-18       3       Execptional
     *  19-20       4       Extraordinary
     *   21         5       Legendary
     *
     */
    private void updateModifier(){

        if(this.score <= 2){
            this.modifier = -4;
            this.description = "Abysmal";
        }
        else if(this.score <= 4){
            this.modifier = -3;
            this.description = "Pathetic";
        }
        else if(this.score <= 6){
            this.modifier = -2;
            this.description = "Inferior";
        }
        else if(this.score <= 8){
            this.modifier = -1;
            this.description = "Poor";
        }
        else if(this.score <= 12){
            this.modifier = 0;
            this.description = "Average";
        }
        else if(this.score <= 14){
            this.modifier = 1;
            this.description = "Good";
        }
        else if(this.score <= 16){
            this.modifier = 2;
            this.description = "Superior";
        }
        else if(this.score <= 18){
            this.modifier = 3;
            this.description = "Exceptional";
        }
        else if(this.score <= 20){
            this.modifier = 4;
            this.description = "Extraordinary";
        }
        else{
            this.modifier = 5;
            this.description = "Legendary";
        }
    }

    //public methods

    public AttributeScore(int score) {
        this.score = score;
        this.updateModifier();
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
        this.updateModifier();
    }

    public int getModifier() {
        return modifier;
    }

    /*  USE updateModifier() INSTEAD
    public void setModifier(int modifier) {
        this.modifier = modifier;
    }*/

    public String getDescription() {
        return description;
    }

    /* USE updateModifier() INSTEAD
    public void setDescription(String description) {
        this.description = description;
    }*/
}
