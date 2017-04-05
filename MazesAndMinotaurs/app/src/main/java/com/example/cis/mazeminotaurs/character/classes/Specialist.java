package com.example.cis.mazeminotaurs.character.classes;

/**
 * Created by jusmith on 3/31/17.
 */

public abstract class Specialist extends BaseClass {
    private int mSpecialScoreId;

    public int getSpecialScoreId() { return mSpecialScoreId; }

    public void setSpecialScoreId(int specialScoreId) {
        mSpecialScoreId = specialScoreId;
    }
}
