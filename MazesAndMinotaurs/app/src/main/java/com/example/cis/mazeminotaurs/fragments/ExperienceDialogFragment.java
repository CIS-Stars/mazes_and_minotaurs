package com.example.cis.mazeminotaurs.fragments;

import android.support.v4.app.DialogFragment;

/**
 * Created by JayTSmith on 12/5/17.
 */

public class ExperienceDialogFragment extends DialogFragment {
    public interface ChangeListener {
        void onExperienceChange();

        void onLevelChange();
    }


}
