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

public class LevelSelect implements Screen {
    private Camera camera;
    private Viewport viewport;
    private SpriteBatch spriteBatch;
    private Texture bgLevels;
    private Options btnBack;
    private Options btnLvl1;
    private Options btnLvl2;
    private Options btnLvl3;
    private Options btnLvl4;
    private ByeBee byebee;

    public LevelSelect(ByeBee byebee) {
        this.byebee = byebee;
        camera = new OrthographicCamera();
        viewport = new StretchViewport(ByeBee.WIDTH, ByeBee.HEIGHT, camera);

        bgLevels = new Texture("beeNiveles_Vacio.png");
        btnBack = new Options(0, 0, ByeBee.WIDTH / 7, ByeBee.HEIGHT / 7, new Texture("btn_Atras.png"));
        btnLvl1 = new Options(ByeBee.WIDTH / 20, ByeBee.HEIGHT / 4, ByeBee.WIDTH / 5, ByeBee.HEIGHT / 2, new Texture("btn_Niveles1.png"));
        btnLvl2 = new Options(ByeBee.WIDTH / 12 + btnLvl1.getWidth(), ByeBee.HEIGHT / 4, ByeBee.WIDTH / 5, ByeBee.HEIGHT / 2, new Texture("btn_Niveles2.png"));
        btnLvl3 = new Options(ByeBee.WIDTH / 9 + btnLvl2.getWidth() * 2, ByeBee.HEIGHT / 4, ByeBee.WIDTH / 5, ByeBee.HEIGHT / 2, new Texture("btn_Niveles3.png"));
        btnLvl4 = new Options(ByeBee.WIDTH / 3 + btnLvl3.getWidth() * 2, ByeBee.HEIGHT / 4, ByeBee.WIDTH / 5, ByeBee.HEIGHT / 2, new Texture("btn_Niveles4.png"));
        spriteBatch = new SpriteBatch();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        spriteBatch.begin();
        spriteBatch.draw(bgLevels, 0, 0, ByeBee.WIDTH, ByeBee.HEIGHT);
        spriteBatch.draw(btnBack.getTexture(), btnBack.getPosX(), btnBack.getPosY(), btnBack.getWidth(), btnBack.getHeight());
        spriteBatch.draw(btnLvl1.getTexture(), btnLvl1.getPosX(), btnLvl1.getPosY(), btnLvl1.getWidth(), btnLvl1.getHeight());
        spriteBatch.draw(btnLvl2.getTexture(), btnLvl2.getPosX(), btnLvl2.getPosY(), btnLvl2.getWidth(), btnLvl2.getHeight());
        spriteBatch.draw(btnLvl3.getTexture(), btnLvl3.getPosX(), btnLvl3.getPosY(), btnLvl3.getWidth(), btnLvl3.getHeight());
        spriteBatch.draw(btnLvl4.getTexture(), btnLvl4.getPosX(), btnLvl4.getPosY(), btnLvl4.getWidth(), btnLvl4.getHeight());
        detectTouch();
        spriteBatch.end();
    }

    public void detectTouch() {
        Vector2 touched; // Guarda las coordenadas para saber en qué parte de la pantalla se toca

        if (Gdx.input.justTouched()) {
            touched = viewport.unproject(new Vector2(Gdx.input.getX(), Gdx.input.getY()));

            if (btnLvl1.getBoton().contains(touched)) {
                System.out.println("LEVEL 1 SELECTED");
                byebee.setLevel1();
            }

            if (btnLvl2.getBoton().contains(touched)) {
                System.out.println("LEVEL 2 SELECTED");
                byebee.setLevel2();
            }

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
        this.dispose();
    }
}
