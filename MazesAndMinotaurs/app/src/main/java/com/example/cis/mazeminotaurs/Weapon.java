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
    private int mWeaponSize; //uses string values from game_references.xml
    private int mRange;

    /**public Weapon(int resId, int encumberance, int quantity, double costInSp,
                  String longDescription, int damageDie, int numberOfDice, int damageBonus,
                  int weaponType, int weaponSize) {
        super(resId, encumberance, quantity, costInSp, longDescription);
        mDamageDie = damageDie;
        mNumberOfDice = numberOfDice;
        mDamageBonus = damageBonus;
        mWeaponType = weaponType;
        mWeaponSize = weaponSize;
    }*/

    /**
     *   weapon constructor
     * @param resId
     * @param encumberance
     * @param quantity
     * @param costInSp
     * @param longDescription
     * @param damageDie
     * @param numberOfDice
     * @param damageBonus
     * @param weaponType
     * @param weaponSize
     * @param range
     */
    public Weapon(String resId, int encumberance, int quantity, double costInSp,
                  String longDescription, int damageDie, int numberOfDice, int damageBonus,
                  int weaponType, int weaponSize, int range) {
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

    public int getWeaponSize() {
        return mWeaponSize;
    }

    public void setWeaponSize(int weaponSize) {
        mWeaponSize = weaponSize;
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

