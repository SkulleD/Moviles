package com.mygdx.byebee.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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

public class Level2 implements Screen {
    private Camera camera;
    private Viewport viewport;
    private SpriteBatch spriteBatch;
    private BitmapFont texto;
    private ByeBee byebee;
    private Music bgmLevel2;

    private Texture[] backgrounds;

    // Timing cosas
    private float[] bgOffsets = {0, 0, 0, 0}; // Para el efecto parallax del fondo
    private float bgMaxScrollSpeed; // Para el movimiento del escenario

    private float timeBetweenSpawnsBird = 7f;
    private float timeBetweenSpawnsBeeLancer = 3f;
    private float timeBetweenSpawnsMeta = 60f;
    private float birdSpawnTimer = 0;
    private float beeLancerSpawnTimer = 0;
    private float metaSpawnTimer = 0;

    // Salud de la abeja
    private Health health;

    // Personaje controlable
    private Bee bee;

    // Puntuación en pantalla
    private Score puntuacion;

    // Enemigos
    private LinkedList<Enemy> enemyList;
    private Enemy bird;
    private Enemy beeLancer;

    // Fin del juego y nivel completado
    private boolean stopRunning = false; // Las cosas se detienen al aparecer el mensaje
    private boolean optionsMenu; // Los botones se vuelven activos en cuanto se pierda o gane la partida
    private boolean levelFinished;
    private Texture gameOver;
    private Options btnRetry;
    private Options btnMenu;
    private Texture levelCompleted;
    private Options btnContinue;
    private Enemy meta;

    public Level2(ByeBee byebee) {
        this.byebee = byebee;
        camera = new OrthographicCamera();
        viewport = new StretchViewport(ByeBee.WIDTH, ByeBee.HEIGHT, camera);

        bgmLevel2 = Gdx.audio.newMusic(Gdx.files.internal("bgm_level2.mp3"));
        bgmLevel2.setLooping(true);
        bgmLevel2.setVolume(1);
        bgmLevel2.play();

        backgrounds = new Texture[4];
        backgrounds[0] = new Texture("lvl2_background3.png");
        backgrounds[1] = new Texture("lvl2_background2.png");
        backgrounds[2] = new Texture("lvl2_background1.png");
        backgrounds[3] = new Texture("lvl2_foreground.png");
        bgMaxScrollSpeed = (float) (ByeBee.WIDTH / 4);

        enemyList = new LinkedList<>();

        health = new Health(10, ByeBee.HEIGHT - 100, ByeBee.WIDTH / 8, ByeBee.HEIGHT / 8, new Texture[8]);
        bee = new Bee(ByeBee.WIDTH / 6, ByeBee.HEIGHT / 3,
                150, 150, new Texture("bee.png"), 7);
        bird = new Enemy(ByeBee.WIDTH, (float) (Math.random() * ByeBee.HEIGHT + 1),
                170, 170, new Texture("bird.png"), 10, false, -400);
        beeLancer = new Enemy(ByeBee.WIDTH, (float) (Math.random() * ByeBee.HEIGHT + 1),
                170, 170, new Texture("bee_lancer.png"), 4, false, -400);

        meta = new Enemy(ByeBee.WIDTH, 0, ByeBee.WIDTH / 7, ByeBee.HEIGHT, new Texture("beeMeta.png"), 100, true, -400);

        gameOver = new Texture("beeGameOver_vacio.png");
        btnRetry = new Options(ByeBee.WIDTH / 5, ByeBee.HEIGHT / 4, Gdx.graphics.getWidth() / 4, Gdx.graphics.getHeight() / 5, new Texture("btnReintentar.png"));
        btnMenu = new Options(btnRetry.getPosX() * 2 + btnRetry.getWidth() / 2, ByeBee.HEIGHT / 4, Gdx.graphics.getWidth() / 4, Gdx.graphics.getHeight() / 5, new Texture("btnVolverMenu.png"));
        levelCompleted = new Texture("beeLevelFinished_vacio.png");
        btnContinue = new Options(ByeBee.WIDTH / 8 + btnRetry.getWidth(), ByeBee.HEIGHT / 4, ByeBee.WIDTH / 4, ByeBee.HEIGHT / 5, new Texture("btnContinuar.png"));

        texto = new BitmapFont();
        texto.getData().setScale(3);
        texto.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        texto.setColor(Color.YELLOW);

        puntuacion = new Score(texto, health.getPosX() + health.getWidth(), ByeBee.HEIGHT - health.getHeight() / 2);
        spriteBatch = new SpriteBatch();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float deltaTime) {
        // Llamada a métodos de personajes
        bee.fly();
        bee.update(deltaTime);

        spriteBatch.begin();

        renderBackground(deltaTime); // Muestra fondo parallax
        spriteBatch.draw(bee.getTexture(), bee.getPosX(), bee.getPosY(), bee.getWidth(), bee.getHeight());

        spawnEnemies(deltaTime);
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

    private void renderBackground(float deltaTime) { // Método para mostrar el fondo con efecto parallax
        bgOffsets[0] += deltaTime * bgMaxScrollSpeed / 8;
        bgOffsets[1] += deltaTime * bgMaxScrollSpeed / 4;
        bgOffsets[2] += deltaTime * bgMaxScrollSpeed / 2;
        bgOffsets[3] += deltaTime * bgMaxScrollSpeed;

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

    private void detectCollisions() { // Detecta colisiones de hitboxes
        ListIterator<Enemy> enemyListIterator = enemyList.listIterator();

        while (enemyListIterator.hasNext()) {
            Enemy enemy = enemyListIterator.next();

            if (bee.intersects(enemy.getHitbox())) { // Si la abeja toca la meta se completa el nivel
                if (enemy.isMeta()) {
                    bee.setGRAVITY(0);
                    optionsMenu = true;
                    levelFinished = true;
                } else {
                    if (!enemy.isHasHit() && !bee.isInvencible()) {
                        System.out.println("BEE IS HIT");
                        enemy.setHasHit(true); // Cuando un enemigo golpea a la abeja, ya no puede volver a golpearla
                        bee.setHealth(bee.getHealth() - 1);
                        puntuacion.setScore2(puntuacion.getScore2() - 100); // Pierdes 100 puntos si un enemigo te toca
                    }
                }
                break;
            }
        }
    }

    private void spawnEnemies(float deltaTime) { // Hace que los enemigos vayan apareciendo de forma aleatoria
        birdSpawnTimer += deltaTime;
        beeLancerSpawnTimer += deltaTime;
        metaSpawnTimer += deltaTime;

        if (birdSpawnTimer > timeBetweenSpawnsBird) { // PÁJARO
            enemyList.add(new Enemy(ByeBee.WIDTH, (float) (Math.random() * ByeBee.HEIGHT + 1),
                    ByeBee.WIDTH / 6, ByeBee.HEIGHT / 7, new Texture("bird.png"), 10, false, -400));

            birdSpawnTimer -= timeBetweenSpawnsBird;
        }

        if (beeLancerSpawnTimer > timeBetweenSpawnsBeeLancer) { // ABEJA LANCERA
            enemyList.add(new Enemy(ByeBee.WIDTH, (float) (Math.random() * ByeBee.HEIGHT + 1),
                    ByeBee.WIDTH / 6, ByeBee.HEIGHT / 7, new Texture("bee_lancer.png"), 5, false, -400));

            beeLancerSpawnTimer -= timeBetweenSpawnsBeeLancer;
        }

        if (metaSpawnTimer > timeBetweenSpawnsMeta) { // META FINAL DE NIVEL
            enemyList.add(new Enemy(ByeBee.WIDTH, 0, ByeBee.WIDTH / 7, ByeBee.HEIGHT, new Texture("beeMeta.png"), 100, true, -400));

            metaSpawnTimer -= timeBetweenSpawnsMeta;
        }
    }

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

    private void gameOver() {
        if (bee.getHealth() == 0) {
            System.out.println("GAME OVER");
            //spriteBatch.draw(gameOver, ByeBee.WIDTH / 6, ByeBee.HEIGHT / 5, gameOver.getWidth(), gameOver.getHeight());
            spriteBatch.draw(gameOver, Gdx.graphics.getWidth() / ByeBee.WIDTH, Gdx.graphics.getHeight() / ByeBee.HEIGHT, gameOver.getWidth(), gameOver.getHeight());
            spriteBatch.draw(btnRetry.getTexture(), btnRetry.getPosX(), btnRetry.getPosY(), btnRetry.getWidth(), btnRetry.getHeight());
            spriteBatch.draw(btnMenu.getTexture(), btnMenu.getPosX(), btnMenu.getPosY(), btnMenu.getWidth(), btnMenu.getHeight());
        }
    }

    private void levelCompleted() {
        if (levelFinished) {
            System.out.println("FINISH LINE");
            //spriteBatch.draw(levelCompleted, ByeBee.WIDTH / 6, ByeBee.HEIGHT / 5, levelCompleted.getWidth(), levelCompleted.getHeight());
            spriteBatch.draw(levelCompleted, Gdx.graphics.getWidth() / ByeBee.WIDTH, Gdx.graphics.getHeight() / ByeBee.HEIGHT, levelCompleted.getWidth(), levelCompleted.getHeight());
            spriteBatch.draw(btnContinue.getTexture(), btnContinue.getPosX(), btnContinue.getPosY(), btnContinue.getWidth(), btnContinue.getHeight());

            if (bee.isInvencible()) {
                puntuacion.setScore2(0);
            }

            texto.draw(spriteBatch, "Puntuación: " + puntuacion.getScore2(), btnContinue.getPosX(), btnContinue.getPosY() + btnContinue.getHeight() + btnContinue.getHeight() / 2);

            puntuacion.setHighScore2(puntuacion.getScore2());

            System.out.println("Puntuacion: " + puntuacion.getScore2());
            System.out.println("Puntuacion Maxima: " + puntuacion.getHighScore2());
        }
    }

    private void scoringPoints() {
        if (!optionsMenu) {
            puntuacion.setScore2(puntuacion.getScore2() + 1);
            texto.draw(spriteBatch, " Puntos: " + puntuacion.getScore2(), puntuacion.getPosX(), puntuacion.getPosY());
        }
    }

    public void detectTouch() {
        Vector2 touched; // Guarda las coordenadas para saber en qué parte de la pantalla se toca

        if (Gdx.input.justTouched()) {
            touched = viewport.unproject(new Vector2(Gdx.input.getX(), Gdx.input.getY()));
            System.out.println("TOUCHED");

            if (btnRetry.getBoton().contains(touched) && optionsMenu) { // VOLVER A INTENTAR EL NIVEL
                System.out.println("RETRY LEVEL 1");
                byebee.setLevel2();
            }

            if (btnMenu.getBoton().contains(touched) && optionsMenu) { // VOLVER AL MENÚ DE SELECCIÓN DE NIVEL
                System.out.println("BACK TO MENU");
                bgmLevel2.stop();
                byebee.setTitleScreen();
            }

            if (btnContinue.getBoton().contains(touched) && optionsMenu) { // COMPLETAR NIVEL Y VOLVER A MENÚ DE SELECCIÓN DE NIVEL
                System.out.println("LEVEL COMPLETED");
                bgmLevel2.stop();
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

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        texto.dispose();
        spriteBatch.dispose();
        bgmLevel2.dispose();
    }
}
