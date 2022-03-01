package com.mygdx.byebee.characters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class Score {
    BitmapFont scoreFont;

    // Variables que guardan la puntuación de cada nivel
    int score;
    int highScore;
    int score2;
    int highScore2;
    int score3;
    int highScore3;
    int score4;
    int highScore4;
    float posX;
    float posY;
    Preferences preferences;

    public Score(BitmapFont scoreFont, float posX, float posY) {
        this.scoreFont = scoreFont;
        this.posX = posX;
        this.posY = posY;

        preferences = Gdx.app.getPreferences("byebee");
        this.highScore = preferences.getInteger("highScore", 0);
        this.highScore2 = preferences.getInteger("highScore2", 0);
        this.highScore3 = preferences.getInteger("highScore3", 0);
        this.highScore4 = preferences.getInteger("highScore4", 0);
    }

    public float getPosX() {
        return posX;
    }

    public void setPosX(float posX) {
        this.posX = posX;
    }

    public float getPosY() {
        return posY;
    }

    public void setPosY(float posY) {
        this.posY = posY;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getHighScore() {
        return highScore;
    }

    public void setHighScore(int score) { // Si el nuevo score es mayor que el highscore, este se actualiza y se pone el mayor
        if (score > highScore) {
            this.highScore = score;
            preferences.putInteger("highScore", this.highScore);
            preferences.flush();
        }
    }

    public int getScore2() {
        return score2;
    }

    public void setScore2(int score2) {
        this.score2 = score2;
    }

    public int getHighScore2() {
        return highScore2;
    }

    public void setHighScore2(int score2) {
        if (score2 > highScore2) {
            this.highScore2 = score2;
            preferences.putInteger("highScore2", this.highScore2);
            preferences.flush();
        }
    }

    public int getScore3() {
        return score3;
    }

    public void setScore3(int score3) {
        this.score3 = score3;
    }

    public int getHighScore3() {
        return highScore3;
    }

    public void setHighScore3(int score3) {
        if (score3 > highScore3) {
            this.highScore3 = score3;
            preferences.putInteger("highScore3", this.highScore3);
            preferences.flush();
        }
    }

    public int getScore4() {
        return score4;
    }

    public void setScore4(int score4) {
        this.score4 = score4;
    }

    public int getHighScore4() {
        return highScore4;
    }

    public void setHighScore4(int score4) {
        if (score4 > highScore4) {
            this.highScore4 = score4;
            preferences.putInteger("highScore4", this.highScore4);
            preferences.flush();
        }
    }
}
