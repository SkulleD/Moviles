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

        bgTitle = new Texture("beeTitleFinal.png");
        optionsInfo = new Options(0, 0, ByeBee.WIDTH / 6, ByeBee.HEIGHT / 6, new Texture("btn_info.png"));
        optionsCredits = new Options(0,0, ByeBee.WIDTH / 6, ByeBee.HEIGHT / 6, new Texture("btn_credits.png"));
        optionsSettings = new Options(0, 0, ByeBee.WIDTH / 6, ByeBee.HEIGHT / 6, new Texture("btn_options.png"));
        optionsRecords = new Options(0, 0, ByeBee.WIDTH / 6, ByeBee.HEIGHT / 6, new Texture("btn_records.png"));
        optionsPlay = new Options(ByeBee.WIDTH / 3, (float) (ByeBee.HEIGHT / 2.6), ByeBee.WIDTH / 3, ByeBee.HEIGHT / 4, new Texture("btn_jugar.png"));

        // Se vuelven a poner para que funcione como quería, ya que ahora están inicializados y puedo usar sus datos para la anchura y altura
        optionsInfo = new Options(0, ByeBee.HEIGHT - optionsInfo.getHeight(), ByeBee.WIDTH / 6, ByeBee.HEIGHT / 6, new Texture("btn_info.png"));
        optionsSettings = new Options(ByeBee.WIDTH - optionsSettings.getWidth(), ByeBee.HEIGHT - optionsCredits.getHeight(), ByeBee.WIDTH / 6, ByeBee.HEIGHT / 6, new Texture("btn_options.png"));
        optionsRecords = new Options(ByeBee.WIDTH - optionsRecords.getWidth(), 0, ByeBee.WIDTH / 6, ByeBee.HEIGHT / 6, new Texture("btn_records.png"));
        spriteBatch = new SpriteBatch();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        spriteBatch.begin();
        spriteBatch.draw(bgTitle, 0, 0, ByeBee.WIDTH, ByeBee.HEIGHT);
        spriteBatch.draw(optionsInfo.getTexture(),  optionsInfo.getPosX(), optionsInfo.getPosY(),  optionsInfo.getWidth(), optionsInfo.getHeight());
        spriteBatch.draw(optionsCredits.getTexture(),  optionsCredits.getPosX(), optionsCredits.getPosY(),  optionsCredits.getWidth(), optionsCredits.getHeight());
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

            if (optionsPlay.getBoton().contains(touched)) { // BOTÓN JUGAR
                System.out.println("JUGAR");
                byebee.setLevelSelect();
            }

            if (optionsInfo.getBoton().contains(touched)) { // BOTÓN INFO
                System.out.println("INFO");
                byebee.setInfoScreen();
            }

            if (optionsCredits.getBoton().contains(touched)) { // BOTÓN CRÉDITOS
                System.out.println("CREDITS");
                byebee.setCreditsScreen();
            }

            if (optionsSettings.getBoton().contains(touched)) { // BOTÓN AJUSTES
                System.out.println("SETTINGS");
                //byebee.setSettingsScreen();
            }

            if (optionsRecords.getBoton().contains(touched)) { // BOTÓN PUNTUACIONES
                System.out.println("RECORDS");
                byebee.setScoreScreen();
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
