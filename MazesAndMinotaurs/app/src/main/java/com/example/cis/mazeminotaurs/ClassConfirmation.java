package com.example.cis.mazeminotaurs;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;

/**
 *  Made to confirm class selection.
 *
 * Created by Chaos on 3/16/2017.
 */

public class ClassConfirmation extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.class_popup);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int) (width*.8), (int) (height*.6));
    }
}
