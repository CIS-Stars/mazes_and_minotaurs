package com.example.cis.mazeminotaurs;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.cis.mazeminotaurs.character.PlayerCharacter;
import com.example.cis.mazeminotaurs.rollDice.rollDice;

/**
 * This fragment displays the results of an attack roll and damage roll of the
 * equipped weapon.
 * The user can also click to get a second damage roll for critical damage.
 * @author Thorin Schmidt on 4/13/2017.
 */

public class AttackResultFragment extends DialogFragment {

    /**
     * The index of the character in the Portfolio singleton.
     */
    int mCurrentCharacterIndex;

    /**
     * The list of all of the player's characters.
     */
    Portfolio mPortfolio;

    /**
     * The character that is currently being used.
     */
    PlayerCharacter mCurrentCharacter;

    /**
     * The score modifier being used, based on weapon type.
     */
    int mMod;

    /**
     * The first attack roll, based on D20 values.
     */
    int mAttackRoll1;

    /**
     * The second attack roll, based on D20 values. Used for special situations.
     */
    int mAttackRoll2;

    /**
     * The first damage roll, based on the weapon's damageDie.
     */
    int mDamage1;

    /**
     * The second damgage roll, based on the weapon's damageDie. Used for special
     * situations.
     */
    int mDamage2;

    /**
     * The type of attack being done. (Melee or Ranged)
     */
    String mAttackType;

    /**
     * The sum of the attack roll and modifier. (mAttack1 and mMod)
     */
    int mTotal1;

    /**
     * The sum of the attack roll and modifier. (mAttack2 and mMod)
     */
    int mTotal2;

    /*
     * These are widgets found in the layout.
     */
    Button mCritButton;

    static AttackResultFragment newInstance(int characterIndex) {
        AttackResultFragment f = new AttackResultFragment();

        // Supply num input as an argument.
        Bundle args = new Bundle();
        args.putInt("index", characterIndex);

        f.setArguments(args);

        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        mCurrentCharacterIndex = getArguments().getInt("index");
        mPortfolio = Portfolio.get();
        mCurrentCharacter = mPortfolio.getPlayerCharacter(mCurrentCharacterIndex);

        if(mCurrentCharacter.getCurrentWeapon().getWeaponType() == R.string.melee){
            mMod = mCurrentCharacter.getMeleeMod();
        }
        else if(mCurrentCharacter.getCurrentWeapon().getWeaponType() == R.string.missile){
            mMod = mCurrentCharacter.getMissileMod();
        }
        else {//this should never happen
            mMod = -10;
        }
        mAttackType = getString(mCurrentCharacter.getCurrentWeapon().getWeaponType());
        mAttackRoll1 = rollDice.roll(20);
        mAttackRoll2 = rollDice.roll(20);
        mDamage1 = rollDice.roll(mCurrentCharacter.getCurrentWeapon().getDamageDie());
        mDamage2 = rollDice.roll(mCurrentCharacter.getCurrentWeapon().getDamageDie());
        mTotal1 = mAttackRoll1 + mMod;
        mTotal2 = mAttackRoll2 + mMod;


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.dialog_attack_result, container, false);
        View attackView1 = v.findViewById(R.id.attack_roll_1_view);
        View attackView2 = v.findViewById(R.id.attack_roll_2_view);
        View damageView1 = v.findViewById(R.id.damage_roll_1_view);
        final View damageView2 = v.findViewById(R.id.damage_roll_2_view);



        ((TextView) attackView1).setText(mAttackType + " Roll: " + mAttackRoll1 +
                " + " + mMod + " = " + mTotal1);
        ((TextView) attackView2).setText(mAttackType + " Roll: " + mAttackRoll2 +
                " + " + mMod + " = " + mTotal2);
        ((TextView) damageView1).setText("Damage: " + mDamage1);
        ((TextView) damageView2).setText("Damage: " + mDamage2);

        if (mCurrentCharacter.isWeaponOfChoiceEquipped()) {
            (attackView2).setVisibility(View.VISIBLE);
        }
        else{
            ((TextView) attackView2).setVisibility(View.GONE);
        }

        mCritButton = (Button) v.findViewById(R.id.critical_damage_button);
        mCritButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                damageView2.setVisibility(View.VISIBLE);
                mCritButton.setVisibility(View.GONE);
            }
        });

        return v;
    }

}
