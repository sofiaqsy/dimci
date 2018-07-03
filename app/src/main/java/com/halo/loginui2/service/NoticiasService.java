package com.halo.loginui2.service;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.halo.loginui2.Adapter.NoticiasAdapter;
import com.halo.loginui2.Decoration.SimpleDividerItemDecoration;
import com.halo.loginui2.Model.Noticias;
import com.halo.loginui2.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

public class NoticiasService  extends AppController{

    private Noticias noticias = null;

    private NoticiasAdapter noticiasAdapter;

    private ArrayList<Noticias> items;

    JsonArrayRequest jsonArrayRequest ;

    StringRequest stringRequest;

    JsonObjectRequest jsonObjectRequest;

    private Context context = null;

    private String url;

    private int idCitizen = 0;

    JSONArray jsonArray = new JSONArray();

    public NoticiasService(Context context){

        this.context = context;


    }

    public void getListNoticias(final View view){

        url = "https://dinci-rest.herokuapp.com/noticia";
        jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {

                        items = parseJson(response);

                        if(items.size() != 0){
                            showRecyclerView(view, items);
                        }
                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(context,"ERROR pasoOOO coshowRecyclerViewn  ",Toast.LENGTH_LONG).show();

                    }
                }

        );

        AppController.getInstance().addToRequestQueue(jsonArrayRequest);

    }

    public ArrayList<Noticias> parseJson(JSONArray jsonArray){
        ArrayList<Noticias> noticias = new ArrayList();
        JSONObject jsonObject = null;
        Noticias noticia;
        Gson gson = new Gson();
        for (int i = 0; i < jsonArray.length(); i++){
            try {
                jsonObject = jsonArray.getJSONObject(i);
                noticia = gson.fromJson(jsonObject.toString(),Noticias.class);
                noticias.add(noticia);
            } catch (JSONException e) {
                Log.e("JsonObjet", "Se ha producido al crear la lista de incidentes. "+ e.getMessage());
            }
        }
        return noticias;
    }


    public void showRecyclerView(View view, ArrayList<Noticias> noticias){

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerViewNoticias);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        noticiasAdapter = new NoticiasAdapter(noticias, view.getContext());
        recyclerView.setAdapter(noticiasAdapter);

        recyclerView.addItemDecoration(new SimpleDividerItemDecoration(view.getContext()));

    }

}
