package com.example.cis.mazeminotaurs.character.classes;

import com.example.cis.mazeminotaurs.character.stats.Score;

/**
 * Created by jusmith on 4/10/17.
 */

public interface Level {
    void doLevelUp();
    void doLevelUp(Score score);
    void doLevelDown();
}
