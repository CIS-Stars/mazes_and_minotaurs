package com.example.cis.mazeminotaurs;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Thorin Schmidt on 4/13/2017.
 */

public class AttackResultFragment extends DialogFragment {

    int mMod;
    int mAttackRoll1;
    int mAttackRoll2;
    int mDamage1;
    int mDamage2;
    boolean mWOCequipped;
    String mTypeName;
    int mTotal1;
    int mTotal2;

    /**
     * Create a new instance of RollResultFragment, providing "roll"
     * and "mod" as arguments.
     */
    static AttackResultFragment newInstance(int roll1, int roll2, int damage1,
                                            int damage2, int mod, String name,
                                            boolean wocEquipped) {
        AttackResultFragment f = new AttackResultFragment();

        // Supply num input as an argument.
        Bundle args = new Bundle();
        args.putInt("roll1", roll1);
        args.putInt("roll2", roll2);
        args.putInt("damage1", damage1);
        args.putInt("damage2", damage2);
        args.putInt("mod", mod);
        args.putString("name", name);
        args.putBoolean("woc", wocEquipped);
        f.setArguments(args);

        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        mAttackRoll1 = getArguments().getInt("roll1");
        mAttackRoll2 = getArguments().getInt("roll2");
        mDamage1 = getArguments().getInt("damage1");
        mDamage2 = getArguments().getInt("damage2");
        mMod = getArguments().getInt("mod");
        mTypeName = getArguments().getString("name");
        mWOCequipped = getArguments().getBoolean("woc");
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
        View damageView2 = v.findViewById(R.id.damage_roll_2_view);


        ((TextView) attackView1).setText(mTypeName + " Roll: " + mAttackRoll1 + " + " + mMod + " = " +
                + mTotal1);
        ((TextView) attackView2).setText(mTypeName + " Roll: " + mAttackRoll2 + " + " + mMod + " = " +
                +mTotal2);
        ((TextView) damageView1).setText("Damage: " + mDamage1);
        ((TextView) damageView2).setText("Damage: " + mDamage2);

        if (mWOCequipped) {
            ((TextView) attackView2).setVisibility(View.VISIBLE);
        }
        else{
            ((TextView) attackView2).setVisibility(View.GONE);
        }

        return v;
    }

}
