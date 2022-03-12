package com.mygdx.byebee.characters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

/**
 * Clase que representa a las distintas puntuaciones del juego.
 */
public class Score {

    /**
     * Sirve para escribir texto en pantalla
     */
    BitmapFont scoreFont;

    // Variables que guardan la puntuación de cada nivel
    /**
     * La puntuación conseguida en el nivel 1.
     */
    int score;

    /**
     * La puntuación máxima conseguida en el nivel 1.
     */
    int highScore;

    /**
     * La puntuación conseguida en el nivel 2.
     */
    int score2;

    /**
     * La puntuación máxima conseguida en el nivel 2.
     */
    int highScore2;

    /**
     * La puntuación conseguida en el nivel 3.
     */
    int score3;

    /**
     * La puntuación máxima conseguida en el nivel 3.
     */
    int highScore3;

    /**
     * La puntuación conseguida en el nivel 4.
     */
    int score4;

    /**
     * La puntuación máxima conseguida en el nivel 4.
     */
    int highScore4;

    /**
     * La posición en el eje X de la puntuación.
     */
    float posX;

    /**
     * La posición en el eje Y de la puntuación.
     */
    float posY;

    /**
     * Se usa para poder utilizar persistencia de datos.
     */
    Preferences preferences;

    /**
     * Constructor que inicializa los parámetros iniciales de las puntuaciones.
     * @param scoreFont El texto que contiene el valor de la puntuación.
     * @param posX La posición en el eje X de la puntuación.
     * @param posY La posición en el eje Y de la puntuación.
     */
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

    /**
     * Método para conseguir la posición X de la puntuación.
     * @return la posición X del personaje.
     */
    public float getPosX() {
        return posX;
    }

    /**
     * Método para establecer la posición X de la puntuación.
     * @param posX la posición X del personaje.
     */
    public void setPosX(float posX) {
        this.posX = posX;
    }

    /**
     * Método para conseguir la posición Y de la puntuación.
     * @return la posición Y del personaje.
     */
    public float getPosY() {
        return posY;
    }

    /**
     * Método para establecer la posición Y de la puntuación.
     * @param posY la posición Y del personaje.
     */
    public void setPosY(float posY) {
        this.posY = posY;
    }

    /**
     * Método que obtiene la puntuación conseguida en el nivel 1.
     * @return la puntuación conseguida en el nivel 1.
     */
    public int getScore() {
        return score;
    }

    /**
     * Método que establece la puntuación conseguida en el nivel 1.
     * @param score la puntuación conseguida en el nivel 1.
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * Método que obtiene la puntuación máxima conseguida en el nivel 1.
     * @return la puntuación máxima conseguida en el nivel 1.
     */
    public int getHighScore() {
        return highScore;
    }

    /**
     * Método que establece la puntuación máxima conseguida en el nivel 1.
     * @param score la puntuación máxima conseguida en el nivel 1.
     */
    public void setHighScore(int score) { // Si el nuevo score es mayor que el highscore, este se actualiza y se pone el mayor
        if (score > highScore) {
            this.highScore = score;
            preferences.putInteger("highScore", this.highScore);
            preferences.flush();
        }
    }

    /**
     * Método que obtiene la puntuación conseguida en el nivel 2.
     * @return la puntuación conseguida en el nivel 2.
     */
    public int getScore2() {
        return score2;
    }

    /**
     * Método que establece la puntuación conseguida en el nivel 2.
     * @param score2 la puntuación conseguida en el nivel 2.
     */
    public void setScore2(int score2) {
        this.score2 = score2;
    }

    /**
     * Método que obtiene la puntuación máxima conseguida en el nivel 2.
     * @return la puntuación máxima conseguida en el nivel 2.
     */
    public int getHighScore2() {
        return highScore2;
    }

    /**
     * Método que establece la puntuación máxima conseguida en el nivel 2.
     * @param score2 la puntuación máxima conseguida en el nivel 2.
     */
    public void setHighScore2(int score2) {
        if (score2 > highScore2) {
            this.highScore2 = score2;
            preferences.putInteger("highScore2", this.highScore2);
            preferences.flush();
        }
    }

    /**
     * Método que obtiene la puntuación conseguida en el nivel 3.
     * @return la puntuación conseguida en el nivel 3.
     */
    public int getScore3() {
        return score3;
    }

    /**
     * Método que establece la puntuación conseguida en el nivel 3.
     * @param score3 la puntuación conseguida en el nivel 3.
     */
    public void setScore3(int score3) {
        this.score3 = score3;
    }

    /**
     * Método que obtiene la puntuación máxima conseguida en el nivel 3.
     * @return la puntuación máxima conseguida en el nivel 3.
     */
    public int getHighScore3() {
        return highScore3;
    }

    /**
     * Método que establece la puntuación máxima conseguida en el nivel 3.
     * @param score3 la puntuación máxima conseguida en el nivel 3.
     */
    public void setHighScore3(int score3) {
        if (score3 > highScore3) {
            this.highScore3 = score3;
            preferences.putInteger("highScore3", this.highScore3);
            preferences.flush();
        }
    }

    /**
     * Método que obtiene la puntuación conseguida en el nivel 4.
     * @return la puntuación conseguida en el nivel 4.
     */
    public int getScore4() {
        return score4;
    }

    /**
     * Método que establece la puntuación conseguida en el nivel 4.
     * @param score4 la puntuación conseguida en el nivel 4.
     */
    public void setScore4(int score4) {
        this.score4 = score4;
    }

    /**
     * Método que obtiene la puntuación máxima conseguida en el nivel 4.
     * @return la puntuación máxima conseguida en el nivel 4.
     */
    public int getHighScore4() {
        return highScore4;
    }

    /**
     * Método que establece la puntuación máxima conseguida en el nivel 4.
     * @param score4 la puntuación máxima conseguida en el nivel 4.
     */
    public void setHighScore4(int score4) {
        if (score4 > highScore4) {
            this.highScore4 = score4;
            preferences.putInteger("highScore4", this.highScore4);
            preferences.flush();
        }
    }
}