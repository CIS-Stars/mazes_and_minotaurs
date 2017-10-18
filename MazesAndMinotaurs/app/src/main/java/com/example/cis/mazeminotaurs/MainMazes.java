package com.example.cis.mazeminotaurs;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.cis.mazeminotaurs.NewCharacter.CharacterCreationFragment;
import com.example.cis.mazeminotaurs.character.PlayerCharacter;
import com.example.cis.mazeminotaurs.character.SaveAndLoadPerformer;
import com.example.cis.mazeminotaurs.util.Util;
import com.example.cis.mazeminotaurs.web_resources.CompanionFragment;
import com.example.cis.mazeminotaurs.web_resources.PlayerManualFragment;
import com.example.cis.mazeminotaurs.web_resources.WebsiteFragment;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainMazes extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public Portfolio mPortfolio;
    public EquipmentDB mEquipment;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            try {
                String basePath = getApplicationContext().getFilesDir().getPath() + "/";
                FileInputStream fis = new FileInputStream(basePath + Portfolio.FILENAME);
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new BufferedInputStream(fis)));

                StringBuilder builder = new StringBuilder();
                String line = bufferedReader.readLine();
                while (line != null && !line.equals("")) {
                    builder.append(line);
                    line = bufferedReader.readLine();
                }
                SaveAndLoadPerformer.loadPortfolio(builder.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }

            mPortfolio = Portfolio.get();
            if (mPortfolio.getPortfolio() == null) {
                mPortfolio.resetPortfolio();
                mPortfolio.addPlayerCharacter(Util.createDummyCharacter());
            }
            mEquipment = EquipmentDB.getInstance();

            try{
                Log.i("Get Weapon", mEquipment.getWeapon(R.string.barb_axe).getLongDescription());
            }
            catch(NullPointerException e){
                Log.e("Get Weapon", "Failed!");
            }

            setContentView(R.layout.activity_main);
            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);

            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                    this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
            drawer.setDrawerListener(toggle);
            toggle.syncState();

            NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
            navigationView.setNavigationItemSelectedListener(this);

            Fragment contentFragment = new SplashScreenFragment();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame, contentFragment);
            ft.commit();
        }

        @Override
        public void onBackPressed() {
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            if (drawer.isDrawerOpen(GravityCompat.START)) {
                drawer.closeDrawer(GravityCompat.START);
            } else {
                super.onBackPressed();
            }
        }

        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            // Inflate the menu; this adds items to the action bar if it is present.
            getMenuInflater().inflate(R.menu.main, menu);
            return true;
        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            // Handle action bar item clicks here. The action bar will
            // automatically handle clicks on the Home/Up button, so long
            // as you specify a parent activity in AndroidManifest.xml.
            int id = item.getItemId();

            //noinspection SimplifiableIfStatement
            if (id == R.id.action_settings) {
                return true;
            } else if (id == R.id.action_save_portfolio) {
                try {
                    FileOutputStream fos = getApplicationContext().openFileOutput(Portfolio.FILENAME, Context.MODE_PRIVATE);
                    OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fos);
                    outputStreamWriter.write(SaveAndLoadPerformer.savePortfolio());
                    outputStreamWriter.close();
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return true;
            }

            return super.onOptionsItemSelected(item);
        }

        @SuppressWarnings("StatementWithEmptyBody")
        @Override
        public boolean onNavigationItemSelected(MenuItem item) {
            // Handle navigation view item clicks here.
            int id = item.getItemId();
            Fragment contentFragment = new SplashScreenFragment();

            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame, contentFragment);
            ft.commit();

            if (id == R.id.create_character) {
               contentFragment = new CharacterCreationFragment();
            } else if (id == R.id.play_character) {
                contentFragment = new CharacterSheetFragment();
            } else if (id == R.id.select_character) {
                contentFragment = new CharacerSelectionFragment();
            } else if (id == R.id.delete_character) {

            } else if (id == R.id.player_manual) {
                contentFragment = new PlayerManualFragment();
            } else if (id == R.id.player_comapanion) {
                contentFragment = new CompanionFragment();
            } else if (id == R.id.website) {
                contentFragment = new WebsiteFragment();
            }

            if (contentFragment != null){
                ft.replace(R.id.content_frame, contentFragment);
            }

            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            drawer.closeDrawer(GravityCompat.START);
            return true;
        }
}
