package com.example.cis.mazeminotaurs;

/**
 * Created by Thorin Schmidt on 3/20/2017.
 */

public class Weapons extends Equipment {

    //instance variables
    int mDamageDie;
    int mNumberOfDice;
    int mDamageBonus;

    public Weapons(int resId, int encumberance, int quantity, double costInSp,
                   String longDescription, int damageDie, int numberOfDice, int damageBonus) {
        super(resId, encumberance, quantity, costInSp, longDescription);
        mDamageDie = damageDie;
        mNumberOfDice = numberOfDice;
        mDamageBonus = damageBonus;
    }

    public int getDamageDie() {
        return mDamageDie;
    }

    public void setDamageDie(int damageDie) {
        mDamageDie = damageDie;
    }

    public int getNumberOfDice() {
        return mNumberOfDice;
    }

    public void setNumberOfDice(int numberOfDice) {
        mNumberOfDice = numberOfDice;
    }

    public int getDamageBonus() {
        return mDamageBonus;
    }

    public void setDamageBonus(int damageBonus) {
        mDamageBonus = damageBonus;
    }

    @Override
    public String toString() {
        super.toString();
        return "Weapons{" +
                "mDamageDie=" + mDamageDie +
                ", mNumberOfDice=" + mNumberOfDice +
                ", mDamageBonus=" + mDamageBonus +
                '}';
    }
}

