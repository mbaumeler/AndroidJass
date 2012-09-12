package com.zuehlke.jhp.bucamp.android.jass;

import ch.mbaumeler.jass.core.Match;
import ch.mbaumeler.jass.core.game.PlayerToken;
import ch.mbaumeler.jass.extended.ui.JassModelObserver;
import ch.mbaumeler.jass.extended.ui.ObserverableMatch.Event;

public class AnsageObserver implements JassModelObserver {

	private PlayerToken humanPlayer;
	private MainActivity mainActivity;

	public AnsageObserver(PlayerToken humanPlayer, MainActivity mainActivity) {
		this.humanPlayer = humanPlayer;
		this.mainActivity = mainActivity;
	}

	public void updated(Event event, PlayerToken playerToken, Object object) {

		Match currentMatch = mainActivity.getGame().getCurrentMatch();

		PlayerToken activePlayer = mainActivity.getGame().getCurrentMatch()
				.getActivePlayer();
		if (activePlayer == humanPlayer && currentMatch.getAnsage() == null) {
			AnsageDialog ansageDialog = new AnsageDialog(mainActivity.getGame());
			ansageDialog.setCancelable(false);
			ansageDialog
					.show(mainActivity.getFragmentManager(), "ansagedialog");
		}

	}

}
