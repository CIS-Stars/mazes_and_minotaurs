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
import java.util.Collections;

/**
 * This class represents the Elementalist that is in the game.
 *
 * @author zsteck on 9/11/17.
 */

public class Elementalist extends Magician {

    /**
     * Blank constructor. Used primarily for reflection purposes.
     * <b>DO NOT USE THIS FOR UI DISPLAYS.</b>
     */
    public Elementalist() {
        this(null);
    }

    /**
     * Constructor that requires a {@link PlayerCharacter} instance.
     *
     * @param playerCharacter the character using this class
     */
    public Elementalist(PlayerCharacter playerCharacter) {
        setPossibleStartWeapons(new Weapon[]{});

        // The scores that can be used for level ups.
        setPossibleLevelScores(new Score[]{Score.WILL, Score.WITS});

        Score[] primAttrs = {Score.WITS, Score.WILL};
        ArrayList<Score> primAttributes = new ArrayList<>();
        Collections.addAll(primAttributes, primAttrs);

        EquipmentDB equipmentDB = EquipmentDB.getInstance();
        ArrayList<Equipment> startGear = new ArrayList<>();

        int rolledGold = rollDice.roll(6, 3) * 5;

        startGear.add(equipmentDB.getWeapon(CommonStrings.STAFF.getValue()));
        startGear.add(equipmentDB.getWeapon(CommonStrings.DAGGER.getValue()));

        setBasicHits(8);
        setCharacter(playerCharacter);
        setPrimaryAttributes(primAttributes);
        setRequiredGender(Gender.EITHER);
        setResId(Classes.ELEMENTALIST.getResId());
        setSpecialTalentResId(R.string.elementalist_talent);
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
        return (4 * getLevel()) + getCharacter().getScore(Score.WILL).getModifier();
    }
}
