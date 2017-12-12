package com.example.cis.mazeminotaurs;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * This fragment displays a single image.
 * The user can do nothing on this fragment.
 * @author Thorin Schmidt on 3/31/2017.
 */

public class SplashScreenFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater li, ViewGroup vg, Bundle b){
        super.onCreateView(li, vg, b);
        View rootView = li.inflate(R.layout.fragment_splash_screen, vg, false);

        return rootView;
    }
}
