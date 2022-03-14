package com.mygdx.byebee.characters;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

/**
 * Esta clase se usa para que los sprites de los personajes estén animados.
 */
public class Animation {

    /**
     * Aquí se guarda cada frame de los sprites.
     */
    private Array<TextureRegion> frames;

    /**
     * El tiempo que un frame se mantiene a la vista hasta pasar al siguiente frame.
     */
    private float maxFrameTime;

    /**
     * El tiempo que la animación ha estado en el frame actual.
     */
    private float currentFrameTime;

    /**
     * El número de frames en la animación.
     */
    private int frameCount;

    /**
     * El frame en el que estamos actualmente.
     */
    private int frameActual;

    /**
     * El ancho de cada frame.
     */
    private int frameWidth;

    /**
     * Constructor que inicializa los parámetros iniciales de la clase.
     * @param region la sprite sheet que contiene cada frame de la animación del sprite.
     * @param frameCount el número de frames de la animación.
     * @param ciclo cuánto se va a tardar en completar un ciclo de la animación.
     */
    public Animation (TextureRegion region, int frameCount, float ciclo) {
        frames = new Array<>();
        frameWidth = region.getRegionWidth() / frameCount;

        for (int i = 0; i < frameCount; i++) {
            frames.add(new TextureRegion(region, i * frameWidth, 0, frameWidth, region.getRegionHeight()));
        }

        this.frameCount = frameCount;
        maxFrameTime = ciclo / frameCount;
        frameActual = 0;
    }

    /**
     * Método que actualiza los valores de los enemigos cada vez que la pantalla es renderizada.
     * @param deltaTime El tiempo en segundos desde el último renderizado.
     */
    public void update(float deltaTime) {
        currentFrameTime += deltaTime;

        if (currentFrameTime > maxFrameTime) {
            frameActual++;
            currentFrameTime = 0;
        }

        if (frameActual >= frameCount) {
            frameActual= 0;
        }
    }

    /**
     * Método que devuelve cada frame del sprite animado de la abeja.
     * @return cada frame del sprite animado de la abeja.
     */
    public TextureRegion getFrame() {
        return frames.get(frameActual);
    }
}