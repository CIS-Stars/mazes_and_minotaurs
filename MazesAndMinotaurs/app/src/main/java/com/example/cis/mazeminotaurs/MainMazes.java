package com.example.cis.mazeminotaurs;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainMazes extends AppCompatActivity {

    AttributeScoreGenerator scoreGen = new AttributeScoreGenerator();
    AttributeScore[] testAttributes = scoreGen.nextValidSet();

    Button mNewChara;
    Button mCharaCont;
    Button mPlayerManual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_mazes);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        for(int i = 0; i < testAttributes.length; i++){
            System.out.println(testAttributes[i].getScore());
        }

        mNewChara = (Button) findViewById(R.id.new_character_bttn);
        mNewChara.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                setContentView(R.layout.fragment_character_creator);
            }
        });

        mCharaCont = (Button) findViewById(R.id.character_continue_bttn);
        mCharaCont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setContentView(R.layout.activity_character_sheet);
            }
        });
        mPlayerManual = (Button) findViewById(R.id.player_manual_bttn);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_mazes, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        Fragment contentFrag = null;
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_help) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }
}
