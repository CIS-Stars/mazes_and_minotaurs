package com.example.cis.mazeminotaurs.character;

import com.example.cis.mazeminotaurs.Armor;
import com.example.cis.mazeminotaurs.Portfolio;
import com.example.cis.mazeminotaurs.Weapon;
import com.example.cis.mazeminotaurs.character.classes.Barbarian;
import com.example.cis.mazeminotaurs.character.classes.BaseClass;
import com.example.cis.mazeminotaurs.character.stats.Score;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * This class is meant for transforming the PlayerCharacter data into json strings.
 * @author Justin Smith
 */

public class SaveAndLoadPerformer {
    /**
     * Takes the player character parameter and transforms it into a json string.
     * @param playerCharacter the player character that needs to be serialized.
     * @return json string of playerCharacter
     */
    public static String save(PlayerCharacter playerCharacter){
        int index = Portfolio.get().getPortfolio().indexOf(playerCharacter);
        if (index > -1) return save(index);
        return null;
    }

    /**
     * Takes the character index parameter and transforms it into a json string.
     * @param characterIndex the index of the player character inside of the Portfoilo singleton to save.
     * @return json string of playerCharacter
     */
    public static String save(int characterIndex) {
        return new Gson().toJson(Portfolio.get().getPlayerCharacter(characterIndex));
    }

    /**
     * Returns a playerCharacter based on the json string given.
     * @param jsonString the playerCharacter in json
     * @return the generated playerCharacter
     */
    public static PlayerCharacter load(String jsonString){
        return new Gson().fromJson(jsonString, PlayerCharacter.class);
    }

    public static String savePortfolio() {
        Gson gson = new Gson();
        Type listType = new TypeToken<ArrayList<PlayerCharacter>>() {
        }.getType();
        return gson.toJson(Portfolio.get(), listType);
    }

    public static void loadPortfolio(String jsonString) {
        Gson gson = new Gson();
        Type listType = new TypeToken<ArrayList<PlayerCharacter>>() {
        }.getType();
        Portfolio newPort = gson.fromJson(jsonString, listType);
        Portfolio.get().setPortfolio(newPort.getPortfolio());
    }
}
