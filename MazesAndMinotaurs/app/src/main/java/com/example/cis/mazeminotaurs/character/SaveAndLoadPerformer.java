package com.example.cis.mazeminotaurs.character;

import android.content.Context;

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

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * This class is meant for transforming the PlayerCharacter data into json strings.
 * @author Justin Smith
 */

public class SaveAndLoadPerformer {
    public static String save(PlayerCharacter playerCharacter){
        int index = Portfolio.get().getPortfolio().indexOf(playerCharacter);
        if (index > -1) return save(index);
        return null;
    }

    public static String save(int characterIndex) {
        GsonBuilder gson = new GsonBuilder();
        gson.registerTypeAdapter(PlayerCharacter.class, new CharacterSerializer());
        return gson.create().toJson(Portfolio.get().getPlayerCharacter(characterIndex));
    }

    public static PlayerCharacter load(String jsonString){
        PlayerCharacter returnCharacter = new PlayerCharacter();
        JsonObject loadedData = (JsonObject) new Gson().fromJson(jsonString, JsonElement.class);

        for (Score score: Score.values()) {
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
}

class CharacterSerializer implements JsonSerializer<PlayerCharacter> {
    @Override
    public JsonElement serialize(PlayerCharacter src, Type typeOfSrc, JsonSerializationContext context) {
        BaseClass characterClass = src.getCharClass();
        characterClass.setCharacter(null);

        JsonObject rootObject = new JsonObject();
        rootObject.add("mScores", context.serialize(src.getScores()));
        rootObject.add("mCharClass", context.serialize(src.getCharClass()));
        rootObject.add("mGender", context.serialize(src.getGender()));
        rootObject.add("mMoney", context.serialize(src.getMoney()));
        rootObject.add("mAge", context.serialize(src.getAge()));
        rootObject.add("mName",context.serialize(src.getName()));
        rootObject.add("mInventory", context.serialize(src.getInventory()));
        rootObject.add("mCurrentWeapon", context.serialize(src.getCurrentWeapon()));
        rootObject.add("mHelmet", context.serialize(src.getHelmet()));
        rootObject.add("mBreastplate", context.serialize(src.getBreastplate()));
        rootObject.add("mShield",context.serialize(src.getShield()));

        JsonElement classJson = context.serialize(characterClass);
        rootObject.add("mCharClass", classJson);

        characterClass.setCharacter(src);
        return rootObject;
    }
}
