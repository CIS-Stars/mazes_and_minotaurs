package com.example.cis.mazeminotaurs.fragments;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.cis.mazeminotaurs.R;

/**
 * This fragment displays the old and new value of a character's hits.
 * The user can change the value of the character's hits.
 *
 * @author jsmith on 9/21/17.
 */

public class HitsChangeFragment extends DialogFragment {
    /**
     * This is meant to be implemented by the parent that opened the dialog.
     */
    public interface onHitsChangeListener {
        /**
         * This is fired when a new value is submitted for hits.
         *
         * @param newValue the new value of hits.
         */
        void onHitsChange(int newValue);
    }

    /**
     * This is intended to be the parent that created the dialog. The parent must
     * call the setter for this field with itself as the argument.
     */
    onHitsChangeListener mHitsChangeListener;

    /**
     * The old value of hits. Supplied as an argument in a bundle under the key
     * "value".
     */
    int mOldValue;

    /*
     * The widgets found in the layout.
     */
    EditText mNewValue;

    /**
     * Create a new instance of HitsChangeFragment, providing "value"
     * as an argument.
     */
    public static HitsChangeFragment newInstance(int value) {
        HitsChangeFragment f = new HitsChangeFragment();

        // Supply num input as an argument.
        Bundle args = new Bundle();
        args.putInt("value", value);
        f.setArguments(args);

        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mOldValue = getArguments().getInt("value");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.dialog_attribute_edit, container, false);
        String hitsString = getContext().getString(R.string.hits_title);
        ((TextView) v.findViewById(R.id.attribute_old_base))
                .setText(getContext().getString(R.string.old_attribute_value, hitsString, mOldValue));
        mNewValue = (EditText) v.findViewById(R.id.attribute_new_base_edit);
        v.findViewById(R.id.attribute_confirm_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!mNewValue.getText().toString().trim().equals("")) {
                    int newValue = Integer.valueOf(mNewValue.getText().toString());
                    getHitsChangeListener().onHitsChange(newValue);
                }
                dismiss();
            }
        });

        return v;
    }

    /**
     * Getter for the mHitsChangeListener property.
     * @return the value of mHitsChangeListener.
     */
    public onHitsChangeListener getHitsChangeListener() {
        return mHitsChangeListener;
    }

    /**
     * Setter for the mHitsChangeListener property.
     * @param hitsChangeListener the new listener for mHitsChangeListener.
     */
    public void setHitsChangeListener(onHitsChangeListener hitsChangeListener) {
        mHitsChangeListener = hitsChangeListener;
    }
}
