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

public class InfoScreen implements Screen {
    private Camera camera;
    private Viewport viewport;
    private SpriteBatch spriteBatch;
    private ByeBee byebee;
    private Texture bgInfo;
    private Texture bgInfo2;
    private Options btnBack;
    private Options btnRight;
    private Options btnLeft;
    private boolean rightOrLeft = false;

    public InfoScreen(ByeBee byebee) {
        this.byebee = byebee;
        camera = new OrthographicCamera();
        viewport = new StretchViewport(ByeBee.WIDTH, ByeBee.HEIGHT, camera);

        bgInfo = new Texture("beeInfo_vacio.png");
        bgInfo2 = new Texture("beeInfo2.png");
        btnBack = new Options(0, 0, ByeBee.WIDTH / 7, ByeBee.HEIGHT / 7, new Texture("btn_Atras.png"));
        btnRight = new Options(0, 0, ByeBee.WIDTH / 7, ByeBee.HEIGHT / 7, new Texture("btn_derecha.png"));
        btnLeft = new Options(0, 0, ByeBee.WIDTH / 7, ByeBee.HEIGHT / 7, new Texture("btn_izquierda.png"));

        // Se vuelven a poner para que funcione como quería, ya que ahora están inicializados y puedo usar sus datos para la anchura y altura
        btnRight = new Options(ByeBee.WIDTH - btnRight.getWidth(), 0, ByeBee.WIDTH / 7, ByeBee.HEIGHT / 7, new Texture("btn_derecha.png"));
        btnLeft = new Options(ByeBee.WIDTH - btnLeft.getWidth(), 0, ByeBee.WIDTH / 7, ByeBee.HEIGHT / 7, new Texture("btn_izquierda.png"));
        spriteBatch = new SpriteBatch();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        spriteBatch.begin();

        if (rightOrLeft) {
            spriteBatch.draw(bgInfo2,0, 0, ByeBee.WIDTH, ByeBee.HEIGHT);
            spriteBatch.draw(btnLeft.getTexture(), btnLeft.getPosX(), btnLeft.getPosY(), btnLeft.getWidth(), btnRight.getHeight());

        } else {
            spriteBatch.draw(bgInfo, 0,0, ByeBee.WIDTH, ByeBee.HEIGHT);
            spriteBatch.draw(btnRight.getTexture(), btnRight.getPosX(), btnRight.getPosY(), btnRight.getWidth(), btnRight.getHeight());
        }

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

            if (btnRight.getBoton().contains(touched)) {
                if (rightOrLeft) {
                    rightOrLeft = false;
                } else {
                    rightOrLeft = true;
                }
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
