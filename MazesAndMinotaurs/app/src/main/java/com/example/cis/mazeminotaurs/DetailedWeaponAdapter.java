package com.example.cis.mazeminotaurs;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * An adapter that displays Weapons with their name and encumberance values.
 * @author jsmith on 10/11/17.
 */

public class DetailedWeaponAdapter extends BaseAdapter {
    /**
     * The list of weapons to display.
     */
    private ArrayList<Weapon> mDataset;

    /**
     * The context to get the layout from.
     */
    private Context mContext;

    /**
     * Default constructor.
     *
     * @param context a context from a fragment or activity.
     * @param weapons a list of weapons.
     */
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
        return mDataset.get(0).getResId().hashCode();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext)
                    .inflate(R.layout.spinner_item_weapon_detailed, parent, false);
        }

        Weapon curWep = mDataset.get(position);
        TextView nameView = (TextView) convertView.findViewById(R.id.detail_weapon_name_view);
        TextView encumbView = (TextView) convertView.findViewById(R.id.detail_weapon_encumberance_view);

        if (curWep != null) {
            nameView.setText(curWep.getResId());
            encumbView.setText(Integer.toString(curWep.getEncumberance()));
        } else {
            nameView.setText("-");
            encumbView.setText("-");
        }

        return convertView;
    }

    /**
     * Getter for the mDataset property.
     * @return the value of mDataset.
     */
    public ArrayList<Weapon> getDataset() {
        return mDataset;
    }

    /**
     * Setter for the mDataset property.
     * @param dataset the new value of mDataset.
     */
    public void setDataset(ArrayList<Weapon> dataset) {
        mDataset = dataset;
    }
}
