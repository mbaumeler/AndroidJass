package com.zuehlke.jhp.bucamp.android.jass;

import ch.mbaumeler.jass.core.Match;
import ch.mbaumeler.jass.core.game.PlayerToken;
import ch.mbaumeler.jass.extended.ui.JassModelObserver;
import ch.mbaumeler.jass.extended.ui.ObservableGame;
import ch.mbaumeler.jass.extended.ui.ObserverableMatch.Event;

public class AnsageObserver implements JassModelObserver{

	private final ObservableGame game;
	private PlayerToken humanPlayer;
	private MainActivity mainActivity;
	
	public AnsageObserver(ObservableGame observegame, PlayerToken humanPlayer, MainActivity mainActivity) {
		this.game = observegame;
		this.humanPlayer = humanPlayer;
		this.mainActivity = mainActivity;
	}
	
	public void updated(Event event, PlayerToken playerToken, Object object) {
		
		 Match currentMatch = game.getCurrentMatch();
		
		PlayerToken activePlayer = game.getCurrentMatch().getActivePlayer();
		if (activePlayer == humanPlayer && currentMatch.getAnsage() == null) {
			AnsageDialog ansageDialog = new AnsageDialog(game);
			ansageDialog.setCancelable(false);
			ansageDialog.show(mainActivity.getFragmentManager(), "ansagedialog");
		}
		
	}

}
