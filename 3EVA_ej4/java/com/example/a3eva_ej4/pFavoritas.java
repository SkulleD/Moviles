package com.example.a3eva_ej4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.Calendar;

public class pFavoritas extends AppCompatActivity {
    private ArrayList<Pelicula> peliculas;
    private pFavAdapter pFavAdapter;
    private RecyclerView rv;
    private RecyclerView.LayoutManager pLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler);

        crearLista();
        crearRecyclerView();
    }

    public void crearLista() {
        Datos datos = new Datos();
        peliculas = datos.rellenaPeliculas();
    }

    public void crearRecyclerView() {
        pFavAdapter = new pFavAdapter(peliculas);
        rv = findViewById(R.id.recyclerFavs);
        rv.setHasFixedSize(true);
        pLayout = new LinearLayoutManager(this);
        rv.setLayoutManager(pLayout);
        rv.setAdapter(pFavAdapter);
    }
}