package com.example.cis.mazeminotaurs.character.classes;

import com.example.cis.mazeminotaurs.Equipment;
import com.example.cis.mazeminotaurs.EquipmentDB;
import com.example.cis.mazeminotaurs.Weapon;
import com.example.cis.mazeminotaurs.character.Gender;
import com.example.cis.mazeminotaurs.character.PlayerCharacter;
import com.example.cis.mazeminotaurs.character.stats.Score;
import com.example.cis.mazeminotaurs.rollDice.rollDice;
import com.example.cis.mazeminotaurs.util.CommonStrings;
import com.example.cis.mazeminotaurs.util.Util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * This class represents the Barbarian that is in the game.
 *
 * @author jusmith on 3/31/17.
 */

public class Barbarian extends Warrior {

    /**
     * Blank constructor. Used primarily for reflection purposes.
     * <b>DO NOT USE THIS FOR UI DISPLAYS.</b>
     */
    public Barbarian() {
        this(null, null, null);
    }

    /**
     * Constructor that requires a {@link PlayerCharacter} instance and two weapons.
     * <p>If the starting weapon is invalid, it will automatically assign it an bow.</p>
     * <p>If the selected weapon of choice is invalid, it will automatically assign it
     * an barbarian axe</p>
     *
     * @param playerCharacter the character using this class
     * @param weaponOfChoice  the user desired weapon of choice
     * @param startWeapon     the user desired starting weapon
     */
    public Barbarian(PlayerCharacter playerCharacter, Weapon weaponOfChoice, Weapon startWeapon) {
        setPossibleStartWeapons(new Weapon[]{
                EquipmentDB.getInstance().getWeapon(CommonStrings.BOW.getValue()),
                EquipmentDB.getInstance().getWeapon(CommonStrings.JAVELIN.getValue()),
                EquipmentDB.getInstance().getWeapon(CommonStrings.SLING.getValue()),
        });

        setPossibleWeaponsOfChoice(new Weapon[] {
                EquipmentDB.getInstance().getWeapon(CommonStrings.BARB_AXE.getValue()),
                EquipmentDB.getInstance().getWeapon(CommonStrings.BARB_CLUB.getValue()),
                EquipmentDB.getInstance().getWeapon(CommonStrings.BARB_MACE.getValue()),
                EquipmentDB.getInstance().getWeapon(CommonStrings.BARB_SWORD.getValue()),
        });

        // The scores that can be used for level ups.
        setPossibleLevelScores(new Score[]{Score.SKILL, Score.WILL, Score.MIGHT});

        Score[] primAttrs = {Score.MIGHT, Score.WILL};
        ArrayList<Score> primAttributes = new ArrayList<>();
        Collections.addAll(primAttributes, primAttrs);

        EquipmentDB equipDB = EquipmentDB.getInstance();
        ArrayList<Equipment> startGear = new ArrayList<>();

        //Equipment checks
        setWeaponOfChoice(weaponOfChoice);
        startGear.add(getWeaponOfChoice());

        if (Arrays.asList(getPossibleStartWeapons()).contains(startWeapon)){
            startGear.add(startWeapon);
            Equipment ammo = Util.getAmmo(startWeapon);
            if (ammo != null) {startGear.add(ammo);}
        } else {
            startGear.add(equipDB.getWeapon(CommonStrings.BOW.getValue()));
            startGear.add(equipDB.getWeapon(CommonStrings.ARROWS.getValue()));
        }

        int rolledGold = rollDice.roll(6, 3) * 5;

        //Add the rest of starting equipment
        startGear.add(equipDB.getWeapon(CommonStrings.DAGGER.getValue()));
        startGear.add(equipDB.getArmor(CommonStrings.SHIELD.getValue()));

        setBasicHits(12);
        setCharacter(playerCharacter);
        setPrimaryAttributes(primAttributes);
        setRequiredGender(Gender.MALE);
        setResId(Classes.BARBARIAN.getResId());
        setStartMoney(rolledGold * 5);
        setStartGear(startGear);
    }

    /**
     * The addition made to the damage roll when attacking using a barbarian melee
     * weapon.
     * <i>As of 12/7/17, this is not used in the code.</i>
     *
     * @return Character's modifier of their Might score.
     */
    public int getBattleMightBonus(){
        return getCharacter().getScore(Score.MIGHT).getModifier();
    }

    /**
     * The addition made to the EDC of the character, unless surprised or wearing a
     * breastplate. Only applies to melee attacks.
     * <i>As of 12/7/17, this is not used in the code.</i>
     *
     * @return Character's modifier of their Will score.
     */
    public int getBattleFuryBonus(){
        return getCharacter().getScore(Score.WILL).getModifier();
    }
}
