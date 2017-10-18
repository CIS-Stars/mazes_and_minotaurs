package com.example.cis.mazeminotaurs;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.cis.mazeminotaurs.character.PlayerCharacter;

import java.util.ArrayList;

/**
 * Created by jsmith on 10/18/17.
 */

public class CharacterAdapter extends BaseAdapter {
    private ArrayList<PlayerCharacter> mDataset;
    private Context mContext;

    @Override
    public int getCount() {
        return mDataset.size();
    }

    @Override
    public PlayerCharacter getItem(int i) {
        return mDataset.get(i);
    }

    @Override
    public long getItemId(int i) {
        return mDataset.get(i).getCharClass().getResId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = LayoutInflater.from(mContext)
                    .inflate(R.layout.spinner_item_character, viewGroup, false);
        }

        TextView nameView = (TextView) view.findViewById(R.id.character_name_view);
        TextView classView = (TextView) view.findViewById(R.id.character_class_view);
        TextView levelView = (TextView) view.findViewById(R.id.character_level_view);

        nameView.setText(mDataset.get(i).getName());
        classView.setText(mDataset.get(i).getCharClass().getResId());
        levelView.setText(mDataset.get(i).getCharClass().getLevel());
        return view;
    }
}
