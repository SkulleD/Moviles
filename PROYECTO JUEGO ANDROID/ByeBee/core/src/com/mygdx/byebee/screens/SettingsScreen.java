package com.mygdx.byebee.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.byebee.characters.Bee;
import com.mygdx.byebee.characters.Options;

public class SettingsScreen implements Screen {
    private Camera camera;
    private Viewport viewport;
    private SpriteBatch spriteBatch;
    private ByeBee byebee;
    private Texture bgSettings;
    private Options btnBack;
    private Options btnGodModeON;
    private Options btnGodModeOFF;
    private Options btnMusicON;
    private Options btnMusicOFF;
    private Options btnFullScreenON;
    private Options btnFullScreenOFF;
    private Preferences preferences;
    Graphics.DisplayMode displayMode;
    private boolean godmode;
    private boolean fullscreen;
    private boolean musicSound;

    public SettingsScreen(ByeBee byebee) {
        this.byebee = byebee;
        camera = new OrthographicCamera();
        viewport = new StretchViewport(ByeBee.WIDTH, ByeBee.HEIGHT, camera);
        displayMode = Gdx.graphics.getDisplayMode();
        preferences = Gdx.app.getPreferences("byebee");
        godmode = preferences.getBoolean("invencible", false);
        fullscreen = preferences.getBoolean("fullscreen", false);
        musicSound = preferences.getBoolean("musicSound", true);

        bgSettings = new Texture("beeSettings_vacio.png");
        btnBack = new Options(0, 0, ByeBee.WIDTH / 7, ByeBee.HEIGHT / 7, new Texture("btn_Atras.png"));
        btnGodModeON = new Options(btnBack.getPosX() + btnBack.getWidth() / 5, btnBack.getHeight() * 3, ByeBee.WIDTH / 8, ByeBee.HEIGHT / 8, new Texture("btn_GodmodeON.png"));
        btnGodModeOFF = new Options(btnGodModeON.getPosX() + btnBack.getWidth(), btnGodModeON.getPosY(), ByeBee.WIDTH / 8, ByeBee.HEIGHT / 8, new Texture("btn_GodmodeOFF.png"));
        btnMusicON = new Options(btnGodModeON.getPosX() + ByeBee.WIDTH / 3, btnGodModeON.getPosY(), ByeBee.WIDTH / 8, ByeBee.HEIGHT / 8, new Texture("btn_MusicaON.png"));
        btnMusicOFF = new Options(btnMusicON.getPosX() + btnBack.getWidth(), btnGodModeON.getPosY(), ByeBee.WIDTH / 8, ByeBee.HEIGHT / 8, new Texture("btn_MusicaOFF.png"));
        btnFullScreenON = new Options(btnMusicON.getPosX() + ByeBee.WIDTH / 3, btnGodModeON.getPosY(), ByeBee.WIDTH / 8, ByeBee.HEIGHT / 8, new Texture("btn_FullscreenON.png"));
        btnFullScreenOFF = new Options(btnFullScreenON.getPosX() + btnBack.getWidth(), btnGodModeON.getPosY(), ByeBee.WIDTH / 8, ByeBee.HEIGHT / 8, new Texture("btn_FullscreenOFF.png"));

        spriteBatch = new SpriteBatch();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        spriteBatch.begin();
        spriteBatch.draw(bgSettings, 0, 0, ByeBee.WIDTH, byebee.HEIGHT);
        spriteBatch.draw(btnBack.getTexture(), btnBack.getPosX(), btnBack.getPosY(), btnBack.getWidth(), btnBack.getHeight());
        spriteBatch.draw(btnGodModeON.getTexture(), btnGodModeON.getPosX(), btnGodModeON.getPosY(), btnGodModeON.getWidth(), btnGodModeON.getHeight());
        spriteBatch.draw(btnGodModeOFF.getTexture(), btnGodModeOFF.getPosX(), btnGodModeOFF.getPosY(), btnGodModeOFF.getWidth(), btnGodModeOFF.getHeight());
        spriteBatch.draw(btnMusicON.getTexture(), btnMusicON.getPosX(), btnMusicON.getPosY(), btnMusicON.getWidth(), btnMusicON.getHeight());
        spriteBatch.draw(btnMusicOFF.getTexture(), btnMusicOFF.getPosX(), btnMusicOFF.getPosY(), btnMusicOFF.getWidth(), btnMusicOFF.getHeight());
        spriteBatch.draw(btnFullScreenON.getTexture(), btnFullScreenON.getPosX(), btnFullScreenON.getPosY(), btnFullScreenON.getWidth(), btnFullScreenON.getHeight());
        spriteBatch.draw(btnFullScreenOFF.getTexture(), btnFullScreenOFF.getPosX(), btnFullScreenOFF.getPosY(), btnFullScreenOFF.getWidth(), btnFullScreenOFF.getHeight());
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

            // Activa la invencibilidad de la abeja contra los enemigos
            if (btnGodModeON.getBoton().contains(touched)) {
                System.out.println("GOD MODE ON");
                godmode = true;
                preferences.putBoolean("invencible", godmode);
                preferences.flush();
            }

            // Desactiva la invencibilidad de la abeja contra los enemigos
            if (btnGodModeOFF.getBoton().contains(touched)) {
                System.out.println("GOD MODE OFF");
                godmode = false;
                preferences.putBoolean("invencible", godmode);
                preferences.flush();
            }

            // Activa la música y los sonidos del juego
            if (btnMusicON.getBoton().contains(touched)) {
                System.out.println("MUSICA Y SONIDOS ON");
                //byebee.bgmMenus.play();
                musicSound = true;
                preferences.putBoolean("musicSound", musicSound);
                preferences.flush();
            }

            // Desactiva la música y los sonidos del juego
            if (btnMusicOFF.getBoton().contains(touched)) {
                System.out.println("MUSICA Y SONIDOS OFF");
                //byebee.bgmMenus.stop();
                musicSound = false;
                preferences.putBoolean("musicSound", musicSound);
                preferences.flush();
            }

            // El juego se pone en pantalla completa (en escritorio, porque en móvil ya lo está)
            if (btnFullScreenON.getBoton().contains(touched)) {
                if (!fullscreen) {
                    System.out.println("FULLSCREEN ON");
                    fullscreen = true;
                    Gdx.graphics.setUndecorated(true);
                    Gdx.graphics.setWindowedMode(displayMode.width, displayMode.height);
                    preferences.putBoolean("fullscreen", fullscreen);
                    preferences.flush();
                }
            }

            // El juego se pone en modo ventana (en escritorio, porque en móvil solo está en pantalla completa)
            if (btnFullScreenOFF.getBoton().contains(touched)) {
                if (fullscreen) {
                    System.out.println("FULLSCREEN OFF");
                    fullscreen = false;
                    Gdx.graphics.setUndecorated(false);
                    Gdx.graphics.setWindowedMode(ByeBee.WIDTH, ByeBee.HEIGHT);
                    preferences.putBoolean("fullscreen", fullscreen);
                    preferences.flush();
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
        this.dispose();
    }
}
