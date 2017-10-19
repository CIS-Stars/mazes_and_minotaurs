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
    private Portfolio mPortfolio = Portfolio.get();
    private Context mContext;

    public CharacterAdapter(Context context) {
        mContext = context;
    }

    public void removeCharacter(int i) {
        mPortfolio.deletePlayerCharacter(mPortfolio.getPlayerCharacter(i));
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mPortfolio.getPortfolio().size();
    }

    @Override
    public PlayerCharacter getItem(int i) {
        return mPortfolio.getPlayerCharacter(i);
    }

    @Override
    public long getItemId(int i) {
        return getItem(i).getCharClass().getResId();
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

        nameView.setText(getItem(i).getName());
        classView.setText(getItem(i).getCharClass().getResId());
        levelView.setText(String.valueOf(getItem(i).getCharClass().getLevel()));
        return view;
    }
}
