package com.example.cis.mazeminotaurs.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
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

    private Button mLevelButton;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        LayoutInflater li = LayoutInflater.from(getContext());
        View rootView = li.inflate(R.layout.dialog_experience_manage, null);

        final Bundle args = getArguments();
        final PlayerCharacter playerCharacter = Portfolio.get()
                .getPlayerCharacter(args.getInt(CommonStrings.CHARACTER_ARG.getValue()));
        // Obtain the class of the character that has been selected.
        mCharClass = playerCharacter.getCharClass();

        // Initialize the references for every widget. (Alphabetically in order)
        mAddButton = (Button) rootView.findViewById(R.id.add_experience_button);
        mCurrentExperience = (TextView) rootView.findViewById(R.id.experience_text_view);
        mCurrentLevel = (TextView) rootView.findViewById(R.id.actual_level_text_view);
        mEffectiveLevel = (TextView) rootView.findViewById(R.id.expected_level_text_view);
        mExperienceEdit = (EditText) rootView.findViewById(R.id.add_experience_edit_text);
        mLevelButton = (Button) rootView.findViewById(R.id.level_up_experience_button);


        mCurrentLevel.setText(String.valueOf(mCharClass.getLevel()));
        updateGUI();

        // Adds the experience in the EditText widget into the class' experience.
        mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String value = mExperienceEdit.getText().toString();
                // Stops any empty value from crashing the program.
                if (value != null && !value.equals("")) {
                    mCharClass.addExperience(Integer.valueOf(value));
                    updateGUI();
                }
            }
        });

        // Opens up the level up dialog.
        mLevelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LevelStatSelectDialogFragment dialog = new LevelStatSelectDialogFragment();
                dialog.setArguments(args);

                FragmentManager fm = getFragmentManager();

                dismiss();
                dialog.show(fm, TAG);
            }
        });

        return new AlertDialog.Builder(getContext())
                .setTitle("Manage Experience")
                .setView(rootView)
                .setNegativeButton(R.string.cancel, null)
                .create();
    }

    private void updateGUI() {
        // Update the effective level of the character
        mCharClass.updateLevel();

        // Update the text of the dialog.
        mCurrentExperience.setText(String.valueOf(mCharClass.getExperience()));
        mEffectiveLevel.setText(String.valueOf(mCharClass.getEffectiveLevel()));
        mExperienceEdit.setText(null);

        // If the character is able to level up, enable the button
        mLevelButton.setEnabled(mCharClass.getLevel() < mCharClass.getEffectiveLevel());
    }

    public ChangeListener getListener() {
        return mListener;
    }

    public void setListener(ChangeListener listener) {
        mListener = listener;
    }
}
