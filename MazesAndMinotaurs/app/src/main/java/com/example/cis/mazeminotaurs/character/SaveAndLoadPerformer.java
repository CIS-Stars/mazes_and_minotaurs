package com.example.cis.mazeminotaurs.character;

import com.example.cis.mazeminotaurs.Armor;
import com.example.cis.mazeminotaurs.Portfolio;
import com.example.cis.mazeminotaurs.Weapon;
import com.example.cis.mazeminotaurs.character.classes.Barbarian;
import com.example.cis.mazeminotaurs.character.stats.Score;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
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
        GsonBuilder gson = new GsonBuilder();
        gson.registerTypeAdapter(PlayerCharacter.class, new CharacterSerializer());
        return gson.create().toJson(Portfolio.get().getPlayerCharacter(characterIndex));
    }

    /**
     * Returns a playerCharacter based on the json string given.
     * @param jsonString the playerCharacter in json
     * @return the generated playerCharacter
     */
    public static PlayerCharacter load(String jsonString){
        PlayerCharacter returnCharacter = new PlayerCharacter();
        JsonObject loadedData = (JsonObject) new Gson().fromJson(jsonString, JsonElement.class);

        for (Score score : Score.values()) {
            int loadedScore = loadedData.getAsJsonObject("mScores").getAsJsonObject(score.toString()).get("mScore").getAsInt();
            returnCharacter.getScore(score).setScore(loadedScore);
        }
        Gson gson = new Gson();

        returnCharacter.setAge(gson.fromJson(loadedData.get("mAge"), Integer.class));
        returnCharacter.setBreastplate(gson.fromJson(loadedData.get("mBreastplate"), Armor.class));
        returnCharacter.setCharClass(gson.fromJson(loadedData.get("mCharClass"), Barbarian.class));
        returnCharacter.setCurrentWeapon(gson.fromJson(loadedData.get("mCurrentWeapon"), Weapon.class));
        returnCharacter.setGender(gson.fromJson(loadedData.get("mGender"), Gender.class));
        returnCharacter.setHelmet(gson.fromJson(loadedData.get("mHelmet"), Armor.class));
        returnCharacter.setMoney(gson.fromJson(loadedData.get("mMoney"), HashMap.class));
        returnCharacter.setName(loadedData.get("mName").getAsString());
        returnCharacter.setInventory(gson.fromJson(loadedData.get("mInventory"), ArrayList.class));
        returnCharacter.setShield(gson.fromJson(loadedData.get("mShield"), Armor.class));

        return returnCharacter;
    }

    public static String savePortfolio() {
        Gson gson = new Gson();
        Type listType = new TypeToken<ArrayList<PlayerCharacter>>() {
        }.getType();
        return gson.toJson(Portfolio.get().getPortfolio(), listType);
    }

    public static void loadPortfolio(String jsonString) {
        GsonBuilder builder = new GsonBuilder();
        Type listType = new TypeToken<ArrayList<PlayerCharacter>>() {
        }.getType();
        builder.registerTypeAdapter(PlayerCharacter.class, new CharacterSerializer());
        ArrayList<PlayerCharacter> newPort = builder.create().fromJson(jsonString, listType);
        Portfolio.get().setPortfolio(newPort);
    }
}
