package com.example.cis.mazeminotaurs.web_resources;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.example.cis.mazeminotaurs.R;

/**
 * This fragment shows the M&M website.
 * @author Devin on 4/4/17.
 */

public class WebsiteFragment extends Fragment {

    /**
     * Serves as the TAG in certain functions. e.g. dialog.show() and logging.
     */
    public static final String TAG = WebsiteFragment.class.getName();

    /*
     * These are widgets found in the layout.
     */
    WebView mPlayerManualWeb;

    /**
     * This is a web link to the M&M website.
     */
    String mManualUrl = "http://mazesandminotaurs.free.fr/revised.html";
    @Override
    public View onCreateView(LayoutInflater li, ViewGroup vg, Bundle b) {
        super.onCreateView(li, vg, b);
        Log.i(TAG, "called super...");

        View rootView = li.inflate(R.layout.fragment_website, vg, false);
        Log.i(TAG, "inflated view...");

        mPlayerManualWeb = (WebView) rootView.findViewById(R.id.website_web_view);
        Log.i(TAG, "instantiated WebView...");

        //1 if portrait 2 if landscape
        int orientation = getActivity().getResources().getConfiguration().orientation;


        mPlayerManualWeb.setInitialScale(orientation * 100);
        mPlayerManualWeb.getSettings().setBuiltInZoomControls(true);
        mPlayerManualWeb.loadUrl(mManualUrl);
        Log.i(TAG, "performed loadUrl...");

        return rootView;

    }
}
