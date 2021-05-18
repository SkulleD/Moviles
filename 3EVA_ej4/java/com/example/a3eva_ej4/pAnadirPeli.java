package com.example.a3eva_ej4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class pAnadirPeli extends AppCompatActivity {
    Spinner spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.anadir_peli);
        String[] valores = {"Gran Vía", "Plaza Elíptica", "Travesía"};
        spinner = findViewById(R.id.spinnerAddSala);
        ArrayAdapter<String> arrayadapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, valores);
        spinner.setAdapter(arrayadapter);
    }
}