package com.example.cis.mazeminotaurs.web_resources;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.example.cis.mazeminotaurs.R;

/**
 * Created by Devin on 4/4/17.
 */

public class PlayerManualFragment extends Fragment {

    WebView mPlayerManualWeb;
    String mManualUrl = "mazesandminotaurs.free.fr/RMM1.pdf";
    @Override
    public View onCreateView(LayoutInflater li, ViewGroup vg, Bundle b){
        super.onCreateView(li, vg, b);
        View rootView = li.inflate(R.layout.fragment_player_manual, vg, false);
        mPlayerManualWeb = (WebView) rootView.findViewById(R.id.player_manual_web_view);
        mPlayerManualWeb.getSettings().setJavaScriptEnabled(true);
        mPlayerManualWeb.loadUrl("https://docs.google.com/viewer?url="+mManualUrl);




        return rootView;
    }
}