package com.example.cis.mazeminotaurs;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.cis.mazeminotaurs.R;
import com.example.cis.mazeminotaurs.Weapon;

import java.util.ArrayList;

/**
 * Created by jsmith on 10/11/17.
 */

public class DetailedWeaponAdapter extends BaseAdapter {
    private ArrayList<Weapon> mDataset;
    private Context mContext;

    public DetailedWeaponAdapter(Context context, ArrayList<Weapon> weapons) {
        mContext = context;
        mDataset = weapons;
    }


    @Override
    public int getCount() {
        return mDataset.size();
    }

    @Override
    public Weapon getItem(int i) {
        return mDataset.get(i);
    }

    @Override
    public long getItemId(int i) {
        return mDataset.get(i).getResId();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext)
                    .inflate(R.layout.spinner_item_weapon_detailed, parent, false);
        }

        ((TextView) convertView.findViewById(R.id.detail_weapon_name_view)).setText(mDataset.get(position).getResId());
        ((TextView) convertView.findViewById(R.id.detail_weapon_encumberance_view)).setText(Integer.toString(mDataset.get(position).getEncumberance()));

        return convertView;
    }
}
