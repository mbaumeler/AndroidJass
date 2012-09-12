package com.zuehlke.jhp.bucamp.android.jass;

import java.util.List;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import ch.mbaumeler.jass.core.Game;
import ch.mbaumeler.jass.core.Match;
import ch.mbaumeler.jass.core.card.Card;
import ch.mbaumeler.jass.core.game.PlayerToken;
import ch.mbaumeler.jass.extended.ui.JassModelObserver;
import ch.mbaumeler.jass.extended.ui.ObserverableMatch.Event;

public class HandFragment extends Fragment implements JassModelObserver {

	private Game game;
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

	public void updated(Event event, PlayerToken playerToken, Object object) {

		Match currentMatch = game.getCurrentMatch();

		List<Card> cardsInHand = currentMatch.getCards(mainActivity
				.getGameController().getHumanPlayerToken());

		int i = 0;
		for (i = 0; i < cardsInHand.size(); i++) {
			final Card card = cardsInHand.get(i);

			Button button = getButtonByIndex(i);
			button.setText(CardUtil.toCardString(card));
			button.setTextColor(CardUtil.color(card.getSuit()));
			button.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					game.getCurrentMatch().playCard(card);
				}
			});
			button.setEnabled(currentMatch.isCardPlayable(card));
		}
		while (i < 9) {
			getButtonByIndex(i++).setVisibility(View.INVISIBLE);
		}
	}

	private Button getButtonByIndex(int index) {
		switch (index) {
		case 0:
			return button(R.id.button0);
		case 1:
			return button(R.id.button1);
		case 2:
			return button(R.id.button2);
		case 3:
			return button(R.id.button3);
		case 4:
			return button(R.id.button4);
		case 5:
			return button(R.id.button5);
		case 6:
			return button(R.id.button6);
		case 7:
			return button(R.id.button7);
		case 8:
			return button(R.id.button8);

		}
		return null;
	}

	private Button button(int id) {
		return (Button) mainActivity.findViewById(id);
	}

}
