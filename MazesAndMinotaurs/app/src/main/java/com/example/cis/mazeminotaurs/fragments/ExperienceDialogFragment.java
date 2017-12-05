package com.example.cis.mazeminotaurs.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.cis.mazeminotaurs.Portfolio;
import com.example.cis.mazeminotaurs.R;
import com.example.cis.mazeminotaurs.character.PlayerCharacter;
import com.example.cis.mazeminotaurs.character.classes.BaseClass;
import com.example.cis.mazeminotaurs.util.CommonStrings;

/**
 * Created by JayTSmith on 12/5/17.
 */

public class ExperienceDialogFragment extends DialogFragment {
    public interface ChangeListener {
        void onExperienceChange();

        void onLevelChange();
    }

    public static final String TAG = ExperienceDialogFragment.class.getName();

    private ChangeListener mListener;

    private BaseClass mCharClass;

    private TextView mCurrentExperience;
    private TextView mCurrentLevel;
    private TextView mEffectiveLevel;

    private EditText mExperienceEdit;
    private Button mAddButton;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        LayoutInflater li = LayoutInflater.from(getContext());
        View rootView = li.inflate(R.layout.dialog_experience_manage, null);

        Bundle args = getArguments();
        final PlayerCharacter playerCharacter = Portfolio.get()
                .getPlayerCharacter(args.getInt(CommonStrings.CHARACTER_ARG.getValue()));

        mCharClass = playerCharacter.getCharClass();

        mAddButton = (Button) rootView.findViewById(R.id.add_experience_button);
        mCurrentExperience = (TextView) rootView.findViewById(R.id.experience_text_view);
        mCurrentLevel = (TextView) rootView.findViewById(R.id.actual_level_text_view);
        mEffectiveLevel = (TextView) rootView.findViewById(R.id.expected_level_text_view);
        mExperienceEdit = (EditText) rootView.findViewById(R.id.add_experience_edit_text);


        mCurrentExperience.setText(String.valueOf(mCharClass.getExperience()));
        mCurrentLevel.setText(String.valueOf(mCharClass.getLevel()));
        mEffectiveLevel.setText(String.valueOf(mCharClass.getEffectiveLevel()));

        mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String value = mExperienceEdit.getText().toString();
                if (value != null && !value.equals("")) {
                    mCharClass.addExperience(Integer.valueOf(value));
                    updateGUI();
                }
            }
        });

        return new AlertDialog.Builder(getContext())
                .setTitle("Manage Experience")
                .setView(rootView)
                .setNegativeButton(R.string.cancel, null)
                .create();
    }

    private void updateGUI() {
        mCharClass.updateLevel();
        mCurrentExperience.setText(String.valueOf(mCharClass.getExperience()));
        mEffectiveLevel.setText(String.valueOf(mCharClass.getEffectiveLevel()));
        mExperienceEdit.setText(null);
    }

    public ChangeListener getListener() {
        return mListener;
    }

    public void setListener(ChangeListener listener) {
        mListener = listener;
    }
}
