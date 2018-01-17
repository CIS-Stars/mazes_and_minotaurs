package com.example.cis.mazeminotaurs.character.classes;

import com.example.cis.mazeminotaurs.AttributeScore;
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
 * This class represents the Noble that is in the game.
 *
 * @author jusmith on 5/15/17.
 */

public class Noble extends Warrior {
    /**
     * A score that is given a bonus value when doHeritage is called.
     */
    private Score mPhysicalHeritage = Score.MIGHT;

    /**
     * A score that is given a bonus value when doHeritage is called.
     */
    private Score mOtherHeritage = Score.WITS;

    /**
     * One-way flag that checks if the class has called doHeritage.
     */
    private boolean mHasHeritage = false;

    /**
     * Blank constructor. Used primarily for reflection purposes.
     * <b>DO NOT USE THIS FOR UI DISPLAYS.</b>
     */
    public Noble() {
        this(null,null,null,null);
    }

    /**
     * Constructor that requires a {@link PlayerCharacter} instance, a weapon, and
     * 2 scores.
     * <p>If the selected weapon of choice is invalid, it will automatically assign
     * it an bow.</p>
     * <p>If the selected score for martial heritage is invalid, it will automatically
     * assign it Might.</p>
     * <p>If the selected score for mental heritage, it will automatically
     * assign it Wits.</p>
     *
     * @param playerCharacter the character using this class
     * @param weaponOfChoice  the user desired weapon of choice
     * @param martialHeritage a score selected to be enhanced
     * @param mentalHeritage  a score selected to be enhanced
     */
    public Noble(PlayerCharacter playerCharacter, Weapon weaponOfChoice, Score martialHeritage, Score mentalHeritage) {
        setPossibleStartWeapons(new Weapon[]{});
        setPossibleWeaponsOfChoice(new Weapon[]{
                EquipmentDB.getInstance().getWeapon(CommonStrings.BOW.getValue()),
                EquipmentDB.getInstance().getWeapon(CommonStrings.JAVELIN.getValue()),
                EquipmentDB.getInstance().getWeapon(CommonStrings.SPEAR.getValue()),
                EquipmentDB.getInstance().getWeapon(CommonStrings.SWORD.getValue()),
        });

        // The scores that can be used for level ups.
        setPossibleLevelScores(new Score[]{Score.MIGHT, Score.GRACE,
                Score.WILL, Score.WITS, Score.SKILL});

        // Noble-Specific things
        // TODO Verify the supplied heritage scores.
        mPhysicalHeritage = martialHeritage;
        mOtherHeritage = mentalHeritage;

        Score[] primAttrs = {mPhysicalHeritage, Score.LUCK};
        ArrayList<Score> primAttributes = new ArrayList<>();
        Collections.addAll(primAttributes, primAttrs);

        EquipmentDB equipmentDB = EquipmentDB.getInstance();
        ArrayList<Equipment> startGear = new ArrayList<>();

        // Check if the weapon of choice is valid
        setWeaponOfChoice(weaponOfChoice);

        int rolledGold = rollDice.roll(6, 3) * 100;

        startGear.add(equipmentDB.getWeapon(CommonStrings.SWORD.getValue()));
        startGear.add(equipmentDB.getWeapon(CommonStrings.DAGGER.getValue()));
        startGear.add(equipmentDB.getArmor(CommonStrings.SHIELD.getValue()));
        startGear.add(equipmentDB.getArmor(CommonStrings.HELMET.getValue()));
        startGear.add(equipmentDB.getArmor(CommonStrings.BREASTPLATE.getValue()));

        setBasicHits(12);
        setCharacter(playerCharacter);
        setPrimaryAttributes(primAttributes);
        setRequiredGender(Gender.EITHER);
        setResId(Classes.NOBLE.getResId());
        setStartMoney(rolledGold);
        setStartGear(startGear);

        if (getCharacter() != null) {
            doHeritage();
        }
    }

    /**
     * A helper shorthand of {@code doHeritage}.
     */
    public void doHeritage() {
        doHeritage(mPhysicalHeritage, mOtherHeritage);
    }

    /**
     * Adds two points to the provided scores. The supplied scores are assigned to
     * mPhysicalHeritage and mOtherHeritage, respectively. If either physical or other
     * are invalid, they will be corrected to Might and Wits respectively.
     *
     * @param physical a score to be enhanced
     * @param other    a score to be enhanced
     */
    public void doHeritage(Score physical, Score other) {
        if (!hasHeritage()) {
            Score realPhysical;
            if (physical != null &&
                    (physical.equals(Score.MIGHT) || physical.equals(Score.SKILL))) {
                realPhysical = physical;
            } else {
                realPhysical = Score.MIGHT;
            }
            AttributeScore pScore = getCharacter().getScore(realPhysical);
            pScore.setScore(pScore.getScore() + 2);
            setPhysicalHeritage(physical);

            Score realOther;
            if (other != null &&
                    (other.equals(Score.WITS) || other.equals(Score.WILL) || other.equals(Score.GRACE))) {
                realOther = other;
            } else {
                realOther = Score.WITS;
            }
            AttributeScore oScore = getCharacter().getScore(realOther);
            oScore.setScore(oScore.getScore() + 2);
            setOtherHeritage(other);
        }
    }

    /**
     * The addition made to the Initiative of the character. Always applies.
     * <i>As of 12/7/17, this is not used in the code.</i>
     *
     * @return Character's modifier of their Luck score.
     */
    public int getBattleFortuneBonus() {
        return getCharacter().getScore(Score.LUCK).getModifier();
    }

    /**
     * Getter for mPhysicalHeritage
     * @return the value of mPhysicalHeritage
     */
    public Score getPhysicalHeritage() {
        return mPhysicalHeritage;
    }

    /**
     * Setter for mPhysicalHeritage
     * @param physicalHeritage the new value of mPhysicalHeritage
     */
    public void setPhysicalHeritage(Score physicalHeritage) {
        mPhysicalHeritage = physicalHeritage;
    }

    /**
     * Getter for mOtherHeritage
     * @return the value of mOtherHeritage
     */
    public Score getOtherHeritage() {
        return mOtherHeritage;
    }

    /**
     * Setter for mOtherHeritage
     * @param otherHeritage the new value of the otherHeritage
     */
    public void setOtherHeritage(Score otherHeritage) {
        mOtherHeritage = otherHeritage;
    }

    /**
     * Getter for mHasHeritage
     * @return the value of mHasHeritage
     */
    public boolean hasHeritage() {
        return mHasHeritage;
    }

    /**
     * Setter for mHasHeritage
     * @param hasHeritage the new value of mHasHeritage.
     */
    public void setHasHeritage(boolean hasHeritage) {
        mHasHeritage = hasHeritage;
    }
}
