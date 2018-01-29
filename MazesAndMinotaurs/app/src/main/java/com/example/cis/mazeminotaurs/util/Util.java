package com.example.cis.mazeminotaurs.util;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

import com.example.cis.mazeminotaurs.EquipmentDB;
import com.example.cis.mazeminotaurs.Weapon;
import com.example.cis.mazeminotaurs.character.PlayerCharacter;
import com.example.cis.mazeminotaurs.character.classes.Barbarian;

/**
 * @author jusmith on 9/28/2017.
 */

public class Util {
    /**
     * Returns the ammo used for a specified missile weapon.
     * @return A weapon instance of the ammo or null.
     */
    public static Weapon getAmmo(Weapon rangedWeapon){
        Weapon[] possibleWeapons = new Weapon[]{EquipmentDB.getInstance().getWeapon(CommonStrings.BOW.getValue()),
                EquipmentDB.getInstance().getWeapon(CommonStrings.SLING.getValue())};
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
            return EquipmentDB.getInstance().getWeapon(CommonStrings.ARROWS.getValue());
        } else {
            return EquipmentDB.getInstance().getWeapon(CommonStrings.SLINGSHOT.getValue());
        }
    }

    /**
     * Creates a PlayerCharacter instance for testing purposes. Will always be
     * barbarian class.
     *
     * @return a PlayerCharacter instance.
     */
    public static PlayerCharacter createDummyCharacter() {
        PlayerCharacter pc = new PlayerCharacter();
        Barbarian barbarian = new Barbarian(pc, EquipmentDB.getInstance().getWeapon(CommonStrings.BARB_AXE.getValue()),
                EquipmentDB.getInstance().getWeapon(CommonStrings.BARB_AXE.getValue()));
        pc.setCharClass(barbarian);
        pc.initializeClass();

        return pc;
    }

    /**
     * Searches for a specified object in an array.
     *
     * @param array The array to search through
     * @param obj   The object to search for
     * @return If obj inside of array
     */
    public static boolean contains(Object[] array, Object obj) {
        for (Object i : array) {
            if (obj == i) {
                return true;
            }
        }
        return false;
    }

    /**
     * A helper method for clearing the backstack.
     * @param activity the activity to get the fragment manager from.
     */
    public static void clearBackStack(FragmentActivity activity) {
        FragmentManager fm = activity.getSupportFragmentManager();
        clearBackStack(fm);
    }

    /**
     * A helper method for clearing the backstack.
     * @param fragment the fragment to get the fragment manager from.
     */
    public static void clearBackStack(Fragment fragment) {
        FragmentManager fm = fragment.getFragmentManager();
        clearBackStack(fm);
    }

    /**
     * Pops the backstack, aka goes back to the home activity, clearing all fragments.
     * @param fragmentManager fragment manager instance to clear.
     */
    public static void clearBackStack(FragmentManager fragmentManager) {
        fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }
}
