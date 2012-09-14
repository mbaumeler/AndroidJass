package com.zuehlke.jhp.bucamp.android.jass.audio;

import com.zuehlke.jhp.bucamp.android.jass.R;

public enum Sample {

	BACKGROUND_NOISE(R.raw.background_noise), PLAY_CARD(R.raw.place_card), HMMM(
			R.raw.hmmm);

	int id;

	Sample(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

}
