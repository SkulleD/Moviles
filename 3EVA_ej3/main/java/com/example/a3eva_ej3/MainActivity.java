package com.example.a3eva_ej3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
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
    RadioButton radioButton2;
    RadioButton radioButton3;
    TextView txtCheck;
    DatePicker datePicker;
    EditText txtCalendar;
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
        radioButton2 = findViewById(R.id.radioButton2);
        radioButton3 = findViewById(R.id.radioButton3);
        datePicker = findViewById(R.id.datePicker);
        txtCalendar = findViewById(R.id.editTextDate);

        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!checkBox.isChecked()) {
                    valor++;
                    txtBoton.setText((valor) + "");
                } else {
                    valor--;
                    txtBoton.setText((valor) + "");
                }
            }
        });

        botonggle.setOnClickListener(new View.OnClickListener() {
            boolean flag = false;

            @Override
            public void onClick(View v) {
                if (flag) {
                    getWindow().getDecorView().setBackgroundColor(Color.WHITE);
                } else {
                    getWindow().getDecorView().setBackgroundColor(Color.GRAY);
                }
                flag = !flag;
            }
        });

        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBox.isChecked()) {
                    boton.setText("RESTAR");
                    checkBox.setText("Cambiar a Suma");
                } else {
                    boton.setText("SUMAR");
                    checkBox.setText("Cambiar a Resta");
                }
            }
        });

        radioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtCheck.setText("1º seleccionado");
            }
        });

        radioButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtCheck.setText("2º seleccionado");
            }
        });

        radioButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtCheck.setText("3º seleccionado");
            }
        });

        datePicker.init(datePicker.getYear(), datePicker.getMonth(), datePicker.getDayOfMonth(), new DatePicker.OnDateChangedListener(){
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                txtCalendar.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
            }
        });
    }
}