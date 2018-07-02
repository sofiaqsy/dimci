package com.halo.loginui2.vistas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.halo.loginui2.R;
import com.bumptech.glide.Glide;


public class DetailsNoticias extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_noticias);
        getIncomingIntent();

    }


    private void getIncomingIntent(){

        if(getIntent().hasExtra("title") && getIntent().hasExtra("image_name")){

            String title = getIntent().getStringExtra("title");
            String description = getIntent().getStringExtra("description");
            String datePublication = getIntent().getStringExtra("datePublication");

            setNoticia(title, description, datePublication);
        }
    }


    private void setNoticia(String title, String description, String datePublication){

       TextView lbldatePublication = findViewById(R.id.datePublication);
        lbldatePublication.setText(datePublication);

        TextView lbltitle = findViewById(R.id.title);
        lbltitle.setText(title);

        TextView lbldescription = findViewById(R.id.description);
        lbldescription.setText(description);

        ImageView imagen = findViewById(R.id.imagen);
        Glide.with(this)
                .load("https://jsequeiros.com/sites/default/files/imagen-cachorro-comprimir.jpg")
                .into(imagen);
    }


}
