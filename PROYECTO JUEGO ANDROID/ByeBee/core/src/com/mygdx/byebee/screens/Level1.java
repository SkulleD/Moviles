package com.mygdx.byebee.screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.byebee.characters.Bee;
import com.mygdx.byebee.characters.Enemy;
import com.mygdx.byebee.characters.Health;

import java.io.Console;
import java.util.LinkedList;
import java.util.ListIterator;

public class Level1 implements Screen {
    private Camera camera;
    private Viewport viewport;
    private SpriteBatch spriteBatch;

    private Texture[] backgrounds;

    // Timing cosas
    private float[] bgOffsets = {0, 0, 0, 0}; // Para el efecto parallax
    private float bgMaxScrollSpeed; // Para el movimiento del escenario
    private float timeBetweenSpawnsBird = 7f;
    private float timeBetweenSpawnsBeeLancer = 3f;
    private float birdSpawnTimer = 0;
    private float beeLancerSpawnTimer = 0;

    // Salud de la abeja
    private Health health;

    // Personaje controlable
    private Bee bee;

    // Enemigos
    private LinkedList<Enemy> enemyList;
    private Enemy bird;
    private Enemy beeLancer;

    public Level1() {
        camera = new OrthographicCamera();
        viewport = new StretchViewport(ByeBee.WIDTH, ByeBee.HEIGHT, camera);

        backgrounds = new Texture[4];
        backgrounds[0] = new Texture("lvl1_background3.png");
        backgrounds[1] = new Texture("lvl1_background2.png");
        backgrounds[2] = new Texture("lvl1_background1.png");
        backgrounds[3] = new Texture("lvl1_foreground.png");
        bgMaxScrollSpeed = (float) (ByeBee.WIDTH / 4);

        enemyList = new LinkedList<>();

        health = new Health(10, ByeBee.HEIGHT - 100, 90, 90, new Texture[8]);
        bee = new Bee(ByeBee.WIDTH / 6, ByeBee.HEIGHT / 3,
                150, 150, new Texture("bee.png"), 7);
        bird = new Enemy(ByeBee.WIDTH, (float) (Math.random() * ByeBee.HEIGHT + 1),
                170, 170, new Texture("bird.png"), 10);
        beeLancer = new Enemy(ByeBee.WIDTH, (float) (Math.random() * ByeBee.HEIGHT + 1),
                170, 170, new Texture("bee_lancer.png"), 4);

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
            System.out.println("333333333");
            enemyList.get(i).move();
            enemyList.get(i).update(deltaTime);

            spriteBatch.draw(enemyList.get(i).getTexture(), enemyList.get(i).getPosX(), enemyList.get(i).getPosY(), enemyList.get(i).getWidth(), enemyList.get(i).getHeight());
            // Detecta colisiones entre la abeja y los enemigos
            detectCollisions();
        }

        drawHealth(); // Muestra el indicador de vida

        spriteBatch.end();
    }

    private void renderBackground(float deltaTime) { // Método para mostrar el fondo con efecto parallax
        bgOffsets[0] += deltaTime * bgMaxScrollSpeed / 8;
        bgOffsets[1] += deltaTime * bgMaxScrollSpeed / 4;
        bgOffsets[2] += deltaTime * bgMaxScrollSpeed / 2;
        bgOffsets[3] += deltaTime * bgMaxScrollSpeed;

        for (int layer = 0; layer < bgOffsets.length; layer++) { // Va recorriendo las capas del fondo y mostrándolas
            if (bgOffsets[layer] > ByeBee.WIDTH) {
                bgOffsets[layer] = 0;
            }

            spriteBatch.draw(backgrounds[layer], -bgOffsets[layer], 0, ByeBee.WIDTH, ByeBee.HEIGHT);
            spriteBatch.draw(backgrounds[layer], -bgOffsets[layer] + ByeBee.WIDTH, 0, ByeBee.WIDTH, ByeBee.HEIGHT);
        }
    }

    private void detectCollisions() { // Detecta colisiones de hitboxes
        ListIterator<Enemy> enemyListIterator = enemyList.listIterator();

        while (enemyListIterator.hasNext()) {
            Enemy enemy = enemyListIterator.next();

            if (bee.intersects(enemy.getHitbox())) {
                if (!enemy.isHasHit()) {
                    enemy.setHasHit(true); // Cuando un enemigo golpea a la abeja, ya no puede volver a golpearla
                    bee.setHealth(bee.getHealth() - 1);
                }
                //enemyListIterator.remove(); // (DE PRUEBA) borra el enemigo chocado
                break;
            }
        }
    }

    private void spawnEnemies(float deltaTime) { // Hace que los enemigos vayan apareciendo de forma aleatoria
        birdSpawnTimer += deltaTime;
        beeLancerSpawnTimer += deltaTime;

        if (birdSpawnTimer > timeBetweenSpawnsBird) {
            enemyList.add(new Enemy(ByeBee.WIDTH, (float) (Math.random() * ByeBee.HEIGHT + 1), // PÁJARO
                    250, 200, new Texture("bird.png"), 10));

            birdSpawnTimer -= timeBetweenSpawnsBird;
        }

        if (beeLancerSpawnTimer > timeBetweenSpawnsBeeLancer) {
            enemyList.add(new Enemy(ByeBee.WIDTH, (float) (Math.random() * ByeBee.HEIGHT + 1), // ABEJA LANCERA
                    250, 200, new Texture("bee_lancer.png"), 5));

            beeLancerSpawnTimer -= timeBetweenSpawnsBeeLancer;
        }
    }

    private void drawHealth() {
        if (bee.getHealth() >= 0) {
            spriteBatch.draw(health.getTexture()[bee.getHealth()], health.getPosX(), health.getPosY(), health.getWidth(), health.getHeight());
        } else {
            spriteBatch.draw(health.getTexture()[0], health.getPosX(), health.getPosY(), health.getWidth(), health.getHeight());
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