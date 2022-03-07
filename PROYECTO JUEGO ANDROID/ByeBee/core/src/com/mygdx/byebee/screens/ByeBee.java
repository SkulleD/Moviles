package com.mygdx.byebee.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.mygdx.byebee.screens.TitleScreen;

import java.util.Random;

public class ByeBee extends Game implements Screen {
	// Valores para usar durante el juego
	public static final int WIDTH = 1280;
	public static final int HEIGHT = 720;
	public static final String TITLE = "ByeBee";
	public static Random random = new Random();
	public Music bgmMenus;

	// Instaciación de las diferentes pantallas del juego
	private TitleScreen titleScreen;
	private CreditsScreen creditsScreen;
	private InfoScreen infoScreen;
	private SettingsScreen settingsScreen;
	private ScoreScreen scoreScreen;
	private LevelSelect levelSelect;
	private Level1 level1;
	private Level2 level2;
	private Level3 level3;
	private Level4 level4;

	// Comprobaciones de ajustes por si algo había sido seleccionado la anterior vez que se jugó al juego
	private boolean checkFullScreen;
	private boolean checkMusicSound;
	Preferences preferences;
	Graphics.DisplayMode displayMode;

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
		level1 = new Level1(this);
		setScreen(level1);
	}

	public void setLevel2() { // Pantalla de nivel 2
		level2 = new Level2(this);
		setScreen(level2);
	}

	public void setLevel3() { // Pantalla de nivel 3
		level3 = new Level3(this);
		setScreen(level3);
	}

	public void setLevel4() { // Pantalla de nivel 4
		level4 = new Level4(this);
		setScreen(level4);
	}

	@Override
	public void create () {
		preferences = Gdx.app.getPreferences("byebee");
		displayMode = Gdx.graphics.getDisplayMode();

		checkFullScreen = preferences.getBoolean("fullscreen", false);
		checkMusicSound = preferences.getBoolean("musicSound", true);

		// Comprueba si al iniciar el juego la opción de pantalla completa estaba activada o desactivada y actúa de forma correspondiente
		switch(Gdx.app.getType()) {
			case Desktop:
				if (checkFullScreen) {
					Gdx.graphics.setUndecorated(true);
					Gdx.graphics.setWindowedMode(displayMode.width, displayMode.height);
				} else {
					Gdx.graphics.setUndecorated(false);
					Gdx.graphics.setWindowedMode(ByeBee.WIDTH, ByeBee.HEIGHT);
				}
				break;
		}

		//if (checkMusicSound) {
			// La música de menús se coloca en esta clase para evitar que al navegar entre menús se creen nuevas instancias de la misma canción y suenen todas de golpe
			bgmMenus = Gdx.audio.newMusic(Gdx.files.internal("bgm_menus.mp3"));
			bgmMenus.setLooping(true);
			bgmMenus.setVolume(1);
			bgmMenus.play();
		//}

		setTitleScreen();
	}

	@Override
	public void render () {
		super.render();
	}

	@Override
	public void show() {

	}

	@Override
	public void render(float v) {

	}

	@Override
	public void resize(int width, int height) {
		titleScreen.resize(width, height);
	}

	@Override
	public void hide() {

	}

	@Override
	public void dispose () {
		bgmMenus.dispose();
	}
}