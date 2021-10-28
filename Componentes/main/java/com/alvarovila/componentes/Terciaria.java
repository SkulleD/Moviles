package com.alvarovila.componentes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class Terciaria extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terciaria);

        Intent intent = getIntent();
        Toast.makeText(this, "Funciono", Toast.LENGTH_LONG).show();
        Log.i("checked", String.valueOf(intent.getBooleanExtra("checked1", false)));
        Log.i("checked", String.valueOf(intent.getBooleanExtra("checked2", false)));
        Log.i("checked", String.valueOf(intent.getBooleanExtra("checked3", false)));
    }
}