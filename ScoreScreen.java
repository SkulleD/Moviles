package com.mygdx.byebee.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
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

/**
 * Esta pantalla muestra las puntuaciones máximas conseguidas en cada nivel del juego.
 */
public class ScoreScreen implements Screen {

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
     * Se usa para generar una fuente de letra customizada
     */
    private FreeTypeFontGenerator fontGenerator;

    /**
     * Se usa para crear diferentes parámetros para la fuente customizada
     */
    private FreeTypeFontGenerator.FreeTypeFontParameter fontParameter;

    /**
     * Sirve para escribir texto en pantalla
     */
    private BitmapFont texto;

    /**
     * Objeto que se utiliza para acceder a las demás pantallas usando los métodos correspondientes de la clase base.
     */
    private ByeBee byebee;

    /**
     * Recurso gráfico que se muestra de fondo de la pantalla.
     */
    private Texture bgScore;

    /**
     * Recurso sonoro que emite un sonido cada vez que se pulsa una opción.
     */
    private Sound soundBtnClick;

    /**
     * Botón para volver a la pantalla anterior.
     */
    private Options btnBack;

    /**
     * Muestra la puntuación máxima obtenida en el primer nivel
     */
    private Score puntuacion;

    /**
     * Muestra la puntuación máxima obtenida en el segundo nivel
     */
    private Score puntuacion2;

    /**
     * Muestra la puntuación máxima obtenida en el tercer nivel
     */
    private Score puntuacion3;

    /**
     * Muestra la puntuación máxima obtenida en el nivel bonus
     */
    private Score puntuacion4;

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
    public ScoreScreen(ByeBee byebee) {
        this.byebee = byebee;
        camera = new OrthographicCamera();
        viewport = new StretchViewport(ByeBee.WIDTH, ByeBee.HEIGHT, camera);
        preferences = Gdx.app.getPreferences("byebee");
        checkMusicSound = preferences.getBoolean("musicSound", true);

        soundBtnClick = Gdx.audio.newSound(Gdx.files.internal("sound_clickBtn.mp3"));
        soundBtnClick.setVolume(7, 1);

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
        spriteBatch.draw(bgScore, 0, 0, ByeBee.WIDTH, byebee.HEIGHT);
        spriteBatch.draw(btnBack.getTexture(), btnBack.getPosX(), btnBack.getPosY(), btnBack.getWidth(), btnBack.getHeight());
        texto.draw(spriteBatch, "" + puntuacion4.getHighScore4(), puntuacion4.getPosX(), puntuacion4.getPosY());
        texto.draw(spriteBatch, "" + puntuacion3.getHighScore3(), puntuacion3.getPosX(), puntuacion3.getPosY());
        texto.draw(spriteBatch, "" + puntuacion2.getHighScore2(), puntuacion2.getPosX(), puntuacion2.getPosY());
        texto.draw(spriteBatch, "" + puntuacion.getHighScore(), puntuacion.getPosX(), puntuacion.getPosY());

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
         spriteBatch.dispose();
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