package com.example.cis.mazeminotaurs;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cis.mazeminotaurs.character.PlayerCharacter;
import com.example.cis.mazeminotaurs.character.SaveAndLoadDialog;
import com.example.cis.mazeminotaurs.character.classes.Magician;
import com.example.cis.mazeminotaurs.character.stats.Score;
import com.example.cis.mazeminotaurs.util.Util;

/**
 * Created by Thorin Schmidt on 4/1/2017.
 */

public class CharacterSheetFragment extends Fragment implements StatChangeFragment.OnStatChangeListener{

    public static final String ROLL_RESULT = "RollResult";
    public static final String TAG = "CharacterSheetFragment";

    int mCurrentCharacterIndex = 0;
    Portfolio mPortfolio;
    PlayerCharacter mSheetPlayerCharacter;
    TextView mCharacterNameView;
    TextView mCharacterLevelView;
    TextView mCharacterClassView;
    TextView mAttackType;
    TextView mMagicTitleView;
    TextView mTalentBonusTitleView;
    TextView mMagicStrengthTitleView;
    TextView mTotalPowerTitleView;
    TextView mCurrentPowerTitleView;

    Button mMightButton;
    Button mSkillButton;
    Button mWitsButton;
    Button mLuckButton;
    Button mWillButton;
    Button mGraceButton;
    Button mAttackButton;
    Button mEquippedWeaponButton;
    Button mInitiativeButton;
    Button mAPbutton;
    Button mDEbutton;
    Button mMFbutton;
    Button mPVbutton;
    Button mTalentBonusButton;
    Button mMagicStrengthButton;
    Button mTotalPowerButton;
    Button mCurrentPowerButton;




    Button mSaveButton;

    public CharacterSheetFragment(){
        mPortfolio = Portfolio.get();
    }

    @Override
    public View onCreateView(LayoutInflater li, ViewGroup vg, Bundle b){
        super.onCreateView(li, vg, b);
        View rootView = li.inflate(R.layout.fragment_character_sheet, vg, false);


        mSheetPlayerCharacter = mPortfolio.getPlayerCharacter(mCurrentCharacterIndex);
        mCharacterLevelView = (TextView) rootView.findViewById(R.id.character_level_view);
        mCharacterLevelView.setText(Integer.toString(mSheetPlayerCharacter.getCharClass().getLevel()));

        mCharacterNameView = (TextView) rootView.findViewById(R.id.character_name_view);
        mCharacterNameView.setText(mSheetPlayerCharacter.getName());

        mCharacterClassView = (TextView) rootView.findViewById(R.id.character_class_view);
        mCharacterClassView.setText(mSheetPlayerCharacter.getCharClass().getResId());

        mMightButton = (Button) rootView.findViewById(R.id.might_score_button);
        mMightButton.setText(Integer.toString(mSheetPlayerCharacter.
                getScore(Score.MIGHT).getScore()));
        mMightButton.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                onScoreLongClick(Score.MIGHT, "Might");
                return true;
            }
        });
        mMightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onScoreClick(Score.MIGHT, "Might");
            }
        });

        mSkillButton = (Button) rootView.findViewById(R.id.skill_score_button);
        mSkillButton.setText(Integer.toString(mSheetPlayerCharacter.
                getScore(Score.SKILL).getScore()));
        mSkillButton.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                onScoreLongClick(Score.SKILL, "Skill");
                return true;
            }
        });
        mSkillButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                onScoreClick(Score.SKILL, "Skill");
            }
        });

        mWitsButton = (Button) rootView.findViewById(R.id.wits_score_button);
        mWitsButton.setText(Integer.toString(mSheetPlayerCharacter.
                getScore(Score.WITS).getScore()));
        mWitsButton.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                onScoreLongClick(Score.WITS, "Wits");
                return true;
            }
        });
        mWitsButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                onScoreClick(Score.WITS, "Wits");
            }
        });

        mLuckButton = (Button) rootView.findViewById(R.id.luck_score_button);
        mLuckButton.setText(Integer.toString(mSheetPlayerCharacter.
                getScore(Score.LUCK).getScore()));
        mLuckButton.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                onScoreLongClick(Score.LUCK, "Luck");
                return true;
            }
        });
        mLuckButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                onScoreClick(Score.LUCK, "Luck");
            }
        });

        mWillButton = (Button) rootView.findViewById(R.id.will_score_button);
        mWillButton.setText(Integer.toString(mSheetPlayerCharacter.
                getScore(Score.WILL).getScore()));
        mWillButton.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                onScoreLongClick(Score.WILL, "Will");
                return true;
            }
        });
        mWillButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                onScoreClick(Score.WILL, "Will");
            }
        });

        mGraceButton = (Button) rootView.findViewById(R.id.grace_score_button);
        mGraceButton.setText(Integer.toString(mSheetPlayerCharacter.
                getScore(Score.GRACE).getScore()));
        mGraceButton.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                onScoreLongClick(Score.GRACE, "Grace");
                return true;
            }
        });
        mGraceButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                onScoreClick(Score.GRACE, "Grace");
            }
        });

        mAttackType = (TextView) rootView.findViewById(R.id.attack_title_view);
        mAttackType.setText(mSheetPlayerCharacter.getCurrentWeapon().getWeaponType());

        mAttackButton = (Button) rootView.findViewById(R.id.attack_button);
        if(mSheetPlayerCharacter.getCurrentWeapon().getWeaponType() == R.string.melee) {
            mAttackButton.setText(Integer.toString(mSheetPlayerCharacter.getMeleeMod()));
        }
        else{
            mAttackButton.setText(Integer.toString(mSheetPlayerCharacter.getMissileMod()));
        }
        mAttackButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //just for testing, replace these with calls to character methods
                int weaponType = mSheetPlayerCharacter.getCurrentWeapon().getWeaponType();
                boolean woc = true;
                onAttackClick(weaponType, woc);
            }
        });

        mEquippedWeaponButton = (Button) rootView.findViewById(R.id.equipped_weapon_button);
        //Get equipped weapon from character Class
        //int equippedWeaponID = R.string.barb_axe;
        Weapon equippedWeapon = mSheetPlayerCharacter.getCurrentWeapon();
        Log.i(TAG, equippedWeapon.toString());
        int equippedWeaponID = equippedWeapon.getResId();
        mEquippedWeaponButton.setText(equippedWeaponID);
        mEquippedWeaponButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),
                        "Will soon open a listpicker", Toast.LENGTH_SHORT).show();
            }
        });

        mInitiativeButton = (Button) rootView.findViewById(R.id.initiative_modifier_button);
        mInitiativeButton.setText(Integer.toString(mSheetPlayerCharacter.getInitiative()));


        mAPbutton = (Button) rootView.findViewById(R.id.athletic_prowess_button);
        mAPbutton.setText(Integer.toString(mSheetPlayerCharacter.getAthleticProwess()));
        mAPbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSaveClick(mAPbutton.getId(), "AP");
            }
        });

        mDEbutton = (Button) rootView.findViewById(R.id.danger_evasion_button);
        mDEbutton.setText(Integer.toString(mSheetPlayerCharacter.getDangerEvasion()));
        mDEbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSaveClick(mDEbutton.getId(), "DE");
            }
        });

        mMFbutton = (Button) rootView.findViewById(R.id.mystic_fortitude_button);
        mMFbutton.setText(Integer.toString(mSheetPlayerCharacter.getMysticFortitude()));
        mMFbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSaveClick(mMFbutton.getId(), "MF");
            }
        });

        mPVbutton = (Button) rootView.findViewById(R.id.physical_vigor_button);
        mPVbutton.setText(Integer.toString(mSheetPlayerCharacter.getPhysicalVigor()));
        mPVbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSaveClick(mPVbutton.getId(), "PV");
            }
        });

        ((Button) rootView.findViewById(R.id.edc_button)).setText(Integer.toString(mSheetPlayerCharacter.getEDC()));
        ((Button) rootView.findViewById(R.id.total_hits_button)).setText(Integer.toString(mSheetPlayerCharacter.getHitTotal()));
        ((Button) rootView.findViewById(R.id.current_hits_button)).setText(Integer.toString(mSheetPlayerCharacter.getHitTotal()));

        mSaveButton = (Button) rootView.findViewById(R.id.save_button);
//      Commented out to disable in production.
//        mSaveButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                onSaveCharacterClick();
//            }
//        });

        // Magical and specialist section
        mMagicTitleView = (TextView) rootView.findViewById(R.id.magic_title_view);
        mTalentBonusTitleView = (TextView) rootView.findViewById(R.id.talent_bonus_title_view);
        mMagicStrengthTitleView = (TextView) rootView.findViewById(R.id.magic_strength_title_view);
        mTotalPowerTitleView = (TextView) rootView.findViewById(R.id.total_power_title_view);
        mCurrentPowerTitleView = (TextView) rootView.findViewById(R.id.current_power_title_view);

        mTalentBonusButton = (Button) rootView.findViewById(R.id.talent_bonus_button);
        mTalentBonusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        mMagicStrengthButton = (Button) rootView.findViewById(R.id.magic_strength_button);
        mTotalPowerButton = (Button) rootView.findViewById(R.id.total_power_button);
        mCurrentPowerButton = (Button) rootView.findViewById(R.id.current_power_button);

        //this variable for testing only!  should normally be set to false!!!
        boolean testing = false;
        if (mSheetPlayerCharacter.getCharClass() instanceof Magician || testing == true){
            mMagicTitleView.setVisibility(View.VISIBLE);
            mTalentBonusTitleView.setVisibility(View.VISIBLE);
            mMagicStrengthTitleView.setVisibility(View.VISIBLE);
            mTotalPowerTitleView.setVisibility(View.VISIBLE);
            mCurrentPowerTitleView.setVisibility(View.VISIBLE);

            mTalentBonusButton.setVisibility(View.VISIBLE);
            mMagicStrengthButton.setVisibility(View.VISIBLE);
            mTotalPowerButton.setVisibility(View.VISIBLE);
            mCurrentPowerButton.setVisibility(View.VISIBLE);

        }

        return rootView;
    }

    public void onScoreClick(Score skill, String name){
        int modifier =  mSheetPlayerCharacter.getScore(skill).getModifier();
        int dieRoll = Util.roll(20);
        FragmentManager fm = getFragmentManager();
        RollResultFragment dialog = RollResultFragment.newInstance(dieRoll, modifier, name);
        dialog.show(fm, ROLL_RESULT);
    }

    public void onScoreLongClick(Score skill, String name) {
        FragmentManager fm = getFragmentManager();
        StatChangeFragment dialog = StatChangeFragment.newInstance(name,
                mSheetPlayerCharacter.getScore(skill).getScore());
        dialog.setStatChangeListener(this);
        dialog.show(fm, ROLL_RESULT);
    }

    public void onAttackClick(int attackType, boolean wocEquipped){
        int modifier;
        int attackRoll1 = Util.roll(20);
        int attackRoll2 = Util.roll(20);
        int damageRoll1 = Util.roll(6);
        int damageRoll2 = Util.roll(6);
        if (attackType == R.string.melee){
            modifier = mSheetPlayerCharacter.getMeleeMod();
        }
        else if (attackType == R.string.missile){
            //TODO make missile mod
            modifier = mSheetPlayerCharacter.getMeleeMod();
        }
        else{
            modifier = 0;
        }
        FragmentManager fm = getFragmentManager();
        AttackResultFragment dialog = AttackResultFragment.newInstance(mCurrentCharacterIndex);
        dialog.show(fm, ROLL_RESULT);
    }

    void onSaveClick(int buttonID, String saveName){
        int modifier;
        int saveRoll = Util.roll(20);
        FragmentManager fm = getFragmentManager();
        SaveResultFragment dialog = null;
        switch (buttonID){
            case R.id.athletic_prowess_button:
                modifier = mSheetPlayerCharacter.getAthleticProwess();
                dialog = SaveResultFragment.newInstance(saveRoll, modifier, saveName);
                break;
            case R.id.danger_evasion_button:
                modifier = mSheetPlayerCharacter.getDangerEvasion();
                dialog = SaveResultFragment.newInstance(saveRoll, modifier, saveName);
                break;
            case R.id.mystic_fortitude_button:
                modifier = mSheetPlayerCharacter.getMysticFortitude();
                dialog = SaveResultFragment.newInstance(saveRoll, modifier, saveName);
                break;
            case R.id.physical_vigor_button:
                modifier = mSheetPlayerCharacter.getPhysicalVigor();
                dialog = SaveResultFragment.newInstance(saveRoll, modifier, saveName);
                break;
        }
        dialog.show(fm, ROLL_RESULT);
    }

    public void onSaveCharacterClick(){
        FragmentManager fm = getFragmentManager();
        SaveAndLoadDialog dialog = SaveAndLoadDialog.newInstance(mCurrentCharacterIndex);
        dialog.show(fm, ROLL_RESULT);
    }

    @Override
    public void onStatChange(Score score, int newValue) {
        if (mSheetPlayerCharacter.getScore(score).getScore() != newValue) {
            mSheetPlayerCharacter.getScore(score).setScore(newValue);
            mSheetPlayerCharacter.validateScores();
        }
        refreshView();
    }

    private void refreshView() {
        mMightButton.setText(Integer.toString(mSheetPlayerCharacter.
                getScore(Score.MIGHT).getScore()));
        mSkillButton.setText(Integer.toString(mSheetPlayerCharacter.
                getScore(Score.SKILL).getScore()));
        mWitsButton.setText(Integer.toString(mSheetPlayerCharacter.
                getScore(Score.WITS).getScore()));
        mLuckButton.setText(Integer.toString(mSheetPlayerCharacter.
                getScore(Score.LUCK).getScore()));
        mWillButton.setText(Integer.toString(mSheetPlayerCharacter.
                getScore(Score.WILL).getScore()));
        mGraceButton.setText(Integer.toString(mSheetPlayerCharacter.
                getScore(Score.GRACE).getScore()));
        mInitiativeButton.setText(Integer.toString(mSheetPlayerCharacter.getInitiative()));
        mAPbutton.setText(Integer.toString(mSheetPlayerCharacter.getAthleticProwess()));
        mDEbutton.setText(Integer.toString(mSheetPlayerCharacter.getDangerEvasion()));
        mMFbutton.setText(Integer.toString(mSheetPlayerCharacter.getMysticFortitude()));
        mPVbutton.setText(Integer.toString(mSheetPlayerCharacter.getPhysicalVigor()));
    }
}
