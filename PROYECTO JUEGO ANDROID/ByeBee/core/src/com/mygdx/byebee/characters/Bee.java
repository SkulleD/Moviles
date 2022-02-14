package com.mygdx.byebee.characters;

import com.badlogic.gdx.Gdx;
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
    private static final int GRAVITY = -5;

    public Bee(float posX, float posY, float width, float height, Texture texture, int health) {
        super(posX, posY, width, height, texture, health);
        speed = new Vector2(0, 0);
    }

    public void update(float deltaTime) {
        speed.add(0, GRAVITY);
        speed.scl(deltaTime);
        posY += speed.y;
        speed.scl(1/deltaTime);
    }

    public Rectangle getHitbox() {
        return new Rectangle(posX, posY, width, height);
    }

    public void fly() {
        if (Gdx.input.justTouched()) {
            if (this.posY > 0) {
                speed.y = 170;
            } else {
                posY = 0;
            }
        }
    }
}