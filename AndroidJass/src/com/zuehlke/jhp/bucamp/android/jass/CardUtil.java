package com.zuehlke.jhp.bucamp.android.jass;

import static ch.mbaumeler.jass.core.card.CardSuit.DIAMONDS;
import static ch.mbaumeler.jass.core.card.CardSuit.HEARTS;
import android.graphics.Color;
import ch.mbaumeler.jass.core.card.Card;
import ch.mbaumeler.jass.core.card.CardSuit;
import ch.mbaumeler.jass.core.card.CardValue;
import ch.mbaumeler.jass.core.game.Ansage;

public final class CardUtil {

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
		} else {
			return R.string.spadesSymbol;
		}
	}

	public static char getAnsageIcon(Ansage ansage) {
		if (ansage.isTrumpf(CardSuit.HEARTS)) {
			return CardUtil.getSuitChar(CardSuit.HEARTS);
		} else if (ansage.isTrumpf(CardSuit.DIAMONDS)) {
			return CardUtil.getSuitChar(CardSuit.DIAMONDS);
		} else if (ansage.isTrumpf(CardSuit.CLUBS)) {
			return CardUtil.getSuitChar(CardSuit.CLUBS);
		} else {
			return CardUtil.getSuitChar(CardSuit.SPADES);
		}
	}

	public static char getSuitChar(CardSuit cardSuit) {

		switch (cardSuit) {
		case CLUBS:
			return 9827;
		case SPADES:
			return 9824;
		case DIAMONDS:
			return 9830;
		case HEARTS:
			return 9829;
		}
		return '?';

	}

	private static String toString(CardValue value) {
		switch (value) {

		case SIX:
			return "6";
		case SEVEN:
			return "7";
		case EIGHT:
			return "8";
		case NINE:
			return "9";
		case TEN:
			return "10";
		case JACK:
			return "J";
		case QUEEN:
			return "Q";
		case KING:
			return "K";
		case ACE:
			return "A";
		default:
			return null;
		}
	}

	public static String toCardString(Card card) {
		return getSuitChar(card.getSuit()) + "\n" + toString(card.getValue());
	}
}
