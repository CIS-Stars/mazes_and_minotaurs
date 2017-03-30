package com.example.cis.mazeminotaurs.character;

import com.example.cis.mazeminotaurs.character.stats.Core;

import java.util.HashMap;

/**
 * Created by jusmith on 3/30/17.
 */

public class Character {
    private HashMap<Core, Integer> mCoreStats;
    private CharClass mClass;
    private int mAge;
    private String mName;

    public Character() {
        mAge = 0;
        mClass = CharClass.AMAZON;
        for (Core coreStat: Core.values()) {
            mCoreStats.put(coreStat, 0);
        }
        mName = "Thorin";
    }

    public int getMod(Core coreStat) {
        int statValue = mCoreStats.get(coreStat);
        if (statValue <= 2) {
            return -4;
        } else if (statValue <= 4) {
            return -3;
        } else if (statValue <= 6) {
            return -2;
        } else if (statValue <= 8) {
            return -1;
        } else if (statValue <= 12) {
            return 0;
        } else if (statValue <= 14) {
            return 1;
        } else if (statValue <= 16) {
            return 2;
        } else if (statValue <= 18) {
            return 3;
        } else {
            return 4;
        }
    }

    public int getMeleeMod() {
        return getMod(Core)
    }

    public Integer getCoreStatScore(Core coreStat) {
        return mCoreStats.get(coreStat);
    }

    public HashMap<Core, Integer> getCoreStats() {
        return mCoreStats;
    }

    public void setCoreStats(HashMap<Core, Integer> coreStats) {
        mCoreStats = coreStats;
    }

    public CharClass getCharClass() {
        return mClass;
    }

    public void setCharClass(CharClass aClass) {
        mClass = aClass;
    }

    public int getAge() {
        return mAge;
    }

    public void setAge(int age) {
        mAge = age;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }
}
