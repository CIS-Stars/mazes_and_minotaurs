package com.example.cis.mazeminotaurs;

import java.util.ArrayList;

/**
 * Created by Thorin Schmidt on 4/18/2017.
 */

public class EquipmentDB {
    private static final String TAG = Portfolio.class.getName();
    private ArrayList<Equipment> equipment;
    private ArrayList<Weapon> weapons;
    private ArrayList<Armor> armor;



    private static final EquipmentDB ourInstance = new EquipmentDB();

    public static EquipmentDB getInstance() {
        return ourInstance;
    }

    private EquipmentDB() {
        equipment = new ArrayList<>();
        weapons = new ArrayList<>();
        armor = new ArrayList<>();

        //for testing only, later this will be read in from a file.
        weapons.add(new Weapon(R.string.barb_axe, 2, 1, 30, "Large, Ugly Axe!", 6, 1, 0, "melee"));
        weapons.add(new Weapon(R.string.bow, 1, 1, 40, "Standard Bow", 6, 1, 0, "missile"));
        weapons.add(new Weapon(R.string.arrows, 1, 12, 12, "A quiver of Arrows", 0, 0, 0, "missile"));

    }

    public Weapon getWeapon(int resId){
        for (Weapon weapon : weapons){
            if (resId == weapon.getResId()){
                return weapon;
            }
        }
        return null;
    }
}
