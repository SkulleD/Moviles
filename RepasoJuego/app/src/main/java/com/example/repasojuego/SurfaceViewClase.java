package com.example.repasojuego;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Build;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

public class SurfaceViewClase extends SurfaceView implements SurfaceHolder.Callback {
    private SurfaceHolder surfaceHolder;
    private Context context;
    private Bitmap imagenFondo;
    private int anchoPantalla = 1;
    private int altoPantalla = 1;
    private Hilo hilo;
    private boolean running = false;

    public SurfaceViewClase(Context context) {
        super(context);
        this.surfaceHolder = getHolder();
        this.surfaceHolder.addCallback(this);
        this.context = context;

        hilo = new Hilo();
        setFocusable(true);
        imagenFondo = BitmapFactory.decodeResource(context.getResources(), R.drawable.fondovivas);
    }

    public void actualizaFisica() {

    }

    public void dibujar(Canvas canvas) {
        try {
            canvas.drawBitmap(imagenFondo, 0, 0, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder) {

    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder holder) {

    }

    class Hilo extends Thread {
        public Hilo() {

        }

        @RequiresApi(api = Build.VERSION_CODES.O)
        @Override
        public void run() {
            while (running) {
                Canvas canvasHilo = null;

                try {
                    if (!surfaceHolder.getSurface().isValid()) {
                        continue;
                    }

                    canvasHilo = surfaceHolder.lockHardwareCanvas();
                    synchronized (surfaceHolder) {
                        actualizaFisica();
                        dibujar(canvasHilo);
                    }
                } finally {

                }
            }
        }
    }
}
