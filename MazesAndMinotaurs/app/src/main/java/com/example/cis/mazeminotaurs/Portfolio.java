package com.example.cis.mazeminotaurs;

import com.example.cis.mazeminotaurs.character.PlayerCharacter;

import java.util.ArrayList;

/**
 * Created by Thorin Schmidt on 4/6/2017.
 */

public class Portfolio {
    private static final String TAG = Portfolio.class.getName();
    private ArrayList<PlayerCharacter> portfolio;
    private static Portfolio sPortfolio;

    private Portfolio(){
        portfolio = new ArrayList<>();
        //For Testing Only
        portfolio.add(new PlayerCharacter());
    }

    public static Portfolio get(){
        if (sPortfolio == null){
            sPortfolio = new Portfolio();
        }
        return sPortfolio;
    }

    public void resetPortfolio(){
        portfolio = new ArrayList<>();
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

}
