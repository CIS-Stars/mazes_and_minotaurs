package com.example.cis.mazeminotaurs;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.cis.mazeminotaurs.character.PlayerCharacter;
import com.example.cis.mazeminotaurs.character.stats.Score;
import com.example.cis.mazeminotaurs.util.Util;

/**
 * Created by Thorin Schmidt on 4/1/2017.
 */

public class CharacterSheetFragment extends Fragment {

    public static final String ROLL_RESULT = "RollResult";
    public static final String TAG = "CharacterSheetFragment";

    Portfolio mPortfolio;
    PlayerCharacter mSheetPlayerCharacter;
    TextView mCharacterNameView;
    TextView mCharacterLevelView;
    TextView mCharacterClassView;

    Button mMightButton;
    Button mSkillButton;
    Button mWitsButton;
    Button mLuckButton;
    Button mWillButton;
    Button mGraceButton;

    public CharacterSheetFragment(){
        mPortfolio = Portfolio.get();
    }

    @Override
    public View onCreateView(LayoutInflater li, ViewGroup vg, Bundle b){
        super.onCreateView(li, vg, b);
        View rootView = li.inflate(R.layout.fragment_character_sheet, vg, false);


        mSheetPlayerCharacter = mPortfolio.getPlayerCharacter(0);
        mCharacterLevelView = (TextView) rootView.findViewById(R.id.character_level_view);
        mCharacterLevelView.setText(Integer.toString(mSheetPlayerCharacter.getCharClass().getLevel()));

        mCharacterNameView = (TextView) rootView.findViewById(R.id.character_name_view);
        mCharacterNameView.setText(mSheetPlayerCharacter.getName());

        mCharacterClassView = (TextView) rootView.findViewById(R.id.character_class_view);
        mCharacterClassView.setText(mSheetPlayerCharacter.getCharClass().getResId());

        mMightButton = (Button) rootView.findViewById(R.id.might_score_button);
        mMightButton.setText(Integer.toString(mSheetPlayerCharacter.
                getCoreStatScore(Score.MIGHT).getScore()));
        mMightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onScoreClick(Score.MIGHT, "Might");
            }
        });

        mSkillButton = (Button) rootView.findViewById(R.id.skill_score_button);
        mSkillButton.setText(Integer.toString(mSheetPlayerCharacter.
                getCoreStatScore(Score.SKILL).getScore()));
        mSkillButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                onScoreClick(Score.SKILL, "Skill");
            }
        });

        mWitsButton = (Button) rootView.findViewById(R.id.wits_score_button);
        mWitsButton.setText(Integer.toString(mSheetPlayerCharacter.
                getCoreStatScore(Score.WITS).getScore()));
        mWitsButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                onScoreClick(Score.WITS, "Wits");
            }
        });

        mLuckButton = (Button) rootView.findViewById(R.id.luck_score_button);
        mLuckButton.setText(Integer.toString(mSheetPlayerCharacter.
                getCoreStatScore(Score.LUCK).getScore()));
        mLuckButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                onScoreClick(Score.LUCK, "Luck");
            }
        });

        mWillButton = (Button) rootView.findViewById(R.id.will_score_button);
        mWillButton.setText(Integer.toString(mSheetPlayerCharacter.
                getCoreStatScore(Score.WILL).getScore()));
        mWillButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                onScoreClick(Score.WILL, "Will");
            }
        });

        mGraceButton = (Button) rootView.findViewById(R.id.grace_score_button);
        mGraceButton.setText(Integer.toString(mSheetPlayerCharacter.
                getCoreStatScore(Score.GRACE).getScore()));
        mGraceButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                onScoreClick(Score.GRACE, "Grace ");
            }
        });

        return rootView;
    }

    public void onScoreClick(Score skill, String name){
        int modifier =  mSheetPlayerCharacter.getCoreStatScore(skill).getModifier();
        int dieRoll = Util.roll(20);
        int rollResult = dieRoll + modifier;
        FragmentManager fm = getFragmentManager();
        RollResultFragment dialog = RollResultFragment.newInstance(dieRoll, modifier, name);
        dialog.show(fm, ROLL_RESULT);
    }
}
