package com.mygdx.byebee.characters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.byebee.screens.ByeBee;

/**
 * Esta es la clase que representa a los enemigos, los objetos y la línea de meta.
 */
public class Enemy extends Character {

    /**
     * La velocidad del enemigo en float.
     */
    private Vector2 speed;

    /**
     * La velocidad del enemigo en int.
     */
    private int velocidad;

    /**
     * Variable que determina si el enemigo actual ha golpeado a la abeja o no.
     */
    private boolean hasHit;

    /**
     * Variable que determina si lo que toca la abeja es la línea de meta o no.
     */
    private boolean isMeta;

    /**
     * Variable que determina si lo que toca la abeja es un objeto o no.
     */
    private boolean isItem;

    /**
     * Variable que determina si la abeja puede moverse o no.
     */
    private boolean finJuego;

    /**
     * Se usa para poder tener un sprite animado.
     */
    private Animation enemyAnimation;

    /**
     * Recurso gráfico que contiene la spritesheet del enemigo.
     */
    private Texture enemyTexture;


    /**
     * Constructor que inicializa los parámetros iniciales de los enemigos.
     * @param posX La posición en el eje X del enemigo.
     * @param posY La posición en el eje Y del enemigo.
     * @param width La anchura del enemigo.
     * @param height La altura del enemigo.
     * @param texture La imagen del enemigo.
     * @param health La cantidad de salud del enemigo
     * @param isMeta Si el enemigo es la línea de meta o no.
     * @param isItem Si el enemigo es un objeto o no.
     * @param speedX La velocidad horizontal del enemigo.
     */
    public Enemy(float posX, float posY, float width, float height, Texture texture, int health, boolean isMeta, boolean isItem, float speedX) {
        super(posX, posY, width, height, texture, health);
        this.speed = new Vector2(speedX, 0);
        this.velocidad = (int) speedX;
        this.isMeta = isMeta;
        this.isItem = isItem;
        this.finJuego = false;
        this.enemyTexture = texture;
        enemyAnimation = new Animation(new TextureRegion(enemyTexture), 3, 0.5f);
    }

    /**
     * Método que actualiza los valores de los enemigos cada vez que la pantalla es renderizada.
     * @param deltaTime El tiempo en segundos desde el último renderizado.
     */
    public void update(float deltaTime) {
        enemyAnimation.update(deltaTime);

        speed.scl(deltaTime);
        posX += speed.x;
        speed.scl(1/deltaTime);

        if (posY < 0) { // Impide que los enemigos/objetos salgan por debajo de la pantalla
            posY = (float) (Math.random() * ByeBee.HEIGHT + 1);
        }

        if (posY + getHitbox().height > Gdx.graphics.getHeight()) { // Impide que los enemigos/objetos salgan por encima de la pantalla
            posY = (float) (Math.random() * ByeBee.HEIGHT + 1);
        }
    }

    /**
     * Devuelve un rectángulo que se usará para detectar colisiones.
     * @return Un rectángulo del tamaño del enemigo.
     */
    public Rectangle getHitbox() {
        return new Rectangle(posX, posY, width, height);
    }

    /**
     * Método que sirve para que los enemigos se vayan moviendo a través de la pantalla.
     */
    public void move() {
        if (finJuego) {
            speed.x = 0;
        } else {
            speed.x = velocidad;
        }

        // Intento fallido de movimiento semi aleatorio (terrible)
        //if (this.posY > ByeBee.HEIGHT) {
        //    trayectoria = ByeBee.random.nextFloat() * 1;

        //    if (trayectoria == 1) {
        //        speed.y = 20;
        //    } else {
        //        speed.y = -20;
        //    }
        //}
    }

    /**
     * Método que determina si el enemigo actual ha tocado a la abeja o no.
     * @return si este enemigo ha tocado a la abeja o no.
     */
    public boolean isHasHit() {
        return hasHit;
    }

    /**
     * Método que establece si el enemigo actual ha tocado a la abeja solamente si este enemigo no es la línea de meta.
     * @param hasHit si este enemigo ha tocado a la abeja o no.
     */
    public void setHasHit(boolean hasHit) {
        if (isMeta) {
            this.hasHit = false;
        } else {
            this.hasHit = hasHit;
        }
    }

    /**
     * Método que determina si este enemigo es la línea de meta o no.
     * @return si este enemigo es la línea de meta o no.
     */
    public boolean isMeta() {
        return isMeta;
    }

    /**
     * Método que establece si este enemigo es la línea de meta o no.
     * @param meta si este enemigo es la línea de meta o no.
     */
    public void setMeta(boolean meta) {
        isMeta = meta;
    }

    /**
     * Método que determina si este enemigo es un objeto o no.
     * @return si este enemigo es un objeto o no.
     */
    public boolean isItem() {
        return isItem;
    }

    /**
     * Método que establece si este enemigo es un objeto o no.
     * @param item si este enemigo es un objeto o no.
     */
    public void setItem(boolean item) {
        isItem = item;
    }

    /**
     * Método que sirve para determinar si el juego se ha terminado o no.
     * @return si el juego se ha terminado o no.
     */
    public boolean isFinJuego() {
        return finJuego;
    }

    /**
     * Método que hace que el juego se termine y, por lo tanto, quede pausado.
     * @param finJuego hace que el juego se pause y las entidades y fondo dejen de moverse.
     */
    public void setFinJuego(boolean finJuego) {
        this.finJuego = finJuego;
    }

    /**
     * Método que devuelve cada frame del sprite animado del enemigo.
     * @return cada frame del sprite animado de la abeja.
     */
    public TextureRegion getEnemyTexture() {
        return enemyAnimation.getFrame();
    }
}