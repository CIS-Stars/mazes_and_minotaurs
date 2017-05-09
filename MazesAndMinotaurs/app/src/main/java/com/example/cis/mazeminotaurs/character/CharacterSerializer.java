package com.example.cis.mazeminotaurs.character;

import android.util.Log;

import com.example.cis.mazeminotaurs.character.classes.BaseClass;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Field;
import java.lang.reflect.Type;

/**
 * Created by jusmith on 5/9/17.
 */

public class CharacterSerializer implements JsonSerializer<PlayerCharacter>{
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

        System.out.println(rootObject.toString());
        return rootObject;
    }
}
