package com.example.cis.mazeminotaurs;

import com.example.cis.mazeminotaurs.util.CommonStrings;

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
        equipment.add(new Equipment(CommonStrings.ROWBOAT.getValue(), 3, 1, 300, "A Sturdy Rowboat"));
        equipment.add(new Equipment(CommonStrings.SMALL_SAIL.getValue(), 300, 1, 3000, "A Small, Sturdy Sailing Vessel"));
        equipment.add(new Equipment(CommonStrings.MERCHANT_SHIP.getValue(), 3000, 1, 15000, "A Merchant Roundship"));
        equipment.add(new Equipment(CommonStrings.WAR_GALLEY.getValue(), 3000, 1, 30000, "A Dangerous War Galley"));
        equipment.add(new Equipment(CommonStrings.HORSE.getValue(), 16, 1, 600, "A Sturdy Horse"));
        equipment.add(new Equipment(CommonStrings.MULE.getValue(), 13, 1, 100, "A Sturdy Mule"));
        equipment.add(new Equipment(CommonStrings.STAFF.getValue(), 2, 1, 5, "A Sturdy Staff"));
        equipment.add(new Equipment(CommonStrings.OIL_FLASK.getValue(), 0, 1, 10, "A Small Flask of Oil"));
        equipment.add(new Equipment(CommonStrings.TORCH.getValue(), 1, 1, 2, "A Simple Torch"));
        equipment.add(new Equipment(CommonStrings.FLINT_AND_TINDER.getValue(), 0, 1, 5, "Standard Fire-Starting Kit"));
        equipment.add(new Equipment(CommonStrings.ROPE.getValue(), 2, 1, 15, "A 30' Rope!"));
        equipment.add(new Equipment(CommonStrings.BEDROLL.getValue(), 2, 1, 25, "A Comfy Bed Roll"));
        equipment.add(new Equipment(CommonStrings.RATIONS.getValue(), 0, 1, 2, "One Day's Worth Of Food"));
        equipment.add(new Equipment(CommonStrings.WATERSKIN.getValue(), 1, 1, 2, "A Skin Full of Water"));

        //(resID, encumbrance, qty, costInSilver, longDescription, dmgDie, numDice, dmgBonus, type, size, range)
        weapons.add(new Weapon(CommonStrings.BARB_AXE.getValue(), 2, 1, 30, "Large, Ugly Axe!", 6, 1, 0, R.string.melee, R.string.size_barbarian, 0));
        weapons.add(new Weapon(CommonStrings.BARB_CLUB.getValue(), 2, 1, 0, "Large, Heavy Club!", 6, 1, 0, R.string.melee, R.string.size_barbarian, 0));
        weapons.add(new Weapon(CommonStrings.BARB_SWORD.getValue(), 2, 1, 60, "Large, Ugly, Heavy Sword!", 6, 1, 0, R.string.melee, R.string.size_barbarian, 0));
        weapons.add(new Weapon(CommonStrings.BARB_MACE.getValue(), 2, 1, 30, "Large, Ugly Mace!", 6, 1, 0, R.string.melee, R.string.size_barbarian, 0));
        weapons.add(new Weapon(CommonStrings.DAGGER.getValue(), 0, 1, 15, "Small Sinister dagger!", 3, 1, 0, R.string.melee, R.string.size_normal, 0));
        weapons.add(new Weapon(CommonStrings.THROW_KNIFE.getValue(), 0, 1, 15, "A knife made for throwing!", 3, 1, 0, R.string.missile, R.string.size_normal, 0));
        weapons.add(new Weapon(CommonStrings.AXE.getValue(), 1, 1, 30, "A Nasty, Wicked Axe!", 6, 1, 0, R.string.melee, R.string.size_normal, 0));
        weapons.add(new Weapon(CommonStrings.CLUB.getValue(), 1, 1, 0, "A not-so-heavy Club!", 6, 1, 0, R.string.melee, R.string.size_normal, 0));
        weapons.add(new Weapon(CommonStrings.SWORD.getValue(), 1, 1, 60, "A Dangerous, Deadly Blade!", 6, 1, 0, R.string.melee, R.string.size_normal, 0));
        weapons.add(new Weapon(CommonStrings.SPEAR.getValue(), 2, 1, 30, "Wicked, Deadly Spear!", 6, 1, 0, R.string.melee, R.string.size_normal, 0));
        weapons.add(new Weapon(CommonStrings.BOW.getValue(), 1, 1, 40, "Standard Bow", 6, 1, 0, R.string.missile, R.string.size_normal, 300));
        weapons.add(new Weapon(CommonStrings.SLING.getValue(), 0, 1, 5, "Standard Sling", 6, 1, 0, R.string.missile, R.string.size_normal, 150));
        weapons.add(new Weapon(CommonStrings.JAVELIN.getValue(), 2, 1, 30, "A Deadly Javelin", 6, 1, 0, R.string.missile, R.string.size_normal, 150));
        weapons.add(new Weapon(CommonStrings.ARROWS.getValue(), 1, 12, 12, "A quiver of Arrows", 0, 0, 0, R.string.missile, R.string.size_normal, 0));
        weapons.add(new Weapon(CommonStrings.SLINGSHOT.getValue(), 1, 10, 2, "A Small Sack of Sling Stones", 0, 0, 0, R.string.missile, R.string.size_normal, 0));

        //(resID, encumbrance, qty, costInSilver, longDescription, defenseBonus)
        armor.add(new Armor(CommonStrings.BREASTPLATE.getValue(), 3, 1, 150, "A Sturdy, Functional Breastplate", 2));
        armor.add(new Armor(CommonStrings.HELMET.getValue(), 1, 1, 75, "A Sturdy, Functional Helmet", 2));
        armor.add(new Armor(CommonStrings.SHIELD.getValue(), 2, 1, 75, "A Sturdy, Functional Shield", 2));
        armor.add(new Armor(CommonStrings.BOEOTIAN.getValue(), 1, 1, 40, "A Cheap Boeotian Helmet", 1));
        armor.add(new Armor(CommonStrings.PELTAST.getValue(), 1, 1, 35, "A Lightweight Peltast Shield", 1));
        armor.add(new Armor(CommonStrings.LINOTHORAX.getValue(), 2, 1, 75, "A Light Linothorax Breastplate", 1));

    }

    /**
     * Attempts to add a weapon to the list if the resId is unique.
     *
     * @param weapon The weapon to add
     * @return If the weapon was added to the database.
     */
    public boolean addWeapon(Weapon weapon) {
        boolean isValid = true;
        for (Weapon wep : getWeapons()) {
            if (weapon.getResId().equals(wep.getResId())) {
                isValid = false;
                break;
            }
        }

        if (isValid) {
            getWeapons().add(weapon);
        }
        return isValid;
    }

    /**
     * Attempts to add a armor to the list if the resId is unique.
     *
     * @param armor The armor to add
     * @return If the armor was added to the database.
     */
    public boolean addArmor(Armor armor) {
        boolean isValid = true;
        for (Armor arm : getArmors()) {
            if (armor.getResId().equals(arm.getResId())) {
                isValid = false;
                break;
            }
        }

        if (isValid) {
            getArmors().add(armor);
        }
        return isValid;
    }

    /**
     * Attempts to add a equipment to the list if the resId is unique.
     *
     * @param equipment The equipment to add
     * @return If the equipment was added to the database.
     */
    public boolean addEquipment(Equipment equipment) {
        boolean isValid = true;
        for (Equipment equip : getEquipments()) {
            if (equipment.getResId().equals(equip.getResId())) {
                isValid = false;
                break;
            }
        }

        if (isValid) {
            getEquipments().add(equipment);
        }
        return isValid;
    }

    public Weapon getWeapon(String resId) {
        for (Weapon weapon : weapons){
            if (weapon.getResId().equals(resId)) {
                return weapon;
            }
        }
        return null;
    }

    public Armor getArmor(String resId) {
        for (Armor item : armor){
            if (item.getResId().equals(resId)) {
                return item;
            }
        }
        return null;
    }

    public Equipment getEquipment(String resId) {
        for (Equipment item : equipment){
            if (item.getResId().equals(resId)) {
                return item;
            }
        }
        return null;
    }

    public ArrayList<Weapon> getWeapons() {
        return weapons;
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
