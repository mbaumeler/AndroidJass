package com.zuehlke.jhp.bucamp.android.jass;

import java.util.List;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import ch.mbaumeler.jass.core.game.PlayerToken;
import ch.mbaumeler.jass.core.game.PlayerTokenRepository;
import ch.mbaumeler.jass.core.game.Score;
import ch.mbaumeler.jass.extended.ui.JassModelObserver;
import ch.mbaumeler.jass.extended.ui.ObservableGame;
import ch.mbaumeler.jass.extended.ui.ObserverableMatch.Event;

public class ScoreFragment extends Fragment implements JassModelObserver {

	private MainActivity mainActivity;

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		mainActivity = (MainActivity) activity;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		mainActivity.getGame().addObserver(this);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.score_fragment, container, false);
	}

	public void updated(Event event, PlayerToken token, Object arg) {
		ObservableGame game = mainActivity.getGame();
		PlayerTokenRepository playerRepository = game.getPlayerRepository();
		Score totalScore = game.getTotalScore();
		PlayerToken humanPlayerToken = mainActivity.getGameController()
				.getHumanPlayerToken();

		StringBuilder score = new StringBuilder();
		team(score, playerRepository.getTeam1());
		score.append(totalScore.getPlayerScore(humanPlayerToken));
		score.append(". ");
		team(score, playerRepository.getTeam2());
		score.append(totalScore.getOppositeScore(humanPlayerToken));

		textView(R.id.scoreContent).setText(score.toString());
	}

	private void team(StringBuilder stringBuilder, List<PlayerToken> team) {
		stringBuilder.append(getName(team.get(0)));
		stringBuilder.append(" & ");
		stringBuilder.append(getName(team.get(1)));
		stringBuilder.append(": ");
	}

	private String getName(PlayerToken playerToken) {
		return mainActivity.getName(playerToken);
	}

	private TextView textView(int id) {
		return (TextView) mainActivity.findViewById(id);
	}
}
