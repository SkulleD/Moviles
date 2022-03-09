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

/**
 * Clase base que inicia el juego.
 * Contiene los métodos necesarios para navegar por las distintas pantallas del juego.
 */
public class ByeBee extends Game implements Screen {
	// Valores para usar durante el juego
	/**
	 * El ancho de la ventana del juego al abrirse en escritorio.
	 * Representa el valor del ancho que usan como referencia todos los recursos del juego.
	 */
	public static final int WIDTH = 1280;

	/**
	 * El alto de la ventana del juego al abrirse en escritorio.
	 * Representa el valor del alto que usan como referencia todos los recursos del juego.
	 */
	public static final int HEIGHT = 720;

	/**
	 * El nombre que se le asigna a la aplicación.
	 */
	public static final String TITLE = "ByeBee";

	/**
	 * Valor aleatorio que se usa en diferentes clases.
	 */
	public static Random random = new Random();

	/**
	 * Música que se suena mientras se navega por los menús del juego.
	 */
	public Music bgmMenus;

	// Instaciación de las diferentes pantallas del juego
	/**
	 * Objeto para acceder a la pantalla del título.
	 */
	private TitleScreen titleScreen;

	/**
	 * Objeto para acceder a la pantalla de créditos.
	 */
	private CreditsScreen creditsScreen;

	/**
	 * Objeto para acceder a la pantalla de información.
	 */
	private InfoScreen infoScreen;

	/**
	 * Objeto para acceder a la pantalla de ajustes.
	 */
	private SettingsScreen settingsScreen;

	/**
	 * Objeto para acceder a la pantalla de puntuaciones.
	 */
	private ScoreScreen scoreScreen;

	/**
	 * Objeto para acceder a la pantalla de selección de nivel.
	 */
	private LevelSelect levelSelect;

	/**
	 * Objeto para acceder a la pantalla del nivel 1: Campo de Flores.
	 */
	private Level1 level1;

	/**
	 * Objeto para acceder a la pantalla del nivel 2: Manantial
	 */
	private Level2 level2;

	/**
	 * Objeto para acceder a la pantalla del nivel 3: Colmena.
	 */
	private Level3 level3;

	/**
	 * Objeto para acceder a la pantalla del nivel 4: Sin Fin.
	 */
	private Level4 level4;

	// Comprobaciones de ajustes por si algo había sido seleccionado la anterior vez que se jugó al juego
	/**
	 * Comprueba si la opción de pantalla completa había sido seleccionada la última vez que se jugó al juego (solo en escritorio).
	 */
	private boolean checkFullScreen;

	/**
	 * Comprueba si la opción de activar/desactivar música había sido seleccionada la última vez que se jugó al juego.
	 */
	private boolean checkMusicSound;

	/**
	 * Se usa para poder utilizar persistencia de datos.
	 */
	Preferences preferences;

	/**
	 * Se usa para poder poner y quitar la pantalla completa del juego (solo en escritorio).
	 */
	Graphics.DisplayMode displayMode;

	// Métodos para llamar a las diferentes pantallas

	/**
	 * Método al que se llama para poder acceder a la pantalla del título.
	 */
	public void setTitleScreen() {
		titleScreen = new TitleScreen(this);
		setScreen(titleScreen);
	}

	/**
	 * Método al que se llama para poder acceder a la pantalla de créditos.
	 */
	public void setCreditsScreen() { // Pantalla de cŕeditos
		creditsScreen = new CreditsScreen(this);
		setScreen(creditsScreen);
	}

	/**
	 * Método al que se llama para poder acceder a la pantalla de información.
	 */
	public void setInfoScreen() { // Pantalla de información del juego
		infoScreen = new InfoScreen(this);
		setScreen(infoScreen);
	}

	/**
	 * Método al que se llama para poder acceder a la pantalla de puntuaciones.
	 */
	public void setScoreScreen() { // Pantalla de puntuaciones máximas
		scoreScreen = new ScoreScreen(this);
		setScreen(scoreScreen);
	}

	/**
	 * Método al que se llama para poder acceder a la pantalla de ajustes.
	 */
	public void setSettingsScreen() { // Pantalla de ajustes
		settingsScreen = new SettingsScreen(this);
		setScreen(settingsScreen);
	}

	/**
	 * Método al que se llama para poder acceder a la pantalla de selección de nivel.
	 */
	public void setLevelSelect() { // Pantalla de selección de nivel
		levelSelect = new LevelSelect(this);
		setScreen(levelSelect);
	}

	/**
	 * Método al que se llama para poder acceder a la pantalla del nivel 1: Campo de Flores.
	 */
	public void setLevel1() { // Pantalla de nivel 1
		level1 = new Level1(this);
		setScreen(level1);
	}

	/**
	 * Método al que se llama para poder acceder a la pantalla del nivel 2: Manantial.
	 */
	public void setLevel2() { // Pantalla de nivel 2
		level2 = new Level2(this);
		setScreen(level2);
	}

	/**
	 * Método al que se llama para poder acceder a la pantalla del nivel 3: Colmena.
	 */
	public void setLevel3() { // Pantalla de nivel 3
		level3 = new Level3(this);
		setScreen(level3);
	}

	/**
	 * Método al que se llama para poder acceder a la pantalla del nivel 4: Sin Fin.
	 */
	public void setLevel4() { // Pantalla de nivel 4
		level4 = new Level4(this);
		setScreen(level4);
	}

	/**
	 * Método que inicializa la música y modo de la pantalla y llama a la pantalla del título.
	 */
	@Override
	public void create () {
		preferences = Gdx.app.getPreferences("byebee");
		displayMode = Gdx.graphics.getDisplayMode();

		checkFullScreen = preferences.getBoolean("fullscreen", false);
		checkMusicSound = preferences.getBoolean("musicSound", true);

		// Comprueba si al iniciar el juego la opción de pantalla completa estaba activada o desactivada y actúa de forma correspondiente
		switch(Gdx.app.getType()) {
			case Desktop: // Se asegura de solo hacerlo si el juego está en escritorio y no en Android
				if (checkFullScreen) {
					Gdx.graphics.setUndecorated(true);
					Gdx.graphics.setWindowedMode(displayMode.width, displayMode.height);
				} else {
					Gdx.graphics.setUndecorated(false);
					Gdx.graphics.setWindowedMode(ByeBee.WIDTH, ByeBee.HEIGHT);
				}
				break;
		}

		bgmMenus = Gdx.audio.newMusic(Gdx.files.internal("bgm_menus.mp3"));
		bgmMenus.setLooping(true);
		bgmMenus.setVolume(1);

		// La música de menús se coloca en esta clase para evitar que al navegar entre menús se creen nuevas instancias de la misma canción y suenen todas de golpe
		if (checkMusicSound) {
			bgmMenus.play();
		}

		setTitleScreen();
	}

	/**
	 * Método al que se llama cuando esta pantalla se tiene que renderizar.
	 */
	@Override
	public void render () {
		super.render();
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

	}

	/**
	 * Método al que se llama cuando se redimensiona la pantalla.
	 * @param width Ancho de la pantalla.
	 * @param height Alto de la pantalla.
	 */
	@Override
	public void resize(int width, int height) {
		titleScreen.resize(width, height);
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
	public void dispose () {
		bgmMenus.dispose();
	}
}