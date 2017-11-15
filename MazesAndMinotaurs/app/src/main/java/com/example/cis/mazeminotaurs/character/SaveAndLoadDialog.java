package com.example.cis.mazeminotaurs.character;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.cis.mazeminotaurs.Portfolio;
import com.example.cis.mazeminotaurs.R;
import com.example.cis.mazeminotaurs.serialization.SaveAndLoadPerformer;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * @deprecated
 * Created by jusmith on 4/26/17.
 */

public class SaveAndLoadDialog extends DialogFragment {
    Button mSaveButton;
    Button mLoadButton;
    int mPlayerCharacterIndex;

    public static SaveAndLoadDialog newInstance(int playerCharacterIndex) {
        Bundle args = new Bundle();
        args.putInt("character", playerCharacterIndex);

        SaveAndLoadDialog dialog = new SaveAndLoadDialog();
        dialog.setArguments(args);
        return dialog;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mPlayerCharacterIndex = getArguments().getInt("character");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        final SaveAndLoadDialog dialog = this;
        View v = inflater.inflate(R.layout.dialog_save_and_load, container, false);
        mSaveButton = (Button) v.findViewById(R.id.save_dialog_button);
        mLoadButton = (Button) v.findViewById(R.id.load_dialog_button);
        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    String fileName = String.format("character_%d", mPlayerCharacterIndex);

                    FileOutputStream fos = getContext().openFileOutput(fileName, Context.MODE_PRIVATE);
                    OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fos);
                    System.out.println(SaveAndLoadPerformer.save(mPlayerCharacterIndex));
                    outputStreamWriter.write(SaveAndLoadPerformer.save(mPlayerCharacterIndex));
                    outputStreamWriter.close();
                    fos.close();

                } catch (IOException e) {
                    e.printStackTrace();
                }
                dialog.dismiss();
            }
        });

        mLoadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String fileName = String.format("character_%d", mPlayerCharacterIndex);

                    FileInputStream fis = getContext().openFileInput(fileName);
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new BufferedInputStream(fis)));

                    StringBuilder output = new StringBuilder();
                    String line = bufferedReader.readLine();

                    while( line != null) {
                        output.append(line);
                        line = bufferedReader.readLine();
                    }

                    System.out.println(output.toString());
                    PlayerCharacter loadedCharacter = SaveAndLoadPerformer.load(output.toString());
                    Portfolio.get().getPortfolio().set(mPlayerCharacterIndex, loadedCharacter);
                    bufferedReader.close();
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                dialog.dismiss();

                // Thanks to @davidsnider for solving the issue of refreshing the fragment

                /*Fragment contentFragment = new CharacterSheetFragment();

                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.content_frame, contentFragment);
                ft.commit();*/

            }
        });

        return v;
    }
}
