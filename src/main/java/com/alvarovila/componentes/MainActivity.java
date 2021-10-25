package com.alvarovila.componentes;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Rating;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {
    ToggleButton tglButton;
    CheckBox check1;
    CheckBox check2;
    CheckBox check3;
    SeekBar seekbar;
    Switch btnSwitch;
    TextView seekbarNum;
    TextView txtView5;
    TextView txtView2;
    EditText editText;
    Button button1;
    Button button2;
    RatingBar rating;
    RadioButton radio1;
    RadioButton radio2;
    RadioGroup radioGroup;
    ImageButton imgBtn;
    boolean decrement = false;
    ActivityResultLauncher<Intent> launcher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tglButton = findViewById(R.id.toggleButton);
        check1 = findViewById(R.id.checkBox);
        check2 = findViewById(R.id.checkBox2);
        check3 = findViewById(R.id.checkBox3);
        seekbar = findViewById(R.id.seekBar2);
        seekbarNum = findViewById(R.id.SeekBarNum);
        btnSwitch = findViewById(R.id.switch1);
        txtView5 = findViewById(R.id.textView5);
        txtView2 = findViewById(R.id.textView2);
        editText = findViewById(R.id.editTextTextPersonName);
        button1 = findViewById(R.id.button);
        button2 = findViewById(R.id.button2);
        rating = findViewById(R.id.ratingBar);
        radio1 = findViewById(R.id.radioButton);
        radio2 = findViewById(R.id.radioButton2);
        radioGroup = findViewById(R.id.radioGroup);
        imgBtn = findViewById(R.id.imageButton);

        launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if (result.getResultCode()==RESULT_OK) {
                    Intent intent = result.getData();
                    editText.setText(intent.getStringExtra("texto2"));
                    rating.setRating(intent.getFloatExtra("valor2", 3));
                }
            }
        });

        button1.setOnClickListener(new View.OnClickListener() { // REINICIAR T0D0
            @Override
            public void onClick(View view) {
                btnSwitch.setChecked(false);
                rating.setRating(3);
                editText.setText("");
                radio1.setChecked(false);
                radio2.setChecked(false);
                seekbar.setProgress(0);
                tglButton.setChecked(false);
                check1.setChecked(false);
                check2.setChecked(false);
                check3.setChecked(false);
            }
        });

        tglButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                check1.setChecked(!b);
            }
        });

        check1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

            }
        });

        check2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                decrement=!decrement;
            }
        });

        check3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

            }
        });

        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                seekbarNum.setText(""+i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        btnSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    txtView5.setText(R.string.activo);
                } else {
                    txtView5.setText(R.string.desactivado);
                }
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            int cont = 0;

            @Override
            public void onClick(View view) {
                if (!decrement) {
                    cont++;
                } else {
                    cont--;
                }

                txtView2.setText(cont + "");
            }
        });

        imgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Secundaria.class);
                intent.putExtra("valor", rating.getRating());
                intent.putExtra("texto", editText.getText().toString());

                launcher.launch(intent);
            }
        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            String cadena = "";

            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (radio1.isChecked()) {
                    cadena = "Pulsado el primero";
                    Toast.makeText(getApplicationContext(), cadena, Toast.LENGTH_SHORT).show();
                }

                if (radio2.isChecked()) {
                    cadena = "Pulsado el segundo";
                    Toast.makeText(getApplicationContext(), cadena, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}