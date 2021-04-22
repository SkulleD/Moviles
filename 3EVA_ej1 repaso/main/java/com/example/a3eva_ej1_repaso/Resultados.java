package com.example.a3eva_ej1_repaso;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Resultados extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resultados);

        TextView txtSuma = findViewById(R.id.txtsuma);
        TextView txtResta = findViewById(R.id.txtresta);
        TextView txtMulti = findViewById(R.id.txtmulti);
        TextView txtDivision = findViewById(R.id.txtdivision);
        int[] valores;

        Intent intent = getIntent();
        valores = intent.getIntArrayExtra("calcular");

        txtSuma.setText(("Suma: " + (valores[0] + valores[1])));
        txtResta.setText(("Resta: " + (valores[0] - valores[1])));
        txtMulti.setText(("Multiplicación: " + (valores[0] * valores[1])));
        txtDivision.setText(("División: " + (valores[0] / valores[1])));
    }
}
