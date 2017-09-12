package com.example.cis.mazeminotaurs.character;

import com.example.cis.mazeminotaurs.R;

/**
 * Created by jusmith on 3/30/17.
 */

public enum Patron {
    APHRODITE(R.string.aphrodite),
    APOLLO(R.string.apollo),
    ARES(R.string.ares),
    ARTEMIS(R.string.artemis),
    ATHENA(R.string.athena),
    DEMETER(R.string.demeter),
    HEPHAESTUS(R.string.hephaestus),
    HERA(R.string.hera),
    HERMES(R.string.hermes),
    HESTIA(R.string.hestia),
    POSEIDON(R.string.poseidon),
    ZEUS(R.string.zeus);

    private int mResId;

    Patron(int resId) {
        mResId = resId;
    }

    public int getResId(){
        return mResId;
    }
}
