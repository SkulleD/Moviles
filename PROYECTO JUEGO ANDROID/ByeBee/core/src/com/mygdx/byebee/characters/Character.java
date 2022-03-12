package com.mygdx.byebee.characters;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Esta es la clase de la que heredan la clase Bee y Enemy.
 */
public class Character {

    /**
     * La velocidad del personaje.
     */
    Vector2 speed;

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
     * La cantidad de salud del personaje.
     */
    int health;

    /**
     * Constructor que inicializa los parámetros iniciales de los personajes.
     * @param posX La posición en el eje X del personaje.
     * @param posY La posición en el eje Y del personaje.
     * @param width La anchura del personaje.
     * @param height La altura del personaje.
     * @param texture La imagen del personaje.
     * @param health La cantidad de salud del personaje
     */
    public Character(float posX, float posY, float width, float height, Texture texture, int health) {
        this.posX = posX;
        this.posY = posY;
        this.width = width;
        this.height = height;
        this.texture = texture;
        this.health = health;
    }

    /**
     * Método que sirve para comprobar si la hitbox de este personaje choca con la de otro.
     * @param anotherRect la hitbox del personaje con la que ha chocado este personaje.
     * @return si las 2 hitboxes han chocado o no.
     */
    public boolean intersects(Rectangle anotherRect) {
        Rectangle rectangle = new Rectangle(posX, posY, width, height);
        return rectangle.overlaps(anotherRect);
    }

    /**
     * Método para conseguir la velocidad de este personaje.
     * @return la velocidad del personaje.
     */
    public Vector2 getSpeed() {
        return speed;
    }

    /**
     * Método para establecer la velocidad del personaje.
     * @param speed la velocidad del personaje.
     */
    public void setSpeed(Vector2 speed) {
        this.speed = speed;
    }

    /**
     * Método para conseguir la posición X del personaje.
     * @return la posición X del personaje.
     */
    public float getPosX() {
        return posX;
    }

    /**
     * Método para establecer la posición X del personaje.
     * @param posX la posición X del personaje.
     */
    public void setPosX(float posX) {
        this.posX = posX;
    }

    /**
     * Método para conseguir la posición Y del personaje.
     * @return la posición Y del personaje.
     */
    public float getPosY() {
        return posY;
    }

    /**
     * Método para establecer la posición Y del personaje.
     * @param posY la posición Y del personaje.
     */
    public void setPosY(float posY) {
        this.posY = posY;
    }

    /**
     * Método para conseguir la anchura del personaje.
     * @return la anchura del personaje.
     */
    public float getWidth() {
        return width;
    }

    /**
     * Método para establecer la anchura del personaje.
     * @param width la anchura del personjaje.
     */
    public void setWidth(float width) {
        this.width = width;
    }

    /**
     * Método para conseguir la altura del personaje.
     * @return la altura del personaje.
     */
    public float getHeight() {
        return height;
    }

    /**
     * Método para establecer la altura del personaje.
     * @param height la altura del personaje.
     */
    public void setHeight(float height) {
        this.height = height;
    }

    /**
     * Método para conseguir la imagen del personaje.
     * @return la imagen del personaje.
     */
    public Texture getTexture() {
        return texture;
    }

    /**
     * Método para establecer la imagen del personaje.
     * @param texture la imagen del personaje.
     */
    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    /**
     * Método para conseguir la cantidad de salud del personaje.
     * @return la cantidad de salud del personaje.
     */
    public int getHealth() {
        return health;
    }

    /**
     * Método para establecer la cantidad de salud del personaje.
     * @param health la cantidad de salud del personaje.
     */
    public void setHealth(int health) {
        this.health = health;
    }
}