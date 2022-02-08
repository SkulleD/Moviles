package com.mygdx.byebee.characters;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Character {
    Vector2 speed;
    float posX;
    float posY;
    float width;
    float height;
    Texture texture;
    int health;

    public Character(float posX, float posY, float width, float height, Texture texture, int health) {
        this.posX = posX;
        this.posY = posY;
        this.width = width;
        this.height = height;
        this.texture = texture;
        this.health = health;
    }

    public boolean intersects(Rectangle anotherRect) {
        Rectangle rectangle = new Rectangle(posX, posY, width, height);
        return rectangle.overlaps(anotherRect);
    }

    public Vector2 getSpeed() {
        return speed;
    }

    public void setSpeed(Vector2 speed) {
        this.speed = speed;
    }

    public float getPosX() {
        return posX;
    }

    public void setPosX(float posX) {
        this.posX = posX;
    }

    public float getPosY() {
        return posY;
    }

    public void setPosY(float posY) {
        this.posY = posY;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public Texture getTexture() {
        return texture;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }
}
