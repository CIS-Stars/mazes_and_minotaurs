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
import java.util.Collections;

/**
 * This class represents the Spearman (Normal) that is in the game.
 *
 * @author jusmith on 5/15/17.
 */

public class Spearman extends Warrior {
    /**
     * Blank constructor. Used primarily for reflection purposes.
     * <b>DO NOT USE THIS FOR UI DISPLAYS.</b>
     */
    public Spearman() {
        this(null);
    }

    /**
     * Constructor that requires a {@link PlayerCharacter} instance.
     *
     * @param playerCharacter the character using this class
     */
    public Spearman(PlayerCharacter playerCharacter) {
        setPossibleStartWeapons(new Weapon[]{});
        setPossibleWeaponsOfChoice(new Weapon[]{EquipmentDB.getInstance().getWeapon(CommonStrings.SPEAR.getValue())});

        // The scores that can be used for level ups.
        setPossibleLevelScores(new Score[]{Score.MIGHT, Score.WITS,
                Score.WILL, Score.SKILL});

        Score[] primAttrs = {Score.SKILL, Score.WILL};
        ArrayList<Score> primAttributes = new ArrayList<>();
        Collections.addAll(primAttributes, primAttrs);

        EquipmentDB equipmentDB = EquipmentDB.getInstance();
        ArrayList<Equipment> startGear = new ArrayList<>();

        int rolledGold = rollDice.roll(6, 3) * 10;

        startGear.add(equipmentDB.getWeapon(CommonStrings.SPEAR.getValue()));
        startGear.add(equipmentDB.getWeapon(CommonStrings.SWORD.getValue()));
        startGear.add(equipmentDB.getWeapon(CommonStrings.DAGGER.getValue()));
        startGear.add(equipmentDB.getArmor(CommonStrings.SHIELD.getValue()));
        startGear.add(equipmentDB.getArmor(CommonStrings.HELMET.getValue()));
        startGear.add(equipmentDB.getArmor(CommonStrings.BREASTPLATE.getValue()));

        setBasicHits(12);
        setCharacter(playerCharacter);
        setPrimaryAttributes(primAttributes);
        setRequiredGender(Gender.MALE);
        setResId(Classes.SPEARMAN.getResId());
        setStartMoney(rolledGold);
        setStartGear(startGear);
        setWeaponOfChoice(equipmentDB.getWeapon(CommonStrings.SPEAR.getValue()));
    }

    /**
     * The addition made to the EDC of the character, unless surprised.
     * Must be fighting with spear and shield; also, does not apply
     * to missile attacks.
     * <i>As of 12/7/17, this is not used in the code.</i>
     *
     * @return Character's modifier of their Skill score.
     */
    public int getDefensiveFightingBonus() {
        return getCharacter().getScore(Score.SKILL).getModifier();
    }

    /**
     * The addition made to the Initiative of the character when fighting
     * with a spear.
     * <i>As of 12/7/17, this is not used in the code.</i>
     *
     * @return Character's modifier of their Will score.
     */
    public int getMartialDisciplineBonus() {
        return getCharacter().getScore(Score.WILL).getModifier();
    }
}
