package com.example.cis.mazeminotaurs;

import com.example.cis.mazeminotaurs.character.PlayerCharacter;
import com.example.cis.mazeminotaurs.util.Util;

import java.util.ArrayList;

/**
 * @author Thorin Schmidt on 4/6/2017.
 */

public class Portfolio {
    /**
     * The file name that this class is saved under.
     */
    public static final String FILENAME = "SavedPortfolio";

    /**
     * Serves as the TAG in certain functions. e.g. dialog.show() and logging.
     */
    private static final String TAG = Portfolio.class.getName();

    /**
     * A list of characters. Supposed to be limited to a max of six.
     */
    private ArrayList<PlayerCharacter> portfolio;

    /**
     * The single portfolio instance.
     */
    private static Portfolio sPortfolio;

    /**
     * The character that is currently being played.
     */
    private int activeCharacterIndex;

    /**
     * A one-way flag that says if data has already been loaded in.
     */
    private transient boolean mIsLoaded;

    /**
     * Default constructor.
     */
    private Portfolio(){
        portfolio = new ArrayList<>();
        mIsLoaded = false;
        activeCharacterIndex = 0;
        resetPortfolio();
    }

    /**
     * Retrieves the singleton instance. If the instance is null, creates one and
     * returns that one.
     *
     * @return an instance of Portfolio
     */
    public static Portfolio get(){
        if (sPortfolio == null){
            sPortfolio = new Portfolio();
        }
        return sPortfolio;
    }

    /**
     * Empties the list of characters. Inserts a dummy one to prevent errors.
     */
    public void resetPortfolio(){
        portfolio.clear();
        // Not having a single character in the portfolio causes errors. So we need a 'dummy' character.
        portfolio.add(Util.createDummyCharacter());
    }

    /**
     * Getter for the portfolio property.
     * @return the value of portfolio.
     */
    public ArrayList<PlayerCharacter> getPortfolio() {
        return portfolio;
    }

    /**
     * Setter for the portfolio property.
     * @param portfolio the new value of portfolio.
     */
    public void setPortfolio(ArrayList<PlayerCharacter> portfolio) {
        this.portfolio = portfolio;
    }

    /**
     * Adds a player character to portfolio.
     * @param pc a character to add.
     */
    public void addPlayerCharacter(PlayerCharacter pc){
        portfolio.add(pc);
    }

    /**
     * Removes a player character from the portfolio.
     * @param pc the character to remove.
     */
    public void deletePlayerCharacter(PlayerCharacter pc){
        portfolio.remove(pc);
    }

    /**
     * Retrieves a character from the portfolio at specifed index. If it
     * doesn't exist, retrieves first character.
     * @param i the index of the character to retrieve
     * @return a player character instance.
     */
    public PlayerCharacter getPlayerCharacter(int i){
        int index;
        if(i >= 0 && i < portfolio.size()) {
            index = i;
        } else {
            index = 0;
        }
        return portfolio.get(index);
    }

    /**
     * Getter for the activeCharacterIndex property.
     * @return the value of activeCharacterIndex.
     */
    public int getActiveCharacterIndex() {
        return activeCharacterIndex;
    }

    /**
     * Setter for the activeCharacterIndex property.
     * @param activeCharacterIndex the new value of activeCharacterIndex.
     */
    public void setActiveCharacterIndex(int activeCharacterIndex) {
        this.activeCharacterIndex = activeCharacterIndex;
    }

    /**
     * Getter for the mIsLoaded property.
     * @return the value of mIsLoaded.
     */
    public boolean isLoaded() {
        return mIsLoaded;
    }

    /**
     * Setter for the mIsLoaded property.
     * @param isLoaded the new value of mIsLoaded.
     */
    public void setLoaded(boolean isLoaded) {
        mIsLoaded = isLoaded;
    }
}
