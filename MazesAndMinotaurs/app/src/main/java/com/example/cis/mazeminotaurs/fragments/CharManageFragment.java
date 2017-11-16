package com.example.cis.mazeminotaurs.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.cis.mazeminotaurs.Portfolio;
import com.example.cis.mazeminotaurs.R;
import com.example.cis.mazeminotaurs.character.PlayerCharacter;
import com.example.cis.mazeminotaurs.util.CommonStrings;

/**
 * Created by JayTSmith on 11/16/17.
 */

public class CharManageFragment extends DialogFragment {
    public static final String TAG = CharManageFragment.class.getName();

    public interface ManagementListener {
        void onSelect(int i);

        void onDelete(int i);
    }

    public ManagementListener mListener;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        LayoutInflater li = LayoutInflater.from(getContext());
        View rootView = li.inflate(R.layout.dialog_manage_character, null);

        Bundle args = getArguments();
        final PlayerCharacter playerCharacter = (PlayerCharacter) args.get(CommonStrings.CHARACTER_ARG.getValue());
        final int charIndex = Portfolio.get().getPortfolio().indexOf(playerCharacter);

        String nameString = getContext().getString(R.string.name_view, playerCharacter.getName());
        String levelString = getContext().getString(R.string.level_view, playerCharacter.getCharClass().getLevel());
        String classString = getContext().getString(R.string.class_view, getContext().getString(playerCharacter.getCharClass().getResId()));

        TextView charName = (TextView) rootView.findViewById(R.id.manage_name_view);
        TextView levelName = (TextView) rootView.findViewById(R.id.manage_level_view);
        TextView className = (TextView) rootView.findViewById(R.id.manage_class_view);

        Button selectButton = (Button) rootView.findViewById(R.id.manage_select_button);
        Button deleteButton = (Button) rootView.findViewById(R.id.manage_delete_button);

        charName.setText(nameString);
        levelName.setText(levelString);
        className.setText(classString);

        // Listeners
        selectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onSelect(charIndex);
                dismiss();
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onDelete(charIndex);
                dismiss();
            }
        });

        return new AlertDialog.Builder(getContext())
                .setTitle(R.string.manage_character)
                .setView(rootView)
                .create();
    }

    public ManagementListener getListener() {
        return mListener;
    }

    public void setListener(ManagementListener listener) {
        mListener = listener;
    }
}
