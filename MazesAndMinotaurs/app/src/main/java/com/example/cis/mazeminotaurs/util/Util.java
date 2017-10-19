package com.example.cis.mazeminotaurs.util;

import com.example.cis.mazeminotaurs.EquipmentDB;
import com.example.cis.mazeminotaurs.R;
import com.example.cis.mazeminotaurs.Weapon;
import com.example.cis.mazeminotaurs.character.PlayerCharacter;
import com.example.cis.mazeminotaurs.character.classes.Barbarian;

/**
 * Created by jusmith on 9/28/2017.
 */

public class Util {
    public static PlayerCharacter createDummyCharacter() {
        PlayerCharacter pc = new PlayerCharacter();
        Barbarian barbarian = new Barbarian(pc, EquipmentDB.getInstance().getWeapon(R.string.barb_axe),
                EquipmentDB.getInstance().getWeapon(R.string.barb_axe));
        pc.setCharClass(barbarian);
        pc.initializeClass();

        return pc;
    }

    /**
     * Returns the ammo used for a specified missile weapon.
     */
    public static Weapon getAmmo(Weapon rangedWeapon){
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
