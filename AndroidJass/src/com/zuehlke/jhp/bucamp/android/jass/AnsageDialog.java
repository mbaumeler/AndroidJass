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
		
		getDialog().setTitle("Choose ansage");
		View v = inflater.inflate(R.layout.ansage_fragment, container, false);

		getButton(R.id.selectEcken, v).setOnClickListener(
				new OnClickListener() {
					public void onClick(View v) {
						ansage(new Ansage(CardSuit.DIAMONDS));
					}
				});
		getButton(R.id.selectHerz, v).setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				ansage(new Ansage(CardSuit.HEARTS));
			}
		});
		getButton(R.id.selectKreuz, v).setOnClickListener(
				new OnClickListener() {
					public void onClick(View v) {
						ansage(new Ansage(CardSuit.CLUBS));
					}
				});
		getButton(R.id.selectSchaufel, v).setOnClickListener(
				new OnClickListener() {
					public void onClick(View v) {
						game.getCurrentMatch().setAnsage(
								new Ansage(CardSuit.SPADES));
					}
				});
		getButton(R.id.selectObenabe, v).setOnClickListener(
				new OnClickListener() {
					public void onClick(View v) {
						ansage(new Ansage(SpielModi.OBENABE));
					}
				});
		getButton(R.id.selectEcken, v).setOnClickListener(
				new OnClickListener() {
					public void onClick(View v) {
						ansage(new Ansage(SpielModi.UNDEUFE));
					}
				});
		getButton(R.id.selectEcken, v).setOnClickListener(
				new OnClickListener() {
					public void onClick(View v) {
						ansage(new Ansage(CardSuit.DIAMONDS));
					}
				});
		return v;
	}

	private void ansage(Ansage ansage) {
		game.getCurrentMatch().setAnsage(ansage);
		dismiss();
	}

	public Button getButton(int id, View view) {
		return (Button) view.findViewById(id);
	}

}
