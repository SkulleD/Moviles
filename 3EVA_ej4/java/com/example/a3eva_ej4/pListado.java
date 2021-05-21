package com.example.a3eva_ej4;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Calendar;

public class pListado extends AppCompatActivity {
    private ArrayList<Pelicula> peliculas;
    private pListadoAdapter pListadoAdapter;
    private RecyclerView rv;
    private RecyclerView.LayoutManager pLayout;
    int posi;
    Intent intentPeliInfo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setTitle("Listado de películas");
        actionbar.setHomeButtonEnabled(true);
        actionbar.setDisplayHomeAsUpEnabled(true);

        crearLista();
        crearRecyclerView();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivityForResult(intent, 0);
        return true;
    }

    public void crearLista() {
        Datos datos = new Datos();
        peliculas = datos.rellenaPeliculas();
    }

    public void crearRecyclerView() {
        pListadoAdapter = new pListadoAdapter(peliculas);
        rv = findViewById(R.id.recyclerFavs);
        rv.setHasFixedSize(true);
        pLayout = new LinearLayoutManager(this);
        rv.setLayoutManager(pLayout);
        rv.setAdapter(pListadoAdapter);
        rv.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL));
        intentPeliInfo = new Intent(this, pInfoPeli.class);

        pListadoAdapter.setOnItemClickListener(new pListadoAdapter.onItemClickListener() {
            @Override
            public void onItemClick(int position) {
                posi = position;
                Log.i("pos", "dfgfd " + position);
                intentPeliInfo.putExtra("info", peliculas.get(position));
                startActivity(intentPeliInfo);
            }
        });
    }
}
