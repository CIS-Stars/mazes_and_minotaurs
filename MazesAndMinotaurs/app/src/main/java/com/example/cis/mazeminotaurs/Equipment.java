package com.example.cis.mazeminotaurs;

/**
 * This serves as the base class for every piece of equipment.
 * @author Thorin Schmidt on 3/19/2017.
 */

public class Equipment {
    //instance variables
    /**
     * The resource id. As of now, it is now the name of the item.
     */
    private String mResId;

    /**
     * The encumberance value of the item.
     */
    private int mEncumberance;

    /**
     * How much of the item one has.
     */
    private int mQuantity;

    /**
     * The cost of the item in silver.
     */
    private double mCostInSp;

    /**
     * The description of the item.
     */
    private String mLongDescription;

    /**
     * A boolean that shows if the player has it equipped.
     */
    private boolean mEquipped;

    /**
     * Default constructor
     *
     * @param resId           The name of the item. Must be unique.
     * @param encumberance    The encumberance of the item.
     * @param quantity        The number of the item. Unused.
     * @param costInSp        The cost of the item.
     * @param longDescription The description of the item.
     */
    public Equipment(String resId, int encumberance, int quantity,
                     double costInSp, String longDescription) {
        mResId = resId;
        mEncumberance = encumberance;
        mCostInSp = costInSp;
        mLongDescription = longDescription;
        mEquipped = false;
    }

    /**
     * Getter for the mEquipped property.
     * @return the value of mEquipped.
     */
    public boolean isEquipped() {
        return mEquipped;
    }

    /**
     * Setter for the mEquipped property.
     * @param equipped the new value of mEquipped.
     */
    public void setEquipped(boolean equipped) {
        mEquipped = equipped;
    }

    /**
     * Getter for the mResId property.
     * @return the value of -.
     */
    public String getResId() {
        return mResId;
    }

    /**
     * Setter for the mResId property.
     * @param resId the new value of mResId.
     */
    public void setResId(String resId) {
        mResId = resId;
    }

    /**
     * Getter for the mEncumberance property.
     * @return the value of mEncumberance.
     */
    public int getEncumberance() {
        return mEncumberance;
    }

    /**
     * Setter for the mEncumberance property.
     * @param encumberance the new value of mEncumberance.
     */
    public void setEncumberance(int encumberance) {
        mEncumberance = encumberance;
    }

    /**
     * Getter for the mQuantity property.
     * @return the value of mQuantity.
     */
    public int getQuantity() {
        return mQuantity;
    }

    /**
     * Setter for the mQuantity property.
     * @param quantity the new value of mQuantity.
     */
    public void setQuantity(int quantity) {
        mQuantity = quantity;
    }

    /**
     * Getter for the mCostInSp property.
     * @return the value of mCostInSp.
     */
    public double getCostInSp() {
        return mCostInSp;
    }

    /**
     * Setter for the mCostInSp property.
     * @param costInSp the new value of mCostInSp.
     */
    public void setCostInSp(double costInSp) {
        mCostInSp = costInSp;
    }

    /**
     * Getter for the mLongDescription property.
     * @return the value of mLongDescription.
     */
    public String getLongDescription() {
        return mLongDescription;
    }

    /**
     * Setter for the mLongDescription property.
     * @param longDescription the new value of mLongDescription.
     */
    public void setLongDescription(String longDescription) {
        mLongDescription = longDescription;
    }

    @Override
    public String toString() {
        return "Equipment{" +
                "mResId=" + mResId +
                ", mEncumberance=" + mEncumberance +
                ", mCostInSp=" + mCostInSp + "\n" +
                ", mLongDescription='" + mLongDescription + '\'' +
                '}';
    }
}
