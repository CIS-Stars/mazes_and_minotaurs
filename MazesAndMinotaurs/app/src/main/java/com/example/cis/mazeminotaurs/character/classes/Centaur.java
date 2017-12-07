package com.example.cis.mazeminotaurs.character.classes;

import com.example.cis.mazeminotaurs.Equipment;
import com.example.cis.mazeminotaurs.EquipmentDB;
import com.example.cis.mazeminotaurs.Weapon;
import com.example.cis.mazeminotaurs.character.Gender;
import com.example.cis.mazeminotaurs.character.PlayerCharacter;
import com.example.cis.mazeminotaurs.character.stats.Score;
import com.example.cis.mazeminotaurs.util.CommonStrings;
import com.example.cis.mazeminotaurs.util.Util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * This class represents the Centaur that is in the game.
 *
 * @author jusmith on 5/15/17.
 */

public class Centaur extends Warrior {

    /**
     * Blank constructor. Used primarily for reflection purposes.
     * <b>DO NOT USE THIS FOR UI DISPLAYS.</b>
     */
    public Centaur() {
        this(null,null,null);
    }

    /**
     * Constructor that requires a {@link PlayerCharacter} instance and a weapon.
     * <p>If the starting weapon is invalid, it will automatically assign it an bow.</p>
     * <p>If the selected weapon of choice is invalid, it will automatically assign
     * it an bow.</p>
     *
     * @param playerCharacter the character using this class
     * @param weaponOfChoice  the user desired weapon of choice
     * @param startingWeapon  the user desired starting weapon
     */
    public Centaur(PlayerCharacter playerCharacter, Weapon weaponOfChoice, Weapon startingWeapon) {
        setPossibleStartWeapons(new Weapon[]{
                EquipmentDB.getInstance().getWeapon(CommonStrings.BOW.getValue()),
                EquipmentDB.getInstance().getWeapon(CommonStrings.JAVELIN.getValue()),
                EquipmentDB.getInstance().getWeapon(CommonStrings.SLING.getValue()),
        });
        setPossibleWeaponsOfChoice(new Weapon[] {
                EquipmentDB.getInstance().getWeapon(CommonStrings.BOW.getValue()),
                EquipmentDB.getInstance().getWeapon(CommonStrings.CLUB.getValue()),
                EquipmentDB.getInstance().getWeapon(CommonStrings.JAVELIN.getValue()),
                EquipmentDB.getInstance().getWeapon(CommonStrings.SPEAR.getValue()),
        });

        // The scores that can be used for level ups.
        setPossibleLevelScores(new Score[]{Score.MIGHT, Score.SKILL,
                Score.WILL, Score.WITS});

        Score[] primAttrs = {Score.MIGHT, Score.SKILL};
        ArrayList<Score> primAttributes = new ArrayList<>();
        Collections.addAll(primAttributes, primAttrs);

        // Setting up for equipment check
        EquipmentDB equipmentDB = EquipmentDB.getInstance();
        ArrayList<Equipment> startGear = new ArrayList<>();

        setWeaponOfChoice(weaponOfChoice);

        Weapon finalStartingWeapon;
        if (Arrays.asList(getPossibleStartWeapons()).contains(startingWeapon)) {
            finalStartingWeapon = startingWeapon;
        } else {
            finalStartingWeapon = getPossibleStartWeapons()[0];
        }

        startGear.add(finalStartingWeapon);
        Equipment ammo = Util.getAmmo(finalStartingWeapon);
        if (ammo != null) {startGear.add(ammo);}
        // Equipment done

        int rolledGold = 0;

        startGear.add(equipmentDB.getWeapon(CommonStrings.DAGGER.getValue()));
        startGear.add(equipmentDB.getWeapon(CommonStrings.SPEAR.getValue()));

        setBasicHits(12);
        setCharacter(playerCharacter);
        setPrimaryAttributes(primAttributes);
        setResId(Classes.CENTAUR.getResId());
        setRequiredGender(Gender.MALE);
        setStartMoney(rolledGold);
        setStartGear(startGear);
    }

    /**
     * The addition made to the EDC of the character, unless surprised or wearing a
     * breastplate. Applies to melee attacks; only applies to missile attacks if
     * galloping.
     * <i>As of 12/7/17, this is not used in the code.</i>
     *
     * @return Character's modifier of their Skill score.
     */
    public int getExtraoridnaryAgilityBonus(){
        return getCharacter().getScore(Score.SKILL).getModifier();
    }
}
