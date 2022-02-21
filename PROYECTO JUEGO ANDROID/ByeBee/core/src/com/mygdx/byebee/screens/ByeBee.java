package com.mygdx.byebee.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.mygdx.byebee.screens.TitleScreen;

import java.util.Random;

public class ByeBee extends Game {
	// Valores para usar durante el juego
	public static final int WIDTH = 1280;
	public static final int HEIGHT = 720;
	public static final String TITLE = "ByeBee";
	public static Random random = new Random();

	// Instaciación de las diferentes pantallas del juego
	private TitleScreen titleScreen;
	private CreditsScreen creditsScreen;
	private InfoScreen infoScreen;
	private SettingsScreen settingsScreen;
	private ScoreScreen scoreScreen;
	private LevelSelect levelSelect;
	private Level1 level1;
	private Level2 level2;

	// Métodos para llamar a las diferentes pantallas

	public void setTitleScreen() {
		titleScreen = new TitleScreen(this);
		setScreen(titleScreen);
	}

	public void setCreditsScreen() { // Pantalla de cŕeditos
		creditsScreen = new CreditsScreen(this);
		setScreen(creditsScreen);
	}

	public void setInfoScreen() { // Pantalla de información del juego
		infoScreen = new InfoScreen(this);
		setScreen(infoScreen);
	}

	public void setScoreScreen() { // Pantalla de puntuaciones máximas
		scoreScreen = new ScoreScreen(this);
		setScreen(scoreScreen);
	}

	public void setSettingsScreen() { // Pantalla de ajustes
		settingsScreen = new SettingsScreen(this);
		setScreen(settingsScreen);
	}

	public void setLevelSelect() { // Pantalla de selección de nivel
		levelSelect = new LevelSelect(this);
		setScreen(levelSelect);
	}

	public void setLevel1() { // Pantalla de nivel 1
		level1 = new Level1();
		setScreen(level1);
	}

	public void setLevel2() { // Pantalla de nivel 2
		level2 = new Level2();
		setScreen(level2);
	}

	@Override
	public void create () {
		setTitleScreen();
	}

	@Override
	public void render () {
		super.render();
	}

	@Override
	public void resize(int width, int height) {
		titleScreen.resize(width, height);
	}

	@Override
	public void dispose () {
		titleScreen.dispose();
	}


}