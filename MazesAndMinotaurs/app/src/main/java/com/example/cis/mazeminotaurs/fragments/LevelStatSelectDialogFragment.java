package com.example.cis.mazeminotaurs.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.cis.mazeminotaurs.Portfolio;
import com.example.cis.mazeminotaurs.R;
import com.example.cis.mazeminotaurs.character.PlayerCharacter;
import com.example.cis.mazeminotaurs.character.classes.BaseClass;
import com.example.cis.mazeminotaurs.character.stats.Score;
import com.example.cis.mazeminotaurs.util.CommonStrings;

/**
 * Created by JayTSmith on 12/6/17.
 */

public class LevelStatSelectDialogFragment extends DialogFragment {
    public static final String TAG = LevelStatSelectDialogFragment.class.getName();

    private BaseClass mCharClass;
    private Score mSelectedScore;

    private ListView mListView;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        LayoutInflater li = LayoutInflater.from(getContext());
        View rootView = li.inflate(R.layout.dialog_stat_select, null);

        Bundle args = getArguments();
        final PlayerCharacter playerCharacter = Portfolio.get()
                .getPlayerCharacter(args.getInt(CommonStrings.CHARACTER_ARG.getValue()));

        mCharClass = playerCharacter.getCharClass();

        mListView = (ListView) rootView.findViewById(R.id.select_stat_list_view);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                mSelectedScore = (Score) adapterView.getItemAtPosition(i);
            }
        });

        ArrayAdapter<Score> adapter = new ArrayAdapter<>(getContext(),
                R.layout.support_simple_spinner_dropdown_item);

        // Validate the items
        // If we can't go higher on the score, then we probably shouldn't use it.
        for (Score score : mCharClass.getPossibleLevelScores()) {
            if (playerCharacter.canAddToScore(score)) {
                adapter.addAll(score);
            }
        }

        mListView.setAdapter(adapter);

        return new AlertDialog.Builder(getContext())
                .setTitle("Level Up - Select Stat")
                .setView(rootView)
                .setPositiveButton(R.string.confirm_button, getOnPositiveClick())
                .setNegativeButton(R.string.cancel, null)
                .create();
    }

    private DialogInterface.OnClickListener getOnPositiveClick() {
        return new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (mSelectedScore != null) {
                    dismiss();
                }
                //TODO Implement next step.
                // dialog = new AlertDialog.Builder(getContext())
                //             .create();
            }
        };
    }

}
