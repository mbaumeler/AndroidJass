package com.zuehlke.jhp.bucamp.android.jass.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import android.os.Handler;
import ch.mbaumeler.jass.core.Match;
import ch.mbaumeler.jass.core.card.Card;
import ch.mbaumeler.jass.core.game.PlayerToken;
import ch.mbaumeler.jass.extended.ai.JassStrategy;
import ch.mbaumeler.jass.extended.ai.simple.SimpleStrategyEngine;
import ch.mbaumeler.jass.extended.ui.JassModelObserver;
import ch.mbaumeler.jass.extended.ui.ObservableGame;
import ch.mbaumeler.jass.extended.ui.ObserverableMatch.Event;

import com.zuehlke.jhp.bucamp.android.jass.MainActivity;
import com.zuehlke.jhp.bucamp.android.jass.audio.AudioManager;
import com.zuehlke.jhp.bucamp.android.jass.audio.Sample;
import com.zuehlke.jhp.bucamp.android.jass.settings.model.JassSettings;
import com.zuehlke.jhp.bucamp.android.jass.settings.model.Player;

public class GameController implements JassModelObserver {

	private Timer timer = new Timer();
	private final Handler handler = new Handler();
	private ObservableGame game;
	private JassSettings settings;
	private MainActivity mainActivity;
	private Map<PlayerToken, Player> players = new HashMap<PlayerToken, Player>();
	private Map<String, JassStrategy> strategies = new HashMap<String, JassStrategy>();
	private AudioManager audioManager;

	public GameController(ObservableGame game, MainActivity mainActivity,
			JassSettings settings, AudioManager audioManager) {
		this.game = game;
		this.mainActivity = mainActivity;
		this.settings = settings;
		this.audioManager = audioManager;
		initPlayersMap(settings);
	}

	private void initPlayersMap(JassSettings settings) {
		List<PlayerToken> all = this.game.getPlayerRepository().getAll();
		players.put(all.get(0), settings.getTeam1().getPlayer1());
		players.put(all.get(1), settings.getTeam2().getPlayer1());
		players.put(all.get(2), settings.getTeam1().getPlayer2());
		players.put(all.get(3), settings.getTeam2().getPlayer2());
	}

	public PlayerToken getHumanPlayerToken() {
		return this.game.getPlayerRepository().getAll().get(0);
	}

	public void updated(Event arg0, PlayerToken arg1, Object arg2) {
		Match currentMatch = game.getCurrentMatch();
		int cardsOnTable = currentMatch.getCardsOnTable().size();
		if (cardsOnTable == 4 && isGameFinished()) {
			this.mainActivity.showGameFinishedDialog();
			return;
		}
		if (isComputerPlayer(currentMatch) && cardsOnTable != 4 ) {
			initTimer();
		}

	}

	private boolean isComputerPlayer(Match currentMatch) {
		return !currentMatch.getActivePlayer().equals(getHumanPlayerToken());
	}

	private void initTimer() {
		
		Random r = new Random();
		int percent = r.nextInt(100);
		if( percent > 95) {
			new Timer().schedule(new TimerTask() {
				@Override
				public void run() {
					handler.post(new Runnable() {
						public void run() {
							audioManager.play(Sample.HMMM);
						}
					});

				}
			}, settings.getPlayDelay());			
		}
		
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

	private boolean isGameFinished() {
		return this.game.getCurrentMatch().getScore()
				.getPlayerScore(getHumanPlayerToken()) >= this.settings
				.getTargetPoints()
				|| this.game.getCurrentMatch().getScore()
						.getOppositeScore(getHumanPlayerToken()) >= this.settings
						.getTargetPoints();
	}

	public void playCard() {

		Match currentMatch = game.getCurrentMatch();

		if (currentMatch.getActivePlayer() != getHumanPlayerToken()) {

			PlayerToken token = this.game.getCurrentMatch().getActivePlayer();
			JassStrategy strategy = getStrategyForPlayerToken(token);
			if (currentMatch.getAnsage() == null) {
				currentMatch.setAnsage(new SimpleStrategyEngine().create()
						.getAnsage(currentMatch));
			}

			Card cardToPlay = strategy.getCardToPlay(this.game
					.getCurrentMatch());
			
			this.audioManager.play(Sample.PLAY_CARD);
			this.game.getCurrentMatch().playCard(cardToPlay);
		}
	}

	private JassStrategy getStrategyForPlayerToken(PlayerToken token) {
		String className = players.get(token).getStrategy();

		if (strategies.containsKey(className)) {
			return strategies.get(className);
		} else {
			JassStrategy s = null;
			if (className
					.equals("ch.mbaumeler.jass.extended.ai.simple.SimpleStrategy")) {
				s = new SimpleStrategyEngine().create();
			} else if (className
					.equals("ch.mbaumeler.jass.extended.ai.dummy.DummyStrategy")) {
				s = null;
			}

			if (s == null) {
				s = new SimpleStrategyEngine().create();
			}
			strategies.put(className, s);

			return s;
		}
	}

	public String getPlayerName(PlayerToken token) {
		return players.get(token).getName();
	}
	
	public void setGameSpeed(long delay) {
		this.settings.setPlayDelay(delay);
	}

}
