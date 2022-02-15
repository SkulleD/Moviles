package com.mygdx.byebee.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.byebee.characters.Options;

public class TitleScreen implements Screen {
    private Camera camera;
    private Viewport viewport;
    private SpriteBatch spriteBatch;
    private Texture bgTitle;
    private ByeBee byebee;

    //Se crean los botones de la pantalla del título
    private Options optionsInfo;
    private Options optionsCredits;
    private Options optionsRecords;
    private Options optionsSettings;
    private Options optionsPlay;

    public TitleScreen(ByeBee byebee) {
        this.byebee = byebee;
        camera = new OrthographicCamera();
        viewport = new StretchViewport(ByeBee.WIDTH, ByeBee.HEIGHT, camera);

        // Se inicializan algunas posiciones
        // optionsCredits.setPosY(ByeBee.HEIGHT - optionsCredits.getHeight());

        bgTitle = new Texture("beeTitleFinal.png");
        optionsCredits = new Options(0,0, ByeBee.WIDTH / 6, ByeBee.HEIGHT / 6, new Texture("btn_credits.png"));
        optionsInfo = new Options(0, 0, ByeBee.WIDTH / 6, ByeBee.HEIGHT / 6, new Texture("btn_info.png"));
        optionsSettings = new Options(0, 0, ByeBee.WIDTH / 6, ByeBee.HEIGHT / 6, new Texture("btn_options.png"));
        optionsRecords = new Options(0, 0, ByeBee.WIDTH / 6, ByeBee.HEIGHT / 6, new Texture("btn_records.png"));
        optionsPlay = new Options(ByeBee.WIDTH / 3, (float) (ByeBee.HEIGHT / 2.6), ByeBee.WIDTH / 3, ByeBee.HEIGHT / 4, new Texture("btn_jugar.png"));
        spriteBatch = new SpriteBatch();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        spriteBatch.begin();
        spriteBatch.draw(bgTitle, 0, 0, ByeBee.WIDTH, ByeBee.HEIGHT);
        spriteBatch.draw(optionsCredits.getTexture(),  optionsCredits.getPosX(), optionsCredits.getPosY(),  optionsCredits.getWidth(), optionsCredits.getHeight());
        spriteBatch.draw(optionsInfo.getTexture(),  optionsInfo.getPosX(), optionsInfo.getPosY(),  optionsInfo.getWidth(), optionsInfo.getHeight());
        spriteBatch.draw(optionsSettings.getTexture(),  optionsSettings.getPosX(), optionsSettings.getPosY(), optionsSettings.getWidth(), optionsSettings.getHeight());
        spriteBatch.draw(optionsRecords.getTexture(),  optionsRecords.getPosX(), optionsRecords.getPosY(), optionsRecords.getWidth(), optionsRecords.getHeight());
        spriteBatch.draw(optionsPlay.getTexture(), optionsPlay.getPosX(), optionsPlay.getPosY(), optionsPlay.getWidth(), optionsPlay.getHeight());
        detectTouch();

        spriteBatch.end();
    }

    public void detectTouch() {
        Vector2 touched; // Guarda las coordenadas para saber en qué parte de la pantalla se toca

        if (Gdx.input.justTouched()) {
            touched = viewport.unproject(new Vector2(Gdx.input.getX(), Gdx.input.getY()));
            System.out.println("AAAAAAAA");

            if (optionsPlay.getBoton().contains(touched)) {
                System.out.println("BBBBBBBBB");
                byebee.setLevelSelect();
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
