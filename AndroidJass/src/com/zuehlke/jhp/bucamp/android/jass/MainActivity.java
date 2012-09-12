package com.zuehlke.jhp.bucamp.android.jass;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import ch.mbaumeler.jass.core.JassEngine;
import ch.mbaumeler.jass.core.game.PlayerToken;
import ch.mbaumeler.jass.extended.ui.ObservableGame;

import com.zuehlke.jhp.bucamp.android.jass.controller.GameController;
import com.zuehlke.jhp.bucamp.android.jass.settings.model.JassSettings;
import com.zuehlke.jhp.bucamp.android.jass.settings.model.Player;
import com.zuehlke.jhp.bucamp.android.jass.settings.model.SettingsCreator;

public class MainActivity extends Activity {

	private ObservableGame game;
	private GameController gameController;

	private Map<PlayerToken, Player> names = new HashMap<PlayerToken, Player>();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
		JassSettings settings = SettingsCreator.createFromPreferences(sharedPrefs);
		
		game = new ObservableGame(new JassEngine().createJassGame());
		gameController = new GameController(game);
		names = new HashMap<PlayerToken, Player>();

		List<PlayerToken> all = game.getPlayerRepository().getAll();
		names.put(all.get(0), settings.getTeam1().getPlayer1());
		names.put(all.get(1), settings.getTeam2().getPlayer1());
		names.put(all.get(2), settings.getTeam1().getPlayer2());
		names.put(all.get(3), settings.getTeam2().getPlayer2());

		game.addObserver(new AnsageObserver(game, gameController
				.getHumanPlayerToken(), this));
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		game.notifyObservers();
	}

	public String getName(PlayerToken token) {
		return names.get(token).getName();
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
