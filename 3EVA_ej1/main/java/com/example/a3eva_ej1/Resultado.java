package com.example.a3eva_ej1;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Resultado extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resultado);

        int[] valores;
        int suma;
        int resta;
        int multiplicacion;
        int division;

        TextView txtsuma;
        txtsuma = findViewById(R.id.txtsuma);
        TextView txtresta;
        txtresta = findViewById(R.id.txtresta);
        TextView txtmulti;
        txtmulti = findViewById(R.id.txtmulti);
        TextView txtdivision;
        txtdivision = findViewById(R.id.txtdivision);

        Intent intent = getIntent();
        valores = intent.getIntArrayExtra("Calcular");
        //Log.i("nums", "numero1 " + valores[0]);
        //Log.i("nums", "numero2 " + valores[1]);

        txtsuma.setText(("Suma: " + (valores[0] + valores[1])));
        txtresta.setText(("Resta: " + (valores[0] - valores[1])));
        txtmulti.setText(("Multiplicación: " + valores[0] * valores[1]));
        txtdivision.setText(("División: " + valores[0] / valores[1]));
    }
}
