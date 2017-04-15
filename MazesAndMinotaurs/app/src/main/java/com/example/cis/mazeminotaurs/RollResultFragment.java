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

public class RollResultFragment extends DialogFragment {

    int mMod;
    int mRoll;
    String mAttributeName;
    int mTotal;

    /**
     * Create a new instance of RollResultFragment, providing "roll"
     * and "mod" as arguments.
     */
    static RollResultFragment newInstance(int roll, int mod, String name) {
        RollResultFragment f = new RollResultFragment();

        // Supply num input as an argument.
        Bundle args = new Bundle();
        args.putInt("roll", roll);
        args.putInt("mod", mod);
        args.putString("name", name);
        f.setArguments(args);

        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        mRoll = getArguments().getInt("roll");
        mMod = getArguments().getInt("mod");
        mAttributeName = getArguments().getString("name");
        mTotal = mRoll + mMod;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.dialog_roll_result, container, false);
        View tv = v.findViewById(R.id.die_roll_view);
        ((TextView) tv).setText(mAttributeName + " Roll: " + mRoll + " + " + mMod + " = " +
                + mTotal);

        return v;
    }

}
