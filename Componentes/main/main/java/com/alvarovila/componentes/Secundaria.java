package com.alvarovila.componentes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Rating;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

public class Secundaria extends AppCompatActivity {
    RatingBar ratingBar;
    Button btnDevolver;
    EditText editText2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secundaria);

        editText2 = findViewById(R.id.textSecundaria);
        ratingBar = findViewById(R.id.ratingBar2);
        btnDevolver = findViewById(R.id.btnDevolver);

        Intent intent2= getIntent();
        editText2.setText(intent2.getStringExtra("texto"));
        ratingBar.setRating(intent2.getFloatExtra("valor", 3));
        Log.i("valor string", intent2.getStringExtra("texto"));
        Log.i("valor float", String.valueOf(intent2.getFloatExtra("valor", 3)));

        btnDevolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra("valor2", ratingBar.getRating());
                intent.putExtra("texto2", editText2.getText().toString());
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}