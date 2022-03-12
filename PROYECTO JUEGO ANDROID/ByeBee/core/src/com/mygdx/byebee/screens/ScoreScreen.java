package com.mygdx.byebee.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.byebee.characters.Options;
import com.mygdx.byebee.characters.Score;

public class ScoreScreen implements Screen {
    private Camera camera;
    private Viewport viewport;
    private SpriteBatch spriteBatch;
    private FreeTypeFontGenerator fontGenerator;
    private FreeTypeFontGenerator.FreeTypeFontParameter fontParameter;
    private BitmapFont texto;
    private ByeBee byebee;
    private Texture bgScore;
    private Options btnBack;
    private Score puntuacion;
    private Score puntuacion2;
    private Score puntuacion3;
    private Score puntuacion4;

    public ScoreScreen(ByeBee byebee) {
        this.byebee = byebee;
        camera = new OrthographicCamera();
        viewport = new StretchViewport(ByeBee.WIDTH, ByeBee.HEIGHT, camera);

        bgScore = new Texture("BeePuntuaciones_vacio.png");
        btnBack = new Options(0, 0, ByeBee.WIDTH / 7, ByeBee.HEIGHT / 7, new Texture("btn_Atras.png"));

        fontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("PalatinoLinotype.ttf"));
        fontParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        fontParameter.color = Color.ORANGE;
        fontParameter.borderColor = Color.BLACK;
        fontParameter.borderWidth = 1;
        texto = fontGenerator.generateFont(fontParameter);
        texto.getData().setScale(3);
        texto.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        puntuacion4 = new Score(texto,ByeBee.WIDTH - btnBack.getWidth() * 2, ByeBee.HEIGHT / 3);
        puntuacion3 = new Score(texto,ByeBee.WIDTH - btnBack.getWidth() * 2, puntuacion4.getPosY() + btnBack.getHeight() + btnBack.getHeight() / 14);
        puntuacion2 = new Score(texto,ByeBee.WIDTH - btnBack.getWidth() * 2, puntuacion3.getPosY() + btnBack.getHeight() + btnBack.getHeight() / 10);
        puntuacion = new Score(texto,ByeBee.WIDTH - btnBack.getWidth() * 2, puntuacion2.getPosY() + btnBack.getHeight() + btnBack.getHeight() / 14);

        spriteBatch = new SpriteBatch();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        spriteBatch.begin();
        spriteBatch.draw(bgScore, 0, 0, ByeBee.WIDTH, byebee.HEIGHT);
        spriteBatch.draw(btnBack.getTexture(), btnBack.getPosX(), btnBack.getPosY(), btnBack.getWidth(), btnBack.getHeight());
        texto.draw(spriteBatch, "" + puntuacion4.getHighScore4(), puntuacion4.getPosX(), puntuacion4.getPosY());
        texto.draw(spriteBatch, "" + puntuacion3.getHighScore3(), puntuacion3.getPosX(), puntuacion3.getPosY());
        texto.draw(spriteBatch, "" + puntuacion2.getHighScore2(), puntuacion2.getPosX(), puntuacion2.getPosY());
        texto.draw(spriteBatch, "" + puntuacion.getHighScore(), puntuacion.getPosX(), puntuacion.getPosY());

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
         spriteBatch.dispose();
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
