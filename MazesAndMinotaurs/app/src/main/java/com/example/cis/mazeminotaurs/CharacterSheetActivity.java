package com.example.cis.mazeminotaurs;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.cis.mazeminotaurs.character.PlayerCharacter;
import com.example.cis.mazeminotaurs.character.SaveAndLoadDialog;
import com.example.cis.mazeminotaurs.character.classes.Magician;
import com.example.cis.mazeminotaurs.character.classes.Specialist;
import com.example.cis.mazeminotaurs.character.classes.Warrior;
import com.example.cis.mazeminotaurs.character.stats.Score;
import com.example.cis.mazeminotaurs.fragments.HitsChangeFragment;
import com.example.cis.mazeminotaurs.fragments.InventoryFragment;
import com.example.cis.mazeminotaurs.fragments.StatChangeFragment;
import com.example.cis.mazeminotaurs.rollDice.rollDice;

/**
 * Created by Thorin Schmidt on 4/1/2017.
 */

public class CharacterSheetActivity extends AppCompatActivity
        implements StatChangeFragment.OnStatChangeListener, HitsChangeFragment.onHitsChangeListener{

    public static final String ROLL_RESULT = "RollResult";
    public static final String TAG = "CharacterSheetActivity";

    int mCurrentCharacterIndex;
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
    Button mEDCButton;
    Button mTotalHitsButton;
    Button mHitsButton;
    Button mAttackButton;
    Spinner mEquippedWeaponSpinner;
    Button mInitiativeButton;
    Button mAPbutton;
    Button mDEbutton;
    Button mMFbutton;
    Button mPVbutton;
    Button mTalentBonusButton;
    Button mMagicStrengthButton;
    Button mTotalPowerButton;
    Button mCurrentPowerButton;

    Button mInventoryButton;

    public CharacterSheetActivity() {
        mPortfolio = Portfolio.get();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_character_sheet);

        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mCurrentCharacterIndex = mPortfolio.getActiveCharacterIndex();
        Log.d(TAG, String.valueOf(mPortfolio.getActiveCharacterIndex()));

        mSheetPlayerCharacter = mPortfolio.getPlayerCharacter(mCurrentCharacterIndex);
        mCharacterLevelView = (TextView) findViewById(R.id.character_level_view);
        mCharacterNameView = (TextView) findViewById(R.id.character_name_view);
        mCharacterClassView = (TextView) findViewById(R.id.character_class_view);

        mMightButton = (Button) findViewById(R.id.might_score_button);
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

        mSkillButton = (Button) findViewById(R.id.skill_score_button);
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

        mWitsButton = (Button) findViewById(R.id.wits_score_button);
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

        mLuckButton = (Button) findViewById(R.id.luck_score_button);
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

        mWillButton = (Button) findViewById(R.id.will_score_button);
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

        mGraceButton = (Button) findViewById(R.id.grace_score_button);
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

        mAttackType = (TextView) findViewById(R.id.attack_title_view);
        if (mSheetPlayerCharacter.getCurrentWeapon() != null) {
            mAttackType.setText(mSheetPlayerCharacter.getCurrentWeapon().getWeaponType());
        } else {
            mAttackType.setText("-");
        }

        mAttackButton = (Button) findViewById(R.id.attack_button);
        if (mSheetPlayerCharacter.getCurrentWeapon() == null) {
            mAttackButton.setText("-");
        } else if(mSheetPlayerCharacter.getCurrentWeapon().getWeaponType() == R.string.melee) {
            mAttackButton.setText(Integer.toString(mSheetPlayerCharacter.getMeleeMod()));
        } else{
            mAttackButton.setText(Integer.toString(mSheetPlayerCharacter.getMissileMod()));
        }
        mAttackButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                onAttackClick();
            }
        });

        mEquippedWeaponSpinner = (Spinner) findViewById(R.id.equipped_weapon_spinner);
        System.out.println(mSheetPlayerCharacter.getInventory());
        DetailedWeaponAdapter weaponAdapter = new DetailedWeaponAdapter(this, mSheetPlayerCharacter.getWeapons());
        mEquippedWeaponSpinner.setAdapter(weaponAdapter);
        //Get equipped weapon from character Class
        //int equippedWeaponID = R.string.barb_axe;
        /*Weapon equippedWeapon = mSheetPlayerCharacter.getCurrentWeapon();
        if (equippedWeapon != null) {
            int equippedWeaponID = equippedWeapon.getResId();
            mEquippedWeaponSpinner.setText(equippedWeaponID);
        } else {
            mEquippedWeaponSpinner.setText("-");
        }*/

        mEquippedWeaponSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                mSheetPlayerCharacter.setCurrentWeapon((Weapon) adapterView.getItemAtPosition(i));
                populateSheet();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        mInitiativeButton = (Button) findViewById(R.id.initiative_modifier_button);
        mInitiativeButton.setText(Integer.toString(mSheetPlayerCharacter.getInitiative()));


        mAPbutton = (Button) findViewById(R.id.athletic_prowess_button);
        mAPbutton.setText(Integer.toString(mSheetPlayerCharacter.getAthleticProwess()));
        mAPbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSaveClick(mAPbutton.getId(), "AP");
            }
        });

        mDEbutton = (Button) findViewById(R.id.danger_evasion_button);
        mDEbutton.setText(Integer.toString(mSheetPlayerCharacter.getDangerEvasion()));
        mDEbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSaveClick(mDEbutton.getId(), "DE");
            }
        });

        mMFbutton = (Button) findViewById(R.id.mystic_fortitude_button);
        mMFbutton.setText(Integer.toString(mSheetPlayerCharacter.getMysticFortitude()));
        mMFbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSaveClick(mMFbutton.getId(), "MF");
            }
        });

        mPVbutton = (Button) findViewById(R.id.physical_vigor_button);
        mPVbutton.setText(Integer.toString(mSheetPlayerCharacter.getPhysicalVigor()));
        mPVbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSaveClick(mPVbutton.getId(), "PV");
            }
        });

        mEDCButton = (Button) findViewById(R.id.edc_button);
        mTotalHitsButton = (Button) findViewById(R.id.total_hits_button);

        mHitsButton = (Button) findViewById(R.id.current_hits_button);
        mHitsButton.setText(Integer.toString(mSheetPlayerCharacter.getHitTotal()));
        mHitsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onHitsClick();
            }
        });

        mInventoryButton = (Button) findViewById(R.id.inventory_button);
        mInventoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSupportFragmentManager().beginTransaction()
                        .addToBackStack("sheet")
                        .replace(R.id.content_frame, new InventoryFragment())
                        .commit();
            }
        });

        // Magical and specialist section
        mMagicTitleView = (TextView) findViewById(R.id.magic_title_view);
        mTalentBonusTitleView = (TextView) findViewById(R.id.talent_bonus_title_view);
        mMagicStrengthTitleView = (TextView) findViewById(R.id.magic_strength_title_view);
        mTotalPowerTitleView = (TextView) findViewById(R.id.total_power_title_view);
        mCurrentPowerTitleView = (TextView) findViewById(R.id.current_power_title_view);

        mTalentBonusButton = (Button) findViewById(R.id.talent_bonus_button);
        mTalentBonusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        mMagicStrengthButton = (Button) findViewById(R.id.magic_strength_button);
        mTotalPowerButton = (Button) findViewById(R.id.total_power_button);
        mCurrentPowerButton = (Button) findViewById(R.id.current_power_button);

        populateSheet();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                NavUtils.navigateUpTo(this, new Intent(this, MainMazes.class));
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onScoreClick(Score skill, String name){
        int modifier =  mSheetPlayerCharacter.getScore(skill).getModifier();
        int dieRoll = rollDice.roll(20);
        FragmentManager fm = getSupportFragmentManager();
        RollResultFragment dialog = RollResultFragment.newInstance(dieRoll, modifier, name);
        dialog.show(fm, ROLL_RESULT);
    }

    public void onScoreLongClick(Score skill, String name) {
        FragmentManager fm = getSupportFragmentManager();
        StatChangeFragment dialog = StatChangeFragment.newInstance(name,
                mSheetPlayerCharacter.getScore(skill).getScore());
        dialog.setStatChangeListener(this);
        dialog.show(fm, ROLL_RESULT);
    }

    public void onAttackClick(){
        FragmentManager fm = getSupportFragmentManager();
        AttackResultFragment dialog = AttackResultFragment.newInstance(mCurrentCharacterIndex);
        dialog.show(fm, ROLL_RESULT);
    }

    void onSaveClick(int buttonID, String saveName){
        int modifier;
        int saveRoll = rollDice.roll(20);
        FragmentManager fm = getSupportFragmentManager();
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

    void onHitsClick() {
        int curHits = mSheetPlayerCharacter.getCurHits();
        HitsChangeFragment dialog = HitsChangeFragment.newInstance(curHits);
        FragmentManager fm = getSupportFragmentManager();
        dialog.setHitsChangeListener(this);
        dialog.show(fm, ROLL_RESULT);
    }

    public void onSaveCharacterClick(){
        FragmentManager fm = getSupportFragmentManager();
        SaveAndLoadDialog dialog = SaveAndLoadDialog.newInstance(mCurrentCharacterIndex);
        dialog.show(fm, ROLL_RESULT);
    }

    @Override
    public void onStatChange(Score score, int newValue) {
        if (mSheetPlayerCharacter.getScore(score).getScore() != newValue) {
            mSheetPlayerCharacter.getScore(score).setScore(newValue);
            mSheetPlayerCharacter.validateScores();
            refreshStats();
        }
    }

    @Override
    public void onHitsChange(int newValue) {
        if (mSheetPlayerCharacter.getCurHits() != newValue) {
            mSheetPlayerCharacter.setCurHits(newValue);
            // Manually update since nothing else would change
            mHitsButton.setText(Integer.toString(mSheetPlayerCharacter.getCurHits()));
        }
    }

    private void populateSheet() {
        populateSheet(mSheetPlayerCharacter);
    }

    private void populateSheet(PlayerCharacter character) {
        // Hides specific sections(Magician and Specialist) by default
        mMagicTitleView.setVisibility(View.GONE);
        mTalentBonusTitleView.setVisibility(View.GONE);
        mMagicStrengthTitleView.setVisibility(View.GONE);
        mTotalPowerTitleView.setVisibility(View.GONE);
        mCurrentPowerTitleView.setVisibility(View.GONE);

        mTalentBonusButton.setVisibility(View.GONE);
        mMagicStrengthButton.setVisibility(View.GONE);
        mTotalPowerButton.setVisibility(View.GONE);
        mCurrentPowerButton.setVisibility(View.GONE);

        if (character != null) {
            mCharacterNameView.setText(character.getName());
            mCharacterLevelView.setText(Integer.toString(character.getCharClass().getLevel()));
            mCharacterClassView.setText(character.getCharClass().getResId());
            Weapon wepToDisplay = null;

            if (character.getCurrentWeapon() != null) {
                wepToDisplay = character.getCurrentWeapon();
            } else if (character.getWeapons() != null && character.getWeapons().size() > 0){
                wepToDisplay = character.getWeapons().get(0);
            }

            if (wepToDisplay != null) {
                DetailedWeaponAdapter adapter = (DetailedWeaponAdapter) mEquippedWeaponSpinner.getAdapter();
                mEquippedWeaponSpinner.setSelection(adapter.getDataset().indexOf(wepToDisplay));
                mAttackType.setText(wepToDisplay.getWeaponType());
                if (wepToDisplay.getWeaponType() == R.string.melee) {
                    mAttackButton.setText(Integer.toString(character.getMeleeMod()));
                } else {
                    mAttackButton.setText(Integer.toString(character.getMissileMod()));
                }
            }

            mMightButton.setText(Integer.toString(character.getScore(Score.MIGHT).getScore()));
            mWitsButton.setText(Integer.toString(character.getScore(Score.WITS).getScore()));
            mLuckButton.setText(Integer.toString(character.getScore(Score.LUCK).getScore()));
            mWillButton.setText(Integer.toString(character.getScore(Score.WILL).getScore()));
            mGraceButton.setText(Integer.toString(character.getScore(Score.GRACE).getScore()));
            mSkillButton.setText(Integer.toString(character.getScore(Score.SKILL).getScore()));
            mInitiativeButton.setText(Integer.toString(character.getInitiative()));
            mAPbutton.setText(Integer.toString(character.getAthleticProwess()));
            mDEbutton.setText(Integer.toString(character.getDangerEvasion()));
            mMFbutton.setText(Integer.toString(character.getMysticFortitude()));
            mPVbutton.setText(Integer.toString(character.getPhysicalVigor()));
            mHitsButton.setText(Integer.toString(character.getHitTotal()));

            mEDCButton.setText(Integer.toString(character.getEDC()));
            mTotalHitsButton.setText(Integer.toString(character.getHitTotal()));

            if (!(character.getCharClass() instanceof Warrior)) {
                mMagicTitleView.setVisibility(View.VISIBLE);
                if (character.getCharClass() instanceof Magician) {
                    Magician instance = (Magician) character.getCharClass();
                    mTalentBonusTitleView.setText(instance.getSpecialTalentResId());
                    mMagicStrengthButton.setText(Integer.toString(instance.getMysticalStrength()));
                    mTotalPowerButton.setText(Integer.toString(instance.getPowerPoints()));
                    mCurrentPowerButton.setText(Integer.toString(instance.getPowerPoints()));
                    mTalentBonusButton.setText(Integer.toString(instance.getSpecialTalent()));

                    mMagicStrengthTitleView.setVisibility(View.VISIBLE);
                    mTotalPowerTitleView.setVisibility(View.VISIBLE);
                    mCurrentPowerTitleView.setVisibility(View.VISIBLE);
                    mTalentBonusTitleView.setVisibility(View.VISIBLE);
                    mMagicStrengthButton.setVisibility(View.VISIBLE);
                    mTotalPowerButton.setVisibility(View.VISIBLE);
                    mCurrentPowerButton.setVisibility(View.VISIBLE);
                    mTalentBonusButton.setVisibility(View.VISIBLE);
                }
                if (character.getCharClass() instanceof Specialist) {
                    mTalentBonusTitleView.setText(((Specialist) character.getCharClass()).getSpecialScoreId());
                    mTalentBonusButton.setText(Integer.toString(((Specialist) character.getCharClass()).getSpecialTalent()));

                    mTalentBonusTitleView.setVisibility(View.VISIBLE);
                    mTalentBonusButton.setVisibility(View.VISIBLE);
                }
            }
        }
    }

    /**
     * Resets all of the components that could be changed by an attribute edit.
     */
    private void refreshStats() {
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
        if(mSheetPlayerCharacter.getCurrentWeapon().getWeaponType() == R.string.melee) {
            mAttackButton.setText(Integer.toString(mSheetPlayerCharacter.getMeleeMod()));
        }
        else{
            mAttackButton.setText(Integer.toString(mSheetPlayerCharacter.getMissileMod()));
        }
    }
}
