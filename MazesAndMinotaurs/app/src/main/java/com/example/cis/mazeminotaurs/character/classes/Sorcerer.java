package com.example.cis.mazeminotaurs.character.classes;

import com.example.cis.mazeminotaurs.Equipment;
import com.example.cis.mazeminotaurs.EquipmentDB;
import com.example.cis.mazeminotaurs.R;
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
 * This class represents the Sorcerer class that is in the game.
 *
 * @author zsteck on 9/12/17.
 */

public class Sorcerer extends Magician {

    /**
     * Blank constructor. Used primarily for reflection purposes.
     * <b>DO NOT USE THIS FOR UI DISPLAYS.</b>
     */
    public Sorcerer() {
        this(null,null);
    }

    /**
     * Constructor that requires a {@link PlayerCharacter} instance and a weapon.
     * <p>If the starting weapon is invalid, it will automatically assign it an
     * dagger.</p>
     *
     * @param playerCharacter the character using this class
     * @param startingWeapon  the user desired starting weapon
     */
    public Sorcerer(PlayerCharacter playerCharacter, Weapon startingWeapon) {
        setPossibleStartWeapons(new Weapon[] {
                EquipmentDB.getInstance().getWeapon(CommonStrings.DAGGER.getValue()),
                EquipmentDB.getInstance().getWeapon(CommonStrings.STAFF.getValue()),
        });

        // The scores that can be used for level ups.
        setPossibleLevelScores(new Score[]{Score.WILL, Score.WITS});

        Score[] primAttrs = {Score.WITS, Score.WILL};
        ArrayList<Score> primAttributes = new ArrayList<>();
        Collections.addAll(primAttributes, primAttrs);

        ArrayList<Equipment> startGear = new ArrayList<>();

        int rolledGold = rollDice.roll(6, 3) * 10;

        //TODO add a wand weapon

        if (Arrays.asList(getPossibleStartWeapons()).contains(startingWeapon)) {
            startGear.add(startingWeapon);
        } else {
            startGear.add(getPossibleStartWeapons()[0]);
        }

        setBasicHits(8);
        setCharacter(playerCharacter);
        setPrimaryAttributes(primAttributes);
        setRequiredGender(Gender.EITHER);
        setResId(Classes.SORCEROR.getResId());
        setSpecialTalentResId(R.string.sorcerer_talent);
        setStartMoney(rolledGold);
        setStartGear(startGear);
    }

    /**
     * Calculated value of their mystical strength
     *
     * @return value of mystical strength.
     */
    public int getMysticalStrength(){
        return 12 + getSpecialTalent();
    }

    /**
     * The available power points of the class
     * @return number of power points available to the class
     */
    public int getPowerPoints(){
        return (4 * getLevel()) + getCharacter().getScore(Score.GRACE).getModifier();
    }
}