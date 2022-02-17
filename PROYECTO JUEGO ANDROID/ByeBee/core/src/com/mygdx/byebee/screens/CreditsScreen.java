package com.mygdx.byebee.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.byebee.characters.Options;

public class CreditsScreen implements Screen {
    private Camera camera;
    private Viewport viewport;
    private SpriteBatch spriteBatch;
    private ByeBee byebee;
    private Texture bgCredits;
    private Options btnBack;

    public CreditsScreen(ByeBee byebee) {
        this.byebee = byebee;
        camera = new OrthographicCamera();
        viewport = new StretchViewport(ByeBee.WIDTH, ByeBee.HEIGHT, camera);

        bgCredits = new Texture("beeCredits_vacio.png");
        btnBack = new Options(0, 0, ByeBee.WIDTH / 7, ByeBee.HEIGHT / 7, new Texture("btn_Atras.png"));
        spriteBatch = new SpriteBatch();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        spriteBatch.begin();
        spriteBatch.draw(bgCredits, 0, 0, ByeBee.WIDTH, ByeBee.HEIGHT);
        spriteBatch.draw(btnBack.getTexture(), btnBack.getPosX(), btnBack.getPosY(), btnBack.getWidth(), btnBack.getHeight());
        detectTouch();
        spriteBatch.end();
    }

    public void detectTouch() {
        Vector2 touched; // Guarda las coordenadas para saber en qué parte de la pantalla se toca

        if (Gdx.input.justTouched()) {
            touched = viewport.unproject(new Vector2(Gdx.input.getX(), Gdx.input.getY()));

            if (btnBack.getBoton().contains(touched)) {
                System.out.println("BACK");
                byebee.setTitleScreen();
            }
        }
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
