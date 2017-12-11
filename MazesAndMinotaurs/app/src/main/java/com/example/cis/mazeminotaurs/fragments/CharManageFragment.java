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
 * This fragments displays a list of all of the created player characters. The user
 * can select these characters with a click and choose to make them the active
 * character or delete them.
 *
 * @author jsmith on 11/16/17.
 */

public class CharManageFragment extends DialogFragment {
    /**
     * Serves as the TAG in certain functions. e.g. dialog.show() and logging.
     */
    public static final String TAG = CharManageFragment.class.getName();

    /**
     * This is meant to be implemented by the parent that opened the dialog.
     */
    public interface ManagementListener {
        /**
         * This is fired when a character is chosen to be the active character.
         *
         * @param i the character's index in the portfolio
         */
        void onSelect(int i);

        /**
         * This is fired when a character is chosen to be deleted.
         * @param i the character's index in the portfolio
         */
        void onDelete(int i);
    }

    /**
     * This is intended to be the parent that created the dialog. The parent must
     * call the setter for this field with itself as the argument.
     */
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

    /**
     * Getter for the mListener property.
     * @return the value of mListener.
     */
    public ManagementListener getListener() {
        return mListener;
    }

    /**
     * Setter for the mListener property.
     * @param listener the new listener value for mListener.
     */
    public void setListener(ManagementListener listener) {
        mListener = listener;
    }
}
