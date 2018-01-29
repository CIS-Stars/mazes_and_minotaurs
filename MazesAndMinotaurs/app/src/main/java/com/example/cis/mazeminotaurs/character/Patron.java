package com.example.cis.mazeminotaurs.character;

import com.example.cis.mazeminotaurs.R;

/**
 * Enumeration of Patrons featured in Mazes and Minotaurs.
 * @author jusmith on 3/30/17.
 */

public enum Patron {
    /**
     * Value of Aphrodite
     */
    APHRODITE(R.string.aphrodite),

    /**
     * Value of Apollo
     */
    APOLLO(R.string.apollo),

    /**
     * Value of Ares
     */
    ARES(R.string.ares),

    /**
     * Value of Artemis
     */
    ARTEMIS(R.string.artemis),

    /**
     * Value of Athena
     */
    ATHENA(R.string.athena),

    /**
     * Value of Demeter
     */
    DEMETER(R.string.demeter),

    /**
     * Value of Hephaestus
     */
    HEPHAESTUS(R.string.hephaestus),

    /**
     * Value of Hera
     */
    HERA(R.string.hera),

    /**
     * Value of Hermes
     */
    HERMES(R.string.hermes),

    /**
     * Value of Hestia
     */
    HESTIA(R.string.hestia),

    /**
     * Value of Poseidon
     */
    POSEIDON(R.string.poseidon),

    /**
     * Value of Zeus
     */
    ZEUS(R.string.zeus);

    /**
     * The resource id of the patron's name.
     */
    private int mResId;

    /**
     * Default constructor.
     *
     * @param resId the resource id of the patron's name.
     */
    Patron(int resId) {
        mResId = resId;
    }

    /**
     * Getter for mResId.
     * @return the value of mResId.
     */
    public int getResId(){
        return mResId;
    }
}
