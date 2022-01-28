package com.mygdx.byebee.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class TitleScreen extends State implements Screen {
    private Camera camera;
    private Viewport viewport;
    private SpriteBatch spriteBatch;
    private Texture bgTitle;

    public TitleScreen() {
        camera = new OrthographicCamera();
        viewport = new StretchViewport(ByeBee.WIDTH, ByeBee.HEIGHT, camera);

        bgTitle = new Texture("bee_title.png");

        spriteBatch = new SpriteBatch();
    }

    @Override
    protected void handleInput() {
        if (Gdx.input.justTouched()) {

            dispose();
        }
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        spriteBatch.begin();
        spriteBatch.draw(bgTitle, 0, 0, ByeBee.WIDTH, ByeBee.HEIGHT);
        spriteBatch.end();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
        spriteBatch.setProjectionMatrix(camera.combined);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
