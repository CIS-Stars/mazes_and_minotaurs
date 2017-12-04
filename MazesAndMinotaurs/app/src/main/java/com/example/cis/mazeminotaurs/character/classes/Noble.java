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
 * Created by jusmith on 5/15/17.
 */

public class Noble extends Warrior {
    private Score mPhysicalHeritage = Score.MIGHT;
    private Score mOtherHeritage = Score.WITS;
    private boolean mHasHeritage = false;

    public Noble() {
        this(null,null,null,null);
    }

    public Noble(PlayerCharacter playerCharacter, Weapon weaponOfChoice, Score martialHeritage, Score mentalHeritage) {
        setPossibleStartWeapons(new Weapon[]{});
        setPossibleWeaponsOfChoice(new Weapon[]{
                EquipmentDB.getInstance().getWeapon(CommonStrings.BOW.getValue()),
                EquipmentDB.getInstance().getWeapon(CommonStrings.JAVELIN.getValue()),
                EquipmentDB.getInstance().getWeapon(CommonStrings.SPEAR.getValue()),
                EquipmentDB.getInstance().getWeapon(CommonStrings.SWORD.getValue()),
        });

        // Noble-Specific things
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

    private void doHeritage() {
        doHeritage(mPhysicalHeritage, mOtherHeritage);
    }

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

    public int getBattleFortuneBonus() {
        return getCharacter().getScore(Score.LUCK).getModifier();
    }

    public Score getPhysicalHeritage() {
        return mPhysicalHeritage;
    }

    public void setPhysicalHeritage(Score physicalHeritage) {
        mPhysicalHeritage = physicalHeritage;
    }

    public Score getOtherHeritage() {
        return mOtherHeritage;
    }

    public void setOtherHeritage(Score otherHeritage) {
        mOtherHeritage = otherHeritage;
    }

    public boolean hasHeritage() {
        return mHasHeritage;
    }

    public void setHasHeritage(boolean hasHeritage) {
        mHasHeritage = hasHeritage;
    }
}
