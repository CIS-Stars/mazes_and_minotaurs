package com.example.cis.mazeminotaurs;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * @deprecated
 * @author Thorin Schmidt on 5/4/2017.
 */

public class WeaponListFragment extends DialogFragment {
    public interface onWeaponChangeListener {
        void onWeaponChange(Weapon newWeapon);
    }
    onWeaponChangeListener mWeaponChangeListener;
    
    private RecyclerView mWeaponRecyclerView;
    
    /**
     * Create a new instance of WeaponListFragment, providing "weaponId"
     * as an arguments.
     */
    public static WeaponListFragment newInstance(int weaponId) {
        WeaponListFragment f = new WeaponListFragment();

        // Supply num input as an argument.
        Bundle args = new Bundle();
        args.putInt("weaponId", weaponId);
        f.setArguments(args);

        return f;
    }

    @Override
    public View onCreateView(LayoutInflater li, ViewGroup vg, Bundle savedInstanceState) {
        super.onCreateView(li, vg, savedInstanceState);
        View v = li.inflate(R.layout.dialog_weapon_list, vg, false);

        mWeaponRecyclerView = (RecyclerView) v.findViewById(R.id.weapon_recyler_view);
        //ArrayAdapter<Weapon> adapter = new ArrayAdapter<Weapon>(EquipmentDB.getInstance().);

        //mWeaponRecyclerView.setAdapter();

        return v;
    }


    public onWeaponChangeListener getWeaponChangeListener() {
        return mWeaponChangeListener;
    }

    public void setWeaponChangeListener(onWeaponChangeListener weaponChangeListener) {
        mWeaponChangeListener = weaponChangeListener;
    }
}
