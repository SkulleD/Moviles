package com.example.repasojuego;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.PointF;
import android.os.Build;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import java.util.HashMap;

public class SurfaceViewClase extends SurfaceView implements SurfaceHolder.Callback {
    private SurfaceHolder surfaceHolder;
    private Context context;
    private Bitmap imagenFondo;
    private Bitmap imagenNave;
    private int anchoPantalla = 1;
    private int altoPantalla = 1;
    private Hilo hilo;
    private boolean running = false;
    private boolean esTitulo = true;
    private static final int MIN_DXDY = 2;
    final private static HashMap<Integer, PointF> posiciones = new HashMap<>();

    public SurfaceViewClase(Context context) {
        super(context);
        this.surfaceHolder = getHolder();
        this.surfaceHolder.addCallback(this);
        this.context = context;

        hilo = new Hilo();
        setFocusable(true);
        imagenFondo = BitmapFactory.decodeResource(context.getResources(), R.drawable.fondovivas);
        imagenNave = BitmapFactory.decodeResource(context.getResources(), R.drawable.nave);
    }

    public void actualizaFisica() {

    }

    public void dibujar(Canvas canvas) {
        try {
            canvas.drawBitmap(imagenFondo, 0, 0, null);

            if (!esTitulo) {
                for (PointF posicion : posiciones.values()) {
                    float x = posicion.x - imagenNave.getWidth() / 2;
                    float y = posicion.y - imagenNave.getHeight() / 2;
                    canvas.drawBitmap(imagenNave, x ,y, null);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder) {
        hilo.setRunning(true);

        if (hilo.getState() == Thread.State.NEW) {
            hilo.start();
        }

        if (hilo.getState() == Thread.State.TERMINATED) {
            hilo = new Hilo();
            hilo.start();
        }
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {
        anchoPantalla = width;
        altoPantalla = height;
        hilo.setSurfaceSize(width, height);
    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder holder) {
        hilo.setRunning(false);

        try {
            hilo.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        synchronized (surfaceHolder) {
            int pointerIndex = event.getActionIndex();
            int pointerID = event.getPointerId(pointerIndex);
            int accion = event.getActionMasked();
            float x = event.getX();
            float y = event.getY();

            switch (accion) {
                case MotionEvent.ACTION_UP:
                    break;
                case MotionEvent.ACTION_POINTER_UP:
                    if (esTitulo) {
                        imagenFondo = BitmapFactory.decodeResource(getResources(), R.drawable.tierraluna);
                        imagenFondo = Bitmap.createScaledBitmap(imagenFondo, anchoPantalla, altoPantalla, true);
                        esTitulo = false;
                    } else {
                        posiciones.remove((pointerID));
                        Log.i("ACTION", "UP -> ActionIndex=" + pointerIndex + " " + "PointerID=" + pointerID);
                    }
                    break;
                case MotionEvent.ACTION_DOWN:
                    break;
                case MotionEvent.ACTION_POINTER_DOWN:
                    if (!esTitulo) {
                        PointF posicion = new PointF(event.getX(pointerIndex), event.getY(pointerIndex));
                        posiciones.put(pointerID, posicion);
                        Log.i("ACTION", "DOWN -> ActionIndex=" + pointerIndex + " " + "PointerID=" + pointerID);
                    }
                    break;
                case MotionEvent.ACTION_MOVE:
                    if (!esTitulo) {
                        for (int i = 0; i < event.getPointerCount(); i++) {
                            int ID = event.getPointerId(i);
                            PointF posicion = posiciones.get(ID);

                            if (posicion != null) {
                                if (Math.abs(posicion.x - event.getX(i)) > MIN_DXDY || Math.abs(posicion.y - event.getY(i)) > MIN_DXDY) {
                                    posicion.set(event.getX(i), event.getY(i));
                                }
                            }
                        }
                        Log.i("ACTION ","MOVE -> ActionIndex="+pointerIndex+" "+"PointerID="+pointerID);
                    }
                    break;
            }
        }
        return true;
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
                    if (canvasHilo != null) {
                        surfaceHolder.unlockCanvasAndPost(canvasHilo);
                    }
                }
            }
        }

        void setRunning(boolean flag) {
            running = flag;
        }

        public void setSurfaceSize(int width, int height) {
            synchronized (surfaceHolder) {
                if (imagenFondo != null) {
                    imagenFondo = Bitmap.createScaledBitmap(imagenFondo, width, height, true);
                }
            }
        }
    }
}
