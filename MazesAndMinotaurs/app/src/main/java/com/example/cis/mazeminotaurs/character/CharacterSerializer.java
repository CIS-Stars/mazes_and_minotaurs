package com.example.cis.mazeminotaurs.character;

/**
 * Created by jsmith on 10/17/17.
 */

import com.example.cis.mazeminotaurs.Armor;
import com.example.cis.mazeminotaurs.Equipment;
import com.example.cis.mazeminotaurs.Weapon;
import com.example.cis.mazeminotaurs.character.Gender;
import com.example.cis.mazeminotaurs.character.Money;
import com.example.cis.mazeminotaurs.character.PlayerCharacter;
import com.example.cis.mazeminotaurs.character.classes.BaseClass;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Custom serializer for transforming the PlayerCharacter class into json.
 */
public class CharacterSerializer implements JsonSerializer<PlayerCharacter>, JsonDeserializer<PlayerCharacter> {
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
        rootObject.add("mName", context.serialize(src.getName()));
        rootObject.add("mInventory", context.serialize(src.getInventory()));
        rootObject.add("mCurrentWeapon", context.serialize(src.getCurrentWeapon()));
        rootObject.add("mHelmet", context.serialize(src.getHelmet()));
        rootObject.add("mBreastplate", context.serialize(src.getBreastplate()));
        rootObject.add("mShield", context.serialize(src.getShield()));

        JsonElement classJson = context.serialize(characterClass);
        rootObject.add("mCharClass", classJson);

        characterClass.setCharacter(src);
        return rootObject;
    }

    @Override
    public PlayerCharacter deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        final PlayerCharacter newCharacter = new PlayerCharacter();
        JsonObject loadedData = json.getAsJsonObject();
        for (Map.Entry<String, JsonElement> e : loadedData.get("mCharClass").getAsJsonObject().entrySet()) {
            System.out.println(e);
        }
        newCharacter.setAge((int) context.deserialize(loadedData.get("mAge"), Integer.class));
        newCharacter.setBreastplate((Armor) context.deserialize(loadedData.get("mBreastplate"), Armor.class));
        newCharacter.setCharClass((BaseClass) context.deserialize(loadedData.get("mCharClass"), BaseClass.class));
        newCharacter.setCurrentWeapon((Weapon) context.deserialize(loadedData.get("mCurrentWeapon"), Weapon.class));
        newCharacter.setGender((Gender) context.deserialize(loadedData.get("mGender"), Gender.class));
        newCharacter.setHelmet((Armor) context.deserialize(loadedData.get("mHelmet"), Armor.class));
        newCharacter.setMoney((HashMap<Money, Integer>) context.deserialize(loadedData.get("mMoney"), HashMap.class));
        newCharacter.setName(loadedData.get("mName").getAsString());
        newCharacter.setInventory((ArrayList<Equipment>) context.deserialize(loadedData.get("mInventory"), ArrayList.class));
        newCharacter.setShield((Armor) context.deserialize(loadedData.get("mShield"), Armor.class));

        return newCharacter;
    }
}
