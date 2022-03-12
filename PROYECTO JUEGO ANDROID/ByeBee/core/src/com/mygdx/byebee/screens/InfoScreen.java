package com.mygdx.byebee.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
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
 * Esta pantalla muestra información acerca de cómo jugar al juego.
 */
public class InfoScreen implements Screen {

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
     * Recurso gráfico que se muestra de primer fondo de la pantalla.
     */
    private Texture bgInfo;

    /**
     * Recurso gráfico que se muestra de segundo fondo de la pantalla.
     */
    private Texture bgInfo2;

    /**
     * Recurso sonoro que emite un sonido cada vez que se pulsa una opción.
     */
    private Sound soundBtnClick;

    /**
     * Botón para volver a la pantalla anterior.
     */
    private Options btnBack;

    /**
     * Botón que muestra la segunda página de información (bgInfo2).
     */
    private Options btnRight;

    /**
     * Botón que muestra la primera página de información (bgInfo).
     */
    private Options btnLeft;

    /**
     * Variable que determina si el botón que tiene que mostrarse abajo a la derecha es btnRight o btnLeft.
     */
    private boolean rightOrLeft = false;

    /**
     * Constructor que inicializa los campos necesario de esta pantalla.
     * @param byebee Sirve para acceder a los métodos de la clase base ByeBee.
     */
    public InfoScreen(ByeBee byebee) {
        this.byebee = byebee;
        camera = new OrthographicCamera();
        viewport = new StretchViewport(ByeBee.WIDTH, ByeBee.HEIGHT, camera);

        soundBtnClick = Gdx.audio.newSound(Gdx.files.internal("sound_clickBtn.mp3"));
        soundBtnClick.setVolume(7, 1);

        bgInfo = new Texture("beeInfo_vacio.png");
        bgInfo2 = new Texture("beeInfo2_vacio.png");
        btnBack = new Options(0, 0, ByeBee.WIDTH / 7, ByeBee.HEIGHT / 7, new Texture("btn_Atras.png"));
        btnRight = new Options(0, 0, ByeBee.WIDTH / 7, ByeBee.HEIGHT / 7, new Texture("btn_derecha.png"));
        btnLeft = new Options(0, 0, ByeBee.WIDTH / 7, ByeBee.HEIGHT / 7, new Texture("btn_izquierda.png"));

        // Se vuelven a poner para que funcione como quería, ya que ahora están inicializados y puedo usar sus datos para la anchura y altura
        btnRight = new Options(ByeBee.WIDTH - btnRight.getWidth(), 0, ByeBee.WIDTH / 7, ByeBee.HEIGHT / 7, new Texture("btn_derecha.png"));
        btnLeft = new Options(ByeBee.WIDTH - btnLeft.getWidth(), 0, ByeBee.WIDTH / 7, ByeBee.HEIGHT / 7, new Texture("btn_izquierda.png"));
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

    /**
     * Método que detecta qué botón ha sido pulsado y acceder a la pantalla correspondiente.
     */
    public void detectTouch() {
        Vector2 touched; // Guarda las coordenadas para saber en qué parte de la pantalla se toca

        if (Gdx.input.justTouched()) {
            touched = viewport.unproject(new Vector2(Gdx.input.getX(), Gdx.input.getY()));

            if (btnBack.getBoton().contains(touched)) {
                System.out.println("BACK");
                soundBtnClick.play();
                byebee.setTitleScreen();
            }

            if (btnRight.getBoton().contains(touched)) {
                soundBtnClick.play();
                if (rightOrLeft) {
                    rightOrLeft = false;
                } else {
                    rightOrLeft = true;
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