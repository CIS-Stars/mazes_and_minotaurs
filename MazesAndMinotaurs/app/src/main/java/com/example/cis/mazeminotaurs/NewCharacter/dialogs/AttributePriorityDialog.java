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
 * This fragment displays the character's primary and secondary attributes in two
 * different lists.
 * The user can set the priority of each attribute for score generation.
 *
 * @author jsmith on 11/9/17.
 */

public class AttributePriorityDialog extends DialogFragment {
    /**
     * Serves as the TAG in certain functions. e.g. dialog.show() and logging.
     */
    private static final String TAG = AttributePriorityDialog.class.getName();

    /**
     * The resource ids of every spinner in the layout.
     */
    private static final int[] SPINNER_IDS = new int[]{R.id.first_attribute_spinner,
            R.id.second_attribute_spinner, R.id.third_attribute_spinner,
            R.id.fourth_attribute_spinner, R.id.fifth_attribute_spinner,
            R.id.sixth_attribute_spinner};

    /**
     * This is meant to be implemented by the parent that opened the dialog.
     */
    public interface PriorityListener {
        /**
         * This is fired when the user clicks on the confirm button.
         *
         * @param priorities the new score priorities.
         */
        void onDialogPositiveClick(Score[] priorities);
    }

    /**
     * This is intended to be the parent that created the dialog. The parent must
     * call the setter for this field with itself as the argument.
     */
    PriorityListener mListener;

    /*
     * These are the widgets found in the layout
     */
    Spinner[] mSpinners;

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

    /**
     * This is a helper method that builds the score array of the spinners' current values.
     * @return a score array of the spinners' value.
     */
    private Score[] getPriorities() {
        Score[] priorities = new Score[6];
        for (int i = 0; i < 6; i++) {
            priorities[i] = (Score) mSpinners[i].getSelectedItem();
        }
        return priorities;
    }

    /**
     * This is a helper method for creating the onclick listener for the positive
     * button. The listener calls mListener's onDialogPositiveClick method.
     *
     * @return the onclick listener for the positive button
     */
    private DialogInterface.OnClickListener getOnClickPositiveButton() {
        return new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                mListener.onDialogPositiveClick(getPriorities());
            }
        };
    }

    /**
     * Getter for the mListener property.
     * @return the value of mListener.
     */
    public PriorityListener getListener() {
        return mListener;
    }

    /**
     * Setter for the mListener property.
     * @param listener the new listener value for mListener.
     */
    public void setListener(PriorityListener listener) {
        mListener = listener;
    }
}
