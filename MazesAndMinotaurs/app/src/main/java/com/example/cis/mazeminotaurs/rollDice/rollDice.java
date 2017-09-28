package com.example.cis.mazeminotaurs.rollDice;

import java.util.Random;

/**
 * Created by jusmith on 3/31/17.
 *
 * modified by thorinSchmidt on 9/28/2017
 *   refactored module to rollDice, added the d20() and d6() methods.
 */

public class rollDice {
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

    /**
     * "Rolls a d20" once.
     *
     * @return the roll
     */
    public static int d20() {
        if (sRandom == null) {
            sRandom = new Random();
        }

        return sRandom.nextInt(20) + 1;
    }

    /**
     * "Rolls a d6" once.
     *
     * @return the roll
     */
    public static int d6() {
        if (sRandom == null) {
            sRandom = new Random();
        }

        return sRandom.nextInt(6) + 1;
    }

}
