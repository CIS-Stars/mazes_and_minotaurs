package com.example.cis.mazeminotaurs.character.classes;

import com.example.cis.mazeminotaurs.Equipment;
import com.example.cis.mazeminotaurs.EquipmentDB;
import com.example.cis.mazeminotaurs.R;
import com.example.cis.mazeminotaurs.Weapon;
import com.example.cis.mazeminotaurs.character.Gender;
import com.example.cis.mazeminotaurs.character.PlayerCharacter;
import com.example.cis.mazeminotaurs.character.stats.Score;
import com.example.cis.mazeminotaurs.rollDice.rollDice;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by zsteck on 9/12/17.
 */

public class Nymph extends Magician {

    public Nymph() {
        this(null);
    }

    public Nymph(PlayerCharacter playerCharacter) {
        setPossibleStartWeapons(new Weapon[]{});
        setPossibleLevelScores(new Score[]{Score.GRACE, Score.WITS, Score.WILL});

        Score[] primAttrs = {Score.GRACE, Score.LUCK};
        ArrayList<Score> primAttributes = new ArrayList<>();
        Collections.addAll(primAttributes, primAttrs);

        EquipmentDB equipmentDB = EquipmentDB.getInstance();
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

    public int getMysticalStrength(){
        return 12 + getSpecialTalent();
    }

    public int getPowerPoints(){
        return (4 * getLevel()) + getCharacter().getScore(Score.GRACE).getModifier();
    }
}
