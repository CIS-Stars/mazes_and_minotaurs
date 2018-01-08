package com.example.cis.mazeminotaurs;

/**
 * This class serves as a container for data related to an armor piece.
 *
 * @author Thorin Schmidt on 3/20/2017.
 */

public class Armor extends Equipment {

    //instance variables
    /**
     * The bonus defense granted by wearing this armor piece.
     */
    private int mDefenseBonus;

    /**
     * Default Constructor.
     *
     * @param resId           The name of the armor.
     * @param encumberance    The encumberance value of the armor.
     * @param quantity        The number the player is holding.
     * @param costInSp        The cost of the armor in silver.
     * @param longDescription The description of the armor.
     * @param defenseBonus    The defense bonus of the armor.
     */
    public Armor(String resId, int encumberance, int quantity, double costInSp, String longDescription, int defenseBonus) {
        super(resId, encumberance, quantity, costInSp, longDescription);
        mDefenseBonus = defenseBonus;
    }

    /**
     * Getter for the mDefenseBonus property.
     * @return the value of mDefenseBonus
     */
    public int getDefenseBonus() {
        return mDefenseBonus;
    }

    /**
     * Setter for the mDefenseBonus property.
     * @param defenseBonus the new value of mDefenseBonus
     */
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
