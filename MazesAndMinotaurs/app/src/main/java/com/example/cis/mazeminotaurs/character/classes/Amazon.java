package com.example.cis.mazeminotaurs.character.classes;

import com.example.cis.mazeminotaurs.Equipment;
import com.example.cis.mazeminotaurs.EquipmentDB;
import com.example.cis.mazeminotaurs.Weapon;
import com.example.cis.mazeminotaurs.character.Gender;
import com.example.cis.mazeminotaurs.character.PlayerCharacter;
import com.example.cis.mazeminotaurs.character.stats.Score;
import com.example.cis.mazeminotaurs.rollDice.rollDice;
import com.example.cis.mazeminotaurs.util.CommonStrings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * This class represents the Amazon (Normal) that is in the game.
 *
 * @author jusmith on 4/13/17.
 */

public class Amazon extends Warrior {

    /**
     * Blank constructor. Used primarily for reflection purposes.
     * <b>DO NOT USE THIS FOR UI DISPLAYS.</b>
     */
    public Amazon() {
        this(null,null);
    }

    /**
     * Constructor that requires a {@link PlayerCharacter} instance and a weapon.
     * <p>If the starting weapon is invalid, it will automatically assign it an axe.</p>
     *
     * @param playerCharacter the character using this class
     * @param startingWeapon  the user desired starting weapon
     */
    public Amazon(PlayerCharacter playerCharacter, Weapon startingWeapon){
        setPossibleStartWeapons(new Weapon[]{
                EquipmentDB.getInstance().getWeapon(CommonStrings.AXE.getValue()),
                EquipmentDB.getInstance().getWeapon(CommonStrings.SPEAR.getValue()),
                EquipmentDB.getInstance().getWeapon(CommonStrings.SWORD.getValue()),
        });

        setPossibleWeaponsOfChoice(new Weapon[]{
                EquipmentDB.getInstance().getWeapon(CommonStrings.BOW.getValue()),
        });

        // The scores that can be used for level ups.
        setPossibleLevelScores(new Score[]{Score.GRACE, Score.MIGHT,
                Score.SKILL, Score.WILL, Score.WITS});

        Score[] primAttrs = {Score.SKILL, Score.GRACE};
        ArrayList<Score> primAttributes = new ArrayList<>();
        Collections.addAll(primAttributes, primAttrs);

        // Setup for checking starting gear
        EquipmentDB equipmentDB = EquipmentDB.getInstance();
        ArrayList<Equipment> startGear = new ArrayList<>();

        // Check the starting melee weapon if it is valid
        if (Arrays.asList(getPossibleStartWeapons()).contains(startingWeapon)) {
            startGear.add(startingWeapon);
        } else {
            startGear.add(getPossibleStartWeapons()[0]);
        }
        setWeaponOfChoice(equipmentDB.getWeapon(CommonStrings.BOW.getValue()));

        // Adding the rest of the equipment
        startGear.add(equipmentDB.getWeapon(CommonStrings.DAGGER.getValue()));
        startGear.add(equipmentDB.getArmor(CommonStrings.SHIELD.getValue()));
        startGear.add(equipmentDB.getWeapon(CommonStrings.BOW.getValue()));
        startGear.add(equipmentDB.getWeapon(CommonStrings.ARROWS.getValue()));

        // Roll for gold
        int rolledGold = rollDice.roll(6, 3) * 5;

        setBasicHits(12);
        setCharacter(playerCharacter);
        setPrimaryAttributes(primAttributes);
        setRequiredGender(Gender.FEMALE);
        setResId(Classes.AMAZON.getResId());
        setStartMoney(rolledGold);
        setStartGear(startGear);

    }

    /**
     * The addition made to the damage roll when attacking using a bow.
     * <i>As of 12/7/17, this is not used in the code.</i>
     *
     * @return Character's modifier of their Skill score.
     */
    public int getDeadlyShotBonus() {
        return getCharacter().getScore(Score.SKILL).getModifier();
    }

    /**
     * The addition made to the EDC of the character, unless surprised or wearing a
     * breastplate. Only applies to melee attacks.
     * <i>As of 12/7/17, this is not used in the code.</i>
     *
     * @return Character's modifier of their Grace score.
     */
    public int getBattleGraceBonus() {
        return getCharacter().getScore(Score.GRACE).getModifier();
    }
}