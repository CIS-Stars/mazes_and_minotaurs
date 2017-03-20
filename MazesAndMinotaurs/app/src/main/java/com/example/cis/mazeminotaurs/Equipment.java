package com.example.cis.mazeminotaurs;

/**
 * Created by Thorin Schmidt on 3/19/2017.
 */

public class Equipment {
    //instance variables
    int mResId;
    int mEncumberance;
    double mCostInSp;
    String mLongDescription;

    public Equipment(int resId, int encumberance, double costInSp, String longDescription) {
        mResId = resId;
        mEncumberance = encumberance;
        mCostInSp = costInSp;
        mLongDescription = longDescription;
    }

    public int getResId() {
        return mResId;
    }

    public void setResId(int resId) {
        mResId = resId;
    }

    public int getEncumberance() {
        return mEncumberance;
    }

    public void setEncumberance(int encumberance) {
        mEncumberance = encumberance;
    }

    public double getCostInSp() {
        return mCostInSp;
    }

    public void setCostInSp(double costInSp) {
        mCostInSp = costInSp;
    }

    public String getLongDescription() {
        return mLongDescription;
    }

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
