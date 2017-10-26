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
 * Created by JayTSmith on 10/23/17.
 */

public class InventoryAdapter extends BaseAdapter {
    private PlayerCharacter mData;
    private Context mContext;

    public InventoryAdapter(PlayerCharacter playerCharacter, Context context) {
        mData = playerCharacter;
        mContext = context;
    }

    public void removeItem(int i) {
        mData.getInventory().remove(i);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mData.getInventory().size();
    }

    @Override
    public Equipment getItem(int i) {
        return mData.getInventory().get(i);
    }

    @Override
    public long getItemId(int i) {
        return mData.getInventory().get(i).getResId().hashCode();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = LayoutInflater.from(mContext)
                    .inflate(R.layout.spinner_item_equipment, viewGroup, false);
        }

        Equipment equipment = getItem(i);
        TextView nameView = (TextView) view.findViewById(R.id.equipment_name_view);

        if (equipment != null) {
            nameView.setText(equipment.getResId());
        } else {
            nameView.setText("-");
        }

        return view;
    }
}
