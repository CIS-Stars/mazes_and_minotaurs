package com.example.cis.mazeminotaurs.NewCharacter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.cis.mazeminotaurs.CharacterSheetFragment;
import com.example.cis.mazeminotaurs.Portfolio;
import com.example.cis.mazeminotaurs.R;
import com.example.cis.mazeminotaurs.character.PlayerCharacter;
import com.example.cis.mazeminotaurs.character.classes.BaseClass;
import com.example.cis.mazeminotaurs.character.stats.Score;

/**
 * Created by ckling on 4/10/17.
 */

public class CreateCharacter extends Fragment {

    //TODO Clean ths up
    BaseClass mBaseClass;

    TextView mCharaClassTextView;
    EditText mCharaNameEditText;
    TextView mCharacterLevelTextView;

    Button mMightButton;
    Button mSkillButton;
    Button mWitsButton;
    Button mLuckButton;
    Button mWillButton;
    Button mGraceButton;

    Button mAPButton;
    Button mDEButton;
    Button mMFButton;
    Button mPVButton;
    Button mInitButton;

    Button mWeaponNameButton;
    Button mWeaponTypeButton;

    public CreateCharacter() {
    }

    @Override
    public View onCreateView(LayoutInflater li, ViewGroup vg, final Bundle b) {
        super.onCreateView(li, vg, b);
        Log.i("Begin OnCreateView", "Start of onCreateView");
        View rootView = li.inflate(R.layout.fragment_create_character, vg, false);

        mBaseClass = (BaseClass) getArguments().get("classInstance");

        mCharaClassTextView = (TextView) rootView.findViewById(R.id.character_class_view);
        mCharaClassTextView.setText(mBaseClass.getResId());

        mCharaNameEditText = (EditText) rootView.findViewById(R.id.character_name_view);
        mCharaNameEditText.setText(mBaseClass.getCharacter().getName());
        mCharaNameEditText.setSingleLine(true);
        mCharaNameEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                mBaseClass.getCharacter().setName(editable.toString());
            }
        });

        mCharacterLevelTextView = (TextView) rootView.findViewById(R.id.character_level_view);
        mCharacterLevelTextView.setText(Integer.toString(mBaseClass.getLevel()));

        mMightButton = (Button) rootView.findViewById(R.id.might_score_button);
        mMightButton.setText(Integer.toString(mBaseClass.getCharacter().getScore(Score.MIGHT).getScore()));
        mSkillButton = (Button) rootView.findViewById(R.id.skill_score_button);
        mSkillButton.setText(Integer.toString(mBaseClass.getCharacter().getScore(Score.SKILL).getScore()));
        mWitsButton = (Button) rootView.findViewById(R.id.wits_score_button);
        mWitsButton.setText(Integer.toString(mBaseClass.getCharacter().getScore(Score.WITS).getScore()));
        mLuckButton = (Button) rootView.findViewById(R.id.luck_score_button);
        mWitsButton.setText(Integer.toString(mBaseClass.getCharacter().getScore(Score.WITS).getScore()));
        mWillButton = (Button) rootView.findViewById(R.id.will_score_button);
        mWillButton.setText(Integer.toString(mBaseClass.getCharacter().getScore(Score.WILL).getScore()));
        mGraceButton = (Button) rootView.findViewById(R.id.grace_score_button);
        mGraceButton.setText(Integer.toString(mBaseClass.getCharacter().getScore(Score.GRACE).getScore()));

        mAPButton = (Button) rootView.findViewById(R.id.athletic_prowess_button);
        mDEButton = (Button) rootView.findViewById(R.id.danger_evasion_button);
        mMFButton = (Button) rootView.findViewById(R.id.mystic_fortitude_button);
        mPVButton = (Button) rootView.findViewById(R.id.physical_vigor_button);
        mInitButton = (Button) rootView.findViewById(R.id.initiative_modifier_button);
        mWeaponNameButton = (Button) rootView.findViewById(R.id.equipped_weapon_button);
        mWeaponTypeButton = (Button) rootView.findViewById(R.id.attack_button);

        // Confirm button
        rootView.findViewById(R.id.confirm_character_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Portfolio.get().addPlayerCharacter(mBaseClass.getCharacter());

                CharacterSheetFragment fragment = new CharacterSheetFragment();
                Bundle bundle = new Bundle();
                bundle.putInt("charIndex", Portfolio.get().getPortfolio().indexOf(mBaseClass.getCharacter()));
                fragment.setArguments(bundle);

                getFragmentManager().beginTransaction().add(R.id.content_frame, fragment)
                        .commit();
            }
        });

        updateStatButtons();

        return rootView;
    }


    private void updateStatButtons() {
        PlayerCharacter character = mBaseClass.getCharacter();
        mAPButton.setText(Integer.toString(character.getAthleticProwess()));
        mDEButton.setText(Integer.toString(character.getDangerEvasion()));
        mMFButton.setText(Integer.toString(character.getMysticFortitude()));
        mPVButton.setText(Integer.toString(character.getPhysicalVigor()));
        mInitButton.setText(Integer.toString(character.getInitiative()));
        mWeaponNameButton.setText(character.getWeapons().get(0).getResId());
        mWeaponTypeButton.setText(character.getWeapons().get(0).getWeaponType());
    }
}
