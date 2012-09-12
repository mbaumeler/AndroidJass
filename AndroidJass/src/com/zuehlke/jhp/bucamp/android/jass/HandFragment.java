package com.zuehlke.jhp.bucamp.android.jass;

import java.util.List;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import ch.mbaumeler.jass.core.Match;
import ch.mbaumeler.jass.core.card.Card;
import ch.mbaumeler.jass.core.game.PlayerToken;
import ch.mbaumeler.jass.extended.ui.JassModelObserver;
import ch.mbaumeler.jass.extended.ui.ObserverableMatch.Event;

public class HandFragment extends Fragment implements JassModelObserver {

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
	}

	private Button findViewById(int id) {
		return (Button) mainActivity.findViewById(id);
	}

	private void initCard(int id, Card card) {
		Button button = findViewById(id);
		button.setOnTouchListener(new CardTouchListener(card));
		button.setText(CardUtil.toCardString(card));
		button.setTextColor(CardUtil.color(card.getSuit()));
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		mainActivity = (MainActivity) activity;
	}

	public void updated(Event event, PlayerToken playerToken, Object object) {

		Match currentMatch = mainActivity.getGame().getCurrentMatch();
		int roundsCompleted = currentMatch.getRoundsCompleted();
		int cardsOnTable = currentMatch.getCardsOnTable().size();
		if (roundsCompleted == 0 && cardsOnTable == 0) {
			List<Card> cardsInHand = currentMatch.getCards(mainActivity
					.getGameController().getHumanPlayerToken());

			initCard(R.id.button0, cardsInHand.get(0));
			initCard(R.id.button1, cardsInHand.get(1));
			initCard(R.id.button2, cardsInHand.get(2));
			initCard(R.id.button3, cardsInHand.get(3));
			initCard(R.id.button4, cardsInHand.get(4));
			initCard(R.id.button5, cardsInHand.get(5));
			initCard(R.id.button6, cardsInHand.get(6));
			initCard(R.id.button7, cardsInHand.get(7));
			initCard(R.id.button8, cardsInHand.get(8));
		}
	}

}
