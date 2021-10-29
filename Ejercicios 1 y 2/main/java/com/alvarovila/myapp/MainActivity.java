package com.alvarovila.myapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public static final String tag = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) { // La actividad se está creando.
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.v(tag, "método onCreate");
    }
    @Override
    protected void onStart() { // La actividad está a punto de hacerse visible.
        super.onStart();
        Log.d(tag, "método onStart");
    }
    @Override
    protected void onResume() { // La actividad se ha hecho visible, se ha reanudado.
        super.onResume();
        Log.i(tag, "método onResume");
    }
    @Override
    protected void onPause() { // Otra actividad está tomando el foco y esta actividad va a ser "pausada".
        super.onPause();
        Log.e(tag, "método onPause");
    }
    @Override
    protected void onStop() { // La actividad ya no es visible y ahora está "parada"
        super.onStop();
        Log.i(tag, "onStop");
    }
    @Override
    protected void onRestart() { // La actividad, que estaba parada, está a punto de iniciarse.
        super.onRestart();
        Log.w(tag, "método onRestart");
    }
    @Override
    protected void onDestroy() { // La actividad está a punto de ser destruida.
        super.onDestroy();
        Log.v(tag, "método onDestroy");
    }
}