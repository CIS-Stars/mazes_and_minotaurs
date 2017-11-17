package com.example.cis.mazeminotaurs;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ListViewCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.example.cis.mazeminotaurs.fragments.CharManageFragment;
import com.example.cis.mazeminotaurs.util.CommonStrings;

public class CharacterManageActivity extends AppCompatActivity implements CharManageFragment.ManagementListener {

    ListViewCompat mListView;
    CharacterAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_manage);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mListView = (ListViewCompat) findViewById(R.id.character_list_view);
        mAdapter = new CharacterAdapter(this);
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                CharManageFragment dialog = new CharManageFragment();
                Bundle args = new Bundle();
                args.putSerializable(CommonStrings.CHARACTER_ARG.getValue(), mAdapter.getItem(i));
                dialog.setListener(CharacterManageActivity.this);
                dialog.setArguments(args);

                FragmentManager fm = getSupportFragmentManager();
                dialog.show(fm, CharManageFragment.TAG);
            }
        });
    }


    @Override
    public void onSelect(int i) {
        Portfolio.get().setActiveCharacterIndex(i);
    }

    @Override
    public void onDelete(int i) {
        if (Portfolio.get().getPortfolio().size() > 1) {
            mAdapter.removeCharacter(i);
        } else {
            Toast.makeText(this, "Must have one character at all times!", Toast.LENGTH_SHORT).show();
        }
    }
}
