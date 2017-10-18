package com.example.cis.mazeminotaurs.character;

import com.example.cis.mazeminotaurs.Armor;
import com.example.cis.mazeminotaurs.Equipment;
import com.example.cis.mazeminotaurs.Portfolio;
import com.example.cis.mazeminotaurs.Weapon;
import com.example.cis.mazeminotaurs.character.classes.Amazon;
import com.example.cis.mazeminotaurs.character.classes.Barbarian;
import com.example.cis.mazeminotaurs.character.classes.BaseClass;
import com.example.cis.mazeminotaurs.character.classes.Centaur;
import com.example.cis.mazeminotaurs.character.classes.Elementalist;
import com.example.cis.mazeminotaurs.character.classes.Hunter;
import com.example.cis.mazeminotaurs.character.classes.Lyrist;
import com.example.cis.mazeminotaurs.character.classes.Magician;
import com.example.cis.mazeminotaurs.character.classes.Noble;
import com.example.cis.mazeminotaurs.character.classes.Nymph;
import com.example.cis.mazeminotaurs.character.classes.Priest;
import com.example.cis.mazeminotaurs.character.classes.Sorcerer;
import com.example.cis.mazeminotaurs.character.classes.Spearman;
import com.example.cis.mazeminotaurs.character.classes.Thief;
import com.example.cis.mazeminotaurs.character.stats.Score;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.google.gson.typeadapters.RuntimeTypeAdapterFactory;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * This class is meant for transforming the PlayerCharacter data into json strings.
 * @author Justin Smith
 */

public class SaveAndLoadPerformer {

    /**
     * Generates a gson that has all of the common features loaded in.
     * @return a gson object
     */
    private static Gson getGson() {
        GsonBuilder builder = new GsonBuilder();
        RuntimeTypeAdapterFactory<BaseClass> adapter = RuntimeTypeAdapterFactory
                .of(BaseClass.class)
                .registerSubtype(Amazon.class)
                .registerSubtype(Barbarian.class)
                .registerSubtype(Centaur.class)
                .registerSubtype(Elementalist.class)
                .registerSubtype(Hunter.class)
                .registerSubtype(Lyrist.class)
                .registerSubtype(Magician.class)
                .registerSubtype(Noble.class)
                .registerSubtype(Nymph.class)
                .registerSubtype(Priest.class)
                .registerSubtype(Sorcerer.class)
                .registerSubtype(Spearman.class)
                .registerSubtype(Thief.class);
        builder.registerTypeAdapter(PlayerCharacter.class, new CharacterSerializer());
        builder.registerTypeAdapterFactory(adapter);
        builder.setPrettyPrinting();
        return builder.create();
    }

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
        return getGson().toJson(Portfolio.get().getPlayerCharacter(characterIndex));
    }

    /**
     * Returns a playerCharacter based on the json string given.
     * @deprecated
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
        Gson gson = getGson();

        Type equipListType = new TypeToken<ArrayList<Equipment>>() {
        }.getType();
        Type moneyMapType = new TypeToken<HashMap<Money, Integer>>() {
        }.getType();

        returnCharacter.setAge(gson.fromJson(loadedData.get("mAge"), Integer.class));
        returnCharacter.setBreastplate(gson.fromJson(loadedData.get("mBreastplate"), Armor.class));
        returnCharacter.setCharClass(gson.fromJson(loadedData.get("mCharClass"), Barbarian.class));
        returnCharacter.setCurrentWeapon(gson.fromJson(loadedData.get("mCurrentWeapon"), Weapon.class));
        returnCharacter.setGender(gson.fromJson(loadedData.get("mGender"), Gender.class));
        returnCharacter.setHelmet(gson.fromJson(loadedData.get("mHelmet"), Armor.class));
        returnCharacter.setMoney((HashMap) gson.fromJson(loadedData.get("mMoney"), moneyMapType));
        returnCharacter.setName(loadedData.get("mName").getAsString());
        returnCharacter.setInventory((ArrayList) gson.fromJson(loadedData.get("mInventory"), equipListType));
        returnCharacter.setShield(gson.fromJson(loadedData.get("mShield"), Armor.class));

        return returnCharacter;
    }

    public static String savePortfolio() {
        Type listType = new TypeToken<ArrayList<PlayerCharacter>>() {
        }.getType();
        return getGson().toJson(Portfolio.get().getPortfolio(), listType);
    }

    public static void loadPortfolio(String jsonString) {
        Type listType = new TypeToken<ArrayList<PlayerCharacter>>() {
        }.getType();
        ArrayList<PlayerCharacter> newPort = getGson().fromJson(jsonString, listType);
        Portfolio.get().setPortfolio(newPort);
    }
}
