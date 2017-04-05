package com.example.cis.mazeminotaurs;

/**
 * Created by Thorin Schmidt on 3/19/2017.
 */

public class TestCharacter {
    /* Given examples like QeoQuiz, this can probably be jsut as effectively done with string resources.
       I will leave this here just in case...
     */
    /**
     * These enums are here to reduce the need for string constants. this way, they are actual values.
     * they also function as actual data types, so the class field is of type Class, and can only
     * be assigned those predefined values.

    enum Class {
        AMAZON, AMAZON_B, ARCHER, BARBARIAN, BEASTMASTER, CAVALRYMAN, CENTAUR, ELEMENTALIST, HUNTER,
        LYRIST, NOBLE, NYMPH, PRIEST, SHAPESHIFTER, SORCEROR, SPEARMAN, THIEF
    }

    enum Patron {
        APHRODITE, APOLLO, ARES, ARTEMIS, ATHENA, DEMETER, HEPHAESTUS, HERA, HERMES, HESTIA, POSEIDON,
        ZEUS
    }

    enum Element {
        AIR, EARTH, FIRE, WATER, LIGHT, DARK
    }

    enum Kin{
        DRYAD, HELEAD, NAIAD, NAPAEA, NEREID, OREAD
    }
    These are all now string resources */

    //instance fields
    private int age;

    public TestCharacter(int age) {
        this.age = age;
    }

    public TestCharacter() {
        this.age = 10;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
