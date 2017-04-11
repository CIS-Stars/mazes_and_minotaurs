package com.example.cis.mazeminotaurs;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cis.mazeminotaurs.character.PlayerCharacter;
import com.example.cis.mazeminotaurs.util.Util;

/**
 * Created by Thorin Schmidt on 4/1/2017.
 */

public class CharacterSheetFragment extends Fragment {

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
        mMightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int dieResult = Util.roll(20);
                Toast.makeText(getContext(), "Might Roll: "+ Integer.toString(dieResult), Toast.LENGTH_SHORT).show();
            }
        });



        return rootView;
    }
}
