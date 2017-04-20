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

public class SaveResultFragment extends DialogFragment {

    int mMod;
    int mRoll;
    String mSaveName;
    int mTotal;

    static SaveResultFragment newInstance(int roll, int mod, String nameID) {
        SaveResultFragment f = new SaveResultFragment();

        // Supply num input as an argument.
        Bundle args = new Bundle();
        args.putInt("roll", roll);
        args.putInt("mod", mod);
        args.putString("name", nameID);
        f.setArguments(args);

        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        mRoll = getArguments().getInt("roll");
        mMod = getArguments().getInt("mod");
        mSaveName = getArguments().getString("name");
        mTotal = mRoll + mMod;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.dialog_roll_result, container, false);
        View tv = v.findViewById(R.id.die_roll_view);
        ((TextView) tv).setText(mSaveName + " Roll: " + mRoll + " + " + mMod + " = " +
                + mTotal);

        return v;
    }

}
