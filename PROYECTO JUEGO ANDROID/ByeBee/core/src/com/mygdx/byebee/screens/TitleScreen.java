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

    private Vector2 touch; // Guarda las coordenadas para saber en qué parte de la pantalla se toca
    Rectangle rect; // Crea un rect usando las coordenadas del touch

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

        touch = new Vector2(0, 0);
        bgTitle = new Texture("beeTitleFinal.png");
        optionsCredits = new Options(0, 0, ByeBee.WIDTH / 6, ByeBee.HEIGHT / 6, new Texture("btn_credits.png"));
        optionsInfo = new Options(0, 0, ByeBee.WIDTH / 6, ByeBee.HEIGHT / 6, new Texture("btn_info.png"));
        optionsSettings = new Options(0, 0, ByeBee.WIDTH / 6, ByeBee.HEIGHT / 6, new Texture("btn_options.png"));
        optionsRecords = new Options(0, 0, ByeBee.WIDTH / 6, ByeBee.HEIGHT / 6, new Texture("btn_records.png"));
        optionsPlay = new Options(0, 0, ByeBee.WIDTH / 3, ByeBee.HEIGHT / 4, new Texture("btn_jugar.png"));
        spriteBatch = new SpriteBatch();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        if (Gdx.input.justTouched()) {
            byebee.setLevel1();
        }

        spriteBatch.begin();
        spriteBatch.draw(bgTitle, 0, 0, ByeBee.WIDTH, ByeBee.HEIGHT);
        spriteBatch.draw(optionsCredits.getTexture(), optionsCredits.getPosX(), ByeBee.HEIGHT - optionsCredits.getHeight(), optionsCredits.getWidth(), optionsCredits.getHeight());
        spriteBatch.draw(optionsInfo.getTexture(), optionsInfo.getPosX(), optionsInfo.getPosY(), optionsInfo.getWidth(), optionsInfo.getHeight());
        spriteBatch.draw(optionsSettings.getTexture(), ByeBee.WIDTH - optionsSettings.getWidth(), ByeBee.HEIGHT - optionsSettings.getHeight(), optionsSettings.getWidth(), optionsSettings.getHeight());
        spriteBatch.draw(optionsRecords.getTexture(), ByeBee.WIDTH - optionsRecords.getWidth(), optionsRecords.getPosY(), optionsRecords.getWidth(), optionsRecords.getHeight());
        spriteBatch.draw(optionsPlay.getTexture(), ByeBee.WIDTH / 3, (float) (ByeBee.HEIGHT / 2.6), optionsPlay.getWidth(), optionsPlay.getHeight());
        detectTouch();

        spriteBatch.end();
    }

    public void detectTouch() {

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
