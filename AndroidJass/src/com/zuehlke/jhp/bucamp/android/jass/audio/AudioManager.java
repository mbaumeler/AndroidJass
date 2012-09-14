package com.zuehlke.jhp.bucamp.android.jass.audio;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.media.MediaPlayer;

public class AudioManager {
	
	
	Map<Sample, MediaPlayer> samples = new HashMap<Sample, MediaPlayer>();
	
	public void init(Context appContext) {
		Sample[] entries = Sample.values();
		for (Sample entry : entries) {
			MediaPlayer mp = MediaPlayer.create(appContext, entry.getId());
			try {
				mp.prepare();
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			samples.put(entry, mp);
		}
	}
	
	public void play(Sample s) {
		stop(s);
		samples.get(s).setLooping(false);
		samples.get(s).start();
	}
	
	public void repeat(Sample s) {
		stop(s);
		samples.get(s).setLooping(true);
		samples.get(s).start();
	}
	
	public void stop(Sample s) {
		if( samples.get(s).isPlaying()) {
			samples.get(s).stop();
		}
	}
	
	
	

}
