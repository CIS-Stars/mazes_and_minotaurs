package com.example.cis.mazeminotaurs.NewCharacter.dialogs;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.cis.mazeminotaurs.R;
import com.example.cis.mazeminotaurs.character.classes.Classes;
import com.example.cis.mazeminotaurs.character.classes.Magician;

import java.util.Arrays;

/**
 * Created by jsmith on 9/15/17.
 */

public class DetailDialogFragment extends DialogFragment {
    public interface DetailDialogListener {
        public void onDialogPositiveClick(DialogFragment dialogFragment);
        public void onDialogNegativeClick(DialogFragment dialogFragment);
    }
    DetailDialogListener mListener;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Bundle args = getArguments();

        LayoutInflater li = LayoutInflater.from(getContext());
        View view = li.inflate(R.layout.dialog_class_selection, null);
        // Class Name
        ((TextView) view.findViewById(R.id.class_label))
                .setText(args.getInt("class"));
        // Class Description
        ((TextView) view.findViewById(R.id.class_description))
                .setText(args.getInt("classInfo"));

        Classes selectedClass = Classes.AMAZON;
        for (Classes cClass: Classes.values()) {
            if (cClass.getResId() == args.getInt("class")) {
                selectedClass = cClass;
                break;
            }
        }

        // Populate choice weapons list.
        if (selectedClass.getJavaClass().getSuperclass() == Magician.class) {
            view.findViewById(R.id.choice_weapon_spinner).setEnabled(false);
        } else {
            // Checks if it has the possibleStartingWeapons method
        }

        Dialog dialog = new AlertDialog.Builder(getActivity())
                .setTitle("Character Details")
                .setView(view)
                .create();
        return dialog;
    }

    @Override
    // Thanks to @kuffs (https://stackoverflow.com/questions/32083053/android-fragment-onattach-deprecated)
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            //EMPTY FOR NOW
        } catch(ClassCastException e) {
            throw new ClassCastException(context.toString() + "is not a DetailDialogListener!");
        }
    }
}
