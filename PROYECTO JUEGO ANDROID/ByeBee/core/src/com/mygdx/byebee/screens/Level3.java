package com.mygdx.byebee.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
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
import com.mygdx.byebee.characters.Bee;
import com.mygdx.byebee.characters.Enemy;
import com.mygdx.byebee.characters.Health;
import com.mygdx.byebee.characters.Options;
import com.mygdx.byebee.characters.Score;

import java.util.LinkedList;
import java.util.ListIterator;

/**
 * Este es el tercer nivel jugable de ByeBee.
 * Dificultad: Media.
 */
public class Level3 implements Screen {

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
     * Música que se suena en el nivel 3.
     */
    private Music bgmLevel3;

    /**
     * Recurso sonoro que emite un sonido cada vez que la abeja recibe daño.
     */
    private Sound soundDamage;

    /**
     * Recurso sonoro que emite un sonido cada vez que la abeja consigue un escudo.
     */
    private Sound soundShieldGet;

    /**
     * Recurso sonoro que emite un sonido cada vez que el escudo obtenido se rompe.
     */
    private Sound soundShieldBreak;

    /**
     * Recurso sonoro que emite un sonido al superar con éxito el nivel.
     */
    private Sound soundLevelClear;

    /**
     * Recurso sonoro que emite un sonido cuando la abeja pierde toda su salud.
     */
    private Sound soundLevelFail;

    /**
     * Recurso sonoro que emite un sonido cada vez que se pulsa una opción.
     */
    private Sound soundBtnClick;

    /**
     * Array que contiene los diferentes recursos gráficos que forman el fondo del nivel.
     */
    private Texture[] backgrounds;

    // Timings del movimiento de fondo y spawns de enemigos
    /**
     * Array de floats usado para calcular la velocidad de las imágenes que forman el fondo del nivel.
     */
    private float[] bgOffsets = {0, 0, 0}; // Para el efecto parallax del fondo

    /**
     * Variable que se usa para determinar la velocidad de movimiento del fondo parallax.
     */
    private float bgMaxScrollSpeed; // Para el movimiento del escenario

    /**
     * Se usa para calcular cada cuánto aparece un enemigo pájaro.
     */
    private float timeBetweenSpawnsBird = 7f;

    /**
     * Se usa para calcular cada cuánto aparece un enemigo abeja lancera.
     */
    private float timeBetweenSpawnsBeeLancer = 3f;

    /**
     * Se usa para calcular el momento en el que aparece la línea meta.
     */
    private float timeBetweenSpawnsMeta = 70f;

    /**
     * Se usa para calcular cada cuánto aparece un objeto escudo.
     */
    private float timeBetweenSpawnsEscudo = 16f;

    /**
     * Sirve como base para los cálculos de aparición de los enemigos pájaros.
     */
    private float birdSpawnTimer = 0;

    /**
     * Sirve como base para los cálculos de aparición de los enemigos abejas lanceras.
     */
    private float beeLancerSpawnTimer = 0;

    /**
     * Sirve como base para los cálculos de aparición de la línea de meta.
     */
    private float metaSpawnTimer = 0;

    /**
     * Sirve como base para los cálculos de aparición de los objetos escudos.
     */
    private float escudoSpawnTimer = 0;

    // Salud de la abeja
    /**
     * Muestra arriba a la izquierda de la pantalla la cantidad de salud actual de la abeja.
     */
    private Health health;

    // Personaje controlable
    /**
     * La abeja protagonista que controlamos en cada nivel.
     */
    private Bee bee;

    // Puntuación en pantalla
    /**
     * Muestra arriba a la izquierda la puntuación que vas consiguiendo durante el nivel.
     */
    private Score puntuacion;

    // Enemigos y entidades
    /**
     * Lista a la que se van añadiendo los enemigos que van apareciendo por pantalla.
     */
    private LinkedList<Enemy> enemyList;

    /**
     * El enemigo pájaro. Si te golpea te quita un punto de vida.
     */
    private Enemy bird;

    /**
     * El enemigo abeja lancera. Si te golpea te quita un punto de vida.
     */
    private Enemy beeLancer;

    /**
     * El objeto escudo. Hace que puedas aguantar un golpe enemigo sin perder una vida.
     */
    private Enemy escudo;

    /**
     * La línea de meta. Hace que completes el nivel al tocarla.
     */
    private Enemy meta;

    // Fin del juego y nivel completado
    /**
     * Hace que el nivel se detenga completamente al ponerse a true.
     */
    private boolean stopRunning = false;

    /**
     * Hace que los botones de opciones se vuelvan activos en cuanto se gane o pierda la partida.
     */
    private boolean optionsMenu;

    /**
     * Hace que se pueda mostrar el menú de nivel completado.
     */
    private boolean levelFinished;

    /**
     * Hace que, una vez se gane o pierda la partida, el sonido correspondiente solo suene una vez.
     */
    private boolean soundOnce = false;

    /**
     * Recurso gráfico que se muestra al perder la partida.
     */
    private Texture gameOver;

    /**
     * Botón que aparece al perder la partida.
     * Sirve para volver a empezar el nivel.
     */
    private Options btnRetry;

    /**
     * Botón que aparece al perder la partida.
     * Sirve para volver al menú principal.
     */
    private Options btnMenu;

    /**
     * Recurso gráfico que se muestra al ganar la partida.
     */
    private Texture levelCompleted;

    /**
     * Botón que aparece al ganar la partida.
     * Sirve para volver al menú principal.
     */
    private Options btnContinue;

    /**
     * Constructor que inicializa los campos necesario de esta pantalla.
     * @param byebee Sirve para acceder a los métodos de la clase base ByeBee.
     */
    public Level3(ByeBee byebee) {
        this.byebee = byebee;
        camera = new OrthographicCamera();
        viewport = new StretchViewport(ByeBee.WIDTH, ByeBee.HEIGHT, camera);

        bgmLevel3 = Gdx.audio.newMusic(Gdx.files.internal("bgm_level3.mp3"));
        bgmLevel3.setLooping(true);
        bgmLevel3.setVolume(1);
        bgmLevel3.play();

        soundDamage = Gdx.audio.newSound(Gdx.files.internal("sound_Damage.mp3"));
        soundDamage.setVolume(2, 1);
        soundShieldGet = Gdx.audio.newSound(Gdx.files.internal("sound_shieldGet.mp3"));
        soundShieldGet.setVolume(3, 1);
        soundShieldBreak = Gdx.audio.newSound(Gdx.files.internal("sound_shieldBreak.mp3"));
        soundShieldBreak.setVolume(4, 1);
        soundLevelClear = Gdx.audio.newSound(Gdx.files.internal("sound_levelClear.mp3"));
        soundLevelClear.setVolume(5, 1);
        soundLevelFail = Gdx.audio.newSound(Gdx.files.internal("sound_levelFail.mp3"));
        soundLevelFail.setVolume(6, 1);
        soundBtnClick = Gdx.audio.newSound(Gdx.files.internal("sound_clickBtn.mp3"));
        soundBtnClick.setVolume(7, 1);

        backgrounds = new Texture[3];
        backgrounds[0] = new Texture("lvl3_background2.png");
        backgrounds[1] = new Texture("lvl3_background1.png");
        backgrounds[2] = new Texture("lvl3_foreground.png");
        bgMaxScrollSpeed = (float) (ByeBee.WIDTH / 4);
        optionsMenu = false; // Esto hace que las opciones que salen al perder o ganar solo aparezcan con esto a true
        levelFinished = false; // Lo de nivel completado aparece en cuanto esto esté true

        enemyList = new LinkedList<>();

        health = new Health(10, ByeBee.HEIGHT - 100, ByeBee.WIDTH / 8, ByeBee.HEIGHT / 8, new Texture[8]);
        bee = new Bee(ByeBee.WIDTH / 6, ByeBee.HEIGHT / 3,
                ByeBee.WIDTH / 7, ByeBee.HEIGHT / 6, new Texture("spriteBee.png"), 7);
        bird = new Enemy(ByeBee.WIDTH, (float) (Math.random() * ByeBee.HEIGHT + 1),
                ByeBee.WIDTH / 6, ByeBee.HEIGHT / 7, new Texture("bird.png"), 10, false, false, -400);
        beeLancer = new Enemy(ByeBee.WIDTH, (float) (Math.random() * ByeBee.HEIGHT + 1),
                ByeBee.WIDTH / 6, ByeBee.HEIGHT / 7, new Texture("bee_lancer.png"), 4, false, false, -400);

        meta = new Enemy(ByeBee.WIDTH, 0, ByeBee.WIDTH / 7, ByeBee.HEIGHT, new Texture("beeMeta.png"), 100, true, false, -400);

        escudo = new Enemy(ByeBee.WIDTH, (float) (Math.random() * ByeBee.HEIGHT + 1), ByeBee.WIDTH / 8, ByeBee.HEIGHT / 8, new Texture("btn_MusicaON.png"), 1, false, true, -400);

        gameOver = new Texture("beeGameOver_vacio.png");
        btnRetry = new Options(ByeBee.WIDTH / 5, ByeBee.HEIGHT / 4, ByeBee.WIDTH / 4, ByeBee.HEIGHT / 5, new Texture("btnReintentar.png"));
        btnMenu = new Options(btnRetry.getPosX() * 2 + btnRetry.getWidth() / 2, ByeBee.HEIGHT / 4, ByeBee.WIDTH / 4, ByeBee.HEIGHT / 5, new Texture("btnVolverMenu.png"));
        levelCompleted = new Texture("beeLevelFinished_vacio.png");
        btnContinue = new Options(ByeBee.WIDTH / 8 + btnRetry.getWidth(), ByeBee.HEIGHT / 4, ByeBee.WIDTH / 4, ByeBee.HEIGHT / 5, new Texture("btnContinuar.png"));

        fontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("PalatinoLinotype.ttf"));
        fontParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        fontParameter.color = Color.ORANGE;
        fontParameter.borderColor = Color.BLACK;
        fontParameter.borderWidth = 1;
        texto = fontGenerator.generateFont(fontParameter);
        texto.getData().setScale(3);
        texto.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        puntuacion = new Score(texto, health.getPosX() + health.getWidth(), ByeBee.HEIGHT - health.getHeight() / 2);
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
        // Llamada a métodos de personajes
        if (!optionsMenu) {
            bee.fly();
            bee.update(deltaTime);
        }

        spriteBatch.begin();

        renderBackground(deltaTime); // Muestra fondo parallax
        spriteBatch.draw(bee.getBeeTexture(), bee.getPosX(), bee.getPosY(), bee.getWidth(), bee.getHeight());

        if (bee.isHasShield()) { // Si la abeja tiene actualmente un escudo se le dibuja encima
            spriteBatch.draw(new Texture("btn_MusicaON.png"), bee.getPosX(), bee.getPosY(), escudo.getWidth(), escudo.getHeight());
        }

        spawnEntities(deltaTime);
        // Los enemigos van apareciendo aleatoriamente cada cierto tiempo

        for (int i = 0; i < enemyList.size(); i++) {
            if (optionsMenu) {
                enemyList.get(i).setFinJuego(true);
                bee.setFinJuego(true);
            }

            enemyList.get(i).move();
            enemyList.get(i).update(deltaTime);

            spriteBatch.draw(enemyList.get(i).getTexture(), enemyList.get(i).getPosX(), enemyList.get(i).getPosY(), enemyList.get(i).getWidth(), enemyList.get(i).getHeight());
            // Detecta colisiones entre la abeja y los enemigos
            detectCollisions();
        }

        drawHealth(); // Muestra el indicador de vida
        scoringPoints(); // Llamada al método que controla la puntuación
        gameOver(); // Fin del juego aparece en cuanto la abeja pierda toda su vida
        levelCompleted(); // Nivel completado en cuanto se llega a la meta

        detectTouch(); // Para detectar toques en pantalla
        spriteBatch.end();
    }

    /**
     * Método que realiza el efecto parallax del fondo del nivel.
     * @param deltaTime El tiempo en segundos desde el último renderizado.
     */
    private void renderBackground(float deltaTime) { // Método para mostrar el fondo con efecto parallax
        bgOffsets[0] += deltaTime * bgMaxScrollSpeed / 8;
        bgOffsets[1] += deltaTime * bgMaxScrollSpeed / 4;
        bgOffsets[2] += deltaTime * bgMaxScrollSpeed / 2;

        if (!optionsMenu) {
            for (int layer = 0; layer < bgOffsets.length; layer++) { // Va recorriendo las capas del fondo y mostrándolas
                if (bgOffsets[layer] > ByeBee.WIDTH) {
                    bgOffsets[layer] = 0;
                }

                spriteBatch.draw(backgrounds[layer], -bgOffsets[layer], 0, ByeBee.WIDTH, ByeBee.HEIGHT);
                spriteBatch.draw(backgrounds[layer], -bgOffsets[layer] + ByeBee.WIDTH, 0, ByeBee.WIDTH, ByeBee.HEIGHT);
            }
        }
    }

    /**
     * Método que sirve para detectar colisiones entre la abeja y las distintas entidades que aparecen en el nivel.
     */
    private void detectCollisions() { // Detecta colisiones de hitboxes
        ListIterator<Enemy> enemyListIterator = enemyList.listIterator();

        while (enemyListIterator.hasNext()) {
            Enemy enemy = enemyListIterator.next();

            if (bee.intersects(enemy.getHitbox())) { // Si la abeja toca la meta se completa el nivel
                if (enemy.isMeta()) {
                    optionsMenu = true;
                    levelFinished = true;
                } else if (enemy.isItem()) {
                    if (!bee.isHasShield()) { // Solo puede conseguir un escudo si no tiene ninguno equipado
                        System.out.println("SHIELD GET");
                        soundShieldGet.play();
                        bee.setHasShield(true);
                        enemyListIterator.remove();
                    }
                } else {
                    if (!enemy.isHasHit() && !bee.isInvencible()) {
                        if (bee.isHasShield()) {
                            System.out.println("SHIELD PROTECTS BEE AND BREAKS");
                            soundShieldBreak.play();
                            enemy.setHasHit(true); // Cuando un enemigo golpea a la abeja, ya no puede volver a golpearla
                            bee.setHasShield(false);
                            spriteBatch.draw(new Texture("bee.png"), bee.getPosX(), bee.getPosY(), bee.getWidth(), bee.getHeight());
                        } else {
                            System.out.println("BEE IS HIT");
                            soundDamage.play();
                            enemy.setHasHit(true); // Cuando un enemigo golpea a la abeja, ya no puede volver a golpearla
                            bee.setHealth(bee.getHealth() - 1);
                            puntuacion.setScore(puntuacion.getScore() - 100); // Pierdes 100 puntos si un enemigo te toca
                            Gdx.input.vibrate(100); // El dispositivo vibra cuando la abeja es golpeada
                        }
                    }
                }
                break;
            }
        }
    }

    /**
     * Método que controla el orden de aparición de las entidades del nivel, así como su posición en el eje Y de forma aleatoria (excepto la línea de meta).
     * @param deltaTime El tiempo en segundos desde el último renderizado.
     */
    private void spawnEntities(float deltaTime) { // Hace que los enemigos vayan apareciendo de forma aleatoria
        birdSpawnTimer += deltaTime;
        beeLancerSpawnTimer += deltaTime;
        metaSpawnTimer += deltaTime;
        escudoSpawnTimer += deltaTime;


        if (birdSpawnTimer > timeBetweenSpawnsBird) { // PÁJARO
            enemyList.add(new Enemy(ByeBee.WIDTH, (float) (Math.random() * ByeBee.HEIGHT + 1),
                    ByeBee.WIDTH / 4, ByeBee.HEIGHT / 5, new Texture("bird.png"), 10, false, false, -400));

            birdSpawnTimer -= timeBetweenSpawnsBird;
        }

        if (beeLancerSpawnTimer > timeBetweenSpawnsBeeLancer) { // ABEJA LANCERA
            enemyList.add(new Enemy(ByeBee.WIDTH, (float) (Math.random() * ByeBee.HEIGHT + 1),
                    ByeBee.WIDTH / 5, ByeBee.HEIGHT / 5, new Texture("bee_lancer.png"), 5, false, false, -400));

            beeLancerSpawnTimer -= timeBetweenSpawnsBeeLancer;
        }

        if (escudoSpawnTimer > timeBetweenSpawnsEscudo) { // ITEM ESCUDO
            enemyList.add(new Enemy(ByeBee.WIDTH, (float) (Math.random() * ByeBee.HEIGHT + 1), ByeBee.WIDTH / 8, ByeBee.HEIGHT / 8, new Texture("btn_MusicaON.png"), 1, false, true, -400));

            escudoSpawnTimer -= timeBetweenSpawnsEscudo;
        }

        if (metaSpawnTimer > timeBetweenSpawnsMeta) { // META FINAL DE NIVEL
            enemyList.add(new Enemy(ByeBee.WIDTH, 0, ByeBee.WIDTH / 7, ByeBee.HEIGHT, new Texture("beeMeta.png"), 100, true, false, -400));

            metaSpawnTimer -= timeBetweenSpawnsMeta;
        }
    }

    /**
     * Método que dibuja la salud actual de la abeja en la esquina superior izquierda.
     */
    private void drawHealth() {
        if (bee.getHealth() > 0) {
            spriteBatch.draw(health.getTexture()[bee.getHealth()], health.getPosX(), health.getPosY(), health.getWidth(), health.getHeight());

            if (!levelFinished) {
                optionsMenu = false;
            }

        } else {
            optionsMenu = true;
            spriteBatch.draw(health.getTexture()[0], health.getPosX(), health.getPosY(), health.getWidth(), health.getHeight());
        }
    }

    /**
     * Método que muestra el menú de fin de la partida si la salud de la abeja llega a 0.
     */
    private void gameOver() {
        if (bee.getHealth() == 0) {
            if (!soundOnce) {
                soundLevelFail.play();
                soundOnce = true;
            }

            System.out.println("GAME OVER");
            //spriteBatch.draw(gameOver, ByeBee.WIDTH / 6, ByeBee.HEIGHT / 5, gameOver.getWidth(), gameOver.getHeight());
            spriteBatch.draw(gameOver, Gdx.graphics.getWidth() / ByeBee.WIDTH, Gdx.graphics.getHeight() / ByeBee.HEIGHT, gameOver.getWidth(), gameOver.getHeight());
            spriteBatch.draw(btnRetry.getTexture(), btnRetry.getPosX(), btnRetry.getPosY(), btnRetry.getWidth(), btnRetry.getHeight());
            spriteBatch.draw(btnMenu.getTexture(), btnMenu.getPosX(), btnMenu.getPosY(), btnMenu.getWidth(), btnMenu.getHeight());
        }
    }

    /**
     * Método que muestra el menú de nivel completado en cuanto se alcanza la línea de meta.
     */
    private void levelCompleted() {
        if (levelFinished) {
            if (!soundOnce) {
                soundLevelClear.play();
                soundOnce = true;
            }

            System.out.println("FINISH LINE");
            //spriteBatch.draw(levelCompleted, ByeBee.WIDTH / 6, ByeBee.HEIGHT / 5, levelCompleted.getWidth(), levelCompleted.getHeight());
            spriteBatch.draw(levelCompleted, Gdx.graphics.getWidth() / ByeBee.WIDTH, Gdx.graphics.getHeight() / ByeBee.HEIGHT, levelCompleted.getWidth(), levelCompleted.getHeight());
            spriteBatch.draw(btnContinue.getTexture(), btnContinue.getPosX(), btnContinue.getPosY(), btnContinue.getWidth(), btnContinue.getHeight());

            if (bee.isInvencible()) {
                puntuacion.setScore3(0);
            }

            texto.draw(spriteBatch, "Puntuación: " + puntuacion.getScore3(), btnContinue.getPosX(), btnContinue.getPosY() + btnContinue.getHeight() + btnContinue.getHeight() / 2);

            puntuacion.setHighScore3(puntuacion.getScore3());

            System.out.println("Puntuacion: " + puntuacion.getScore3());
            System.out.println("Puntuacion Maxima: " + puntuacion.getHighScore3());
        }
    }

    /**
     * Método que va mostrando la puntuación conseguida en cada momento en la esquina superior izquierda.
     */
    private void scoringPoints() {
        if (!optionsMenu) {
            puntuacion.setScore3(puntuacion.getScore3() + 1);
            texto.draw(spriteBatch, " Puntos: " + puntuacion.getScore3(), puntuacion.getPosX(), puntuacion.getPosY());
        }
    }

    /**
     * Método que detecta qué botón ha sido pulsado y acceder a la pantalla correspondiente.
     */
    public void detectTouch() {
        Vector2 touched; // Guarda las coordenadas para saber en qué parte de la pantalla se toca

        if (Gdx.input.justTouched()) {
            touched = viewport.unproject(new Vector2(Gdx.input.getX(), Gdx.input.getY()));
            System.out.println("TOUCHED");

            if (btnRetry.getBoton().contains(touched) && optionsMenu) { // VOLVER A INTENTAR EL NIVEL
                System.out.println("RETRY LEVEL 3");
                soundBtnClick.play();
                bgmLevel3.stop();
                byebee.setLevel3();
            }

            if (btnMenu.getBoton().contains(touched) && optionsMenu) { // VOLVER AL MENÚ DE SELECCIÓN DE NIVEL
                System.out.println("BACK TO MENU");
                soundBtnClick.play();
                bgmLevel3.stop();
                byebee.setTitleScreen();
            }

            if (btnContinue.getBoton().contains(touched) && optionsMenu) { // COMPLETAR NIVEL Y VOLVER A MENÚ DE SELECCIÓN DE NIVEL
                System.out.println("LEVEL COMPLETED");
                soundBtnClick.play();
                bgmLevel3.stop();
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
        texto.dispose();
        spriteBatch.dispose();
        bgmLevel3.dispose();
    }
}