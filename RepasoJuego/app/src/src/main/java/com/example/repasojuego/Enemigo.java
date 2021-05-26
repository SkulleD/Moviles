package com.example.repasojuego;

import android.graphics.Bitmap;
import android.graphics.PointF;

import java.util.Random;

public class Enemigo {
    public PointF posicion;
    public Bitmap imagen;
    private Random g;

    public Enemigo(Bitmap imagen, float x, float y) {
        this.imagen = imagen;
        this.posicion = new PointF(x, y);
        g = new Random();
    }

    public void moverEnemigo(int alto, int ancho, int velocidad) {
        posicion.y = posicion.y + velocidad; //posicion.y += velocidad;

        if (posicion.y > velocidad) {
            posicion.y = 0;
            posicion.x = g.nextFloat() * (ancho - imagen.getWidth());
        }
    }
}
