package com.example.a3eva_ej4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.Calendar;

public class pFavoritas extends AppCompatActivity {
    private ArrayList<Pelicula> peliculas;
    private ListView listView;
    private ArrayAdapter<Pelicula> arrayAdapter;
    private Datos datos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_favs);
        listView = findViewById(R.id.listViewFavs);
        datos = new Datos();
        peliculas = datos.rellenaPeliculas();

        arrayAdapter = new ArrayAdapter<Pelicula>(this, android.R.layout.simple_list_item_1, peliculas);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String cadena = "Posición" + position + "\n" + arrayAdapter.getItem(position) + "\n" + peliculas.get(position);
                Toast.makeText(pFavoritas.this, cadena, Toast.LENGTH_LONG).show();
            }
        });
    }
}