package com.example.a3eva_ej1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static java.lang.Integer.parseInt;

public class MainActivity extends AppCompatActivity {
    EditText num1;
    EditText num2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnCalcular;
        btnCalcular = findViewById(R.id.btnCalcular);

        num1 = findViewById(R.id.num1);
        num2= findViewById(R.id.num2);

        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Aprieta más tiempo", Toast.LENGTH_SHORT).show();
            }
        });

        btnCalcular.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                int numero1 = Integer.parseInt(num1.getText().toString());
                int numero2 = Integer.parseInt(num2.getText().toString());
                int[] numsCalcular = {numero1, numero2};

                Intent intent = new Intent (MainActivity.this, Resultado.class);
                intent.putExtra("Calcular", numsCalcular);
                //intent.putExtra("num1", numero1);
                //intent.putExtra("num2", numero2);
                startActivity(intent);
                return true;
            }
        });
    }
}