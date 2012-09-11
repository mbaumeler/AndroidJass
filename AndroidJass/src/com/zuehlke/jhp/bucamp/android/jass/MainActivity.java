package com.zuehlke.jhp.bucamp.android.jass;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import ch.mbaumeler.jass.core.JassEngine;
import ch.mbaumeler.jass.core.Match;
import ch.mbaumeler.jass.core.game.PlayerToken;
import ch.mbaumeler.jass.extended.ai.simple.SimpleStrategy;
import ch.mbaumeler.jass.extended.ai.simple.SimpleStrategyEngine;
import ch.mbaumeler.jass.extended.ui.ObservableGame;

public class MainActivity extends Activity {

	private ObservableGame game;
	private SimpleStrategy strategy;

	private Map<PlayerToken, String> names = new HashMap<PlayerToken, String>();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		game = new ObservableGame(new JassEngine().createJassGame());

		names = new HashMap<PlayerToken, String>();

		List<PlayerToken> all = game.getPlayerRepository().getAll();
		names.put(all.get(0), "DU");
		names.put(all.get(1), "Kirk");
		names.put(all.get(2), "Spock");
		names.put(all.get(3), "Doctor Leonard McCoy");
		strategy = new SimpleStrategyEngine().create();
		
		game.addObserver(new AnsageObserver(game, getHumanPlayerToken(), this));
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

	public PlayerToken getHumanPlayerToken() {
		return game.getPlayerRepository().getAll().get(0);
	}

	public void refreshClicked(View view) {
		Match match = game.getCurrentMatch();
		if (match.getActivePlayer() != getHumanPlayerToken()) {
			if (match.getAnsage() == null) {
				match.setAnsage(strategy.getAnsage(match));
			}
			game.getCurrentMatch().playCard(strategy.getCardToPlay(match));
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
}
