package com.halo.loginui2.service;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.halo.loginui2.Adapter.IncidentAdapter;
import com.halo.loginui2.Decoration.SimpleDividerItemDecoration;
import com.halo.loginui2.Model.Incident;
import com.halo.loginui2.R;

import org.json.JSONArray;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;



import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

public class IncidentService {

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

                        Toast.makeText(context,"pasoOOO coshowRecyclerViewn " + items.size() + " items",Toast.LENGTH_LONG).show();
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
        for (int i = 0; i < jsonArray.length(); i++){
            try {
                jsonObject = jsonArray.getJSONObject(i);

                String type = jsonObject.getString("type");
                String datePublication = jsonObject.getString("datePublication");
                String comment = jsonObject.getString("comment");
                String state = jsonObject.getString("state");


                incidents.add(new Incident( type, datePublication,"LIMA",comment,state));
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

        incidentAdapter = new IncidentAdapter(incidents);
        recyclerView.setAdapter(incidentAdapter);

        recyclerView.addItemDecoration(new SimpleDividerItemDecoration(view.getContext()));

    }

}
