package com.zuehlke.jhp.bucamp.android.jass;

import static ch.mbaumeler.jass.core.card.CardSuit.DIAMONDS;
import static ch.mbaumeler.jass.core.card.CardSuit.HEARTS;

import java.util.HashMap;

import android.graphics.Color;
import ch.mbaumeler.jass.core.card.CardSuit;
import ch.mbaumeler.jass.core.card.CardValue;
import ch.mbaumeler.jass.core.game.Ansage;
import ch.mbaumeler.jass.core.game.Ansage.SpielModi;

public final class CardUtil {

	private HashMap<String, Integer> cardImageMap;

	{
		cardImageMap = new HashMap<String, Integer>();
		cardImageMap.put(CardSuit.CLUBS.name() + CardValue.TEN.name(),
				R.drawable.clubs_10);
		cardImageMap.put(CardSuit.CLUBS.name() + CardValue.SIX.name(),
				R.drawable.clubs_6);
		cardImageMap.put(CardSuit.CLUBS.name() + CardValue.SEVEN.name(),
				R.drawable.clubs_7);
		cardImageMap.put(CardSuit.CLUBS.name() + CardValue.EIGHT.name(),
				R.drawable.clubs_8);
		cardImageMap.put(CardSuit.CLUBS.name() + CardValue.NINE.name(),
				R.drawable.clubs_9);
		cardImageMap.put(CardSuit.CLUBS.name() + CardValue.ACE.name(),
				R.drawable.clubs_ace);
		cardImageMap.put(CardSuit.CLUBS.name() + CardValue.JACK.name(),
				R.drawable.clubs_jake);
		cardImageMap.put(CardSuit.CLUBS.name() + CardValue.KING.name(),
				R.drawable.clubs_king);
		cardImageMap.put(CardSuit.CLUBS.name() + CardValue.QUEEN.name(),
				R.drawable.clubs_queen);
		cardImageMap.put(CardSuit.DIAMONDS.name() + CardValue.TEN.name(),
				R.drawable.diamonds_10);
		cardImageMap.put(CardSuit.DIAMONDS.name() + CardValue.SIX.name(),
				R.drawable.diamonds_6);
		cardImageMap.put(CardSuit.DIAMONDS.name() + CardValue.SEVEN.name(),
				R.drawable.diamonds_7);
		cardImageMap.put(CardSuit.DIAMONDS.name() + CardValue.EIGHT.name(),
				R.drawable.diamonds_8);
		cardImageMap.put(CardSuit.DIAMONDS.name() + CardValue.NINE.name(),
				R.drawable.diamonds_9);
		cardImageMap.put(CardSuit.DIAMONDS.name() + CardValue.ACE.name(),
				R.drawable.diamonds_ace);
		cardImageMap.put(CardSuit.DIAMONDS.name() + CardValue.JACK.name(),
				R.drawable.diamonds_jake);
		cardImageMap.put(CardSuit.DIAMONDS.name() + CardValue.KING.name(),
				R.drawable.diamonds_king);
		cardImageMap.put(CardSuit.DIAMONDS.name() + CardValue.QUEEN.name(),
				R.drawable.diamonds_queen);
		cardImageMap.put(CardSuit.HEARTS.name() + CardValue.TEN.name(),
				R.drawable.hearts_10);
		cardImageMap.put(CardSuit.HEARTS.name() + CardValue.SIX.name(),
				R.drawable.hearts_6);
		cardImageMap.put(CardSuit.HEARTS.name() + CardValue.SEVEN.name(),
				R.drawable.hearts_7);
		cardImageMap.put(CardSuit.HEARTS.name() + CardValue.EIGHT.name(),
				R.drawable.hearts_8);
		cardImageMap.put(CardSuit.HEARTS.name() + CardValue.NINE.name(),
				R.drawable.hearts_9);
		cardImageMap.put(CardSuit.HEARTS.name() + CardValue.ACE.name(),
				R.drawable.hearts_ace);
		cardImageMap.put(CardSuit.HEARTS.name() + CardValue.JACK.name(),
				R.drawable.hearts_jake);
		cardImageMap.put(CardSuit.HEARTS.name() + CardValue.KING.name(),
				R.drawable.hearts_king);
		cardImageMap.put(CardSuit.HEARTS.name() + CardValue.QUEEN.name(),
				R.drawable.hearts_queen);
		cardImageMap.put(CardSuit.SPADES.name() + CardValue.TEN.name(),
				R.drawable.spades_10);
		cardImageMap.put(CardSuit.SPADES.name() + CardValue.SIX.name(),
				R.drawable.spades_6);
		cardImageMap.put(CardSuit.SPADES.name() + CardValue.SEVEN.name(),
				R.drawable.spades_7);
		cardImageMap.put(CardSuit.SPADES.name() + CardValue.EIGHT.name(),
				R.drawable.spades_8);
		cardImageMap.put(CardSuit.SPADES.name() + CardValue.NINE.name(),
				R.drawable.spades_9);
		cardImageMap.put(CardSuit.SPADES.name() + CardValue.ACE.name(),
				R.drawable.spades_ace);
		cardImageMap.put(CardSuit.SPADES.name() + CardValue.JACK.name(),
				R.drawable.spades_jake);
		cardImageMap.put(CardSuit.SPADES.name() + CardValue.KING.name(),
				R.drawable.spades_king);
		cardImageMap.put(CardSuit.SPADES.name() + CardValue.QUEEN.name(),
				R.drawable.spades_queen);
	}

	public static int color(CardSuit cardSuit) {
		return cardSuit == HEARTS || cardSuit == DIAMONDS ? Color.RED
				: Color.BLACK;
	}

	public static int getResourceFor(Ansage ansage) {
		if (ansage.isTrumpf(CardSuit.HEARTS)) {
			return R.string.heartsSymbol;
		} else if (ansage.isTrumpf(CardSuit.DIAMONDS)) {
			return R.string.dimondsSymbol;
		} else if (ansage.isTrumpf(CardSuit.CLUBS)) {
			return R.string.clubsSymbol;
		} else if (ansage.isTrumpf(CardSuit.SPADES)) {
			return R.string.spadesSymbol;
		} else if (ansage.getSpielModi() == SpielModi.OBENABE) {
			return R.string.obenabe;
		} else if (ansage.getSpielModi() == SpielModi.UNDEUFE) {
			return R.string.undeuffe;
		}
		throw new IllegalArgumentException("Unknown ansage");
	}

}
