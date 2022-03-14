package com.mygdx.byebee.characters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.byebee.screens.ByeBee;

import org.w3c.dom.css.Rect;

import java.awt.Canvas;
import java.awt.Paint;

/**
 * Esta clase representa a la abeja que controlas en el juego.
 */
public class Bee extends Character {

    /**
     * La gravedad de la abeja. Cuanta menos tenga, más rápido se caerá.
     */
    private static int GRAVITY = -4;

    /**
     * Variable que determina si la abeja puede moverse o no.
     */
    private boolean finJuego;

    /**
     * Variable que determina si el modo invencible de la abeja ha sido activado o no.
     */
    private boolean invencible;

    /**
     * Variable que determina si la abeja ha conseguido un escudo o no.
     */
    private boolean hasShield;

    /**
     * Recurso sonoro que emite un sonido cada vez que se ejecuta el método fly() al tocarse la pantalla en un nivel.
     */
    private Sound soundFly;

    /**
     * Se usa para poder utilizar persistencia de datos.
     */
    Preferences preferences;

    /**
     * Se usa para poder tener un sprite animado.
     */
    private Animation beeAnimation;

    /**
     * Recurso gráfico que contiene la spritesheet de la abeja.
     */
    private Texture beeTexture;

    /**
     * Comprueba si la opción de activar/desactivar música había sido seleccionada la última vez que se jugó al juego.
     */
    private boolean checkMusicSound;

    /**
     * Constructor que inicializa los parámetros iniciales de la abeja.
     * @param posX La posición en el eje X de la abeja.
     * @param posY La posición en el eje Y de la abeja.
     * @param width La anchura de la abeja.
     * @param height La altura de la abeja.
     * @param texture La imagen de la abeja.
     * @param health La cantidad de salud de la abeja.
     */
    public Bee(float posX, float posY, float width, float height, Texture texture, int health) {
        super(posX, posY, width, height, texture, health);
        this.speed = new Vector2(0, 0);
        this.finJuego = false;
        this.hasShield = false;
        this.beeTexture = texture;
        beeAnimation = new Animation(new TextureRegion(beeTexture), 3, 0.5f);

        preferences = Gdx.app.getPreferences("byebee");
        this.invencible = preferences.getBoolean("invencible", false);
        checkMusicSound = preferences.getBoolean("musicSound", true);

        soundFly = Gdx.audio.newSound(Gdx.files.internal("sound_Fly.mp3"));
        soundFly.setVolume(1, 1);
    }

    /**
     * Método que actualiza los valores de la abeja cada vez que la pantalla es renderizada.
     * @param deltaTime El tiempo en segundos desde el último renderizado.
     */
    public void update(float deltaTime) {
        beeAnimation.update(deltaTime);

        if (finJuego) {
            speed.x = 0;
            speed.y = 0;
            GRAVITY = 0;
        } else {
            if (posY > 0) {
                speed.add(0, GRAVITY); // Cada vez cae más rápido
            }

            speed.add(0, GRAVITY);
            speed.scl(deltaTime);
            posY += speed.y;
            speed.scl(1/deltaTime);

            if (posY < 0) { // Si caes al suelo es fin del juego
                GRAVITY = 0;
                this.health = 0;
            }

            if (posY > ByeBee.HEIGHT - this.getHeight()) { // Impide que puedas salirte del alto de la pantalla
                posY = ByeBee.HEIGHT - this.getHeight();
            }

            if (posY > 0 && posY < ByeBee.HEIGHT) { // Restablece la gravedad a la normalidad
                GRAVITY = -4;
            }

            accelerate();
        }
    }

    /**
     * Devuelve un rectángulo que se usará para detectar colisiones.
     * @return Un rectángulo del tamaño de la abeja.
     */
    public Rectangle getHitbox() {
        return new Rectangle(posX, posY, width, height);
    }

    /**
     * Método que sirve para que la abeja pegue un salto cada vez que se toca la pantalla.
     */
    public void fly() {
        if (Gdx.input.justTouched() && this.health > 0) {
            if (checkMusicSound) {
                soundFly.play();
            }

            if (this.posY > 0) {
                speed.y = 170;
            } else {
                posY = 0;
            }
        }
    }

    /**
     * Método que usa el acelerómetro con la abeja en el método update();
     */
    public void accelerate() {
        float renderX = 100;
        float renderY = 100;

        renderX += Gdx.input.getAccelerometerX();
        renderY -= Gdx.input.getAccelerometerY();

        if (renderX < 0) {
            renderX = 0;
        }

        if (renderX > ByeBee.WIDTH - 200) {
            renderX = ByeBee.WIDTH - 200;
        }

        if (renderY < 0) {
            renderY = 0;
        }

        if (renderY > ByeBee.HEIGHT - 200) {
            renderY = ByeBee.HEIGHT - 200;
        }
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
     * Método que determina si la abeja es invencible o no.
     * @return si la abeja es invencible o no.
     */
    public boolean isInvencible() {
        return invencible;
    }

    /**
     * Método que hace que la abeja se vuelva invencible.
     * @param invencible hace que la abeja no reciba ningún tipo de daño.
     */
    public void setInvencible(boolean invencible) {
        this.invencible = invencible;
        preferences.putBoolean("invencible", this.invencible);
        preferences.flush();
    }

    /**
     * Método que determina si la abeja ha conseguido un escudo o no.
     * @return si la abeja tiene un escudo o no.
     */
    public boolean isHasShield() {
        return hasShield;
    }

    /**
     * Método que hace que la abeja tenga un escudo que la proteja de un solo ataque.
     * @param hasShield hace que la abeja esté protegida de un solo ataque.
     */
    public void setHasShield(boolean hasShield) {
        this.hasShield = hasShield;
    }

    /**
     * Método que devuelve cada frame del sprite animado de la abeja.
     * @return cada frame del sprite animado de la abeja.
     */
    public TextureRegion getBeeTexture() {
        return beeAnimation.getFrame();
    }
}