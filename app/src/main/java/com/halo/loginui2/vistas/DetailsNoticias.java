package com.halo.loginui2.vistas;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.halo.loginui2.R;
import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;


public class DetailsNoticias extends AppCompatActivity {

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_noticias);

        Bundle bundle = getIntent().getExtras();

        final TextView lblTitle = findViewById(R.id.title);
        final TextView lblDescription = findViewById(R.id.description);
        final TextView lblDatePublication = findViewById(R.id.datePublication);
        final ImageView imgFoto = findViewById(R.id.imagen);


        lblTitle.setText(bundle.getString("title"));
        lblDescription.setText(bundle.getString("description"));
        lblDatePublication.setText(bundle.getString("datePublication"));


        /*Bitmap bmp = BitmapFactory.decodeFile(bundle.getString("imagen"));

        // Ponemos nuestro bitmap en un ImageView que tengamos en la vista
        imgFoto.setImageBitmap(bmp);*/
        Picasso.get().load(bundle.getString("imagen")).into(imgFoto);


    }




}
