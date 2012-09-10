package com.zuehlke.jhp.bucamp.android.jass;

import java.util.List;

import ch.mbaumeler.jass.core.Game;
import ch.mbaumeler.jass.core.card.Card;
import ch.mbaumeler.jass.core.game.PlayerToken;
import ch.mbaumeler.jass.extended.ui.JassModelObserver;
import ch.mbaumeler.jass.extended.ui.ObserverableMatch.Event;
import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class HandFragment extends Fragment implements JassModelObserver {

	private Game game;
	private PlayerToken playerToken;
	private MainActivity mainActivity;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.hand_fragment, container, false);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		mainActivity.getGame().addObserver(this);
		game = mainActivity.getGame();
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
	mainActivity = (MainActivity) activity;
	}

	public void updated(Event arg0, PlayerToken arg1, Object arg2) {

		List<Card> cardsInHand = game.getCurrentMatch().getCards(playerToken);

		for (Card card : cardsInHand) {
			// Set BUTTON X .value ... = card

		}

	}

}
