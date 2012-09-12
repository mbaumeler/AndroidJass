package com.zuehlke.jhp.bucamp.android.jass.settings.model;

import android.content.SharedPreferences;

public class SettingsCreator {
	
	private static final String KEY_POINTS = "points";
	private static final String KEY_PLAY_DELAY = "play_delay";
	private static final String KEY_TEAM1_PLAYER1_NAME = "team1_player1_name";
	private static final String KEY_TEAM1_PLAYER1_AI = "team1_player1_ai";
	private static final String KEY_TEAM1_PLAYER1_AI_STRATEGY = "team1_player1_ai_strategy";
	private static final String KEY_TEAM1_PLAYER2_NAME = "team1_player2_name";
	private static final String KEY_TEAM1_PLAYER2_AI = "team1_player2_ai";
	private static final String KEY_TEAM1_PLAYER2_AI_STRATEGY = "team1_player2_ai_strategy";
	private static final String KEY_TEAM2_PLAYER1_NAME = "team2_player1_name";
	private static final String KEY_TEAM2_PLAYER1_AI = "team2_player1_ai";
	private static final String KEY_TEAM2_PLAYER1_AI_STRATEGY = "team2_player1_ai_strategy";
	private static final String KEY_TEAM2_PLAYER2_NAME = "team2_player2_name";
	private static final String KEY_TEAM2_PLAYER2_AI = "team2_player2_ai";
	private static final String KEY_TEAM2_PLAYER2_AI_STRATEGY = "team2_player2_ai_strategy";
	
	public static JassSettings createDefaultModel() {
		
		return new JassSettings(
				5000,
				1000,
				new Team(
						new Player( "Matthias", true, "dummy"),
						new Player( "Fabien", false, "dummy")								
						),
				new Team(
						new Player( "Volker", false, "dummy"),
						new Player( "Stephan", false, "dummy")								
						)						
				);
		
		
	}
	
	public static JassSettings createFromPreferences( SharedPreferences prefs) {
		
		long points = Long.valueOf(prefs.getString(KEY_POINTS, "5000"));
		long playDelay = Long.valueOf(prefs.getString( KEY_PLAY_DELAY, "1000"));

		Team t1 = null; 
		{
			Player p1 = null;
			{
				String name = prefs.getString( KEY_TEAM1_PLAYER1_NAME, "team1_player1_name");
				boolean isAi = prefs.getBoolean(KEY_TEAM1_PLAYER1_AI, true);
				String aiStrategy = prefs.getString(KEY_TEAM1_PLAYER1_AI_STRATEGY, "dummy");
				p1 = new Player(name, isAi, aiStrategy);
			}
			Player p2 = null;
			{
				String name = prefs.getString( KEY_TEAM1_PLAYER2_NAME, "team1_player2_name");
				boolean isAi = prefs.getBoolean(KEY_TEAM1_PLAYER2_AI, true);
				String aiStrategy = prefs.getString(KEY_TEAM1_PLAYER2_AI_STRATEGY, "dummy");
				p2 = new Player(name, isAi, aiStrategy);
			}
			t1 = new Team(p1,p2);
		}
		
		Team t2 = null; 
		{
			Player p1 = null;
			{
				String name = prefs.getString( KEY_TEAM2_PLAYER1_NAME, "team1_player1_name");
				boolean isAi = prefs.getBoolean(KEY_TEAM2_PLAYER1_AI, true);
				String aiStrategy = prefs.getString(KEY_TEAM2_PLAYER1_AI_STRATEGY, "dummy");
				p1 = new Player(name, isAi, aiStrategy);
			}
			Player p2 = null;
			{
				String name = prefs.getString( KEY_TEAM2_PLAYER2_NAME, "team1_player2_name");
				boolean isAi = prefs.getBoolean(KEY_TEAM2_PLAYER2_AI, true);
				String aiStrategy = prefs.getString(KEY_TEAM2_PLAYER2_AI_STRATEGY, "dummy");
				p2 = new Player(name, isAi, aiStrategy);
			}
			t2 = new Team(p1,p2);
		}
		
		return new JassSettings(points,playDelay,t1,t2);
	}
}
