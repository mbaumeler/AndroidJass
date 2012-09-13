package com.zuehlke.jhp.bucamp.android.jass;

import ch.mbaumeler.jass.core.Game;
import ch.mbaumeler.jass.core.card.CardSuit;
import ch.mbaumeler.jass.core.game.Ansage;
import ch.mbaumeler.jass.core.game.Ansage.SpielModi;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

public class AnsageDialog extends DialogFragment {

	private Game game;

	public AnsageDialog(Game game) {
		this.game = game;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setStyle(DialogFragment.STYLE_NORMAL, DialogFragment.STYLE_NORMAL);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		getDialog().setTitle("Ansage:");
		View view = inflater
				.inflate(R.layout.ansage_fragment, container, false);
		addListener(R.id.selectEcken, view, new Ansage(CardSuit.DIAMONDS));
		addListener(R.id.selectHerz, view, new Ansage(CardSuit.HEARTS));
		addListener(R.id.selectKreuz, view, new Ansage(CardSuit.CLUBS));
		addListener(R.id.selectSchaufel, view, new Ansage(CardSuit.SPADES));
		addListener(R.id.selectObenabe, view, new Ansage(SpielModi.OBENABE));
		addListener(R.id.selectUndeuffe, view, new Ansage(SpielModi.UNDEUFE));
		return view;
	}

	private void addListener(int id, View view, final Ansage ansage) {
		getButton(id, view).setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				
				ansage(ansage);
			}
		});
	}

	private void ansage(Ansage ansage) {
		game.getCurrentMatch().setAnsage(ansage);
		dismiss();
	}

	public Button getButton(int id, View view) {
		return (Button) view.findViewById(id);
	}

}
