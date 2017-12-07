package com.example.cis.mazeminotaurs.character.classes;

import com.example.cis.mazeminotaurs.Equipment;
import com.example.cis.mazeminotaurs.R;
import com.example.cis.mazeminotaurs.Weapon;
import com.example.cis.mazeminotaurs.character.Gender;
import com.example.cis.mazeminotaurs.character.PlayerCharacter;
import com.example.cis.mazeminotaurs.character.stats.Score;
import com.example.cis.mazeminotaurs.rollDice.rollDice;

import java.util.ArrayList;
import java.util.Collections;

/**
 * This class represents the Nymph that is in the game.
 *
 * @author zsteck on 9/12/17.
 */

public class Nymph extends Magician {

    /**
     * Blank constructor. Used primarily for reflection purposes.
     * <b>DO NOT USE THIS FOR UI DISPLAYS.</b>
     */
    public Nymph() {
        this(null);
    }

    /**
     * Constructor that requires a {@link PlayerCharacter} instance.
     *
     * @param playerCharacter the character using this class
     */
    public Nymph(PlayerCharacter playerCharacter) {
        setPossibleStartWeapons(new Weapon[]{});
        setPossibleLevelScores(new Score[]{Score.GRACE, Score.WITS, Score.WILL});

        Score[] primAttrs = {Score.GRACE, Score.LUCK};
        ArrayList<Score> primAttributes = new ArrayList<>();
        Collections.addAll(primAttributes, primAttrs);

        ArrayList<Equipment> startGear = new ArrayList<>();

        int rolledGold = rollDice.roll(6, 3) * 0;

        setBasicHits(8);
        setCharacter(playerCharacter);
        setPrimaryAttributes(primAttributes);
        setRequiredGender(Gender.FEMALE);
        setResId(Classes.NYMPH.getResId());
        setSpecialTalentResId(R.string.nymph_talent);
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
