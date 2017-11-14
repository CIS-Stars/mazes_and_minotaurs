package com.example.cis.mazeminotaurs.NewCharacter.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.cis.mazeminotaurs.R;
import com.example.cis.mazeminotaurs.character.classes.BaseClass;
import com.example.cis.mazeminotaurs.character.stats.Score;
import com.example.cis.mazeminotaurs.util.CommonStrings;

/**
 * Created by JayTSmith on 11/9/17.
 */

public class AttributePriorityDialog extends DialogFragment {
    private static final String TAG = AttributePriorityDialog.class.getName();

    Spinner[] mSpinners;
    private static final int[] SPINNER_IDS = new int[]{R.id.first_attribute_spinner,
            R.id.second_attribute_spinner, R.id.third_attribute_spinner,
            R.id.fourth_attribute_spinner, R.id.fifth_attribute_spinner,
            R.id.sixth_attribute_spinner};

    public interface PriorityListener {
        void onDialogPositiveClick(Score[] priorities);
    }

    PriorityListener mListener;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        LayoutInflater li = LayoutInflater.from(getContext());
        View rootView = li.inflate(R.layout.dialog_attribute_priority, null);

        Bundle args = getArguments();
        BaseClass baseClass = (BaseClass) args.get(CommonStrings.CHARACTER_ARG.getValue());

        // Set up two adapters: one for primary attributes and the other for the left over attributes.
        ArrayAdapter<Score> primAdapter = new ArrayAdapter<>(getContext(),
                R.layout.support_simple_spinner_dropdown_item);
        ArrayAdapter<Score> otherAdapter = new ArrayAdapter<>(getContext(),
                R.layout.support_simple_spinner_dropdown_item);

        // Set up the list of the two adapters
        for (Score score : (Score[]) args.getSerializable(CommonStrings.ATTR_PRIORITY_ARGS.getValue())) {
            if (baseClass.getPrimaryAttributes().contains(score)) {
                primAdapter.add(score);
            } else {
                otherAdapter.add(score);
            }
        }

        mSpinners = new Spinner[6];

        for (int i = 0; i < SPINNER_IDS.length; i++) {
            mSpinners[i] = (Spinner) rootView.findViewById(SPINNER_IDS[i]);
            if (i < 2) {
                mSpinners[i].setAdapter(primAdapter);
                mSpinners[i].setSelection(i);
            } else {
                mSpinners[i].setAdapter(otherAdapter);
                mSpinners[i].setSelection(i - 2);
            }
        }

        Dialog dialog = new AlertDialog.Builder(getActivity())
                .setPositiveButton(R.string.confirm_button, getOnClickPositiveButton())
                .setNegativeButton(R.string.cancel, null)
                .setView(rootView)
                .setTitle(R.string.attribute_priority)
                .create();
        return dialog;
    }

    private Score[] getPriorities() {
        Score[] priorities = new Score[6];
        for (int i = 0; i < 6; i++) {
            priorities[i] = (Score) mSpinners[i].getSelectedItem();
        }
        return priorities;
    }

    private DialogInterface.OnClickListener getOnClickPositiveButton() {
        return new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                mListener.onDialogPositiveClick(getPriorities());
            }
        };
    }

    public PriorityListener getListener() {
        return mListener;
    }

    public void setListener(PriorityListener listener) {
        mListener = listener;
    }
}
