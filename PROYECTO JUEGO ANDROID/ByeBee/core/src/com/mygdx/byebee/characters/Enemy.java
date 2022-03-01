package com.mygdx.byebee.characters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.byebee.screens.ByeBee;


public class Enemy extends Character {
    private Vector2 speed;
    private boolean hasHit;
    private boolean isMeta;
    private boolean finJuego;

    public Enemy(float posX, float posY, float width, float height, Texture texture, int health, boolean isMeta, float speedX) {
        super(posX, posY, width, height, texture, health);
        this.speed = new Vector2(speedX, 0);
        this.isMeta = isMeta;
        this.finJuego = false;
    }

    public void update(float deltaTime) {
        speed.scl(deltaTime);
        posX += speed.x;
        speed.scl(1/deltaTime);

        if (posY < 0) { // Impide que los enemigos salgan por debajo de la pantalla
            posY = (float) (Math.random() * ByeBee.HEIGHT + 1);
        }

        if (posY + this.height > Gdx.graphics.getHeight()) { // Impide que los enemigos salgan por encima de la pantalla (TODO HAY QUE ARREGLARLO)
            posY = (float) (Math.random() * ByeBee.HEIGHT + 1);
        }
    }

    public Rectangle getHitbox() {
        return new Rectangle(posX, posY, width, height);
    }

    public void move() {
        if (finJuego) {
            speed.x = 0;
        } else {
            speed.x = -400;
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

    public boolean isHasHit() {
        return hasHit;
    }

    public void setHasHit(boolean hasHit) {
        if (isMeta) {
            this.hasHit = false;
        } else {
            this.hasHit = hasHit;
        }
    }

    public boolean isMeta() {
        return isMeta;
    }

    public void setMeta(boolean meta) {
        isMeta = meta;
    }

    public boolean isFinJuego() {
        return finJuego;
    }

    public void setFinJuego(boolean finJuego) {
        this.finJuego = finJuego;
    }
}