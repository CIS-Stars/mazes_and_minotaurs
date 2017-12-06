package com.example.cis.mazeminotaurs.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.cis.mazeminotaurs.Portfolio;
import com.example.cis.mazeminotaurs.R;
import com.example.cis.mazeminotaurs.character.PlayerCharacter;
import com.example.cis.mazeminotaurs.character.classes.BaseClass;
import com.example.cis.mazeminotaurs.character.stats.Score;
import com.example.cis.mazeminotaurs.util.CommonStrings;

import java.util.Locale;

/**
 * Created by JayTSmith on 12/6/17.
 */

public class LevelResultDialogFragment extends DialogFragment {
    public static final String TAG = LevelStatSelectDialogFragment.class.getName();

    private ExperienceDialogFragment mRootDialog;

    private BaseClass mCharClass;
    private Score mSelectedScore;

    private TextView mLuckAddTextView;
    private TextView mHitAddTextView;
    private TextView mScoreHeadTextView;
    private TextView mScoreAddTextView;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        LayoutInflater li = LayoutInflater.from(getContext());
        View rootView = li.inflate(R.layout.dialog_level_result, null);

        Bundle args = getArguments();
        final PlayerCharacter playerCharacter = Portfolio.get()
                .getPlayerCharacter(args.getInt(CommonStrings.CHARACTER_ARG.getValue()));
        // Obtain the class of the character that has been selected.
        mCharClass = playerCharacter.getCharClass();
        mSelectedScore = (Score) args.get(CommonStrings.SCORE_ARG.getValue());

        // Initialize the references for every widget.
        mLuckAddTextView = (TextView) rootView.findViewById(R.id.result_luck_text_view);
        mHitAddTextView = (TextView) rootView.findViewById(R.id.result_hits_text_view);
        mScoreHeadTextView = (TextView) rootView.findViewById(R.id.result_score_head_text_view);
        mScoreAddTextView = (TextView) rootView.findViewById(R.id.result_score_value_text_view);

        // The format of each string (decimal number with sign)
        String baseFormat = "%+d";

        // Set the text of each widget.
        mLuckAddTextView.setText(String.format(Locale.getDefault(), baseFormat, BaseClass.LUCK_IMPROVE));
        mHitAddTextView.setText(String.format(Locale.getDefault(), baseFormat, mCharClass.getHitsImprove()));
        // TODO Add a colon seperated resource string for here.
        mScoreHeadTextView.setText(mSelectedScore.getResId());
        mScoreAddTextView.setText(String.format(Locale.getDefault(), baseFormat, 2));

        return new AlertDialog.Builder(getContext())
                .setTitle("Level Up Results")
                .setView(rootView)
                .setPositiveButton(R.string.confirm_button, getOnPositiveListener()) // TODO level up the character here.
                .setNegativeButton(R.string.cancel, null)
                .create();
    }

    private DialogInterface.OnClickListener getOnPositiveListener() {
        return new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                mRootDialog.getListener().onLevelChange();
            }
        };
    }

    public ExperienceDialogFragment getRootDialog() {
        return mRootDialog;
    }

    public void setRootDialog(ExperienceDialogFragment rootDialog) {
        mRootDialog = rootDialog;
    }
}
