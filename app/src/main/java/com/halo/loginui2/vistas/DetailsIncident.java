package com.halo.loginui2.vistas;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.halo.loginui2.R;

import java.io.File;
import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;

public class DetailsIncident extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_incident);

        Bundle bundle = getIntent().getExtras();

        final TextView lblLugar = findViewById(R.id.lblLugar);
        final TextView lblFecha = findViewById(R.id.lblFecha);
        final TextView lblEstado = findViewById(R.id.lblEstado);
        final TextView lblTipo = findViewById(R.id.lblTipo);
        final TextView lblDescripcion = findViewById(R.id.lblDescripcion);
        final ImageView imgFoto = findViewById(R.id.imgFoto);

        lblLugar.setText(bundle.getString("lugar"));
        lblFecha.setText(bundle.getString("fecha"));
        lblEstado.setText(bundle.getString("estado"));
        lblTipo.setText(bundle.getString("tipo"));
        lblDescripcion.setText(bundle.getString("descripcion"));

        Bitmap bmp = BitmapFactory.decodeFile(bundle.getString("imagen"));

        // Ponemos nuestro bitmap en un ImageView que tengamos en la vista
        imgFoto.setImageBitmap(bmp);

    }
}
