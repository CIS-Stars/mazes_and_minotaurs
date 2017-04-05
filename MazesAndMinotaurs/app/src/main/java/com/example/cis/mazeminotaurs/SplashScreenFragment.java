package com.example.cis.mazeminotaurs;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.cis.mazeminotaurs.character.Character;

import static android.content.ContentValues.TAG;

/**
 * Created by Thorin Schmidt on 3/31/2017.
 */

public class SplashScreenFragment extends Fragment {

    Character mSplashCharacter;
    TextView mCharacterName;


    @Override
    public View onCreateView(LayoutInflater li, ViewGroup vg, Bundle b){
        super.onCreateView(li, vg, b);
        View rootView = li.inflate(R.layout.fragment_splash_screen, vg, false);

        mSplashCharacter = ((MainMazes)getActivity()).mCurrentCharacter;
        mSplashCharacter.setName("Billy");
        Log.i(TAG, ((MainMazes)getActivity()).mCurrentCharacter.getName());
        Log.i(TAG, mSplashCharacter.getName());


        mCharacterName = (TextView) rootView.findViewById(R.id.splash_character_name);
        mCharacterName.setText(mSplashCharacter.getName());

        return rootView;
    }
}
