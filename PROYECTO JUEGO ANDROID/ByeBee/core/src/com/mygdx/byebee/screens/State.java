package com.mygdx.byebee.screens;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

public abstract class State  {

    protected abstract void handleInput();
    //public abstract void update(float deltaTime);
    //public abstract void render(SpriteBatch spriteBatch);
    //public abstract void dispose();
}
