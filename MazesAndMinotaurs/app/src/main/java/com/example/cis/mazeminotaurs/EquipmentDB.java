package com.example.cis.mazeminotaurs;

import java.util.ArrayList;

/**
 * Created by Thorin Schmidt on 4/18/2017.
 */

public class EquipmentDB {
    public static final String FILENAME = "EquipmentDB.json";

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
        //(resID, encumbrance, qty, costInSilver, longDescription)
        equipment.add(new Equipment(R.string.rowboat, 3, 1, 300, "A Sturdy Rowboat"));
        equipment.add(new Equipment(R.string.small_sail, 300, 1, 3000, "A Small, Sturdy Sailing Vessel"));
        equipment.add(new Equipment(R.string.merchant_ship, 3000, 1, 15000, "A Merchant Roundship"));
        equipment.add(new Equipment(R.string.war_galley, 3000, 1, 30000, "A Dangerous War Galley"));
        equipment.add(new Equipment(R.string.horse, 16, 1, 600, "A Sturdy Horse"));
        equipment.add(new Equipment(R.string.mule, 13, 1, 100, "A Sturdy Mule"));
        equipment.add(new Equipment(R.string.staff, 2, 1, 5, "A Sturdy Staff"));
        equipment.add(new Equipment(R.string.oil_flask, 0, 1, 10, "A Small Flask of Oil"));
        equipment.add(new Equipment(R.string.torch, 1, 1, 2, "A Simple Torch"));
        equipment.add(new Equipment(R.string.flint_and_tinder, 0, 1, 5, "Standard Fire-Starting Kit"));
        equipment.add(new Equipment(R.string.rope, 2, 1, 15, "A 30' Rope!"));
        equipment.add(new Equipment(R.string.bedroll, 2, 1, 25, "A Comfy Bed Roll"));
        equipment.add(new Equipment(R.string.rations, 0, 1, 2, "One Day's Worth Of Food"));
        equipment.add(new Equipment(R.string.waterskin, 1, 1, 2, "A Skin Full of Water"));

        //(resID, encumbrance, qty, costInSilver, longDescription, dmgDie, numDice, dmgBonus, type, size, range)
        weapons.add(new Weapon(R.string.barb_axe, 2, 1, 30, "Large, Ugly Axe!", 6, 1, 0, R.string.melee, R.string.size_barbarian, 0));
        weapons.add(new Weapon(R.string.barb_club, 2, 1, 0, "Large, Heavy Club!", 6, 1, 0, R.string.melee, R.string.size_barbarian, 0));
        weapons.add(new Weapon(R.string.barb_sword, 2, 1, 60, "Large, Ugly, Heavy Sword!", 6, 1, 0, R.string.melee, R.string.size_barbarian, 0));
        weapons.add(new Weapon(R.string.barb_mace, 2, 1, 30, "Large, Ugly Mace!", 6, 1, 0, R.string.melee, R.string.size_barbarian, 0));
        weapons.add(new Weapon(R.string.dagger, 0, 1, 15, "Small Sinister dagger!", 3, 1, 0, R.string.melee, R.string.size_normal, 0));
        weapons.add(new Weapon(R.string.throw_knife, 0, 1, 15, "A knife made for throwing!", 3, 1, 0, R.string.missile, R.string.size_normal, 0));
        weapons.add(new Weapon(R.string.axe, 1, 1, 30, "A Nasty, Wicked Axe!", 6, 1, 0, R.string.melee, R.string.size_normal, 0));
        weapons.add(new Weapon(R.string.club, 1, 1, 0, "A not-so-heavy Club!", 6, 1, 0, R.string.melee, R.string.size_normal, 0));
        weapons.add(new Weapon(R.string.sword, 1, 1, 60, "A Dangerous, Deadly Blade!", 6, 1, 0, R.string.melee, R.string.size_normal, 0));
        weapons.add(new Weapon(R.string.spear, 2, 1, 30, "Wicked, Deadly Spear!", 6, 1, 0, R.string.melee, R.string.size_normal, 0));
        weapons.add(new Weapon(R.string.bow, 1, 1, 40, "Standard Bow", 6, 1, 0, R.string.missile, R.string.size_normal, 300));
        weapons.add(new Weapon(R.string.sling, 0, 1, 5, "Standard Sling", 6, 1, 0, R.string.missile, R.string.size_normal, 150));
        weapons.add(new Weapon(R.string.javelin, 2, 1, 30, "A Deadly Javelin", 6, 1, 0, R.string.missile, R.string.size_normal, 150));
        weapons.add(new Weapon(R.string.arrows, 1, 12, 12, "A quiver of Arrows", 0, 0, 0, R.string.missile, R.string.size_normal, 0));
        weapons.add(new Weapon(R.string.slingshot, 1, 10, 2, "A Small Sack of Sling Stones", 0, 0, 0, R.string.missile, R.string.size_normal, 0));

        //(resID, encumbrance, qty, costInSilver, longDescription, defenseBonus)
        armor.add(new Armor(R.string.breastplate, 3, 1, 150, "A Sturdy, Functional Breastplate", 2));
        armor.add(new Armor(R.string.helmet, 1, 1, 75, "A Sturdy, Functional Helmet", 2));
        armor.add(new Armor(R.string.shield, 2, 1, 75, "A Sturdy, Functional Shield", 2));
        armor.add(new Armor(R.string.boeotian, 1, 1, 40, "A Cheap Boeotian Helmet", 1));
        armor.add(new Armor(R.string.peltast, 1, 1, 35, "A Lightweight Peltast Shield", 1));
        armor.add(new Armor(R.string.linothorax, 2, 1, 75, "A Light Linothorax Breastplate", 1));



    }

    public Weapon getWeapon(int resId){
        for (Weapon weapon : weapons){
            if (resId == weapon.getResId()){
                return weapon;
            }
        }
        return null;
    }

    public ArrayList<Weapon> getWeapons() {
        return weapons;
    }

    public Armor getArmor(int resId){
        for (Armor item : armor){
            if (resId == item.getResId()){
                return item;
            }
        }
        return null;
    }

    public Equipment getEquipment(int resId){
        for (Equipment item : equipment){
            if (resId == item.getResId()){
                return item;
            }
        }
        return null;
    }

    public ArrayList<Equipment> getEquipments() {
        return equipment;
    }

    public ArrayList<Armor> getArmors() {
        return armor;
    }

    public void setEquipments(ArrayList<Equipment> equipment) {
        this.equipment = equipment;
    }

    public void setWeapons(ArrayList<Weapon> weapons) {
        this.weapons = weapons;
    }

    public void setArmors(ArrayList<Armor> armor) {
        this.armor = armor;
    }
}
