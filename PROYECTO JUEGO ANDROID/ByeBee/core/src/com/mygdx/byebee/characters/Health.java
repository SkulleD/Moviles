package com.mygdx.byebee.characters;

import com.badlogic.gdx.graphics.Texture;

public class Health {
    float posX;
    float posY;
    float width;
    float height;
    Texture[] texture;

    public Health(float posX, float posY, float width, float height, Texture[] texture) {
        this.posX = posX;
        this.posY = posY;
        this.width = width;
        this.height = height;
        this.texture = new Texture[8];
        this.texture[0] = new Texture("vida0.png");
        this.texture[1] = new Texture("vida1.png");
        this.texture[2] = new Texture("vida2.png");
        this.texture[3] = new Texture("vida3.png");
        this.texture[4] = new Texture("vida4.png");
        this.texture[5] = new Texture("vida5.png");
        this.texture[6] = new Texture("vida6.png");
        this.texture[7] = new Texture("vida7.png");
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

    public Texture[] getTexture() {
        return texture;
    }

    public void setTexture(Texture[] texture) {
        this.texture = texture;
    }
}
