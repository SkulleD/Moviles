package com.mygdx.byebee.screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.byebee.characters.Bird;

public class Level1 implements Screen {
    private Camera camera;
    private Viewport viewport;
    private SpriteBatch spriteBatch;

    private Texture[] backgrounds;

    private float[] bgOffsets = {0, 0, 0, 0};
    private float bgMaxScrollSpeed; // Para el movimiento del escenario

    public Level1() {
        camera = new OrthographicCamera();
        viewport = new StretchViewport(ByeBee.WIDTH, ByeBee.HEIGHT, camera);

        backgrounds = new Texture[4];
        backgrounds[0] = new Texture("lvl1_background3.png");
        backgrounds[1] = new Texture("lvl1_background2.png");
        backgrounds[2] = new Texture("lvl1_background1.png");
        backgrounds[3] = new Texture("lvl1_foreground.png");
        bgMaxScrollSpeed = (float) (ByeBee.WIDTH / 4);

        spriteBatch = new SpriteBatch();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float deltaTime) {
        spriteBatch.begin();

        renderBackground(deltaTime);

        spriteBatch.end();
    }

    private void renderBackground(float deltaTime) {
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
