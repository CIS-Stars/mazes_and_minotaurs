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
 * Created by jhiggs on 9/11/17.
 */

public class Priest extends Magician {

    public Priest() {
        this(null);
    }

    public Priest(PlayerCharacter playerCharacter) {
        setPossibleStartWeapons(new Weapon[]{});
        setPossibleLevelScores(new Score[]{Score.SKILL, Score.WILL, Score.MIGHT, Score.WITS});

        Score[] primAttrs = {Score.LUCK, Score.WILL};
        ArrayList<Score> primAttributes = new ArrayList<>();
        Collections.addAll(primAttributes, primAttrs);

        EquipmentDB equipmentDB = EquipmentDB.getInstance();
        ArrayList<Equipment> startGear = new ArrayList<>();

        int rolledGold = rollDice.roll(6, 3) * 10;

        startGear.add(equipmentDB.getWeapon(CommonStrings.STAFF.getValue()));
        startGear.add(equipmentDB.getWeapon(CommonStrings.DAGGER.getValue()));
        //TODO add ceremonial robes

        setBasicHits(8);
        setCharacter(playerCharacter);
        setPrimaryAttributes(primAttributes);
        setRequiredGender(Gender.EITHER);
        setResId(Classes.PRIEST.getResId());
        setSpecialTalentResId(R.string.priest_talent);
        setStartMoney(rolledGold);
        setStartGear(startGear);

    }

    public int getMysticalStrength(){
        return 12 + getSpecialTalent();
    }

    public int getPowerPoints(){
        return (4 * getLevel()) + getCharacter().getScore(Score.WILL).getModifier();
    }
}

