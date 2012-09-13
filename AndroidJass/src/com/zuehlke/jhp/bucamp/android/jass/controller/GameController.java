package com.zuehlke.jhp.bucamp.android.jass.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import android.os.Handler;
import ch.mbaumeler.jass.core.card.Card;
import ch.mbaumeler.jass.core.game.PlayerToken;
import ch.mbaumeler.jass.extended.ai.PlayStrategy;
import ch.mbaumeler.jass.extended.ai.simple.SimpleStrategyEngine;
import ch.mbaumeler.jass.extended.ui.JassModelObserver;
import ch.mbaumeler.jass.extended.ui.ObservableGame;
import ch.mbaumeler.jass.extended.ui.ObserverableMatch.Event;

import com.zuehlke.jhp.bucamp.android.jass.settings.model.JassSettings;
import com.zuehlke.jhp.bucamp.android.jass.settings.model.Player;

public class GameController implements JassModelObserver {

	private Timer timer = new Timer();
	private final Handler handler = new Handler();
	private ObservableGame game;
	private JassSettings settings;
	private Map<PlayerToken, Player> players;
	private Map<String, PlayStrategy> strategies = new HashMap<String, PlayStrategy>();

	public GameController(ObservableGame game, Map<PlayerToken, Player> players, JassSettings settings) {
		this.game = game;
		this.settings = settings;
		this.players = players;
	}

	public PlayerToken getHumanPlayerToken() {
		return this.game.getPlayerRepository().getAll().get(0);
	}

	public void updated(Event arg0, PlayerToken arg1, Object arg2) {
		if (!this.game.getCurrentMatch().getActivePlayer()
				.equals(getHumanPlayerToken())) {
			this.timer.schedule(new TimerTask() {
				@Override
				public void run() {
					handler.post(new Runnable() {
						public void run() {
							playCard();
						}
					});

				}
			}, settings.getPlayDelay());
		}

	}

	public void playCard() {
		PlayerToken token = this.game.getCurrentMatch().getActivePlayer();
		PlayStrategy strategy = getStrategyForPlayerToken(token);
		Card cardToPlay = strategy.getCardToPlay(this.game.getCurrentMatch());
		this.game.getCurrentMatch().playCard(cardToPlay);
	}
	
	
	private PlayStrategy getStrategyForPlayerToken(PlayerToken token) {
		String className = players.get(token).getStrategy();
		
		if( strategies.containsKey(className)) {
			return strategies.get(className);
		}
		else {
			PlayStrategy s = null;
			if( className.equals("ch.mbaumeler.jass.extended.ai.simple.SimpleStrategy")) {
				s = new SimpleStrategyEngine().create();
			}
			else if( className.equals("ch.mbaumeler.jass.extended.ai.dummy.DummyStrategy")) {
				s = null;
			}
			
			if( s == null) {
				s = new SimpleStrategyEngine().create();
			}
			strategies.put(className, s);
			
			return s;
		}
	}
}
