package com.halo.loginui2.Fragments;


import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

//import com.androidnetworking.AndroidNetworking;
//import com.androidnetworking.common.Priority;
//import com.androidnetworking.error.ANError;
//import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.halo.loginui2.Model.Noticias;
import com.halo.loginui2.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

//import pe.edu.utp.newsnow.R;
//import pe.edu.utp.newsnow.adapters.SourcesAdapter;
//import pe.edu.utp.newsnow.models.Source;

import static android.content.res.Configuration.ORIENTATION_LANDSCAPE;
//import static pe.edu.utp.newsnow.network.NewsApiService.SOURCES_URL;

/**
 * A simple {@link Fragment} subclass.
 */
public class SourcesFragment extends Fragment {
    RecyclerView sourcesRecyclerView;
    //SourcesAdapter sourcesAdapter;
    RecyclerView.LayoutManager sourcesLayoutManager;
    List<Noticias> noticias;
    String tag;

    public SourcesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_sources, container, false);
        View view = inflater.inflate(R.layout.fragment_sources, container, false);
        noticias = new ArrayList<>();
        tag = getString(R.string.app_name);
        sourcesRecyclerView = view.findViewById(R.id.sourcesRecyclerView);
        //sourcesAdapter = new SourcesAdapter(sources);
        //sourcesLayoutManager = new GridLayoutManager(view.getContext(),
        //        getSpanCountFor(getResources().getConfiguration()));
        //sourcesRecyclerView.setLayoutManager(sourcesLayoutManager);
        //sourcesRecyclerView.setAdapter(sourcesAdapter);
        //updateSources();
        return view;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        updateLayoutAccordingTo(newConfig);
    }

    private void updateLayoutAccordingTo(Configuration configuration) {
        ((GridLayoutManager) sourcesLayoutManager).setSpanCount(
                getSpanCountFor(configuration));

    }

    private int getSpanCountFor(Configuration configuration) {
       return configuration.orientation == ORIENTATION_LANDSCAPE ? 3 : 2;
    }

    private void updateSources() {
        //AndroidNetworking
        //      .get(SOURCES_URL)
        //      .addQueryParameter("language", "en")
        //      .setTag(tag)
        //      .setPriority(Priority.LOW)
        //      .build()
        //.getAsJSONObject(new JSONObjectRequestListener() {
                    //@Override
                    //public void onResponse(JSONObject response) {
                        //try {
                            //if("error".equalsIgnoreCase(response.getString("status"))) {
                                //Log.d(tag, response.getString("message"));
                                //return;
                                //}
                            //sources = Source.from(response.getJSONArray("sources"));
                            //Log.d(tag, "Sources: ".concat(String.valueOf(sources.size())));
                            //sourcesAdapter.setSources(sources);
                            //sourcesAdapter.notifyDataSetChanged();
                            //} catch (JSONException e) {
                            //e.printStackTrace();
                            //}
                        //}
                    //@Override
                    //public void onError(ANError anError) {
                        //Log.d(tag,anError.getErrorBody());
                        //    }
                //});
    }

}
