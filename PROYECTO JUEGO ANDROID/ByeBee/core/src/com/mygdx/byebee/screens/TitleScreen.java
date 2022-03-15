package com.mygdx.byebee.screens;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.byebee.characters.Options;

/**
 * Es la primera pantalla que ven los jugadores.
 * Sirve para acceder al resto de pantallas del juego: Selección de Nivel, Informacióm, Créditos, Ajustes y Puntuaciones.
 */
public class TitleScreen implements Screen {

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
     * Recurso gráfico que se muestra de fondo de la pantalla.
     */
    private Texture bgTitle;

    /**
     * Recurso sonoro que emite un sonido cada vez que se pulsa una opción.
     */
    private Sound soundBtnClick;

    /**
     * Objeto que se utiliza para acceder a las demás pantallas usando los métodos correspondientes de la clase base.
     */
    private ByeBee byebee;

    /**
     * Se usa para poder utilizar persistencia de datos.
     */
    private Preferences preferences;

    /**
     * Comprueba si la opción de activar/desactivar música había sido seleccionada la última vez que se jugó al juego.
     */
    private boolean checkMusicSound;

    //Se crean los botones de la pantalla del título
    /**
     * Botón para acceder a la pantalla de información.
     */
    private Options optionsInfo;

    /**
     * Botón para acceder a la pantalla de créditos.
     */
    private Options optionsCredits;

    /**
     * Botón para acceder a la pantalla de puntuaciones.
     */
    private Options optionsRecords;

    /**
     * Botón para acceder a la pantalla de ajustes.
     */
    private Options optionsSettings;

    /**
     * Botón para acceder a la pantalla de selección de nivel.
     */
    private Options optionsPlay;

    /**
     * Constructor que inicializa los campos necesario de esta pantalla.
     * @param byebee Sirve para acceder a los métodos de la clase base ByeBee.
     */
    public TitleScreen(ByeBee byebee) {
        this.byebee = byebee;
        camera = new OrthographicCamera();
        viewport = new StretchViewport(ByeBee.WIDTH, ByeBee.HEIGHT, camera);
        preferences = Gdx.app.getPreferences("byebee");
        checkMusicSound = preferences.getBoolean("musicSound", true);

        if (!byebee.bgmMenus.isPlaying() && checkMusicSound) { // Al salir de un nivel deja de sonar su música y vuelve a sonar la de los menús
            byebee.bgmMenus.play();
        }

        soundBtnClick = Gdx.audio.newSound(Gdx.files.internal("sound_clickBtn.mp3"));
        soundBtnClick.setVolume(7, 1);

        bgTitle = new Texture("beeTitle.png");
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
        spriteBatch.draw(bgTitle, 0, 0, ByeBee.WIDTH, ByeBee.HEIGHT);
        spriteBatch.draw(optionsInfo.getTexture(),  optionsInfo.getPosX(), optionsInfo.getPosY(),  optionsInfo.getWidth(), optionsInfo.getHeight());
        spriteBatch.draw(optionsCredits.getTexture(),  optionsCredits.getPosX(), optionsCredits.getPosY(),  optionsCredits.getWidth(), optionsCredits.getHeight());
        spriteBatch.draw(optionsSettings.getTexture(),  optionsSettings.getPosX(), optionsSettings.getPosY(), optionsSettings.getWidth(), optionsSettings.getHeight());
        spriteBatch.draw(optionsRecords.getTexture(),  optionsRecords.getPosX(), optionsRecords.getPosY(), optionsRecords.getWidth(), optionsRecords.getHeight());
        spriteBatch.draw(optionsPlay.getTexture(), optionsPlay.getPosX(), optionsPlay.getPosY(), optionsPlay.getWidth(), optionsPlay.getHeight());
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

            if (optionsPlay.getBoton().contains(touched)) { // BOTÓN JUGAR
                System.out.println("JUGAR");
                if (checkMusicSound) {
                    soundBtnClick.play();
                }

                byebee.setLevelSelect();
            }

            if (optionsInfo.getBoton().contains(touched)) { // BOTÓN INFO
                System.out.println("INFO");
                if (checkMusicSound) {
                    soundBtnClick.play();
                }

                byebee.setInfoScreen();
            }

            if (optionsCredits.getBoton().contains(touched)) { // BOTÓN CRÉDITOS
                System.out.println("CREDITS");
                if (checkMusicSound) {
                    soundBtnClick.play();
                }

                byebee.setCreditsScreen();
            }

            if (optionsSettings.getBoton().contains(touched)) { // BOTÓN AJUSTES
                System.out.println("SETTINGS");
                if (checkMusicSound) {
                    soundBtnClick.play();
                }

                byebee.setSettingsScreen();
            }

            if (optionsRecords.getBoton().contains(touched)) { // BOTÓN PUNTUACIONES
                System.out.println("RECORDS");
                if (checkMusicSound) {
                    soundBtnClick.play();
                }

                byebee.setScoreScreen();
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
        soundBtnClick.dispose();
        this.dispose();
    }
}