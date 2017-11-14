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

import com.example.cis.mazeminotaurs.AttributeScore;
import com.example.cis.mazeminotaurs.AttributeScoreGenerator;
import com.example.cis.mazeminotaurs.CharacterSheetFragment;
import com.example.cis.mazeminotaurs.NewCharacter.dialogs.AttributePriorityDialog;
import com.example.cis.mazeminotaurs.Portfolio;
import com.example.cis.mazeminotaurs.R;
import com.example.cis.mazeminotaurs.character.PlayerCharacter;
import com.example.cis.mazeminotaurs.character.classes.BaseClass;
import com.example.cis.mazeminotaurs.character.stats.AttributeScoreComparator;
import com.example.cis.mazeminotaurs.character.stats.Score;
import com.example.cis.mazeminotaurs.util.CommonStrings;
import com.example.cis.mazeminotaurs.util.Util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by ckling on 4/10/17.
 */

public class CreateCharacter extends Fragment implements AttributePriorityDialog.PriorityListener {

    private static final String TAG = CreateCharacter.class.getName();

    //TODO Clean ths up
    BaseClass mBaseClass;
    Score[] mPriorities;

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

    Button mPriorityButton;
    Button mRerollButton;
    Button mConfirmButton;

    public CreateCharacter() {
    }

    @Override
    public View onCreateView(LayoutInflater li, ViewGroup vg, final Bundle b) {
        super.onCreateView(li, vg, b);
        Log.i("Begin OnCreateView", "Start of onCreateView");
        View rootView = li.inflate(R.layout.fragment_create_character, vg, false);

        mBaseClass = (BaseClass) getArguments().get("classInstance");
        mPriorities = generatePriority();


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
        mSkillButton = (Button) rootView.findViewById(R.id.skill_score_button);
        mWitsButton = (Button) rootView.findViewById(R.id.wits_score_button);
        mLuckButton = (Button) rootView.findViewById(R.id.luck_score_button);
        mWillButton = (Button) rootView.findViewById(R.id.will_score_button);
        mGraceButton = (Button) rootView.findViewById(R.id.grace_score_button);


        mAPButton = (Button) rootView.findViewById(R.id.athletic_prowess_button);
        mDEButton = (Button) rootView.findViewById(R.id.danger_evasion_button);
        mMFButton = (Button) rootView.findViewById(R.id.mystic_fortitude_button);
        mPVButton = (Button) rootView.findViewById(R.id.physical_vigor_button);
        mInitButton = (Button) rootView.findViewById(R.id.initiative_modifier_button);
        mWeaponNameButton = (Button) rootView.findViewById(R.id.equipped_weapon_spinner);
        mWeaponTypeButton = (Button) rootView.findViewById(R.id.attack_button);

        mRerollButton = (Button) rootView.findViewById(R.id.reroll_stats_button);
        mPriorityButton = (Button) rootView.findViewById(R.id.attribute_priority_button);
        mConfirmButton = (Button) rootView.findViewById(R.id.confirm_character_button);

        final CreateCharacter self = this;

        mPriorityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AttributePriorityDialog dialog = new AttributePriorityDialog();
                Bundle args = new Bundle();

                args.putSerializable(CommonStrings.ATTR_PRIORITY_ARGS.getValue(), mPriorities);
                args.putSerializable(CommonStrings.CHARACTER_ARG.getValue(), mBaseClass);

                dialog.setArguments(args);
                dialog.setListener(self);
                dialog.show(getFragmentManager(), dialog.getTag());
            }
        });

        // Confirm button
        mConfirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Portfolio.get().addPlayerCharacter(mBaseClass.getCharacter());
                Portfolio.get().setActiveCharacterIndex(Portfolio.get().getPortfolio().indexOf(mBaseClass.getCharacter()));

                CharacterSheetFragment fragment = new CharacterSheetFragment();
                Bundle bundle = new Bundle();
                fragment.setArguments(bundle);

                // Clear the backstack before replacing the screen
                Util.clearBackStack(getFragmentManager());
                getFragmentManager().beginTransaction().replace(R.id.content_frame, fragment)
                        .commit();
            }
        });

        reroll();
        updateStatButtons();

        return rootView;
    }

    private Score[] generatePriority() {
        List<Score> result = new ArrayList<>();
        Collections.addAll(result, Score.values());

        // Swaps the class' primary attributes towards the front of the list
        for (int i = 0; i < mBaseClass.getPrimaryAttributes().size(); i++) {
            Collections.swap(result, i, result.indexOf(mBaseClass.getPrimaryAttributes().get(i)));
        }

        return result.toArray(new Score[]{});
    }

    private void reroll() {
        PlayerCharacter character = mBaseClass.getCharacter();

        List<AttributeScore> scores = new ArrayList<>();
        Collections.addAll(scores, new AttributeScoreGenerator().nextValidSet());
        Collections.sort(scores, Collections.reverseOrder(new AttributeScoreComparator()));

        for (int i = 0; i < scores.size(); i++) {
            character.getScore(mPriorities[i]).setScore(scores.get(i).getScore());
        }
    }

    private void updateStatButtons() {
        PlayerCharacter character = mBaseClass.getCharacter();

        // Attributes
        mMightButton.setText(Integer.toString(character.getScore(Score.MIGHT).getScore()));
        mSkillButton.setText(Integer.toString(character.getScore(Score.SKILL).getScore()));
        mWitsButton.setText(Integer.toString(character.getScore(Score.WITS).getScore()));
        mLuckButton.setText(Integer.toString(character.getScore(Score.LUCK).getScore()));
        mWillButton.setText(Integer.toString(character.getScore(Score.WILL).getScore()));
        mGraceButton.setText(Integer.toString(character.getScore(Score.GRACE).getScore()));

        // Saves
        mAPButton.setText(Integer.toString(character.getAthleticProwess()));
        mDEButton.setText(Integer.toString(character.getDangerEvasion()));
        mMFButton.setText(Integer.toString(character.getMysticFortitude()));
        mPVButton.setText(Integer.toString(character.getPhysicalVigor()));
        mInitButton.setText(Integer.toString(character.getInitiative()));

        // Weapons
        if (character.getCurrentWeapon() != null) {
            mWeaponNameButton.setText(character.getCurrentWeapon().getResId());
            mWeaponTypeButton.setText(character.getCurrentWeapon().getWeaponType());
        }
        else {
            mWeaponNameButton.setText("-");
            mWeaponTypeButton.setText("-");
        }
    }

    @Override
    public void onDialogPositiveClick(Score[] priorities) {
        boolean valid = true;
        // Checks to make sure every score shows up only once.
        for (int i = 0; i < priorities.length; i++) {
            for (int j = i + 1; j < priorities.length; j++) {
                if (priorities[i] == priorities[j]) {
                    valid = false;
                    break;
                }
            }
        }

        if (valid) {
            mPriorities = priorities;
            reroll();
            updateStatButtons();
        }
    }
}
