package com.example.cis.mazeminotaurs.NewCharacter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
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
    public RadioGroup mSelectedGroup;

    public TextView mSelectClass;
    public String mClassType;

    boolean mPopTrue = false;

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
                mSelectedGroup = mWarriorGroup;
            }
        });

        mMagicianButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSelectClass.setVisibility(View.VISIBLE);
                mWarriorGroup.setVisibility(View.GONE);
                mMagicianGroup.setVisibility(View.VISIBLE);
                mSpecialistGroup.setVisibility(View.GONE);
                mSelectedGroup = mMagicianGroup;
            }
        });

        mSpecialistButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSelectClass.setVisibility(View.VISIBLE);
                mWarriorGroup.setVisibility(View.GONE);
                mMagicianGroup.setVisibility(View.GONE);
                mSpecialistGroup.setVisibility(View.VISIBLE);
                mSelectedGroup = mSpecialistGroup;
            }
        });


        mWarriorGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch (checkedId){
                    case R.id.amazon_radio:
                        mClassType = getResources().getString(R.string.amazon_class);
                        moveToClassConfirmationPopUp();
                        break;
                    case R.id.barbarian_radio:
                        mClassType = getResources().getString(R.string.barbarian_class);
                        moveToClassConfirmationPopUp();
                        break;
                    case R.id.centaur_radio:
                        mClassType = getResources().getString(R.string.centaur_class);
                        moveToClassConfirmationPopUp();
                        break;
                    case R.id.noble_radio:
                        mClassType = getResources().getString(R.string.noble_class);
                        moveToClassConfirmationPopUp();
                        break;
                    case R.id.spear_radio:
                        mClassType = getResources().getString(R.string.spearman_class);
                        moveToClassConfirmationPopUp();
                        break;
                }
            }
        });

        mMagicianGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch (checkedId){
                    case R.id.elementalist_radio:
                        mClassType = getResources().getString(R.string.elementalist_class);
                        moveToClassConfirmationPopUp();
                        break;
                    case R.id.lyrist_radio:
                        mClassType = getResources().getString(R.string.lyrist_class);
                        moveToClassConfirmationPopUp();
                        break;
                    case R.id.nymph_radio:
                        mClassType = getResources().getString(R.string.nymph_class);
                        moveToClassConfirmationPopUp();
                        break;
                    case R.id.priest_radio:
                        mClassType = getResources().getString(R.string.priest_class);
                        moveToClassConfirmationPopUp();
                        break;
                    case R.id.sorcerer_radio:
                        mClassType = getResources().getString(R.string.sorcerer_class);
                        moveToClassConfirmationPopUp();
                        break;
                }
            }
        });

        mSpecialistGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch (checkedId) {
                    case R.id.hunter_radio:
                        mPopTrue = true;
                        mClassType = getResources().getString(R.string.hunter_class);
                        break;
                    case R.id.thief_radio:
                        mPopTrue = true;
                        mClassType = getResources().getString(R.string.thief_class);
                        break;
                }
            }
        });


        if (mPopTrue){
            moveToClassConfirmationPopUp();
        }

        return rootView;
    }

    private void moveToClassConfirmationPopUp() {
        Intent i = new Intent(getActivity(), ClassConfirmationPopUp.class);
        i.putExtra("mClass", mClassType);
        startActivity(i);
        ((Activity) getActivity()).overridePendingTransition(0,0);
    }
}
