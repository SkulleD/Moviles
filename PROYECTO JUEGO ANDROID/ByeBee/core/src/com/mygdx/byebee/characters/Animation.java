package com.mygdx.byebee.characters;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

public class Animation {
    private Array<TextureRegion> frames;
    private float maxFrameTime;
    private float currentFrameTime;
    private int frameCount;
    private int frameActual;
    private int frameWidth;

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

    public TextureRegion getFrame() {
        return frames.get(frameActual);
    }
}