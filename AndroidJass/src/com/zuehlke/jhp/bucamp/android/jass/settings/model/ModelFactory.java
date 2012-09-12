package com.zuehlke.jhp.bucamp.android.jass.settings.model;

public class ModelFactory {
	public static JassSettings createDefaultModel() {
		
		return new JassSettings(
				5000,
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
}
