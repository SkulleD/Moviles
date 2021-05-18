package com.example.a3eva_ej4;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

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
    int request = 1;


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
    public boolean onSupportNavigateUp() {
        Intent intent = new Intent(this, MainActivity.class);
        finish();
        return true;
    }

    public void crearLista() {
        Calendar cal = Calendar.getInstance();
        Pelicula dune = new Pelicula("Dune", "Lynch", 180, cal.getTime(), "Gran vía", R.drawable.g, R.drawable.dune);
        Pelicula a2001 = new Pelicula("2001", "Kubric", 130, cal.getTime(), "Plaza elíptica", R.drawable.pg, R.drawable.d2001);
        Pelicula akira = new Pelicula("Akira", "Otomo", 170, cal.getTime(), "Gran vía", R.drawable.pg13, R.drawable.akira);
        Pelicula ia = new Pelicula("IA", "Spielberg", 140, cal.getTime(), "Travesia", R.drawable.r, R.drawable.ia);
        Pelicula matrix = new Pelicula("Matrix", "Lana Wachowski, Lilly Wachowski", 136, cal.getTime(), "Gran vía", R.drawable.pg13, R.drawable.matrix);
        Pelicula br = new Pelicula("Blade Runner", "Ridley Scott", 117, cal.getTime(), "Plaza elíptica", R.drawable.pg, R.drawable.blade);
        Pelicula inte = new Pelicula("Interstellar", "Christopher Nolan ", 169, cal.getTime(), "Travesia", R.drawable.g, R.drawable.interstellar);
        Pelicula alien = new Pelicula("Alien", "Ridley Scott", 117, cal.getTime(), "Plaza elíptica", R.drawable.pg13, R.drawable.alien);
        Pelicula st = new Pelicula("Star Trek", "J. J. Abrams", 128, cal.getTime(), "Travesia", R.drawable.pg, R.drawable.startrek);
        Pelicula martian = new Pelicula("The Martian", "Ridley Scotts", 151, cal.getTime(), "Gran vía", R.drawable.pg13, R.drawable.martian);

        peliculas = new ArrayList<>();
        peliculas.add(dune);
        peliculas.add(a2001);
        peliculas.add(akira);
        peliculas.add(ia);
        peliculas.add(matrix);
        peliculas.add(br);
        peliculas.add(inte);
        peliculas.add(alien);
        peliculas.add(st);
        peliculas.add(martian);
    }

    public void crearRecyclerView() {
        pListadoAdapter = new pListadoAdapter(peliculas);
        rv = findViewById(R.id.recyclerFavs);
        rv.setHasFixedSize(true);
        pLayout = new LinearLayoutManager(this);
        rv.setLayoutManager(pLayout);
        rv.setAdapter(pListadoAdapter);
        rv.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL));
        Intent intentPeliInfo = new Intent(this, pInfoPeli.class);

        pListadoAdapter.setOnItemClickListener(new pListadoAdapter.onItemClickListener() {
            @Override
            public void onItemClick(int position) {
                posi = position;
                Log.i("pos", "" + position);
                intentPeliInfo.putExtra("info", request);
                startActivity(intentPeliInfo);
            }
        });
    }
}
