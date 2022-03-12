package com.mygdx.byebee.characters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.byebee.screens.ByeBee;

/**
 * Esta clase representa a los botones de opciones del juego.
 */
public class Options {

    /**
     * La posición en el eje X del personaje.
     */
    float posX;

    /**
     * La posición en el eje Y del personaje.
     */
    float posY;

    /**
     * El ancho del personaje
     */
    float width;

    /**
     * El alto del personaje.
     */
    float height;

    /**
     * La imagen que representa al personaje.
     */
    Texture texture;

    /**
     * La hitbox del botón.
     */
    Rectangle boton;

    /**
     * Constructor que inicializa los parámetros iniciales de los botones.
     * @param posX La posición en el eje X del botón.
     * @param posY La posición en el eje Y del botón.
     * @param width La anchura del botón.
     * @param height La altura del botón.
     * @param texture La imagen del botón.
     */
    public Options(float posX, float posY, float width, float height, Texture texture) {
        this.posX = posX;
        this.posY = posY;
        this.width = width;
        this.height = height;
        this.texture = texture;
        boton = new Rectangle(this.posX, this.posY, this.width, this.height);
    }

    /**
     * Método para conseguir la posición X del botón.
     * @return la posición X del botón.
     */
    public float getPosX() {
        return posX;
    }

    /**
     * Método para establecer la posición X del botón.
     * @param posX la posición X del botón.
     */
    public void setPosX(float posX) {
        this.posX = posX;
    }

    /**
     * Método para conseguir la posición Y del botón.
     * @return la posición Y del botón.
     */
    public float getPosY() {
        return posY;
    }

    /**
     * Método para establecer la posición Y del botón.
     * @param posY la posición Y del botón.
     */
    public void setPosY(float posY) {
        this.posY = posY;
    }

    /**
     * Método para conseguir la anchura del botón.
     * @return la anchura del botón.
     */
    public float getWidth() {
        return width;
    }

    /**
     * Método para establecer la anchura del botón.
     * @param width la anchura del botón.
     */
    public void setWidth(float width) {
        this.width = width;
    }

    /**
     * Método para conseguir la altura del botón.
     * @return la altura del botón.
     */
    public float getHeight() {
        return height;
    }

    /**
     * Método para establecer la altura del botón.
     * @param height la altura del botón.
     */
    public void setHeight(float height) {
        this.height = height;
    }

    /**
     * Método para conseguir la imagen del botón.
     * @return la imagen del botón.
     */
    public Texture getTexture() {
        return texture;
    }

    /**
     * Método para establecer la imagen del botón.
     * @param texture la imagen del botón.
     */
    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    /**
     * Método para conseguir la hitbox del botón
     * @return la hitbox del botón
     */
    public Rectangle getBoton() {
        return boton;
    }

    /**
     * Método para determinar la hitbox del botón.
     * @param boton la hitbox del botón
     */
    public void setBoton(Rectangle boton) {
        this.boton = boton;
    }
}