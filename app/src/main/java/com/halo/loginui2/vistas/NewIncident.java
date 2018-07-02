package com.halo.loginui2.vistas;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.halo.loginui2.Fragments.ListaIncidentesFragment;
import com.halo.loginui2.MainActivity;
import com.halo.loginui2.Model.Ciudadano;
import com.halo.loginui2.Model.Empleado;
import com.halo.loginui2.Model.Incident;
import com.halo.loginui2.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NewIncident extends AppCompatActivity {
    private final static String TAG = "NewIncident";
    String tipo = "";
    String lugar;
    String descripcion;
    String foto;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_incident);

        context = NewIncident.this;

        //llenando el comboBox de tipos de incidentes
        List<String> tipos = new ArrayList();
        tipos.add("Seleccione");
        tipos.add("Molestia");
        tipos.add("Limpieza");

        ArrayAdapter<String> adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item,tipos);
        Spinner cbTipos = findViewById(R.id.cbTipos);
        cbTipos.setAdapter(adapter);

        cbTipos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    //Toast.makeText( parent.getContext() ,"Hola",Toast.LENGTH_LONG).show();
                    tipo = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        //agregando funcionalidad al boton Guardar incidente
        Button btnGuardar = findViewById(R.id.btnGuardar);
        btnGuardar.setOnClickListener((vista->{

            EditText txtLugar = findViewById(R.id.txtLugar);
            EditText txtDescripcion = findViewById(R.id.txtDescripcion);

            lugar = txtLugar.getText().toString();
            descripcion = txtDescripcion.getText().toString();

            Gson gsonObjetc = new Gson();

            Incident incident = new Incident();
            incident.setComment(descripcion);
            incident.setPlace(lugar);
            incident.setState("1");
            incident.setType("1");
            incident.setCitizen(new Ciudadano(1,"","",""));
            incident.setEmploye(new Empleado(2,"","",""));

            String gson = gsonObjetc.toJson(incident);
            Log.i(TAG,gson);
            addInciendetToWebService(gson);
        }));

        //agregando funcionalidad al boton cancelar incidente
        Button btnCancelar = findViewById(R.id.btnCancelar);
        btnCancelar.setOnClickListener((view)->{
            finish();
        });
    }

    //este metodo usa la libreria volley para insertar un nuevo incidente en APIrest
    private void addInciendetToWebService(String gson){
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        JSONObject json = new JSONObject();
        try {
            json = new JSONObject(gson);
        } catch (JSONException e) {
            Log.i(TAG,e.toString());
        }

        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.POST,
                "https://dinci-rest.herokuapp.com/incidente",
                json,
                (response) -> {
                    Toast.makeText(context,response.toString(),Toast.LENGTH_LONG).show();
                    Log.i(TAG,response.toString());
                },
                (error) -> {
                    Log.i(TAG,error.toString());
                });
        requestQueue.add(request);
    }



}
