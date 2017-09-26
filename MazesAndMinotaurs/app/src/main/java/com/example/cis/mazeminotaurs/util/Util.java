package com.example.cis.mazeminotaurs.util;

import com.example.cis.mazeminotaurs.EquipmentDB;
import com.example.cis.mazeminotaurs.R;
import com.example.cis.mazeminotaurs.Weapon;

import java.util.Random;

/**
 * Created by jusmith on 3/31/17.
 */

public class Util {
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
     * Returns the ammo used for a specified missile weapon.
     */
    public Weapon getAmmo(Weapon rangedWeapon){
        Weapon[] possibleWeapons = new Weapon[]{EquipmentDB.getInstance().getWeapon(R.string.bow),
                EquipmentDB.getInstance().getWeapon(R.string.sling)};
        boolean valid = false;

        for (Weapon weapon: possibleWeapons) {
            if (weapon==rangedWeapon) {
                valid = true;
                break;
            }
        }

        if (!valid) {
            return null;
        }
        if (rangedWeapon == possibleWeapons[0]) {
            return EquipmentDB.getInstance().getWeapon(R.string.arrows);
        } else {
            return EquipmentDB.getInstance().getWeapon(R.string.slingshot);
        }
    }
}
