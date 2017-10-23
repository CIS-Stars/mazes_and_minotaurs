package com.example.cis.mazeminotaurs.fragments;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.cis.mazeminotaurs.InventoryAdapter;
import com.example.cis.mazeminotaurs.Portfolio;
import com.example.cis.mazeminotaurs.R;
import com.example.cis.mazeminotaurs.character.PlayerCharacter;

/**
 * Created by JayTSmith on 10/23/17.
 */

public class InventoryFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater li, ViewGroup vg, Bundle b) {
        super.onCreateView(li, vg, b);
        View view = li.inflate(R.layout.fragment_inventory, vg, false);

        Portfolio port = Portfolio.get();
        PlayerCharacter active = port.getPlayerCharacter(port.getActiveCharacterIndex());

        ListView equipListView = (ListView) view.findViewById(R.id.inventory_list_view);
        final InventoryAdapter adapter = new InventoryAdapter(active, getContext());
        equipListView.setAdapter(adapter);

        equipListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                final int equipmentIndex = i;
                AlertDialog dialog = new AlertDialog.Builder(getContext())
                        .setTitle((int) l)
                        .setPositiveButton(R.string.confirm_button, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                adapter.removeItem(equipmentIndex);
                            }
                        })
                        .setNegativeButton(R.string.cancel, null)
                        .create();
                dialog.show();
            }
        });

        return view;
    }
}
