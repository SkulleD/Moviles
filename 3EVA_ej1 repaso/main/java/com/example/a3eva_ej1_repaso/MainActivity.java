package com.example.a3eva_ej1_repaso;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText txtNum1;
    EditText txtNum2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnCalcular;
        btnCalcular = findViewById(R.id.btnCalcular);

        txtNum1 = findViewById(R.id.txtnum1);
        txtNum2 = findViewById(R.id.txtnum2);

        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Aprieta más tiempo", Toast.LENGTH_SHORT).show();
            }
        });

        btnCalcular.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                int num1 = Integer.parseInt(txtNum1.getText().toString());
                int num2 = Integer.parseInt(txtNum2.getText().toString());
                int[] calculo = {num1, num2};

                Intent intent = new Intent(MainActivity.this, Resultados.class);
                intent.putExtra("calcular", calculo);
                startActivity(intent);
                return true;
            }
        });
    }
}