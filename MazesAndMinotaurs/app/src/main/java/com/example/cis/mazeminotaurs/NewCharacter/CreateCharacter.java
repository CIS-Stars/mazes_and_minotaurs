package com.example.cis.mazeminotaurs.NewCharacter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.cis.mazeminotaurs.AttributeScoreGenerator;
import com.example.cis.mazeminotaurs.R;
import com.example.cis.mazeminotaurs.character.PlayerCharacter;
import com.example.cis.mazeminotaurs.character.classes.BaseClass;

/**
 * Created by ckling on 4/10/17.
 */

public class CreateCharacter extends Fragment {

    //TESTING
    //TODO Clean ths up
    BaseClass mBaseClass;
    //TESTING

    String mCharaName;
    String mCharacterClass;
    int mCharaLevel;

    TextView mCharaClassTextView;
    EditText mCharaNameEditText;
    TextView mCharacterLevelTextView;

    public CreateCharacter() {
    }

    @Override
    public View onCreateView(LayoutInflater li, ViewGroup vg, Bundle b) {
        super.onCreateView(li, vg, b);
        Log.i("Begin OnCreateView", "Start of onCreateView");
        View rootView = li.inflate(R.layout.fragment_create_character, vg, false);

        mBaseClass = (BaseClass) getArguments().get("classInstance");

        mCharaClassTextView = (TextView) rootView.findViewById(R.id.character_class_view);
        mCharacterClass = getArguments().getString("newClass");
        mCharaClassTextView.setText(mCharacterClass);

        mCharaNameEditText = (EditText) rootView.findViewById(R.id.character_name_view);
        mCharaName = mCharaNameEditText.getText().toString();

        mCharacterLevelTextView = (TextView) rootView.findViewById(R.id.character_level_view);
        mCharaLevel = 1;
        mCharacterLevelTextView.setText(Integer.toString(mBaseClass.getLevel()));



        return rootView;
    }


}
