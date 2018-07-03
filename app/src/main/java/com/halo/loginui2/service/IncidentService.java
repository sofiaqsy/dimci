package com.halo.loginui2.service;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;
import com.halo.loginui2.Adapter.IncidentAdapter;
import com.halo.loginui2.Decoration.SimpleDividerItemDecoration;
import com.halo.loginui2.Model.EstadoIncidente;
import com.halo.loginui2.Model.Incident;
import com.halo.loginui2.Model.TipoIncidente;
import com.halo.loginui2.R;

import org.json.JSONArray;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.halo.loginui2.vistas.DetailsIncident;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

public class IncidentService {
    final static String TAG = "IncidentService";

    private Incident incident = null;

    private IncidentAdapter incidentAdapter;

    private ArrayList<Incident> items;

    JsonArrayRequest jsonArrayRequest ;

    StringRequest stringRequest;

    JsonObjectRequest jsonObjectRequest;

    private Context context = null;

    private String url;

    private int idCitizen = 0;

    JSONArray jsonArray = new JSONArray();

    public IncidentService(Context context){

        this.context = context;


    }

    public void getListIncidents(final View view){

        url = "https://dinci-rest.herokuapp.com/incidente";
        jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(TAG, response.toString());

                        items = parseJson(response);

                        //Toast.makeText(context,"pasoOOO coshowRecyclerViewn " + items.size() + " items",Toast.LENGTH_LONG).show();
                        if(items.size() != 0){
                            showRecyclerView(view, items);
                        }

                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                }

        );

        AppController.getInstance().addToRequestQueue(jsonArrayRequest);

    }

    public ArrayList<Incident> parseJson(JSONArray jsonArray){
        ArrayList<Incident> incidents = new ArrayList();
        JSONObject jsonObject = null;
        Gson gson = new Gson();
        Incident incident;
        for (int i = 0; i < jsonArray.length(); i++){
            try {
                jsonObject = jsonArray.getJSONObject(i);
                //Log.i(TAG,jsonObject.toString());
                incident = gson.fromJson(jsonObject.toString(),Incident.class);
                incidents.add(incident);
            } catch (JSONException e) {
                Log.e("JsonObjet", "Se ha producido al crear la lista de incidentes. "+ e.getMessage());
            }
        }
        return incidents;
    }


    public void showRecyclerView(View view, ArrayList<Incident> incidents){

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        incidentAdapter = new IncidentAdapter(incidents,view.getContext());


        recyclerView.setAdapter(incidentAdapter);

        recyclerView.addItemDecoration(new SimpleDividerItemDecoration(view.getContext()));

    }

}
