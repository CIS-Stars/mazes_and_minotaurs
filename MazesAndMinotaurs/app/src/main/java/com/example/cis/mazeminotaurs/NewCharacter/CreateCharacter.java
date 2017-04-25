package com.example.cis.mazeminotaurs.NewCharacter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cis.mazeminotaurs.AttributeScoreGenerator;
import com.example.cis.mazeminotaurs.R;

/**
 * Created by ckling on 4/10/17.
 */

public class CreateCharacter extends Fragment {

    String mCharaName;

    public CreateCharacter() {
    }

    @Override
    public View onCreateView(LayoutInflater li, ViewGroup vg, Bundle b) {
        super.onCreateView(li, vg, b);
        Log.i("Begin OnCreateView", "Start of onCreateView");
        View rootView = li.inflate(R.layout.fragment_character_sheet, vg, false);



        return rootView;
    }


}
