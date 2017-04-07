package com.example.cis.mazeminotaurs.character;

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

public class PlayerCharacter {

    private HashMap<Score, AttributeScore> mScores = new HashMap<>();
    private BaseClass mCharClass;
    private Gender mGender;
    private HashMap<Money, Integer> mMoney = new HashMap<>();
    private int mAge;
    private String mName;
    private Armor mHelmet;
    private Armor mBreastplate;
    private Armor mShield;

    public PlayerCharacter() {
        initalizeMoneyMap();

        setAge(0);
        setCharClass(new Barbarian(this, R.string.barb_axe, R.string.bow));
        setName("Thorin");

        getMoney().put(Money.SILVER, getCharClass().getStartGold());
        
        AttributeScore[] scores = new AttributeScoreGenerator().nextValidSet();
        for (int i = 0; i < scores.length; i++) {
            mScores.put(Score.values()[i], scores[i]);
        }
    }

    public int getMeleeMod() {
        return getScore(Score.MIGHT).getModifier() +
                getScore(Score.GRACE).getModifier() +
                getScore(Score.LUCK).getModifier();
    }

    public int getMissleMod() {
        return getScore(Score.SKILL).getModifier() +
                getScore(Score.WITS).getModifier() +
                getScore(Score.LUCK).getModifier();
    }

    public int getInititive() {
        return 10 + getScore(Score.SKILL).getModifier() +
                getScore(Score.WITS).getModifier();
    }

    public int getDC() {
        return 12 + getScore(Score.LUCK).getModifier();
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
        return getScore(Score.WILL).getModifier() +
                getScore(Score.GRACE).getModifier() +
                getScore(Score.LUCK).getModifier();
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
    }

    public AttributeScore getScore(Score scoreStat) {
        return mScores.get(scoreStat);
    }

    public HashMap<Score, AttributeScore> getScores() {
        return mScores;
    }

    public void setScores(HashMap<Score, AttributeScore> scores) {
        mScores = scores;
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

    protected void printScores() {
        for (Score score: getScores().keySet()) {
            System.out.println(score.toString() + ":" + String.valueOf(getScore(score).getScore()));
        }
    }
}
