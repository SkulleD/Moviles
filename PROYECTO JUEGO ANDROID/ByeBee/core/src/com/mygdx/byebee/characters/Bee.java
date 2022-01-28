package com.mygdx.byebee.characters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;

import org.w3c.dom.css.Rect;

import java.awt.Canvas;
import java.awt.Paint;

public class Bee {
    private static final int GRAVITY = -5;
    private Vector3 position;
    private Vector3 velocity;
    private Texture bee;

    public Bee(int x, int y) {
        position = new Vector3(x, y, 0);
        velocity = new Vector3(0, 0, 0);
        bee = new Texture("bee.png");
    }

    public void update(float deltaTime) {
        if (position.y > 0) {
            velocity.add(0, GRAVITY, 0);
        }

        velocity.scl(deltaTime);
        position.add(0, velocity.y, 0);
        velocity.scl(1/deltaTime);

        if (position.y < 0) {
            position.y = 0;
        }
    }

    public Vector3 getPosition() {
        return position;
    }

    public Texture getTexture() {
        return bee;
    }

    public void fly() {
        velocity.y = 200;
    }
}