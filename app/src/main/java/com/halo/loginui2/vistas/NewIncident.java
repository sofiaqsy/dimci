package com.halo.loginui2.vistas;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
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
import com.halo.loginui2.Model.EstadoIncidente;
import com.halo.loginui2.Model.Incident;
import com.halo.loginui2.Model.TipoIncidente;
import com.halo.loginui2.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NewIncident extends AppCompatActivity {
    static final int REQUEST_IMAGE_CAPTURE = 1;
    private final static String TAG = "NewIncident";
    TipoIncidente tipo ;
    String lugar;
    String descripcion;
    String foto;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_incident);

        Button btnCamara = findViewById(R.id.btnCamara);
        btnCamara.setOnClickListener((view)->{

            Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            // Ensure that there's a camera activity to handle the intent
            if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                // Create the File where the photo should go
                File photoFile = null;
                try {
                    photoFile = createImageFile();
                    Log.i(TAG,mCurrentPhotoPath);
                } catch (IOException ex) {
                    // Error occurred while creating the File
                }
                // Continue only if the File was successfully created
                if (photoFile != null) {
                    Uri photoURI = FileProvider.getUriForFile(this,
                            "com.halo.loginui2.vistas.fileprovider",
                            photoFile);
                    Log.i(TAG,photoURI.getPath());
                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);



                }
            }
        });


        final Spinner cbTipos = findViewById(R.id.cbTipos);
        findViewById(R.id.txtLugar).requestFocus();
        context = NewIncident.this;


        //llenando el comboBox de tipos de incidentes
        List<TipoIncidente> tipos = new ArrayList();
        tipos.add(new TipoIncidente(0,"Seleccione",""));
        tipos.add(new TipoIncidente(1,"Molestia",""));
        tipos.add(new TipoIncidente(2,"Parque",""));
        tipos.add(new TipoIncidente(3,"Transito",""));
        tipos.add(new TipoIncidente(4,"Limpieza",""));
        tipos.add(new TipoIncidente(5,"Otros",""));

        ArrayAdapter<TipoIncidente> adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item,tipos);

        cbTipos.setAdapter(adapter);

        cbTipos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    //Toast.makeText( parent.getContext() ,((TipoIncidente)parent.getItemAtPosition(position)).getName(),Toast.LENGTH_LONG).show();
                    tipo = (TipoIncidente)parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        //agregando funcionalidad al boton Guardar incidente
        Button btnGuardar = findViewById(R.id.btnGuardar);
        btnGuardar.setOnClickListener((vista->{
            Log.i(TAG,validarCampos().toString());
            if(validarCampos()){
               String data = parseObjectToJson();
               addInciendetToWebService(data);
            }
        }));

        //agregando funcionalidad al boton cancelar incidente
        Button btnCancelar = findViewById(R.id.btnCancelar);
        btnCancelar.setOnClickListener((view)->{
            finish();
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        foto = mCurrentPhotoPath;
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
                    this.limpiarCampos();
                    Log.i(TAG,response.toString());
                },
                (error) -> {
                    Log.i(TAG,error.toString());
                });
        requestQueue.add(request);
    }

    private Boolean validarCampos(){
        final EditText txtLugar = findViewById(R.id.txtLugar);
        final EditText txtDescripcion = findViewById(R.id.txtDescripcion);
        final Spinner cbTipos = findViewById(R.id.cbTipos);

        boolean bandera = true;
        if(txtDescripcion.getText().toString().trim().isEmpty()){
            txtDescripcion.setError("No completo los datos");
            bandera = false;
        }
        if(txtLugar.getText().toString().trim().isEmpty()){
            txtLugar.setError("No completo los datos");
            bandera = false;
        }
        if(tipo == null || tipo.getId() == 0){
            cbTipos.requestFocus();
            Toast.makeText(context,"Primero debe seleccionar un tipo.",Toast.LENGTH_LONG).show();
            bandera = false;
        }
        if(mCurrentPhotoPath.isEmpty()){
            Toast.makeText(context,"Por favor tome una foto antes de enviar la solicitud!!!",Toast.LENGTH_LONG).show();
            bandera = false;
        }
        return bandera;
    }

    private String parseObjectToJson(){
        final EditText txtLugar = findViewById(R.id.txtLugar);
        final EditText txtDescripcion = findViewById(R.id.txtDescripcion);

        lugar = txtLugar.getText().toString();
        descripcion = txtDescripcion.getText().toString();

        Gson gsonObjetc = new Gson();

        Incident incident = new Incident();
        incident.setComment(descripcion);
        incident.setPlace(lugar);
        incident.setState(new EstadoIncidente(1,""));
        incident.setType(tipo);
        incident.setCitizen(new Ciudadano(1,"","",""));
        incident.setEmploye(new Empleado(2,"","",""));
        incident.setImagen(foto);

        String gson = gsonObjetc.toJson(incident);
        Log.i(TAG,gson);
        return gson;
    }

    private void limpiarCampos(){
        final EditText txtLugar = findViewById(R.id.txtLugar);
        final EditText txtDescripcion = findViewById(R.id.txtDescripcion);
        final Spinner cbTipos = findViewById(R.id.cbTipos);

        txtLugar.setText("");
        txtDescripcion.setText("");
        cbTipos.setSelection(0);
        txtLugar.requestFocus();
    }

    String mCurrentPhotoPath;

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = image.getAbsolutePath();
        return image;
    }

}
