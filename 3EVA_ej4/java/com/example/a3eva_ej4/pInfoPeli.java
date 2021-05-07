package com.example.a3eva_ej4;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class pInfoPeli extends AppCompatActivity {
    String poster;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.infopeli);
        ActionBar actionbar = getSupportActionBar();

        poster = getIntent().getExtras().getString("info");
        actionbar.setTitle(poster);
    }
}
