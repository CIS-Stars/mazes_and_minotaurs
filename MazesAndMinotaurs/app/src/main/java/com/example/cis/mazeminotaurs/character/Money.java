package com.example.cis.mazeminotaurs.character;

import com.example.cis.mazeminotaurs.R;

/**
 * Created by jusmith on 4/5/17.
 */

public enum Money {
    COPPER(R.string.copper),
    SILVER(R.string.silver),
    GOLD(R.string.gold);

    private int resId;

    Money(int resId) {
        setResId(resId);
    }

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }
}
