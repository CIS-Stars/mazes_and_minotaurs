package com.example.cis.mazeminotaurs.NewCharacter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.cis.mazeminotaurs.NewCharacter.dialogs.DetailDialogFragment;
import com.example.cis.mazeminotaurs.R;

/**
 * Created by Chaos on 4/4/2017.
 */

public class CharacterCreationFragment extends Fragment implements DetailDialogFragment.DetailDialogListener{

    public ImageButton mWarriorButton;
    public ImageButton mMagicianButton;
    public ImageButton mSpecialistButton;

    public RadioGroup mWarriorGroup;
    public RadioGroup mMagicianGroup;
    public RadioGroup mSpecialistGroup;
    public RadioGroup mSelectedGroup;

    public TextView mSelectClass;
    public String mClassInformation;
    public String mClass;

    AlertDialog.Builder mConfirmPop;

    @Override
    public View onCreateView(LayoutInflater li, ViewGroup vg, Bundle b){
        super.onCreateView(li, vg, b);
        View rootView = li.inflate(R.layout.fragment_character_creation, vg, false);

        mConfirmPop = new AlertDialog.Builder(getActivity());
        mConfirmPop.setTitle("Confirm Class");

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
                AlertDialog mConfirmChoice = mConfirmPop.create();
                switch (checkedId){
                    case R.id.amazon_radio:
                        mClassInformation = getResources().getString(R.string.amazon_class);
                        mClass = "Amazon";
                        mConfirmPop.setMessage(mClassInformation);
                        AlertDialog mConfirmAmazon = mConfirmPop.create();
                        mConfirmAmazon.show();
                        break;
                    case R.id.barbarian_radio:
                        mClassInformation = getResources().getString(R.string.barbarian_class);
                        mClass = "Barbarian";
                        mConfirmPop.setMessage(mClassInformation);
                        AlertDialog mConfirmBarbarian = mConfirmPop.create();
                        mConfirmBarbarian.show();
                        break;
                    case R.id.centaur_radio:
                        mClassInformation = getResources().getString(R.string.centaur_class);
                        mClass = "Centaur";
                        mConfirmPop.setMessage(mClassInformation);
                        AlertDialog mConfirmCentaur = mConfirmPop.create();
                        mConfirmCentaur.show();
                        break;
                    case R.id.noble_radio:
                        mClassInformation = getResources().getString(R.string.noble_class);
                        mClass = "Noble";
                        mConfirmPop.setMessage(mClassInformation);
                        AlertDialog mConfirmNoble = mConfirmPop.create();
                        mConfirmNoble.show();
                        break;
                    case R.id.spear_radio:
                        mClassInformation = getResources().getString(R.string.spearman_class);
                        mClass = "Spearman";
                        mConfirmPop.setMessage(mClassInformation);
                        AlertDialog mConfirmSpearmen = mConfirmPop.create();
                        mConfirmSpearmen.show();
                        break;
                }
            }
        });

        mMagicianGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch (checkedId){
                    case R.id.elementalist_radio:
                        mClassInformation = getResources().getString(R.string.elementalist_class);
                        mClass = "Elementalist";
                        mConfirmPop.setMessage(mClassInformation);
                        AlertDialog mConfirmElementalist = mConfirmPop.create();
                        mConfirmElementalist.show();
                        break;
                    case R.id.lyrist_radio:
                        mClassInformation = getResources().getString(R.string.lyrist_class);
                        mClass = "Lyrist";
                        mConfirmPop.setMessage(mClassInformation);
                        AlertDialog mConfirmLyrists = mConfirmPop.create();
                        mConfirmLyrists.show();
                        break;
                    case R.id.nymph_radio:
                        mClassInformation = getResources().getString(R.string.nymph_class);
                        mClass = "Nymph";
                        mConfirmPop.setMessage(mClassInformation);
                        AlertDialog mConfirmNymph = mConfirmPop.create();
                        mConfirmNymph.show();
                        break;
                    case R.id.priest_radio:
                        mClassInformation = getResources().getString(R.string.priest_class);
                        mClass = "Priest";
                        mConfirmPop.setMessage(mClassInformation);
                        AlertDialog mConfirmPriest = mConfirmPop.create();
                        mConfirmPriest.show();
                        break;
                    case R.id.sorcerer_radio:
                        mClassInformation = getResources().getString(R.string.sorcerer_class);
                        mClass = "Sorcerer";
                        mConfirmPop.setMessage(mClassInformation);
                        AlertDialog mConfirmSorcerer = mConfirmPop.create();
                        mConfirmSorcerer.show();
                        break;
                }
            }
        });

        mSpecialistGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch (checkedId) {
                    case R.id.hunter_radio:
                        mClassInformation = getResources().getString(R.string.hunter_class);
                        mClass = "Hunter";
                        mConfirmPop.setMessage(mClassInformation);
                        AlertDialog mConfirmHunter = mConfirmPop.create();
                        mConfirmHunter.show();
                        break;
                    case R.id.thief_radio:

                        mClassInformation = getResources().getString(R.string.thief_class);
                        mClass = "Thief";
                        /* Testing of the dialog fragment
                        mConfirmPop.setMessage(mClassInformation);
                        AlertDialog mConfirmThief = mConfirmPop.create();
                        mConfirmThief.show();
                        */

                        showDetailDialog(R.string.thief, R.string.thief_class);
                        break;
                }
            }
        });

        mConfirmPop.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                CreateCharacter addedType = new CreateCharacter();
                Bundle classType = new Bundle();
                classType.putString("newClass", mClass);
                addedType.setArguments(classType);

                getFragmentManager().beginTransaction().add(R.id.content_frame, addedType)
                        .commit();
            }
        });

        mConfirmPop.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });

        return rootView;
    }

    public void showDetailDialog(int classRes, int descriptRes) {
        DetailDialogFragment dialog = new DetailDialogFragment();

        Bundle bundle = new Bundle();
        bundle.putInt("class", classRes);
        bundle.putInt("classInfo", descriptRes);
        dialog.setArguments(bundle);

        dialog.show(getChildFragmentManager(), "DetailDialogFragment");
    }

    @Override
    public void onDialogPositiveClick(DialogFragment dialogFragment) {
        CreateCharacter addedType = new CreateCharacter();
        Bundle classType = new Bundle();
        classType.putString("newClass", mClass);
        addedType.setArguments(classType);

        getFragmentManager().beginTransaction().add(R.id.content_frame, addedType)
                .commit();
    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialogFragment) {

    }
}
