package com.mygdx.byebee.characters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.byebee.screens.ByeBee;

import org.w3c.dom.css.Rect;

import java.awt.Canvas;
import java.awt.Paint;

public class Bee extends Character {
    private static int GRAVITY = -4;
    private boolean finJuego;
    private boolean invencible;
    Preferences preferences;

    public Bee(float posX, float posY, float width, float height, Texture texture, int health) {
        super(posX, posY, width, height, texture, health);
        this.speed = new Vector2(0, 0);
        this.finJuego = false;

        preferences = Gdx.app.getPreferences("byebee");
        this.invencible = preferences.getBoolean("invencible", false);
    }

    public void update(float deltaTime) {
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
                GRAVITY = -90;
                this.health = 0;
            }

            if (posY > ByeBee.HEIGHT - this.getHeight()) { // Impide que puedas salirte del alto de la pantalla
                posY = ByeBee.HEIGHT - this.getHeight();
            }

            if (posY > 0 && posY < ByeBee.HEIGHT) { // Restablece la gravedad a la normalidad
                GRAVITY = -4;
            }
        }
    }

    public Rectangle getHitbox() {
        return new Rectangle(posX, posY, width, height);
    }

    public void fly() {
        if (Gdx.input.justTouched() && this.health > 0) {
            if (this.posY > 0) {
                speed.y = 170;
            } else {
                posY = 0;
            }
        }
    }

    public static int getGRAVITY() {
        return GRAVITY;
    }

    public static void setGRAVITY(int GRAVITY) {
        Bee.GRAVITY = GRAVITY;
    }

    public boolean isFinJuego() {
        return finJuego;
    }

    public void setFinJuego(boolean finJuego) {
        this.finJuego = finJuego;
    }

    public boolean isInvencible() {
        return invencible;
    }

    public void setInvencible(boolean invencible) {
        this.invencible = invencible;
        preferences.putBoolean("invencible", this.invencible);
        preferences.flush();
    }
}