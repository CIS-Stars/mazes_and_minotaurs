package com.example.cis.mazeminotaurs.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
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
    public interface SelectedListener {
        void onStatChosenChange();
    }

    public static final String TAG = LevelStatSelectDialogFragment.class.getName();

    private SelectedListener mListener;

    private BaseClass mCharClass;

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
        ArrayAdapter<Score> adapter = new ArrayAdapter<>(getContext(),
                R.layout.support_simple_spinner_dropdown_item);
        for (Score score : mCharClass.getPossibleLevelScores()) {
            if (playerCharacter.canAddToScore(score)) {
                adapter.addAll(score);
            }
        }
        mListView.setAdapter(adapter);

        return new AlertDialog.Builder(getContext())
                .setTitle("Level Up - Select Stat")
                .setView(rootView)
                .setNegativeButton(R.string.cancel, null)
                .create();
    }
}
