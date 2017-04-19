package com.example.cis.mazeminotaurs.NewCharacter;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.cis.mazeminotaurs.R;

/**
 *  Made to confirm class selection.
 *
 * Created by Chaos on 3/16/2017.
 */

public class ClassConfirmationPopUp extends FragmentActivity {

    TextView classInfoTextView;
    Button confirmButton;
    Button cancelButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.class_confirmation_popup);

        savedInstanceState = getIntent().getExtras();
        String classConText = savedInstanceState.getString("mClass");

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int) (width*.8), (int) (height*.6));

        confirmButton = (Button) findViewById(R.id.confirm_button);
        cancelButton = (Button) findViewById(R.id.cancel_button);
        classInfoTextView = (TextView) findViewById(R.id.class_information_TextView);
        classInfoTextView.setText(classConText);

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }

    @Override
    public void onBackPressed(){
        if (getFragmentManager().getBackStackEntryCount() == 0){
            this.finish();
        } else {
            getFragmentManager().popBackStack();
        }
    }

}
