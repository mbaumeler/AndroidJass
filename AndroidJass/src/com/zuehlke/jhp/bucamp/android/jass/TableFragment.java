package com.zuehlke.jhp.bucamp.android.jass;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import ch.mbaumeler.jass.core.Game;
import ch.mbaumeler.jass.core.Match;
import ch.mbaumeler.jass.core.game.Ansage;
import ch.mbaumeler.jass.core.game.PlayedCard;
import ch.mbaumeler.jass.core.game.PlayerToken;
import ch.mbaumeler.jass.extended.ui.JassModelObserver;
import ch.mbaumeler.jass.extended.ui.ObserverableMatch.Event;

public class TableFragment extends Fragment implements JassModelObserver {

	private Game game;
	private MainActivity mainActivity;
	private Map<PlayerToken, TextView> map;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.table_fragment, container, false);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		mainActivity.getGame().addObserver(this);
		game = mainActivity.getGame();

		List<PlayerToken> all = game.getPlayerRepository().getAll();

		map = new HashMap<PlayerToken, TextView>();
		map.put(all.get(0), (TextView) mainActivity.findViewById(R.id.player1));
		map.put(all.get(1), (TextView) mainActivity.findViewById(R.id.player2));
		map.put(all.get(2), (TextView) mainActivity.findViewById(R.id.player3));
		map.put(all.get(3), (TextView) mainActivity.findViewById(R.id.player4));

	}

	private TextView getTextView(int id) {
		return (TextView) mainActivity.findViewById(id);
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		mainActivity = (MainActivity) activity;
	}

	public void updated(Event arg0, PlayerToken arg1, Object arg2) {
		Match currentMatch = game.getCurrentMatch();
		Ansage ansage = currentMatch.getAnsage();
		if (ansage != null) {
			((TextView) mainActivity.findViewById(R.id.trumpf))
					.setText(CardUtil.getResourceFor(ansage));
		}
		List<PlayedCard> cardsOnTable = currentMatch.getCardsOnTable();

		getTextView(R.id.player1).setText("");
		getTextView(R.id.player2).setText("");
		getTextView(R.id.player3).setText("");
		getTextView(R.id.player4).setText("");

		for (PlayedCard playedCard : cardsOnTable) {
			TextView textView = map.get(playedCard.getPlayer());
			textView.setText(CardUtil.toCardString(playedCard.getCard()));
			textView.setText(CardUtil.toCardString(playedCard.getCard()));
			textView.setTextColor(CardUtil
					.color(playedCard.getCard().getSuit()));
		}
	}

}