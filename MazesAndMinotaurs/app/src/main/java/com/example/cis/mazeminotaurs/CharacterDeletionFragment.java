package com.example.cis.mazeminotaurs;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

/**
 * @deprecated
 * Created by jsmith on 10/19/17.
 */

public class CharacterDeletionFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater li, ViewGroup vg, Bundle b) {
        super.onCreateView(li, vg, b);
        View view = li.inflate(R.layout.fragment_character_selection, vg, false);

        final ListView charListView = (ListView) view.findViewById(R.id.character_list_view);
        charListView.setAdapter(new CharacterAdapter(getContext()));

        charListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (charListView.getAdapter().getCount() > 1) {
                    ((CharacterAdapter) charListView.getAdapter()).removeCharacter(i);
                } else {
                    Toast.makeText(getContext(), R.string.error_delete_character, Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }
}
