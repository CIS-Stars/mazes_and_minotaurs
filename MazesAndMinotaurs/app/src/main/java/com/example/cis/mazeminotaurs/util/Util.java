package com.example.cis.mazeminotaurs.util;

import com.example.cis.mazeminotaurs.R;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by jusmith on 3/31/17.
 */

public class Util {
    public static final int[] sBarbWeapons = {R.string.barb_axe, R.string.barb_club, R.string.barb_mace, R.string.barb_sword};
    public static final int[] sMissleWeapons = {R.string.bow, R.string.javelin, R.string.sling, R.string.arrows, R.string.slingshot};
    public static final int[] sMeleeWeapons = {R.string.dagger, R.string.mace, R.string.axe, R.string.spear, R.string.sword};

    private static Random sRandom;

    /**
     * "Rolls a die" with specified number of sides, once.
     * @param sides number of sides of the die
     * @return the roll
     */
    public static int roll(int sides) {
        if (sRandom == null) {
            sRandom = new Random();
        }

        return sRandom.nextInt(sides) + 1;
    }

    /**
     * "Rolls a die" with specified number of sides, the specified number of rolls
     * @param sides number of sides of the die
     * @param rolls how times to roll the die
     *
     * @return the sum of the rolls
     */
    public static int roll(int sides, int rolls) {
        int sum = 0;
        for (int i = 0; i < rolls; i++) {
            sum += roll(sides);
        }
        return sum;
    }
}
