package com.zuehlke.jhp.bucamp.android.jass;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import ch.mbaumeler.jass.core.Game;
import ch.mbaumeler.jass.core.game.PlayerToken;

public class GameFinishedDialogFragment extends DialogFragment {

	public static GameFinishedDialogFragment newInstance(Game game,
			PlayerToken humanPlayerToken) {
		GameFinishedDialogFragment frag = new GameFinishedDialogFragment();
		Bundle args = new Bundle();

		args.putInt("humanPlayerTeamScore", game.getCurrentMatch().getScore()
				.getPlayerScore(humanPlayerToken));
		args.putInt("oppositeTeamScore", game.getCurrentMatch().getScore()
				.getOppositeScore(humanPlayerToken));
		frag.setArguments(args);
		return frag;
	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		int humanPlayerTeamScore = getArguments()
				.getInt("humanPlayerTeamScore");
		int oppositeTeamScore = getArguments().getInt("oppositeTeamScore");

		String message;
		int icon;
		if (humanPlayerTeamScore > oppositeTeamScore) {
			message = getString(R.string.team_won_message);
			icon = R.drawable.winner_smiley;
		} else {
			message = getString(R.string.team_lost_message);
			icon = R.drawable.loser_smiley;
		}
		message = message + " " + humanPlayerTeamScore + "-"
				+ oppositeTeamScore;

		return new AlertDialog.Builder(getActivity())
				.setIcon(icon)
				.setTitle(R.string.game_finished_dialog_title)
				.setMessage(message)
				.setPositiveButton(R.string.game_finished_dialog_ok,
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int whichButton) {
								((MainActivity) getActivity())
										.doNewGameClick();
							}
						})
				.setNegativeButton(R.string.game_finished_dialog_cancel,
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int whichButton) {
								((MainActivity) getActivity())
										.doGoToSettingsClick();
							}
						}).create();
	}
}
