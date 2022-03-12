package com.mygdx.byebee.characters;

import com.badlogic.gdx.graphics.Texture;

/**
 * Clase que representa la cantidad de salud de la abeja.
 */
public class Health {

    /**
     * La posición en el eje X de la salud.
     */
    float posX;

    /**
     * La posición en el eje Y de la salud.
     */
    float posY;

    /**
     * La anchura de la salud.
     */
    float width;

    /**
     * La altura de la salud.
     */
    float height;

    /**
     * Las imágenes que representa a la salud. Van cambiando a medida que se pierde salud.
     */
    Texture[] texture;

    /**
     * Constructor que inicializa los parámetros iniciales de la salud.
     * @param posX La posición en el eje X de la salud.
     * @param posY La posición en el eje Y de la salud.
     * @param width La anchura de la salud.
     * @param height La altura de la salud.
     * @param texture La imagen que representa a la salud.
     */
    public Health(float posX, float posY, float width, float height, Texture[] texture) {
        this.posX = posX;
        this.posY = posY;
        this.width = width;
        this.height = height;
        this.texture = new Texture[8];
        this.texture[0] = new Texture("vida0.png");
        this.texture[1] = new Texture("vida1.png");
        this.texture[2] = new Texture("vida2.png");
        this.texture[3] = new Texture("vida3.png");
        this.texture[4] = new Texture("vida4.png");
        this.texture[5] = new Texture("vida5.png");
        this.texture[6] = new Texture("vida6.png");
        this.texture[7] = new Texture("vida7.png");
    }

    /**
     * Método para conseguir la posición X de la salud.
     * @return la posición X de la salud.
     */
    public float getPosX() {
        return posX;
    }

    /**
     * Método para establecer la posición X de la salud.
     * @param posX la posición X de la salud.
     */
    public void setPosX(float posX) {
        this.posX = posX;
    }

    /**
     * Método para conseguir la posición Y de la salud.
     * @return la posición Y de la salud.
     */
    public float getPosY() {
        return posY;
    }

    /**
     * Método para establecer la posición Y de la salud.
     * @param posY la posición Y de la salud.
     */
    public void setPosY(float posY) {
        this.posY = posY;
    }

    /**
     * Método para conseguir la anchura de la salud.
     * @return la anchura de la salud.
     */
    public float getWidth() {
        return width;
    }

    /**
     * Método para establecer la anchura de la salud.
     * @param width la anchura de la salud.
     */
    public void setWidth(float width) {
        this.width = width;
    }

    /**
     * Método para conseguir la altura de la salud.
     * @return la altura de la salud.
     */
    public float getHeight() {
        return height;
    }

    /**
     * Método para establecer la altura de la salud.
     * @param height la altura de la salud.
     */
    public void setHeight(float height) {
        this.height = height;
    }

    /**
     * Método para conseguir las imágenes de la salud.
     * @return las imágenes de la salud.
     */
    public Texture[] getTexture() {
        return texture;
    }

    /**
     * Método para establecer las imágenes de la salud.
     * @param texture las imágenes de la salud.
     */
    public void setTexture(Texture[] texture) {
        this.texture = texture;
    }
}