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
 * This fragment displays the new and old value of a character's score.
 * The user can set the new value of that score in this dialog.
 *
 * Variant of Thorin Schmidt's RollResultFragment
 * @author jsmith on 9/19/17.
 */

public class StatChangeFragment extends DialogFragment {
    /**
     * This is meant to be implemented by the parent that opened the dialog.
     */
    public interface OnStatChangeListener {
        /**
         * This is fired when the user inputs in a value into the dialog.
         *
         * @param score    The score to change.
         * @param newValue The new value of the score.
         */
        void onStatChange(Score score, int newValue);
    }

    /**
     * This is intended to be the parent that created the dialog. The parent must
     * call the setter for this field with itself as the argument.
     */
    OnStatChangeListener mStatChangeListener;

    /**
     * The string version of the score. Used to find the value of mScore through
     * comparison.
     */
    String mStringScore;

    /**
     * The score that is being modified.
     */
    Score mScore;

    /**
     * The value of the score that is being modified.
     */
    int mOldValue;

    /*
     * These are the widgets found in the layout.
     */
    EditText mNewValue;

    /**
     * Create a new instance of StatChangeFragment, providing "score"
     * and "value" as arguments.
     */
    public static StatChangeFragment newInstance(String score, int value) {
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

    /**
     * Getter for the mStatChangeListener property.
     * @return the value of mStatChangeListener.
     */
    public OnStatChangeListener getStatChangeListener() {
        return this.mStatChangeListener;
    }

    /**
     * Setter for the mStatChangeListener property.
     * @param statChangeListener the new value of mStatChangeListener.
     */
    public void setStatChangeListener(OnStatChangeListener statChangeListener) {
        this.mStatChangeListener = statChangeListener;
    }
}