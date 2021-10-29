package com.alvarovila.holapersonalizado;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button btnAceptar;
    TextView textResult;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAceptar = findViewById(R.id.button);
        textResult = findViewById(R.id.textView2);
        editText = findViewById(R.id.editText);

        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String texto = editText.getText().toString();

                if (texto.contains("a") || texto.contains("A")) {
                    textResult.setText(texto);

                    Log.i("pulsado", "mensaje de ejemplo");
                }
            }
        });
    }
}