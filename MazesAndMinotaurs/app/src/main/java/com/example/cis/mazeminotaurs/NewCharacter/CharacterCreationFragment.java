package com.example.cis.mazeminotaurs.NewCharacter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;

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


        return rootView;
    }
}
