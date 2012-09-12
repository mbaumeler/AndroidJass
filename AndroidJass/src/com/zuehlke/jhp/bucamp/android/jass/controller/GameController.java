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
	private final Handler handler = new Handler();
	private ObservableGame game;
	private SimpleStrategy strategy;

	public GameController(ObservableGame game) {
		this.game = game;
		this.strategy = new SimpleStrategyEngine().create();
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
			}, 2000);
		}

	}

	public void playCard() {
		this.game.getCurrentMatch().playCard(
				this.strategy.getCardToPlay(this.game.getCurrentMatch()));
	}

}
