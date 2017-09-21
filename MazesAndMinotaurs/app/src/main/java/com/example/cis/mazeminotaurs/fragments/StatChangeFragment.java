package com.example.cis.mazeminotaurs.fragments;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.cis.mazeminotaurs.R;
import com.example.cis.mazeminotaurs.character.stats.Score;

/**
 * Created by jsmith on 9/19/17.
 *
 * Variant of Thorin Schmidt's RollResultFragment
 */

public class StatChangeFragment extends DialogFragment {
    public interface OnStatChangeListener {
        void onStatChange(Score score, int newValue);
    }
    OnStatChangeListener mStatChangeListener;

    String mStringScore;
    Score mScore;
    int mOldValue;

    EditText mNewValue;
    /**
     * Create a new instance of StatChangeFragment, providing "score"
     * and "value" as arguments.
     */
    static StatChangeFragment newInstance(String score, int value) {
        StatChangeFragment f = new StatChangeFragment();

        // Supply num input as an argument.
        Bundle args = new Bundle();
        args.putString("stat", score);
        args.putInt("value", value);
        f.setArguments(args);

        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        mStringScore = getArguments().getString("stat");
        if (mStringScore != null) {
            switch (mStringScore) {
                case "Might":
                    mScore = Score.MIGHT;
                    break;
                case "Grace":
                    mScore = Score.GRACE;
                    break;
                case "Skill":
                    mScore = Score.SKILL;
                    break;
                case "Will":
                    mScore = Score.WILL;
                    break;
                case "Wits":
                    mScore = Score.WITS;
                    break;
                case "Luck":
                    mScore = Score.LUCK;
                    break;
            }
        }
        mOldValue = getArguments().getInt("value");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.dialog_attribute_edit, container, false);
        View oldTv = v.findViewById(R.id.attribute_old_base);
        ((TextView) oldTv).setText(getContext().getString(R.string.old_attribute_value, mStringScore, mOldValue));
        mNewValue = (EditText) v.findViewById(R.id.attribute_new_base_edit);
        v.findViewById(R.id.attribute_confirm_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!mNewValue.getText().toString().trim().equals("")) {
                    int newValue = Integer.valueOf(mNewValue.getText().toString());
                    getStatChangeListener().onStatChange(mScore, newValue);
                }
                dismiss();
            }
        });

        return v;

    }

    public OnStatChangeListener getStatChangeListener() {
        return this.mStatChangeListener;
    }

    public void setStatChangeListener(OnStatChangeListener statChangeListener) {
        this.mStatChangeListener = statChangeListener;
    }
}