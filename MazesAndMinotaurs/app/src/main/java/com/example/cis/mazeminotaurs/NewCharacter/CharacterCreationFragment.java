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

    public void onRadioButtonClicked(View view){
        boolean checked = ((RadioButton) view).isChecked();

        if(mWarriorGroup.getVisibility() == View.VISIBLE){
            switch (view.getId()){
                case R.id.amazon_radio:
                    if (checked)
                        // Pull Pop up
                    break;
                case R.id.barbarian_radio:
                    if (checked)
                        // Pop up
                    break;
                case R.id.centaur_radio:
                    if (checked)
                        // Pop Up
                    break;
                case R.id.noble_radio:
                    if (checked)
                        // Pop Up
                    break;
                case R.id.spear_radio:
                    if (checked)
                        // Pop Up
                    break;
            }
        }

        if(mMagicianGroup.getVisibility() == View.VISIBLE){
            switch (view.getId()){
                case R.id.elementalist_radio:
                    if (checked)
                        // Pull Pop up
                        break;
                case R.id.lyrist_radio:
                    if (checked)
                        // Pop up
                        break;
                case R.id.nymph_radio:
                    if (checked)
                        // Pop Up
                        break;
                case R.id.priest_radio:
                    if (checked)
                        // Pop Up
                        break;
                case R.id.sorcerer_radio:
                    if (checked)
                        // Pop Up
                        break;
            }
        }

        if(mSpecialistGroup.getVisibility() == View.VISIBLE){
            switch (view.getId()) {
                case R.id.hunter_radio:
                    if (checked)
                        // Pull Pop up
                        break;
                case R.id.thief_radio:
                    if (checked)
                        // Pop up
                        break;
            }
        }
    }
}
