package com.example.cis.mazeminotaurs.util;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

import com.example.cis.mazeminotaurs.EquipmentDB;
import com.example.cis.mazeminotaurs.Weapon;
import com.example.cis.mazeminotaurs.character.PlayerCharacter;
import com.example.cis.mazeminotaurs.character.classes.Barbarian;

/**
 * Created by jusmith on 9/28/2017.
 */

public class Util {
    public static PlayerCharacter createDummyCharacter() {
        PlayerCharacter pc = new PlayerCharacter();
        Barbarian barbarian = new Barbarian(pc, EquipmentDB.getInstance().getWeapon(CommonStrings.BARB_AXE.getValue()),
                EquipmentDB.getInstance().getWeapon(CommonStrings.BARB_AXE.getValue()));
        pc.setCharClass(barbarian);
        pc.initializeClass();

        return pc;
    }

    /**
     * Returns the ammo used for a specified missile weapon.
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

    public static void clearBackStack(FragmentActivity activity) {
        FragmentManager fm = activity.getSupportFragmentManager();
        clearBackStack(fm);
    }

    public static void clearBackStack(Fragment fragment) {
        FragmentManager fm = fragment.getFragmentManager();
        clearBackStack(fm);
    }

    public static void clearBackStack(FragmentManager fragmentManager) {
        while (fragmentManager.getBackStackEntryCount() > 0) {
            fragmentManager.popBackStack();
        }
    }
}
