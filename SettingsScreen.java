package com.mygdx.byebee.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.byebee.characters.Bee;
import com.mygdx.byebee.characters.Options;

/**
 * Esta pantalla sirve para activar/desactivar varios parámetros: invencibilidad, música/sonidos y pantalla completa.
 */
public class SettingsScreen implements Screen {

    /**
     * Cámara que se usa para proyección ortográfica.
     */
    private Camera camera;

    /**
     * Utiliza la cámara para determinar cómo las coordenadas en pantalla están mapeadas desde un punto a otro de la pantalla.
     */
    private Viewport viewport;

    /**
     * Se usa para dibujar los recursos gráficos en pantalla.
     */
    private SpriteBatch spriteBatch;

    /**
     * Objeto que se utiliza para acceder a las demás pantallas usando los métodos correspondientes de la clase base.
     */
    private ByeBee byebee;

    /**
     * Recurso gráfico que se muestra de fondo de la pantalla.
     */
    private Texture bgSettings;

    /**
     * Recurso sonoro que emite un sonido cada vez que se pulsa una opción.
     */
    private Sound soundBtnClick;

    /**
     * Botón para volver a la pantalla anterior.
     */
    private Options btnBack;

    /**
     * Botón para activar la invencibilidad de la abeja.
     */
    private Options btnGodModeON;

    /**
     * Botón para desactivar la invencibilidad de la abeja.
     */
    private Options btnGodModeOFF;

    /**
     * Botón para activar la música y los sonidos.
     */
    private Options btnMusicON;

    /**
     * Botón para desactivar la música y los sonidos.
     */
    private Options btnMusicOFF;

    /**
     * Botón para activar la pantalla completa (solo en escritorio).
     */
    private Options btnFullScreenON;

    /**
     * Botón para desactivar la pantalla completa (solo en escritorio).
     */
    private Options btnFullScreenOFF;

    /**
     * Se usa para poder utilizar persistencia de datos.
     */
    private Preferences preferences;

    /**
     * Se usa para poder poner y quitar la pantalla completa del juego (solo en escritorio).
     */
    Graphics.DisplayMode displayMode;

    /**
     * Variable que sirve para guardar el valor persistente de la invencibilidad
     */
    private boolean godmode;

    /**
     * Variable que sirve para guardar el valor persistente de la pantalla completa
     */
    private boolean fullscreen;

    /**
     * Variable que sirve para guardar el valor persistente de la música y sonidos
     */
    private boolean musicSound;

    /**
     * Constructor que inicializa los campos necesario de esta pantalla.
     * @param byebee Sirve para acceder a los métodos de la clase base ByeBee.
     */
    public SettingsScreen(ByeBee byebee) {
        this.byebee = byebee;
        camera = new OrthographicCamera();
        viewport = new StretchViewport(ByeBee.WIDTH, ByeBee.HEIGHT, camera);
        displayMode = Gdx.graphics.getDisplayMode();
        preferences = Gdx.app.getPreferences("byebee");
        godmode = preferences.getBoolean("invencible", false);
        fullscreen = preferences.getBoolean("fullscreen", false);
        musicSound = preferences.getBoolean("musicSound", true);

        soundBtnClick = Gdx.audio.newSound(Gdx.files.internal("sound_clickBtn.mp3"));
        soundBtnClick.setVolume(7, 1);

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

    /**
     * Método al que se llama cuando esta pantalla se convierte en la actual.
     */
    @Override
    public void show() {

    }

    /**
     * Método al que se llama cada vez que la pantalla es renderizada.
     * @param deltaTime El tiempo en segundos desde el último renderizado.
     */
    @Override
    public void render(float deltaTime) {
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

    /**
     * Método que detecta qué botón ha sido pulsado y acceder a la pantalla correspondiente.
     */
    public void detectTouch() {
        Vector2 touched; // Guarda las coordenadas para saber en qué parte de la pantalla se toca

        if (Gdx.input.justTouched()) {
            touched = viewport.unproject(new Vector2(Gdx.input.getX(), Gdx.input.getY()));

            if (btnBack.getBoton().contains(touched)) {
                System.out.println("BACK");
                if (musicSound) {
                    soundBtnClick.play();
                }

                byebee.setTitleScreen();
            }

            // Activa la invencibilidad de la abeja contra los enemigos
            if (btnGodModeON.getBoton().contains(touched)) {
                System.out.println("GOD MODE ON");
                if (musicSound) {
                    soundBtnClick.play();
                }

                godmode = true;
                preferences.putBoolean("invencible", godmode);
                preferences.flush();
            }

            // Desactiva la invencibilidad de la abeja contra los enemigos
            if (btnGodModeOFF.getBoton().contains(touched)) {
                System.out.println("GOD MODE OFF");
                if (musicSound) {
                    soundBtnClick.play();
                }

                godmode = false;
                preferences.putBoolean("invencible", godmode);
                preferences.flush();
            }

            // Activa la música y los sonidos del juego
            if (btnMusicON.getBoton().contains(touched)) {
                System.out.println("MUSICA Y SONIDOS ON");
                if (musicSound) {
                    soundBtnClick.play();
                }

                byebee.bgmMenus.play();
                musicSound = true;
                preferences.putBoolean("musicSound", musicSound);
                preferences.flush();
            }

            // Desactiva la música y los sonidos del juego
            if (btnMusicOFF.getBoton().contains(touched)) {
                System.out.println("MUSICA Y SONIDOS OFF");
                if (musicSound) {
                    soundBtnClick.play();
                }

                byebee.bgmMenus.stop();
                musicSound = false;
                preferences.putBoolean("musicSound", musicSound);
                preferences.flush();
            }

            switch(Gdx.app.getType()) { // Fullscreen y modo ventana SOLO funcionan en escritorio y no en Android
                case Desktop:
                    // El juego se pone en pantalla completa (en escritorio, porque en móvil ya lo está)
                    if (btnFullScreenON.getBoton().contains(touched)) {
                        if (!fullscreen) {
                            System.out.println("FULLSCREEN ON");
                            if (musicSound) {
                                soundBtnClick.play();
                            }

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
                            if (musicSound) {
                                soundBtnClick.play();
                            }

                            fullscreen = false;
                            Gdx.graphics.setUndecorated(false);
                            Gdx.graphics.setWindowedMode(ByeBee.WIDTH, ByeBee.HEIGHT);
                            preferences.putBoolean("fullscreen", fullscreen);
                            preferences.flush();
                        }
                    }
            }
        }
    }

    /**
     * Método al que se llama cuando se redimensiona la pantalla.
     * @param width Ancho de la pantalla.
     * @param height Alto de la pantalla.
     */
    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
        spriteBatch.setProjectionMatrix(camera.combined);
    }

    /**
     * Método al que se llama cuando la pantalla es pausada.
     */
    @Override
    public void pause() {

    }

    /**
     * Método al que se llama cuando la pantalla se reanuda después de estar pausada.
     */
    @Override
    public void resume() {

    }

    /**
     * Método al que se llama cuando esta pantalla ya no es la pantalla actual.
     */
    @Override
    public void hide() {

    }

    /**
     * Método al que se llama cuando se destruye la pantalla.
     */
    @Override
    public void dispose() {
        spriteBatch.dispose();
        this.dispose();
    }
}