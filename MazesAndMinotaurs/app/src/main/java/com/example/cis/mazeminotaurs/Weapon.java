package com.example.cis.mazeminotaurs;

/**
 * Created by Thorin Schmidt on 3/20/2017.
 */

public class Weapon extends Equipment {

    //instance variables
    private int mDamageDie;
    private int mNumberOfDice;
    private int mDamageBonus;
    private int mWeaponType; //uses string values melee and missile
    private int mRange = 0;

    public Weapon(int resId, int encumberance, int quantity, double costInSp,
                  String longDescription, int damageDie, int numberOfDice, int damageBonus,
                  int weaponType) {
        super(resId, encumberance, quantity, costInSp, longDescription);
        mDamageDie = damageDie;
        mNumberOfDice = numberOfDice;
        mDamageBonus = damageBonus;
        mWeaponType = weaponType;
    }

    public Weapon(int resId, int encumberance, int quantity, double costInSp,
                  String longDescription, int damageDie, int numberOfDice, int damageBonus,
                  int weaponType, int range) {
        super(resId, encumberance, quantity, costInSp, longDescription);
        mDamageDie = damageDie;
        mNumberOfDice = numberOfDice;
        mDamageBonus = damageBonus;
        mWeaponType = weaponType;
        mRange = range;
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

    public int getWeaponType() {
        return mWeaponType;
    }

    public void setWeaponType(int weaponType) {
        mWeaponType = weaponType;
    }

    public int getRange() {
        return mRange;
    }

    public void setRange(int range) {
        mRange = range;
    }

    @Override
    public String toString() {
        return super.toString() +"\n" +
                "Weapon{" +
                "mDamageDie=" + mDamageDie +
                ", mNumberOfDice=" + mNumberOfDice +
                ", mDamageBonus=" + mDamageBonus +
                '}';
    }
}

