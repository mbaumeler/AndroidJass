package com.zuehlke.jhp.bucamp.android.jass.controller;

import java.util.Timer;
import java.util.TimerTask;

import android.os.Handler;
import ch.mbaumeler.jass.core.game.PlayerToken;
import ch.mbaumeler.jass.extended.ai.simple.SimpleStrategy;
import ch.mbaumeler.jass.extended.ai.simple.SimpleStrategyEngine;
import ch.mbaumeler.jass.extended.ui.JassModelObserver;
import ch.mbaumeler.jass.extended.ui.ObservableGame;
import ch.mbaumeler.jass.extended.ui.ObserverableMatch.Event;

public class GameController implements JassModelObserver {

	private Timer timer = new Timer();
	final Handler handler = new Handler();
	private ObservableGame game;
	private SimpleStrategy strategy;

	public GameController(ObservableGame game) {
		this.game = game;
		strategy = new SimpleStrategyEngine().create();
		this.game.addObserver(this);
	}

	public PlayerToken getHumanPlayerToken() {
		return game.getPlayerRepository().getAll().get(0);
	}

	public void updated(Event arg0, PlayerToken arg1, Object arg2) {
		if (!game.getCurrentMatch().getActivePlayer()
				.equals(getHumanPlayerToken())) {
			timer.schedule(new TimerTask() {
				@Override
				public void run() {
					handler.post(new Runnable() {
						public void run() {
							playCard();
						}
					});

				}
			}, 2000);
		}

	}

	public void playCard() {
		game.getCurrentMatch().playCard(
				strategy.getCardToPlay(game.getCurrentMatch()));
	}

}
