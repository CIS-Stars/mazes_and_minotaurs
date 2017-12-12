package com.example.cis.mazeminotaurs;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.cis.mazeminotaurs.character.PlayerCharacter;

/**
 * This displays the inventory of a character.
 * @author jsmith on 10/23/17.
 */

public class InventoryAdapter extends BaseAdapter {
    /**
     * The character whose inventory will be displayed.
     */
    private PlayerCharacter mData;

    /**
     * A context to get the layout from.
     */
    private Context mContext;

    /**
     * Default constructor.
     *
     * @param playerCharacter a character with items in inventory.
     * @param context         a context from a fragment or activity.
     */
    public InventoryAdapter(PlayerCharacter playerCharacter, Context context) {
        mData = playerCharacter;
        mContext = context;
    }

    /**
     * Helper method to remove an item from the dataset. Calls notifyDataSetChanged
     * when called.
     * @param i index of item within inventory.
     */
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
