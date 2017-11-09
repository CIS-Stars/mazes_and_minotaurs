package com.example.cis.mazeminotaurs.character.stats;

import com.example.cis.mazeminotaurs.AttributeScore;

import java.util.Comparator;

/**
 * Compares AttributeScores based on their score fields.
 * <p>
 * Created by JayTSmith on 11/9/17.
 */

public class AttributeScoreComparator implements Comparator<AttributeScore> {
    @Override
    public int compare(AttributeScore attributeScore, AttributeScore t1) {
        int diff = attributeScore.getScore() - t1.getScore();
        if (diff > 0) {
            return 1;
        }
        if (diff == 0) {
            return 0;
        }
        return -1;
    }
}
