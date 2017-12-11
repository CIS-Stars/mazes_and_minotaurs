package com.example.cis.mazeminotaurs.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.cis.mazeminotaurs.Portfolio;
import com.example.cis.mazeminotaurs.R;
import com.example.cis.mazeminotaurs.character.PlayerCharacter;
import com.example.cis.mazeminotaurs.character.classes.BaseClass;
import com.example.cis.mazeminotaurs.character.stats.Score;
import com.example.cis.mazeminotaurs.util.CommonStrings;

/**
 * This fragment displays a list of scores that can be selected on level up.
 * The user can select one of the scores and continue onto the next dialog in the cycle.
 *
 * @see ExperienceDialogFragment
 * @author jsmith on 12/6/17.
 */

public class LevelStatSelectDialogFragment extends DialogFragment {
    /**
     * Serves as the TAG in certain functions. e.g. dialog.show() and logging.
     */
    public static final String TAG = LevelStatSelectDialogFragment.class.getName();

    /**
     * The root dialog.
     *
     * @see ExperienceDialogFragment
     */
    private ExperienceDialogFragment mRootDialog;

    /**
     * This is the class of the character that is being leveled up.
     */
    private BaseClass mCharClass;

    /**
     * The score selected to improve on level up.
     */
    private Score mSelectedScore;

    /*
     * These are the widgets found in the layout.
     */
    private ListView mScoreListView;
    private TextView mSelectedScoreTextView;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        LayoutInflater li = LayoutInflater.from(getContext());
        View rootView = li.inflate(R.layout.dialog_stat_select, null);

        Bundle args = getArguments();
        final PlayerCharacter playerCharacter = Portfolio.get()
                .getPlayerCharacter(args.getInt(CommonStrings.CHARACTER_ARG.getValue()));

        mCharClass = playerCharacter.getCharClass();

        mScoreListView = (ListView) rootView.findViewById(R.id.select_stat_list_view);
        mSelectedScoreTextView = (TextView) rootView.findViewById(R.id.selected_stat_text_view);

        // Because nothing is selected, display a hyphen for nothing.
        mSelectedScoreTextView.setText("-");

        // Updates the selected score and displays it
        mScoreListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                mSelectedScore = (Score) adapterView.getItemAtPosition(i);
                mSelectedScoreTextView.setText(mSelectedScore.getResId());
            }
        });

        // Instantiate a container for our items.
        ArrayAdapter<Score> adapter = new ArrayAdapter<>(getContext(),
                R.layout.support_simple_spinner_dropdown_item);

        // Validate the items
        // If we can't go higher on the score, then we probably shouldn't use it.
        for (Score score : mCharClass.getPossibleLevelScores()) {
            if (playerCharacter.canAddToScore(score)) {
                adapter.addAll(score);
            }
        }

        // Add the items to the list view.
        mScoreListView.setAdapter(adapter);

        return new AlertDialog.Builder(getContext())
                .setTitle("Level Up - Select Stat")
                .setView(rootView)
                .setPositiveButton(R.string.confirm_button, getOnPositiveClick())
                .setNegativeButton(R.string.cancel, null)
                .create();
    }

    /**
     * This is a helper method for creating the onclick listener for the positive
     * button. The listener will make sure that mSelectedScore has a value and pass
     * it onto the next dialog along with mRootDialog.
     *
     * @return The onclick listener for positive button.
     */
    private DialogInterface.OnClickListener getOnPositiveClick() {
        return new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (mSelectedScore != null) {
                    dismiss();
                    LevelResultDialogFragment dialog = new LevelResultDialogFragment();

                    Bundle args = getArguments();
                    args.putSerializable(CommonStrings.SCORE_ARG.getValue(), mSelectedScore);

                    dialog.setRootDialog(getRootDialog());
                    dialog.setArguments(getArguments());

                    FragmentManager fm = getFragmentManager();

                    dialog.show(fm, LevelResultDialogFragment.TAG);
                }
            }
        };
    }

    /**
     * Getter for the mRootDialog property.
     * @return the value of mRootDialog.
     */
    public ExperienceDialogFragment getRootDialog() {
        return mRootDialog;
    }

    /**
     * Setter for the mRootDialog property.
     * @param rootDialog the new value of mRootDialog.
     */
    public void setRootDialog(ExperienceDialogFragment rootDialog) {
        mRootDialog = rootDialog;
    }
}
