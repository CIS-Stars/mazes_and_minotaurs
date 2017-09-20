package com.example.cis.mazeminotaurs;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.cis.mazeminotaurs.character.stats.Score;

/**
 * Created by jsmith on 9/19/17.
 *
 * Variant of Thorin Schmidt's RollResultFragment
 */

public class StatChangeFragment extends DialogFragment {

    String mStringScore;
    Score mScore;
    int mCharIndex;
    int mOldValue;
    int mNewValue;

    /**
     * Create a new instance of StatChangeFragment, providing "score"
     * and "value" as arguments.
     */
    static StatChangeFragment newInstance(String score, int value, int charIndex) {
        StatChangeFragment f = new StatChangeFragment();

        // Supply num input as an argument.
        Bundle args = new Bundle();
        args.putString("stat", score);
        args.putInt("value", value);
        args.putInt("charIndex", charIndex);
        f.setArguments(args);

        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        mCharIndex = getArguments().getInt("charIndex");

        mStringScore = getArguments().getString("stat");
        if (mStringScore != null) {
            switch (mStringScore) {
                case "Might":
                    mScore = Score.MIGHT;
                    break;
                case "Grace":
                    mScore = Score.GRACE;
                    break;
                case "Skill":
                    mScore = Score.SKILL;
                    break;
                case "Will":
                    mScore = Score.WILL;
                    break;
                case "Wits":
                    mScore = Score.WITS;
                    break;
                case "Luck":
                    mScore = Score.LUCK;
                    break;
            }
        }
        mOldValue = getArguments().getInt("value");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.dialog_attribute_edit, container, false);
        View oldTv = v.findViewById(R.id.attribute_old_base);
        ((TextView) oldTv).setText(getContext().getString(R.string.old_attribute_value, mStringScore, mOldValue));
        ((Button) v.findViewById(R.id.attribute_confirm_button)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Portfolio.get().getPlayerCharacter(mCharIndex).getScore(mScore).setScore(mNewValue);
            }
        });

        return v;

    }



}