package com.zuehlke.jhp.bucamp.android.jass;

import static ch.mbaumeler.jass.core.card.CardSuit.DIAMONDS;
import static ch.mbaumeler.jass.core.card.CardSuit.HEARTS;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.app.Fragment;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnDragListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import ch.mbaumeler.jass.core.Game;
import ch.mbaumeler.jass.core.Match;
import ch.mbaumeler.jass.core.card.Card;
import ch.mbaumeler.jass.core.card.CardSuit;
import ch.mbaumeler.jass.core.card.CardValue;
import ch.mbaumeler.jass.core.game.Ansage;
import ch.mbaumeler.jass.core.game.PlayedCard;
import ch.mbaumeler.jass.core.game.PlayerToken;
import ch.mbaumeler.jass.extended.ui.JassModelObserver;
import ch.mbaumeler.jass.extended.ui.ObserverableMatch.Event;

public class TableFragment extends Fragment implements JassModelObserver,
		OnDragListener {

	private Game game;
	private MainActivity mainActivity;
	private Map<PlayerToken, ImageView> map;

	boolean hasDropped = false;

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

		map = new HashMap<PlayerToken, ImageView>();
		map.put(all.get(0), findImageView(R.id.player1));
		map.put(all.get(1), findImageView(R.id.player2));
		map.put(all.get(2), findImageView(R.id.player3));
		map.put(all.get(3), findImageView(R.id.player4));

		setPlayerName(R.id.player1Name, all.get(0));
		setPlayerName(R.id.player2Name, all.get(1));
		setPlayerName(R.id.player3Name, all.get(2));
		setPlayerName(R.id.player4Name, all.get(3));

		mainActivity.findViewById(R.id.tableFragment).setOnDragListener(this);
	}

	private void setPlayerName(int id, PlayerToken token) {
		findTextView(id).setText(mainActivity.getName(token));
	}

	private ImageView findImageView(int id) {
		return (ImageView) mainActivity.findViewById(id);
	}

	private TextView findTextView(int id) {
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
			TextView trumpfText = findTextView(R.id.trumpf);
			trumpfText.setText(CardUtil.getResourceFor(ansage));
			trumpfText.setTextColor(getResources().getColor(getColor(ansage)));
		}

		List<PlayedCard> cardsOnTable = currentMatch.getCardsOnTable();
		for (PlayerToken playerToken : game.getPlayerRepository().getAll()) {
			Card card = getPlayedCard(playerToken, cardsOnTable);
			int id = card != null ? getCardResource(card) : R.drawable.empty;
			map.get(playerToken).setImageResource(id);
		}
	}

	private int getCardResource(Card card) {

		switch (card.getSuit()) {
		case CLUBS:
			return club(card.getValue());
		case HEARTS:
			return hearts(card.getValue());
		case DIAMONDS:
			return diamonds(card.getValue());
		case SPADES:
			return spades(card.getValue());
		}
		throw new IllegalArgumentException();
	}

	private int spades(CardValue value) {
		switch (value) {
		case SIX:
			return R.drawable.spades_6;
		case SEVEN:
			return R.drawable.spades_7;
		case EIGHT:
			return R.drawable.spades_8;
		case NINE:
			return R.drawable.spades_9;
		case TEN:
			return R.drawable.spades_10;
		case JACK:
			return R.drawable.spades_jake;
		case QUEEN:
			return R.drawable.spades_queen;
		case KING:
			return R.drawable.spades_king;
		case ACE:
			return R.drawable.spades_ace;
		}
		throw new IllegalArgumentException();
	}

	private int diamonds(CardValue value) {
		switch (value) {
		case SIX:
			return R.drawable.diamonds_6;
		case SEVEN:
			return R.drawable.diamonds_7;
		case EIGHT:
			return R.drawable.diamonds_8;
		case NINE:
			return R.drawable.diamonds_9;
		case TEN:
			return R.drawable.diamonds_10;
		case JACK:
			return R.drawable.diamonds_jake;
		case QUEEN:
			return R.drawable.diamonds_queen;
		case KING:
			return R.drawable.diamonds_king;
		case ACE:
			return R.drawable.diamonds_ace;
		}
		throw new IllegalArgumentException();
	}

	private int hearts(CardValue value) {
		switch (value) {
		case SIX:
			return R.drawable.hearts_6;
		case SEVEN:
			return R.drawable.hearts_7;
		case EIGHT:
			return R.drawable.hearts_8;
		case NINE:
			return R.drawable.hearts_9;
		case TEN:
			return R.drawable.hearts_10;
		case JACK:
			return R.drawable.hearts_jake;
		case QUEEN:
			return R.drawable.hearts_queen;
		case KING:
			return R.drawable.hearts_king;
		case ACE:
			return R.drawable.hearts_ace;
		}
		throw new IllegalArgumentException();
	}

	private int club(CardValue value) {
		switch (value) {
		case SIX:
			return R.drawable.clubs_6;
		case SEVEN:
			return R.drawable.clubs_7;
		case EIGHT:
			return R.drawable.clubs_8;
		case NINE:
			return R.drawable.clubs_9;
		case TEN:
			return R.drawable.clubs_10;
		case JACK:
			return R.drawable.clubs_jake;
		case QUEEN:
			return R.drawable.clubs_queen;
		case KING:
			return R.drawable.clubs_king;
		case ACE:
			return R.drawable.clubs_ace;
		}
		throw new IllegalArgumentException();
	}

	private Card getPlayedCard(PlayerToken playerToken,
			List<PlayedCard> cardsOnTable) {
		for (PlayedCard playedCard : cardsOnTable) {
			if (playedCard.getPlayer() == playerToken) {
				return playedCard.getCard();
			}
		}
		return null;
	}

	private int getColor(Ansage ansage) {
		return (ansage.isTrumpf(HEARTS) || ansage.isTrumpf(DIAMONDS)) ? R.color.red
				: R.color.black;
	}

	public boolean onDrag(View table, DragEvent event) {
		Drawable enterShape = getResources().getDrawable(
				R.drawable.shape_droptarget);
		Drawable normalShape = getResources().getDrawable(R.drawable.shape);

		switch (event.getAction()) {
		case DragEvent.ACTION_DRAG_STARTED:
			// Do nothing
			break;
		case DragEvent.ACTION_DRAG_ENTERED:
			table.setBackgroundDrawable(enterShape);
			break;
		case DragEvent.ACTION_DRAG_EXITED:
			table.setBackgroundDrawable(normalShape);
			break;
		case DragEvent.ACTION_DROP:
			// play card --> TODO: ugly implementation!
			String cardString = event.getClipData().getItemAt(0).getText()
					.toString();
			Card card = deserializeCard(cardString);

			if (!game.getCurrentMatch().isCardPlayable(card)) {
				Toast.makeText(table.getContext(), "Card is not playable!",
						Toast.LENGTH_SHORT).show();
				View cardView = (View) event.getLocalState();
				cardView.setVisibility(View.VISIBLE);
				table.setBackgroundDrawable(normalShape);
				hasDropped = false;
			} else {
				game.getCurrentMatch().playCard(card);
				table.setBackgroundDrawable(normalShape);
				hasDropped = true;
			}
			break;
		case DragEvent.ACTION_DRAG_ENDED:
			table.setBackgroundDrawable(normalShape);
			if (!hasDropped) {
				View view1 = (View) event.getLocalState();
				view1.setVisibility(View.VISIBLE);
			}
		default:
			break;
		}
		return true;
	}

	private Card deserializeCard(String string) {
		String[] split = string.split("_");
		return new Card(CardSuit.valueOf(split[0]), CardValue.valueOf(split[1]));
	}

}
