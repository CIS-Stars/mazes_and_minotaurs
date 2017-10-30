package com.example.cis.mazeminotaurs.NewCharacter;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.cis.mazeminotaurs.NewCharacter.dialogs.DetailDialogFragment;
import com.example.cis.mazeminotaurs.R;
import com.example.cis.mazeminotaurs.character.classes.BaseClass;

/**
 * Created by Chaos on 4/4/2017.
 */

public class CharacterCreationFragment extends Fragment implements DetailDialogFragment.DetailDialogListener {

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

    DetailDialogFragment mDialog;

    @Override
    public View onCreateView(LayoutInflater li, ViewGroup vg, Bundle b) {
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
                switch (checkedId) {
                    case R.id.amazon_radio:
                        mClassInformation = getResources().getString(R.string.amazon_class);
                        mClass = "Amazon";
                        showDetailDialog(R.string.amazon, R.string.amazon_class);
                        break;
                    case R.id.barbarian_radio:
                        mClassInformation = getResources().getString(R.string.barbarian_class);
                        mClass = "Barbarian";
                        showDetailDialog(R.string.barbarian, R.string.barbarian_class);
                        break;
                    case R.id.centaur_radio:
                        mClassInformation = getResources().getString(R.string.centaur_class);
                        mClass = "Centaur";
                        showDetailDialog(R.string.centaur, R.string.centaur_class);
                        break;
                    case R.id.noble_radio:
                        mClassInformation = getResources().getString(R.string.noble_class);
                        mClass = "Noble";
                        showDetailDialog(R.string.noble, R.string.noble_class);
                        break;
                    case R.id.spear_radio:
                        mClassInformation = getResources().getString(R.string.spearman_class);
                        mClass = "Spearman";
                        showDetailDialog(R.string.spearman, R.string.spearman_class);
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
                        showDetailDialog(R.string.elementalist, R.string.elementalist_class);
                        break;
                    case R.id.lyrist_radio:
                        mClassInformation = getResources().getString(R.string.lyrist_class);
                        mClass = "Lyrist";
                        showDetailDialog(R.string.lyrist, R.string.lyrist_class);
                        break;
                    case R.id.nymph_radio:
                        mClassInformation = getResources().getString(R.string.nymph_class);
                        mClass = "Nymph";
                        showDetailDialog(R.string.nymph, R.string.nymph_class);
                        break;
                    case R.id.priest_radio:
                        mClassInformation = getResources().getString(R.string.priest_class);
                        mClass = "Priest";
                        showDetailDialog(R.string.priest, R.string.priest_class);
                        break;
                    case R.id.sorcerer_radio:
                        mClassInformation = getResources().getString(R.string.sorcerer_class);
                        mClass = "Sorcerer";
                        showDetailDialog(R.string.sorceror, R.string.sorcerer_class);
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
                        showDetailDialog(R.string.hunter, R.string.hunter_class);
                        break;
                    case R.id.thief_radio:

                        mClassInformation = getResources().getString(R.string.thief_class);
                        mClass = "Thief";
                        showDetailDialog(R.string.thief, R.string.thief_class);
                        break;
                }
            }
        });

        return rootView;
    }

    public void showDetailDialog(int classRes, int descriptRes) {
        mDialog = new DetailDialogFragment();

        Bundle bundle = new Bundle();
        bundle.putInt("class", classRes);
        bundle.putInt("classInfo", descriptRes);
        mDialog.setArguments(bundle);
        mDialog.setListener(this);

        mDialog.show(getChildFragmentManager(), "DetailDialogFragment");
    }

    @Override
    public void onDialogPositiveClick(BaseClass instance) {
        CreateCharacter addedType = new CreateCharacter();
        Bundle classType = new Bundle();
        classType.putString("newClass", mClass);
        classType.putSerializable("classInstance", instance);
        addedType.setArguments(classType);

        getFragmentManager()
                .beginTransaction()
                .replace(R.id.content_frame, addedType)
                .addToBackStack(null)
                .commit();
    }
}
