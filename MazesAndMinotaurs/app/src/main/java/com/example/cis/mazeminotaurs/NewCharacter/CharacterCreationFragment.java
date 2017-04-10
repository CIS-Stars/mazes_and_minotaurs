package com.example.cis.mazeminotaurs.NewCharacter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.cis.mazeminotaurs.R;

/**
 * Created by Chaos on 4/4/2017.
 */

public class CharacterCreationFragment extends Fragment {

    public ImageButton mWarriorButton;
    public ImageButton mMagicianButton;
    public ImageButton mSpecialistButton;

    public RadioGroup mWarriorGroup;
    public RadioGroup mMagicianGroup;
    public RadioGroup mSpecialistGroup;

    public RadioButton mAmazonRadio;
    public RadioButton mBarbarianRadio;
    public RadioButton mCentaurRadio;
    public RadioButton mNobleRadio;
    public RadioButton mSpearRadio;

    public RadioButton mElementalistRadio;
    public RadioButton mLyristRadio;
    public RadioButton mNymphRadio;
    public RadioButton mPriestRadio;
    public RadioButton mSorcererRadio;

    public RadioButton mHunterRadio;
    public RadioButton mThiefRadio;

    public TextView mSelectClass;

    @Override
    public View onCreateView(LayoutInflater li, ViewGroup vg, Bundle b){
        super.onCreateView(li, vg, b);
        View rootView = li.inflate(R.layout.fragment_character_creation, vg, false);

        mWarriorButton = (ImageButton) rootView.findViewById(R.id.warrior_button);
        mMagicianButton = (ImageButton) rootView.findViewById(R.id.magician_button);
        mSpecialistButton = (ImageButton) rootView.findViewById(R.id.specialist_button);

        mWarriorGroup = (RadioGroup) rootView.findViewById(R.id.warrior_radio_set);
        mMagicianGroup = (RadioGroup) rootView.findViewById(R.id.magician_radio_set);
        mSpecialistGroup = (RadioGroup) rootView.findViewById(R.id.specialist_radio_set);

        // Radio Buttons for Warrior Group
        mAmazonRadio = (RadioButton) rootView.findViewById(R.id.amazon_radio);
        mBarbarianRadio = (RadioButton) rootView.findViewById(R.id.barbarian_radio);
        mCentaurRadio = (RadioButton) rootView.findViewById(R.id.centaur_radio);
        mNobleRadio = (RadioButton) rootView.findViewById(R.id.noble_radio);
        mSpearRadio = (RadioButton) rootView.findViewById(R.id.spear_radio);

        // Radio Buttons for Magician Group
        mElementalistRadio = (RadioButton) rootView.findViewById(R.id.elementalist_radio);
        mLyristRadio = (RadioButton) rootView.findViewById(R.id.lyrist_radio);
        mNymphRadio = (RadioButton) rootView.findViewById(R.id.nymph_radio);
        mPriestRadio = (RadioButton) rootView.findViewById(R.id.priest_radio);
        mSorcererRadio = (RadioButton) rootView.findViewById(R.id.sorcerer_radio);

        // Radio Buttons for Specialist Group
        mHunterRadio = (RadioButton) rootView.findViewById(R.id.hunter_radio);
        mThiefRadio = (RadioButton) rootView.findViewById(R.id.thief_radio);

        mSelectClass = (TextView) rootView.findViewById(R.id.select_class_view);

        mWarriorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSelectClass.setVisibility(View.VISIBLE);
                mWarriorGroup.setVisibility(View.VISIBLE);
                mMagicianGroup.setVisibility(View.GONE);
                mSpecialistGroup.setVisibility(View.GONE);
            }
        });

        mMagicianButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSelectClass.setVisibility(View.VISIBLE);
                mWarriorGroup.setVisibility(View.GONE);
                mMagicianGroup.setVisibility(View.VISIBLE);
                mSpecialistGroup.setVisibility(View.GONE);
            }
        });

        mSpecialistButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSelectClass.setVisibility(View.VISIBLE);
                mWarriorGroup.setVisibility(View.GONE);
                mMagicianGroup.setVisibility(View.GONE);
                mSpecialistGroup.setVisibility(View.VISIBLE);
            }
        });



        return rootView;
    }

}
