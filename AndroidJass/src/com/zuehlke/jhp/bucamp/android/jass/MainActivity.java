package com.zuehlke.jhp.bucamp.android.jass;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import ch.mbaumeler.jass.core.Game;
import ch.mbaumeler.jass.core.JassEngine;
import ch.mbaumeler.jass.core.game.PlayerToken;
import ch.mbaumeler.jass.extended.ui.ObservableGame;

import com.zuehlke.jhp.bucamp.android.jass.controller.GameController;
import com.zuehlke.jhp.bucamp.android.jass.settings.model.JassSettings;
import com.zuehlke.jhp.bucamp.android.jass.settings.model.Player;
import com.zuehlke.jhp.bucamp.android.jass.settings.model.SettingsCreator;

public class MainActivity extends Activity {

	private static Game game;
	private ObservableGame observableGame;
	private GameController gameController;

	private Map<PlayerToken, Player> names = new HashMap<PlayerToken, Player>();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
		JassSettings settings = SettingsCreator.createFromPreferences(sharedPrefs);
		
<<<<<<< HEAD
		if (savedInstanceState == null || game == null) {
			game = new JassEngine().createJassGame();
			observableGame = new ObservableGame(game);
		} else {
			observableGame = new ObservableGame(game);
		}
		gameController = new GameController(observableGame);
		observableGame.addObserver(gameController);
=======
		game = new ObservableGame(new JassEngine().createJassGame());
		gameController = new GameController(game);
>>>>>>> branch 'master' of https://github.com/fabiropr/AndroidJass.git
		names = new HashMap<PlayerToken, Player>();

<<<<<<< HEAD
		List<PlayerToken> all = observableGame.getPlayerRepository().getAll();
=======
		List<PlayerToken> all = game.getPlayerRepository().getAll();
>>>>>>> branch 'master' of https://github.com/fabiropr/AndroidJass.git
		names.put(all.get(0), settings.getTeam1().getPlayer1());
		names.put(all.get(1), settings.getTeam2().getPlayer1());
		names.put(all.get(2), settings.getTeam1().getPlayer2());
		names.put(all.get(3), settings.getTeam2().getPlayer2());

		observableGame.addObserver(new AnsageObserver(gameController
				.getHumanPlayerToken(), this));
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		observableGame.notifyObservers();
	}

	public String getName(PlayerToken token) {
		return names.get(token).getName();
	}

	public ObservableGame getGame() {
		return observableGame;
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
