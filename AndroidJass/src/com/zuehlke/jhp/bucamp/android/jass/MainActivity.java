<<<<<<< HEAD
package com.zuehlke.jhp.bucamp.android.jass;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import ch.mbaumeler.jass.core.Game;
import ch.mbaumeler.jass.core.JassEngine;
import ch.mbaumeler.jass.core.game.PlayerToken;
import ch.mbaumeler.jass.extended.ui.ObservableGame;

import com.zuehlke.jhp.bucamp.android.jass.controller.GameController;
import com.zuehlke.jhp.bucamp.android.jass.settings.model.JassSettings;
import com.zuehlke.jhp.bucamp.android.jass.settings.model.SettingsCreator;

public class MainActivity extends Activity {

	public static final int GAME_FINISHED_DIALOG_ID = 0;
	private static Game game;
	private ObservableGame observableGame;
	private GameController gameController;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		SharedPreferences sharedPrefs = PreferenceManager
				.getDefaultSharedPreferences(this);
		JassSettings settings = SettingsCreator
				.createFromPreferences(sharedPrefs);

		if (savedInstanceState == null || game == null) {
			game = new JassEngine().createJassGame();
		}
		observableGame = new ObservableGame(game);

		gameController = new GameController(observableGame, this, settings);
		observableGame.addObserver(gameController);
		
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		observableGame.notifyObservers();
		observableGame.addObserver(new AnsageObserver(gameController
				.getHumanPlayerToken(), this));
		observableGame.notifyObservers();
	}

	public String getName(PlayerToken token) {
		return gameController.getPlayerName(token);
	}

	public ObservableGame getGame() {
		return observableGame;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		MenuItem restartMenuItem = menu.findItem(R.id.menu_item_restart);
		if (restartMenuItem == null) {
			return true;
		}
		restartMenuItem
				.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {

					public boolean onMenuItemClick(MenuItem item) {
						restartGame(item);
						return true;
					}
				});
		return true;
	}

	public void displaySettingsActivity() {
		startActivity(new Intent(this, SetupActivity.class));
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	public GameController getGameController() {
		return gameController;
	}

	public void restartGame(MenuItem item) {
		restartGame();
	}

	private void restartGame() {
		startActivity(new Intent(this, MainActivity.class));
	}

	public void showGameFinishedDialog() {
		DialogFragment dialogFragment = GameFinishedDialogFragment.newInstance(
				game, getGameController().getHumanPlayerToken());
		dialogFragment.show(getFragmentManager(), "dialog");
	}

	public void doPositiveClick() {
		// Do stuff here.
		Log.i("FragmentAlertDialog", "Positive click!");
		restartGame();
	}

	public void doNegativeClick() {
		startActivity(new Intent(this, SetupActivity.class));
	}

}
=======
package com.zuehlke.jhp.bucamp.android.jass;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import ch.mbaumeler.jass.core.Game;
import ch.mbaumeler.jass.core.JassEngine;
import ch.mbaumeler.jass.core.game.PlayerToken;
import ch.mbaumeler.jass.extended.ui.ObservableGame;

import com.zuehlke.jhp.bucamp.android.jass.controller.GameController;
import com.zuehlke.jhp.bucamp.android.jass.settings.model.JassSettings;
import com.zuehlke.jhp.bucamp.android.jass.settings.model.SettingsCreator;

public class MainActivity extends Activity {

	public static final int GAME_FINISHED_DIALOG_ID = 0;
	private static Game game;
	private ObservableGame observableGame;
	private GameController gameController;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		SharedPreferences sharedPrefs = PreferenceManager
				.getDefaultSharedPreferences(this);
		JassSettings settings = SettingsCreator
				.createFromPreferences(sharedPrefs);

		if (savedInstanceState == null || game == null) {
			game = new JassEngine().createJassGame();
		}
		observableGame = new ObservableGame(game);

		gameController = new GameController(observableGame, this, settings);
		observableGame.addObserver(gameController);
		observableGame.addObserver(new AnsageObserver(gameController
				.getHumanPlayerToken(), this));
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		observableGame.notifyObservers();
	}

	public String getName(PlayerToken token) {
		return gameController.getPlayerName(token);
	}

	public ObservableGame getGame() {
		return observableGame;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		MenuItem restartMenuItem = menu.findItem(R.id.menu_item_restart);
		if (restartMenuItem == null) {
			return true;
		}
		restartMenuItem
				.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {

					public boolean onMenuItemClick(MenuItem item) {
						restartGame(item);
						return true;
					}
				});
		return true;
	}

	public void displaySettingsActivity() {
		startActivity(new Intent(this, SetupActivity.class));
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	public GameController getGameController() {
		return gameController;
	}

	public void restartGame(MenuItem item) {
		restartGame();
	}

	private void restartGame() {
		startActivity(new Intent(this, MainActivity.class));
	}

	public void showGameFinishedDialog() {
		DialogFragment dialogFragment = GameFinishedDialogFragment.newInstance(
				game, getGameController().getHumanPlayerToken());
		dialogFragment.show(getFragmentManager(), "dialog");
	}

	public void doPositiveClick() {
		// Do stuff here.
		Log.i("FragmentAlertDialog", "Positive click!");
		restartGame();
	}

	public void doNegativeClick() {
		startActivity(new Intent(this, SetupActivity.class));
	}

}
>>>>>>> branch 'master' of https://github.com/mbaumeler/AndroidJass.git
