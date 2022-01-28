package com.mygdx.byebee.characters;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;

public class Bird {
    private static final int GRAVITY = -5;
    private Vector3 velocity;
    private Vector3 position;
    private Texture bird;


    public Bird(int x, int y) {
        position = new Vector3(x, y, 0);
        velocity = new Vector3(800, 0, 0);
        bird = new Texture("bird.png");
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
        return bird;
    }

    public void move() {
        velocity.x = 70;
        velocity.y = 20;
    }
}