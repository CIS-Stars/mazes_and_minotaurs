package com.example.cis.mazeminotaurs;

/**
 * Created by Thorin Schmidt on 3/20/2017.
 */

public class Armor extends Equipment {

    //instance variables
    private int mDefenseBonus;

    public Armor(String resId, int encumberance, int quantity, double costInSp, String longDescription, int defenseBonus) {
        super(resId, encumberance, quantity, costInSp, longDescription);
        mDefenseBonus = defenseBonus;
    }

    public int getDefenseBonus() {
        return mDefenseBonus;
    }

    public void setDefenseBonus(int defenseBonus) {
        mDefenseBonus = defenseBonus;
    }

    @Override
    public String toString() {
        return super.toString()+ "\n" +
                "Armor{" +
                "mDefenseBonus=" + mDefenseBonus +
                '}';
    }
}
