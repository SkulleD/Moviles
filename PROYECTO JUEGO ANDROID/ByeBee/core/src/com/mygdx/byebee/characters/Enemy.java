package com.mygdx.byebee.characters;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.byebee.screens.ByeBee;


public class Enemy extends Character {
    private boolean hasHit;

    public Enemy(float posX, float posY, float width, float height, Texture texture, int health) {
        super(posX, posY, width, height, texture, health);
        speed = new Vector2(0, 0);
    }

    public void update(float deltaTime) {
        speed.scl(deltaTime);
        posX += speed.x;
        speed.scl(1/deltaTime);

        if (posY < 0) { // Impide que los enemigos salgan por debajo de la pantalla
            posY = (float) (Math.random() * ByeBee.HEIGHT + 1);
        }

        if (posY > ByeBee.HEIGHT) { // Impide que los enemigos salgan por encima de la pantalla
            posY = (float) (Math.random() * ByeBee.HEIGHT + 1);
        }
    }

    public Rectangle getHitbox() {
        return new Rectangle(posX, posY, width, height);
    }

    public void move() {
        speed.x = -300;

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
        this.hasHit = hasHit;
    }
}