package com.example.cis.mazeminotaurs.serialization;

import com.example.cis.mazeminotaurs.Armor;
import com.example.cis.mazeminotaurs.Equipment;
import com.example.cis.mazeminotaurs.EquipmentDB;
import com.example.cis.mazeminotaurs.Portfolio;
import com.example.cis.mazeminotaurs.Weapon;
import com.example.cis.mazeminotaurs.character.Gender;
import com.example.cis.mazeminotaurs.character.Money;
import com.example.cis.mazeminotaurs.character.PlayerCharacter;
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
     * A GSON instance that can handle subclasses of BaseClass.
     */
    private static Gson sGson = null;

    /**
     * Generates a gson that has all of the common features loaded in.
     * @return a gson object
     */
    private static Gson getGson() {
        if (sGson == null) {
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
            sGson = builder.create();
        }
        return sGson;
    }

    /**
     * Takes the player character parameter and transforms it into a json string.
     * @deprecated No longer used, use savePortfolio instead.
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
     * @deprecated No longer used, use savePortfolio instead.
     * @param characterIndex the index of the player character inside of the Portfoilo singleton to save.
     * @return json string of playerCharacter
     */
    public static String save(int characterIndex) {
        return getGson().toJson(Portfolio.get().getPlayerCharacter(characterIndex));
    }

    /**
     * Returns a playerCharacter based on the json string given.
     * @deprecated No longer used, use loadPortfolio.
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

    /**
     * Serializes the Portfolio field in the Portfolio singleton and returns
     * it as a json string.
     *
     * @return a json string version of portfolio.
     */
    public static String savePortfolio() {
        Type listType = new TypeToken<ArrayList<PlayerCharacter>>() {
        }.getType();
        return getGson().toJson(Portfolio.get().getPortfolio(), listType);
    }

    /**
     * De-serializes a json string into an arrayList of PlayerCharacters and updates
     * the portfolio field of the Portfolio singleton.
     * @param jsonString the json string version of an array list of PlayerCharacters.
     */
    public static void loadPortfolio(String jsonString) {
        Type listType = new TypeToken<ArrayList<PlayerCharacter>>() {
        }.getType();
        ArrayList<PlayerCharacter> newPort = getGson().fromJson(jsonString, listType);
        Portfolio.get().setPortfolio(newPort);
    }

    /**
     * Serializes the EquipmentDB singleton and returns it as a json string.
     * @return json string version of EquipmentDB singleton.
     */
    public static String saveEquipmentDB() {
        return getGson().toJson(EquipmentDB.getInstance());
    }

    /**
     * De-serializes a json string into an EquipmentDB instance and replaces
     * the old one.
     * @param jsonString json string version of an equipmentDB instance.
     */
    public static void loadEquipmentDB(String jsonString) {
        EquipmentDB newInstance = getGson().fromJson(jsonString, EquipmentDB.class);
        EquipmentDB curInstance = EquipmentDB.getInstance();

        curInstance.setArmors(newInstance.getArmors());
        curInstance.setEquipments(newInstance.getEquipments());
        curInstance.setWeapons(newInstance.getWeapons());
    }
}
