package com.example.a3eva_ej4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    ArrayList<Pelicula> peliculas;
    RecyclerView rv;
    pAdapter pAdapter;
    RecyclerView.LayoutManager pLayout;
    TextView peliTitulo;
    ImageView hidebar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        peliTitulo = findViewById(R.id.peliTitulo);
        hidebar = findViewById(R.id.hideBar);
        ActionBar actionabr = getSupportActionBar();

        peliculas = new ArrayList<Pelicula>();

        crearLista();
        crearRecyclerView();

        hidebar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (actionabr.isShowing()) {
                    actionabr.hide();
                } else {
                    actionabr.show();
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intentList = new Intent(this, pListado.class);
        Intent intentFav = new Intent(this, pFavoritas.class);
        Intent intentNueva = new Intent(this, pAnadirPeli.class);

        switch (item.getItemId()) {
            case R.id.mListado:
                startActivity(intentList);
                return true;

            case R.id.mFavorita:
                startActivity(intentFav);
            return true;

            case R.id.mAnadir:
                startActivity(intentNueva);
                return true;
        }
        return super.onOptionsItemSelected(item);
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
        pAdapter = new pAdapter(peliculas);
        rv = findViewById(R.id.recyclerMain);
        rv.setHasFixedSize(true);
        pLayout = new LinearLayoutManager(this);
        rv.setLayoutManager(pLayout);
        rv.setAdapter(pAdapter);
        rv.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL));

        pAdapter.setOnItemClickListener(new pAdapter.onItemClickListener() {
            @Override
            public void onItemClick(int position) {
                peliTitulo.setText(peliculas.get(position).getTitulo());
                pAdapter.notifyItemChanged(position);
            }
        });
    }
}