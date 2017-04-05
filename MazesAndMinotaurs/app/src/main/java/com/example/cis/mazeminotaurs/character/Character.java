package com.example.cis.mazeminotaurs.character;

import android.util.Log;

import com.example.cis.mazeminotaurs.Armor;
import com.example.cis.mazeminotaurs.AttributeScore;
import com.example.cis.mazeminotaurs.AttributeScoreGenerator;
import com.example.cis.mazeminotaurs.R;
import com.example.cis.mazeminotaurs.character.classes.Barbarian;
import com.example.cis.mazeminotaurs.character.classes.BaseClass;
import com.example.cis.mazeminotaurs.character.stats.Score;

import java.util.HashMap;

/**
 * Created by jusmith on 3/30/17.
 */

<<<<<<< HEAD:MazesAndMinotaurs/app/src/main/java/com/example/cis/mazeminotaurs/character/Character.java
public class Character {
=======
public class PlayerCharacter {
    private final String TAG = "PlayerCharacter Class";

>>>>>>> 2f6980a2a86150f8f810ee0775cc1434a98701a2:MazesAndMinotaurs/app/src/main/java/com/example/cis/mazeminotaurs/character/PlayerCharacter.java
    private HashMap<Score, AttributeScore> mCoreStats = new HashMap<>();
    private BaseClass mCharClass;
    private Gender mGender;
    private HashMap<Money, Integer> mMoney = new HashMap<>();
    private int mAge;
    private String mName;
    private Armor mHelmet;
    private Armor mBreastplate;
    private Armor mShield;

<<<<<<< HEAD:MazesAndMinotaurs/app/src/main/java/com/example/cis/mazeminotaurs/character/Character.java
    public Character() {
        initalizeMoneyMap();

        setAge(0);
        setCharClass(new Barbarian(this, R.string.barb_axe, R.string.bow));
        setName("Thorin");

        getMoney().put(Money.SILVER, getCharClass().getStartGold());
=======
    public PlayerCharacter() {
        Log.i(TAG, "Creating PlayerCharacter");
        setAge(0);
        setCharClass(new Barbarian(this, R.string.barb_axe, R.string.bow));
        setName("Thorin");
>>>>>>> 2f6980a2a86150f8f810ee0775cc1434a98701a2:MazesAndMinotaurs/app/src/main/java/com/example/cis/mazeminotaurs/character/PlayerCharacter.java
        
        AttributeScore[] scores = new AttributeScoreGenerator().nextValidSet();
        for (int i = 0; i < scores.length; i++) {
            mCoreStats.put(Score.values()[i], scores[i]);
        }
    }

    public int getMeleeMod() {
        return getCoreStatScore(Score.MIGHT).getModifier() +
                getCoreStatScore(Score.GRACE).getModifier() +
                getCoreStatScore(Score.LUCK).getModifier();
    }

    public int getMissleMod() {
        return getCoreStatScore(Score.SKILL).getModifier() +
                getCoreStatScore(Score.WITS).getModifier() +
                getCoreStatScore(Score.LUCK).getModifier();
    }

    public int getInititive() {
        return 10 + getCoreStatScore(Score.SKILL).getModifier() +
                getCoreStatScore(Score.WITS).getModifier();
    }

    public int getDC() {
        return 12 + getCoreStatScore(Score.LUCK).getModifier();
    }

    public int getEDC() {
        int armorBonus = 0;
        if (mHelmet != null) {
            armorBonus += 2;
        }
        if (mBreastplate != null) {
            armorBonus += 2;
        }
        if (mShield != null) {
            armorBonus += 2;
        }

        return getDC() + armorBonus;
    }

    public int getHitTotal(){
        //return mCharClass.getHits() + getMod(Score.MIGHT);
        return 0;
    }

    public int getCharisma(){
        return getCoreStatScore(Score.WILL).getModifier() +
                getCoreStatScore(Score.GRACE).getModifier() +
                getCoreStatScore(Score.LUCK).getModifier();
<<<<<<< HEAD:MazesAndMinotaurs/app/src/main/java/com/example/cis/mazeminotaurs/character/Character.java
    }

    public Gender getGender() {
        return mGender;
    }

    public void setGender(Gender gender) {
        mGender = gender;
    }

    public void addMoney(Money money, int amount) {
        getMoney().put(money, getMoney().get(money) + amount);
        validateMoney();
    }

    public HashMap<Money, Integer> getMoney() {
        return mMoney;
    }

    private void initalizeMoneyMap() {
        mMoney.put(Money.COPPER, 0);
        mMoney.put(Money.SILVER, 0);
        mMoney.put(Money.GOLD, 0);
    }

    public void setMoney(HashMap<Money, Integer> money) {
        mMoney = money;
    }

    public void validateMoney() {
        HashMap<Money, Integer> cash = getMoney();

        int tradedUpSilver = cash.get(Money.COPPER) / 100;
        int tradedUpGold = cash.get(Money.SILVER) / 100;

        cash.put(Money.COPPER, cash.get(Money.COPPER) % 100);
        cash.put(Money.SILVER, (cash.get(Money.SILVER) % 100) + tradedUpSilver);
        cash.put(Money.GOLD, cash.get(Money.GOLD) + tradedUpGold);
    }

    public Armor getHelmet() {
        return mHelmet;
    }

    public void setHelmet(Armor helmet) {
        mHelmet = helmet;
    }

    public Armor getBreastplate() {
        return mBreastplate;
    }

    public void setBreastplate(Armor breastplate) {
        mBreastplate = breastplate;
    }

    public Armor getShield() {
        return mShield;
    }

    public void setShield(Armor shield) {
        mShield = shield;
=======
>>>>>>> 2f6980a2a86150f8f810ee0775cc1434a98701a2:MazesAndMinotaurs/app/src/main/java/com/example/cis/mazeminotaurs/character/PlayerCharacter.java
    }

    public AttributeScore getCoreStatScore(Score scoreStat) {
        return mCoreStats.get(scoreStat);
    }

    public HashMap<Score, AttributeScore> getCoreStats() {
        return mCoreStats;
    }

    public void setCoreStats(HashMap<Score, AttributeScore> coreStats) {
        mCoreStats = coreStats;
    }

    public BaseClass getCharClass() {
        return mCharClass;
    }

    public void setCharClass(BaseClass aClass) {
        mCharClass = aClass;
    }

    public int getAge() {
        return mAge;
    }

    public void setAge(int age) {
        mAge = age;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }
}
