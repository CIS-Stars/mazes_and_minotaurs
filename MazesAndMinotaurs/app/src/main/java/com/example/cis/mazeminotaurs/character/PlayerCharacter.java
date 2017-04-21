package com.example.cis.mazeminotaurs.character;

import com.example.cis.mazeminotaurs.Armor;
import com.example.cis.mazeminotaurs.AttributeScore;
import com.example.cis.mazeminotaurs.AttributeScoreGenerator;
import com.example.cis.mazeminotaurs.Equipment;
import com.example.cis.mazeminotaurs.EquipmentDB;
import com.example.cis.mazeminotaurs.R;
import com.example.cis.mazeminotaurs.Weapon;
import com.example.cis.mazeminotaurs.character.classes.Barbarian;
import com.example.cis.mazeminotaurs.character.classes.BaseClass;
import com.example.cis.mazeminotaurs.character.classes.Magician;
import com.example.cis.mazeminotaurs.character.classes.Specialist;
import com.example.cis.mazeminotaurs.character.classes.Warrior;
import com.example.cis.mazeminotaurs.character.stats.Score;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Used to hold the data of a player's character.
 *
 * <h1>Steps to use a player character</h1>
 * <ul>
 *  <li>1: Create a new PlayerCharacter instance</li>
 *  <li>2: Create a new subclass of BaseClass instance using the PlayerCharacter instance i.e. Barbarian</li>
 *  <li>3: Assign the class to the PlayerCharacter using setCharClass</li>
 *  <li>4: Call the PlayerCharacter's initializeClass method</li>
 *  <li>5: Your PlayerCharacter is now working!</li>
 * </ul>
 *
 * <h1>Example:</h1>
 * <ul>
 *     <li>PlayerCharacter character = new PlayerCharacter();</li>
 *     <li>Barbarian barb = new Barbarian(character, [weapon of choice], [ranged weapon]);</li>
 *     <li>character.setCharClass(barb);</li>
 *     <li>character.initializeClass();</li>
 * </ul>
 *
 * @author Justin Smith
 */

public class PlayerCharacter {
    /**
     * The character's attribute scores.
     */
    private HashMap<Score, AttributeScore> mScores = new HashMap<>();

    /**
     * The character class's i.e. Barbarian
     */
    private BaseClass mCharClass;

    /**
     * The character's gender(MALE or FEMALE)
     */
    private Gender mGender;

    /**
     * The character's money.
     */
    private HashMap<Money, Integer> mMoney = new HashMap<>();

    /**
     * The character's age.
     */
    private int mAge;

    /**
     * The character's name.
     */
    private String mName;

    /**
     * All of the items that the character is holding.
     */
    private ArrayList<Equipment> mInventory = new ArrayList<>();

    /**
     * The character's equipped weapon.
     */
    private Weapon mWeapon;

    /**
     * The helmet that the character is currently wearing.
     */
    private Armor mHelmet;

    /**
     * The breastplate that the character is currently wearing.
     */
    private Armor mBreastplate;

    /**
     * The shield that the character is currently wearing.
     */
    private Armor mShield;

    /**
     * Blank character constructor.
     */
    public PlayerCharacter() {
        initializeMoneyMap();

        EquipmentDB equipmentDB = EquipmentDB.getInstance();

        setAge(0);
        // Purposely inputting improper argument

        setName("Thorin");

        setCharClass(new Barbarian(this, equipmentDB.getWeapon(R.string.barb_axe),
                                         equipmentDB.getWeapon(R.string.barb_axe)));
        initializeClass();

        AttributeScore[] scores = new AttributeScoreGenerator().nextValidSet();
        for (int i = 0; i < scores.length; i++) {
            mScores.put(Score.values()[i], scores[i]);
        }
    }

    /**
     * Gets the modifier for melee attacks.
     * @return      the melee modifier
     */
    public int getMeleeMod() {
        return getScore(Score.MIGHT).getModifier() +
                getScore(Score.GRACE).getModifier() +
                getScore(Score.LUCK).getModifier();
    }

    /**
     * Gets the modifier for missile attacks.
     * @return      the missile modifier
     */
    public int getMissileMod() {
        return getScore(Score.SKILL).getModifier() +
                getScore(Score.WITS).getModifier() +
                getScore(Score.LUCK).getModifier();
    }

    /**
     * Gets initiative of the character.
     * @return      initiative.
     */
    public int getInitiative() {
        return 10 + getScore(Score.SKILL).getModifier() +
                getScore(Score.WITS).getModifier();
    }

    /**
     * Gets the defense class of the character.
     * @return      defense class
     */
    public int getDC() {
        return 12 + getScore(Score.LUCK).getModifier();
    }

    /**
     * Gets the effective defense class of the character.
     * @return      effective defense class
     */
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

    /**
     * Gets the total hits of the character
     * @return      total hits.
     */
    public int getHitTotal() {
        //return mCharClass.getHits() + getMod(Score.MIGHT);
        return 0;
    }

    /**
     * Gets the athletic prowess save score of the character.
     * @return      athletic prowess score
     */
    public int getAthleticProwess(){
        return getScore(Score.MIGHT).getModifier() +
                getScore(Score.SKILL).getModifier() +
                getScore(Score.LUCK).getModifier();
    }

    /**
     * Gets the danger evasion save score of the character.
     * @return      danger evasion score
     */
    public int getDangerEvasion(){
        return getScore(Score.WITS).getModifier() +
                getScore(Score.SKILL).getModifier() +
                getScore(Score.LUCK).getModifier();
    }

    /**
     * Gets the mystic fortitude save score of the character.
     * @return      mystic fortitude score
     */
    public int getMysticFortitude(){
        return getScore(Score.WITS).getModifier() +
                getScore(Score.WILL).getModifier() +
                getScore(Score.LUCK).getModifier();
    }

    /**
     * Gets the physical vigor save score of the character.
     * @return      physical vigor score
     */
    public int getPhysicalVigor(){
        return getScore(Score.MIGHT).getModifier() +
                getScore(Score.WILL).getModifier() +
                getScore(Score.LUCK).getModifier();
    }

    /**
     * Gets the charisma score of the character.
     * @return      charisma score
     */
    public int getCharisma() {
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

    /**
     * Helper method to add money easier to the character and automatically validate.
     * @param money The key of the money map to add to.
     * @param amount The amount of money to add the value.
     */
    public void addMoney(Money money, int amount) {
        getMoney().put(money, getMoney().get(money) + amount);
        validateMoney();
    }

    public HashMap<Money, Integer> getMoney() {
        return mMoney;
    }

    public void setMoney(HashMap<Money, Integer> money) {
        mMoney = money;
    }
    /**
     * Places the correct keys and values inside of the character's mMoney attribute.
     * Will erase any values found in the keys in the map.
     */
    private void initializeMoneyMap() {
        mMoney.put(Money.COPPER, 0);
        mMoney.put(Money.SILVER, 0);
        mMoney.put(Money.GOLD, 0);
    }
    /**
     * Automatically converts the cash that a player character is holding
     */
    public void validateMoney() {
        HashMap<Money, Integer> cash = getMoney();

        int tradedUpSilver = cash.get(Money.COPPER) / 100;
        cash.put(Money.COPPER, cash.get(Money.COPPER) % 100);
        cash.put(Money.SILVER, (cash.get(Money.SILVER) % 100) + tradedUpSilver);

        int tradedUpGold = cash.get(Money.SILVER) / 100;
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

    public Weapon getWeapon() {
        return mWeapon;
    }

    public void setWeapon(Weapon weapon) {
        mWeapon = weapon;
    }

    /**
     * Checks if the current weapon in mWeapon is a weapon of choice.
     * @return      true if the weapon is the same as the CharClass's weapon of choice else false
     */
    public boolean isWeaponOfChoiceEquipped(){
        if (!(getCharClass() instanceof Magician)) {
            if (getCharClass() instanceof Warrior) {
                Warrior warrior = (Warrior) getCharClass();
                return warrior.getWeaponOfChoice().getResId() == getWeapon().getResId();
            } else {
                Specialist specialist = (Specialist) getCharClass();
                return specialist.getWeaponOfChoice().getResId() == getWeapon().getResId();
            }
        }
        return false;
    }

    /**
     * Checks if a score is maxed out
     * @param   score The score to check
     * @return  true if score is not maxed out else false
     */
    public boolean canAddToScore(Score score) {
        boolean isPrimary = getCharClass().getPrimaryAttributes().contains(score);
        int scoreValue = getScore(score).getScore();

        if (isPrimary) {
            return scoreValue < 21;
        } else {
            return scoreValue < 20;
        }
    }

    /**
     * Checks if any score contained in the mScores attribute is over the max value.
     */
    public void validateScores() {
        for (Score score: Score.values()) {
            boolean isPrimary = getCharClass().getPrimaryAttributes().contains(score);
            int scoreValue = getScore(score).getScore();

            if (isPrimary && scoreValue > 21) {
                getScore(score).setScore(21);
            } else if (!isPrimary && scoreValue > 20) {
                getScore(score).setScore(20);
            }
        }
    }

    /**
     * Prints the value of every score contained in the mScores attribute
     */
    protected void debugPrintScores() {
        for (Score score : getScores().keySet()) {
            System.out.println(score.toString() + ":" + String.valueOf(getScore(score).getScore()));
        }
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

    /**
     * A helper method to get the starting gear and starting money of a class added to the character
     */
    public void initializeClass(){
        getMoney().put(Money.SILVER, getCharClass().getStartMoney());
        getInventory().addAll(getCharClass().getStartGear());
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

    public ArrayList<Equipment> getInventory() {
        return mInventory;
    }

    public void setInventory(ArrayList<Equipment> inventory) {
        mInventory = inventory;
    }
}
