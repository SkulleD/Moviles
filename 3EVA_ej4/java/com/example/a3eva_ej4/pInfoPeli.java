package com.example.a3eva_ej4;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class pInfoPeli extends AppCompatActivity {
    Pelicula peli;
    Datos datos;
    ImageView poster;
    TextView sinopsis;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.infopeli);
        datos = new Datos();
        poster = findViewById(R.id.pPoster);
        sinopsis = findViewById(R.id.pSinopsis);
        ActionBar actionbar = getSupportActionBar();

        Log.i("pos", "fdf ");
        peli = (Pelicula) getIntent().getSerializableExtra("info");
        actionbar.setTitle(peli.getTitulo());

        poster.setImageResource(peli.getPortada());
        sinopsis.setText(peli.getSinopsis());

        poster.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datos.watchYoutubeVideo(peli.getIdYoutube());
            }
        });
    }
}
