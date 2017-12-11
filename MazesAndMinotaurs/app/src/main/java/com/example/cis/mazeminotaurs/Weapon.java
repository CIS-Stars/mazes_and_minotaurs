package com.example.cis.mazeminotaurs;

/**
 *
 * @author Thorin Schmidt on 3/20/2017.
 */

public class Weapon extends Equipment {

    //instance variables
    private int mDamageDie;
    private int mNumberOfDice;
    private int mDamageBonus;
    private boolean mIsMelee; //uses string values melee and missile
    private int mWeaponSize; //uses string values from game_references.xml
    private int mRange;

    /*public Weapon(int resId, int encumberance, int quantity, double costInSp,
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
     * Weapon Constructor
     * @param resId The name of the weapon.
     * @param encumberance The encumberance value of the weapon.
     * @param quantity The number that the player is holding.
     * @param costInSp The cost of the weapon in silver.
     * @param longDescription The description of the weapon.
     * @param damageDie The damage die of the weapon.
     * @param numberOfDice The number of damage die to use when attacking.
     * @param damageBonus The damage to add onto the damage roll.
     * @param isMelee If the weapon is melee or not.
     * @param weaponSize The size of the weapon. Unused.
     * @param range The range of the weapon.
     */
    public Weapon(String resId, int encumberance, int quantity, double costInSp,
                  String longDescription, int damageDie, int numberOfDice, int damageBonus,
                  boolean isMelee, int weaponSize, int range) {
        super(resId, encumberance, quantity, costInSp, longDescription);
        mDamageDie = damageDie;
        mNumberOfDice = numberOfDice;
        mDamageBonus = damageBonus;
        mIsMelee = isMelee;
        mRange = range;
    }

    /**
     * Getter of the mDamageDie property.
     *
     * @return the value of mDamageDie.
     */
    public int getDamageDie() {
        return mDamageDie;
    }

    /**
     * Setter of the mDamageDie property.
     * @param damageDie the new value of mDamageDie.
     */
    public void setDamageDie(int damageDie) {
        mDamageDie = damageDie;
    }

    /**
     * Getter of the mNumberOfDie property.
     * @return the value of mNumberOfDie.
     */
    public int getNumberOfDice() {
        return mNumberOfDice;
    }

    /**
     * Setter of the mNumberOfDie property.
     * @param numberOfDice the new value of mNumberOfDie.
     */
    public void setNumberOfDice(int numberOfDice) {
        mNumberOfDice = numberOfDice;
    }

    /**
     * Getter of the mDamageBonus property.
     * @return the value of mDamageBonus.
     */
    public int getDamageBonus() {
        return mDamageBonus;
    }

    /**
     * Setter of the mDamageBonus property.
     * @param damageBonus the new value of mDamageBonus.
     */
    public void setDamageBonus(int damageBonus) {
        mDamageBonus = damageBonus;
    }

    /**
     * Returns a resource id based on if the weapon is melee.
     * @return R.string.melee if mIsMelee is true else R.string.melee
     */
    public int getWeaponType() {
        return (mIsMelee) ? R.string.melee : R.string.missile;
    }

    /**
     * Getter of the mIsMelee property.
     * @return the value of mIsMelee.
     */
    public boolean isMelee() {
        return mIsMelee;
    }

    /**
     * Setter of the mIsMelee property.
     * @param melee the new value of mIsMelee.
     */
    public void setMelee(boolean melee) {
        mIsMelee = melee;
    }

    /**
     * Getter of the mRange property.
     * @return the value of mRange.
     */
    public int getRange() {
        return mRange;
    }

    /**
     * Setter of the mRange property.
     * @param range the new value of mRange.
     */
    public void setRange(int range) {
        mRange = range;
    }

    /**
     * Getter of the mWeaponSize property.
     * @return the value of mWeaponSize.
     */
    public int getWeaponSize() {
        return mWeaponSize;
    }

    /**
     * Setter of the mWeaponSize property.
     * @param weaponSize the new value of mWeaponSize.
     */
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

