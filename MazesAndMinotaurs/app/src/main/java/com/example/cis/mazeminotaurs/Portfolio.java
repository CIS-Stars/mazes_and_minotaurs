package com.example.cis.mazeminotaurs;

import com.example.cis.mazeminotaurs.character.PlayerCharacter;
import com.example.cis.mazeminotaurs.character.classes.Barbarian;
import com.example.cis.mazeminotaurs.util.CommonStrings;
import com.example.cis.mazeminotaurs.util.Util;

import java.util.ArrayList;

/**
 * Created by Thorin Schmidt on 4/6/2017.
 */

public class Portfolio {
    public static final String FILENAME = "SavedPortfolio";

    private static final String TAG = Portfolio.class.getName();
    private ArrayList<PlayerCharacter> portfolio;
    private static Portfolio sPortfolio;

    private int activeCharacterIndex;

    private Portfolio(){
        portfolio = new ArrayList<>();
        activeCharacterIndex = 0;
        resetPortfolio();
    }

    public static Portfolio get(){
        if (sPortfolio == null){
            sPortfolio = new Portfolio();
        }
        return sPortfolio;
    }

    public void resetPortfolio(){
        portfolio.clear();
        // Not having a single character in the portfolio causes errors. So we need a 'dummy' character.
        portfolio.add(Util.createDummyCharacter());
    }

    public ArrayList<PlayerCharacter> getPortfolio() {
        return portfolio;
    }

    public void setPortfolio(ArrayList<PlayerCharacter> portfolio) {
        this.portfolio = portfolio;
    }

    public void addPlayerCharacter(PlayerCharacter pc){
        portfolio.add(pc);
    }

    public void deletePlayerCharacter(PlayerCharacter pc){
        portfolio.remove(pc);
    }

    public PlayerCharacter getPlayerCharacter(int i){
        int index;
        if(i >= 0 && i < portfolio.size()) {
            index = i;
        }
        else {
            index = 0;
        }
        return portfolio.get(index);
    }

    public int getActiveCharacterIndex() {
        return activeCharacterIndex;
    }

    public void setActiveCharacterIndex(int activeCharacterIndex) {
        this.activeCharacterIndex = activeCharacterIndex;
    }
}
