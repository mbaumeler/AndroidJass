package com.zuehlke.jhp.bucamp.android.jass;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import ch.mbaumeler.jass.core.JassEngine;
import ch.mbaumeler.jass.core.game.PlayerToken;
import ch.mbaumeler.jass.extended.ui.ObservableGame;

import com.zuehlke.jhp.bucamp.android.jass.controller.GameController;

public class MainActivity extends Activity {

	private ObservableGame game;
	private GameController gameController;

	private Map<PlayerToken, String> names = new HashMap<PlayerToken, String>();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		game = new ObservableGame(new JassEngine().createJassGame());
		gameController = new GameController(game);
		names = new HashMap<PlayerToken, String>();

		List<PlayerToken> all = game.getPlayerRepository().getAll();
		names.put(all.get(0), "DU");
		names.put(all.get(1), "Kirk");
		names.put(all.get(2), "Spock");
		names.put(all.get(3), "Doctor Leonard McCoy");

		game.addObserver(new AnsageObserver(game, gameController
				.getHumanPlayerToken(), this));
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		game.notifyObservers();
	}

	public String getName(PlayerToken token) {
		return names.get(token);
	}

	public ObservableGame getGame() {
		return game;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	public GameController getGameController() {
		return gameController;
	}
}
