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
	private Level1 level1;

	// Métodos para llamar a las diferentes pantallas

	public void setTitleScreen() {
		titleScreen = new TitleScreen(this);
		setScreen(titleScreen);
	}

	public void setLevel1() {
		level1 = new Level1();
		setScreen(level1);
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