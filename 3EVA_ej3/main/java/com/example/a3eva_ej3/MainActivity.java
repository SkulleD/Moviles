package com.example.a3eva_ej3;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {
    Button boton;
    ToggleButton botonggle;
    TextView txtBoton;
    CheckBox checkBox;
    RadioGroup radioGroup;
    RadioButton radioButton;
    TextView txtCheck;
    int valor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        valor = 0;
        boton = findViewById(R.id.button);
        txtBoton = findViewById(R.id.buttontxt);
        botonggle = findViewById(R.id.buttonFondo);
        checkBox = findViewById(R.id.checkBox);
        txtCheck = findViewById(R.id.textView2);
        radioGroup = findViewById(R.id.radioGroup);
        radioButton = findViewById(R.id.radioButton);
        radioButton = findViewById(R.id.radioButton2);
        radioButton = findViewById(R.id.radioButton3);
        //DatePicker = findViewById(R.id.editTextDate);

        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!checkBox.isChecked()) {
                    txtBoton.setText((valor++) + "");
                } else {
                    txtBoton.setText((valor--) + "");
                }

            }
        });

        botonggle.setOnClickListener(new View.OnClickListener() {
            boolean flag = false;

            @Override
            public void onClick(View v) {
                if (flag) {
                    botonggle.setBackgroundColor(Color.WHITE);
                } else {
                    botonggle.setBackgroundColor(Color.GRAY);
                }
            }
        });

        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBox.isChecked()) {
                    boton.setText("RESTAR");
                } else {
                    boton.setText("SUMAR");
                }
            }
        });
    }
}