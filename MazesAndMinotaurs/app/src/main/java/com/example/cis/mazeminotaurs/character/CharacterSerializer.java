package com.example.cis.mazeminotaurs.character;

/**
 * Created by jsmith on 10/17/17.
 */

import com.example.cis.mazeminotaurs.Armor;
import com.example.cis.mazeminotaurs.Equipment;
import com.example.cis.mazeminotaurs.Mythics;
import com.example.cis.mazeminotaurs.Weapon;
import com.example.cis.mazeminotaurs.character.classes.BaseClass;
import com.example.cis.mazeminotaurs.character.stats.Score;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.reflect.TypeToken;
import com.google.gson.typeadapters.RuntimeTypeAdapterFactory;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Custom serializer for transforming the PlayerCharacter class into json.
 */
public class CharacterSerializer implements JsonSerializer<PlayerCharacter>, JsonDeserializer<PlayerCharacter> {

    /**
     * A GSON instance that is custom built for handling the Equipment serialization
     * within in the character.
     */
    private static Gson sGson = null;

    /**
     * Returns a GSON instance for handling serialization.
     * If sGson is null, it builds one and stores it inside of sGson.
     *
     * @return a GSON instance ready to serialize.
     */
    private static Gson getGson() {
        if (sGson == null) {
            GsonBuilder builder = new GsonBuilder();
            RuntimeTypeAdapterFactory adapter = RuntimeTypeAdapterFactory
                    .of(Equipment.class)
                    .registerSubtype(Equipment.class)
                    .registerSubtype(Weapon.class)
                    .registerSubtype(Armor.class)
                    .registerSubtype(Mythics.class);
            builder.registerTypeAdapterFactory(adapter);
            builder.setPrettyPrinting();
            sGson = builder.create();
        }
        return sGson;
    }

    @Override
    public JsonElement serialize(PlayerCharacter src, Type typeOfSrc, JsonSerializationContext context) {
        BaseClass characterClass = src.getCharClass();
        characterClass.setCharacter(null);

        Type equipListType = new TypeToken<ArrayList<Equipment>>() {
        }.getType();
        Type moneyMapType = new TypeToken<HashMap<Money, Integer>>() {
        }.getType();

        JsonObject rootObject = new JsonObject();
        rootObject.add("mScores", context.serialize(src.getScores()));
        rootObject.add("mCharClass", context.serialize(src.getCharClass(), BaseClass.class));
        rootObject.add("mGender", context.serialize(src.getGender()));
        rootObject.add("mMoney", context.serialize(src.getMoney(), moneyMapType));
        rootObject.add("mAge", context.serialize(src.getAge()));
        rootObject.add("mName", context.serialize(src.getName()));
        System.out.println(getGson().toJsonTree(src.getInventory(), equipListType));
        rootObject.add("mInventory", getGson().toJsonTree(src.getInventory(), equipListType));
        rootObject.add("mCurrentWeapon", context.serialize(src.getCurrentWeapon()));
        rootObject.add("mHelmet", context.serialize(src.getHelmet()));
        rootObject.add("mBreastplate", context.serialize(src.getBreastplate()));
        rootObject.add("mShield", context.serialize(src.getShield()));
        rootObject.add("mPatron", context.serialize(src.getPatron()));

        JsonElement classJson = context.serialize(characterClass);
        rootObject.add("mCharClass", classJson);

        characterClass.setCharacter(src);
        return rootObject;
    }

    @Override
    public PlayerCharacter deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        final PlayerCharacter newCharacter = new PlayerCharacter();
        JsonObject loadedData = json.getAsJsonObject();
        for (Score score : Score.values()) {
            int loadedScore = loadedData.getAsJsonObject("mScores").getAsJsonObject(score.toString()).get("mScore").getAsInt();
            newCharacter.getScore(score).setScore(loadedScore);
        }

        Type equipListType = new TypeToken<ArrayList<Equipment>>() {
        }.getType();
        Type moneyMapType = new TypeToken<HashMap<Money, Integer>>() {
        }.getType();

        newCharacter.setAge((int) context.deserialize(loadedData.get("mAge"), Integer.class));
        newCharacter.setBreastplate((Armor) context.deserialize(loadedData.get("mBreastplate"), Armor.class));
        newCharacter.setCharClass((BaseClass) context.deserialize(loadedData.get("mCharClass"), BaseClass.class));
        newCharacter.setCurrentWeapon((Weapon) context.deserialize(loadedData.get("mCurrentWeapon"), Weapon.class));
        newCharacter.setGender((Gender) context.deserialize(loadedData.get("mGender"), Gender.class));
        newCharacter.setHelmet((Armor) context.deserialize(loadedData.get("mHelmet"), Armor.class));
        newCharacter.setMoney((HashMap<Money, Integer>) context.deserialize(loadedData.get("mMoney"), moneyMapType));
        newCharacter.setName(loadedData.get("mName").getAsString());
        newCharacter.setInventory((ArrayList<Equipment>) getGson().fromJson(loadedData.get("mInventory"), equipListType));
        newCharacter.setShield((Armor) context.deserialize(loadedData.get("mShield"), Armor.class));
        newCharacter.setPatron((Patron) context.deserialize(loadedData.get("mPatron"), Patron.class));

        return newCharacter;
    }
}
