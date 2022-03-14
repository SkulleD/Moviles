package com.mygdx.byebee.screens;

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
 * Esta pantalla se utiliza que los jugadores seleccionen el nivel que quieren jugar.
 */
public class LevelSelect implements Screen {

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
    private Texture bgLevels;

    /**
     * Recurso sonoro que emite un sonido cada vez que se pulsa una opción.
     */
    private Sound soundBtnClick;

    /**
     * Botón para volver a la pantalla anterior.
     */
    private Options btnBack;

    /**
     * Botón que sirve para empezar a jugar el primer nivel: Campo de Flores
     */
    private Options btnLvl1;

    /**
     * Botón que sirve para empezar a jugar el segundo nivel: Manantial
     */
    private Options btnLvl2;

    /**
     * Botón que sirve para empezar a jugar el tercer nivel: Colmena
     */
    private Options btnLvl3;

    /**
     * Botón que sirve para empezar a jugar el nivel bonus: Sin Fin
     */
    private Options btnLvl4;

    /**
     * Comprueba si la opción de activar/desactivar música había sido seleccionada la última vez que se jugó al juego.
     */
    private boolean checkMusicSound;

    /**
     * Se usa para poder utilizar persistencia de datos.
     */
    Preferences preferences;

    /**
     * Constructor que inicializa los campos necesario de esta pantalla.
     * @param byebee Sirve para acceder a los métodos de la clase base ByeBee.
     */
    public LevelSelect(ByeBee byebee) {
        this.byebee = byebee;
        camera = new OrthographicCamera();
        viewport = new StretchViewport(ByeBee.WIDTH, ByeBee.HEIGHT, camera);
        preferences = Gdx.app.getPreferences("byebee");
        checkMusicSound = preferences.getBoolean("musicSound", true);

        soundBtnClick = Gdx.audio.newSound(Gdx.files.internal("sound_clickBtn.mp3"));
        soundBtnClick.setVolume(7, 1);

        bgLevels = new Texture("beeNiveles_Vacio.png");
        btnBack = new Options(0, 0, ByeBee.WIDTH / 7, ByeBee.HEIGHT / 7, new Texture("btn_Atras.png"));
        btnLvl1 = new Options(ByeBee.WIDTH / 20, ByeBee.HEIGHT / 4, ByeBee.WIDTH / 5, ByeBee.HEIGHT / 2, new Texture("btn_Niveles1.png"));
        btnLvl2 = new Options(ByeBee.WIDTH / 12 + btnLvl1.getWidth(), ByeBee.HEIGHT / 4, ByeBee.WIDTH / 5, ByeBee.HEIGHT / 2, new Texture("btn_Niveles2.png"));
        btnLvl3 = new Options(ByeBee.WIDTH / 9 + btnLvl2.getWidth() * 2, ByeBee.HEIGHT / 4, ByeBee.WIDTH / 5, ByeBee.HEIGHT / 2, new Texture("btn_Niveles3.png"));
        btnLvl4 = new Options(ByeBee.WIDTH / 3 + btnLvl3.getWidth() * 2, ByeBee.HEIGHT / 4, ByeBee.WIDTH / 5, ByeBee.HEIGHT / 2, new Texture("btn_Niveles4.png"));

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
        spriteBatch.draw(bgLevels, 0, 0, ByeBee.WIDTH, ByeBee.HEIGHT);
        spriteBatch.draw(btnBack.getTexture(), btnBack.getPosX(), btnBack.getPosY(), btnBack.getWidth(), btnBack.getHeight());
        spriteBatch.draw(btnLvl1.getTexture(), btnLvl1.getPosX(), btnLvl1.getPosY(), btnLvl1.getWidth(), btnLvl1.getHeight());
        spriteBatch.draw(btnLvl2.getTexture(), btnLvl2.getPosX(), btnLvl2.getPosY(), btnLvl2.getWidth(), btnLvl2.getHeight());
        spriteBatch.draw(btnLvl3.getTexture(), btnLvl3.getPosX(), btnLvl3.getPosY(), btnLvl3.getWidth(), btnLvl3.getHeight());
        spriteBatch.draw(btnLvl4.getTexture(), btnLvl4.getPosX(), btnLvl4.getPosY(), btnLvl4.getWidth(), btnLvl4.getHeight());
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

            if (btnLvl1.getBoton().contains(touched)) {
                System.out.println("LEVEL 1 SELECTED");
                if (checkMusicSound) {
                    soundBtnClick.play();
                }

                byebee.bgmMenus.stop();
                byebee.setLevel1();
            }

            if (btnLvl2.getBoton().contains(touched)) {
                System.out.println("LEVEL 2 SELECTED");
                if (checkMusicSound) {
                    soundBtnClick.play();
                }

                byebee.bgmMenus.stop();

                byebee.setLevel2();
            }

            if (btnLvl3.getBoton().contains(touched)) {
                System.out.println("LEVEL 3 SELECTED");
                if (checkMusicSound) {
                    soundBtnClick.play();
                }

                byebee.bgmMenus.stop();
                byebee.setLevel3();
            }

            if (btnLvl4.getBoton().contains(touched)) {
                System.out.println("LEVEL 4 SELECTED");
                if (checkMusicSound) {
                    soundBtnClick.play();
                }

                byebee.bgmMenus.stop();
                byebee.setLevel4();
            }

            if (btnBack.getBoton().contains(touched)) {
                System.out.println("BACK");
                if (checkMusicSound) {
                    soundBtnClick.play();
                }

                byebee.setTitleScreen();
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