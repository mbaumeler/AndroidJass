package com.zuehlke.jhp.bucamp.android.jass;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import ch.mbaumeler.jass.core.Game;
import ch.mbaumeler.jass.core.JassEngine;
import ch.mbaumeler.jass.core.game.PlayerToken;
import ch.mbaumeler.jass.extended.ui.ObservableGame;

import com.zuehlke.jhp.bucamp.android.jass.audio.AudioManager;
import com.zuehlke.jhp.bucamp.android.jass.audio.Sample;
import com.zuehlke.jhp.bucamp.android.jass.controller.GameController;
import com.zuehlke.jhp.bucamp.android.jass.settings.model.JassSettings;
import com.zuehlke.jhp.bucamp.android.jass.settings.model.SettingsCreator;

public class MainActivity extends Activity implements
		SharedPreferences.OnSharedPreferenceChangeListener {

	private static final String GAME_FINISHED_DIALOG_TAG = "GameFinishedDialogFragment";
	private static Game game;
	private ObservableGame observableGame;
	private GameController gameController;
	AudioManager audioManager;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		SharedPreferences sharedPrefs = PreferenceManager
				.getDefaultSharedPreferences(this);
		JassSettings settings = SettingsCreator
				.createFromPreferences(sharedPrefs);

		audioManager = new AudioManager();
		audioManager.init(getApplicationContext());

		if (savedInstanceState == null || game == null) {
			game = new JassEngine().createJassGame();
		}
		observableGame = new ObservableGame(game);

		gameController = new GameController(observableGame, this, settings,
				audioManager);
		observableGame.addObserver(gameController);

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	protected void onPause() {
		super.onPause();
		audioManager.stop(Sample.BACKGROUND_NOISE);
		PreferenceManager.getDefaultSharedPreferences(this)
				.unregisterOnSharedPreferenceChangeListener(this);
	}

	@Override
	protected void onResume() {
		super.onResume();
		audioManager.repeat(Sample.BACKGROUND_NOISE);
		PreferenceManager.getDefaultSharedPreferences(this)
				.registerOnSharedPreferenceChangeListener(this);
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
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
		if (restartMenuItem != null) {
			restartMenuItem
					.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {

						public boolean onMenuItemClick(MenuItem item) {
							restartGame(item);
							return true;
						}
					});
		}
		MenuItem gameSpeedMenuItem = menu.findItem(R.id.menu_item_game_speed);
		if (gameSpeedMenuItem != null) {
			gameSpeedMenuItem
					.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {

						public boolean onMenuItemClick(MenuItem item) {
							final SharedPreferences prefs = PreferenceManager
									.getDefaultSharedPreferences(MainActivity.this);
							final EditText input = new EditText(
									MainActivity.this);
							input.setText(prefs.getString(
									SettingsCreator.KEY_PLAY_DELAY, "1000"));
							new AlertDialog.Builder(MainActivity.this)
									.setTitle(
											R.string.game_speed_setting_dialog_title)
									.setMessage(
											R.string.game_speed_setting_dialog_message)
									.setView(input)
									.setPositiveButton(
											"Ok",
											new DialogInterface.OnClickListener() {
												public void onClick(
														DialogInterface dialog,
														int whichButton) {

													SharedPreferences.Editor editor = prefs
															.edit();
													editor.putString(
															SettingsCreator.KEY_PLAY_DELAY,
															input.getText()
																	.toString());
													editor.commit();
												}
											})
									.setNegativeButton(
											"Cancel",
											new DialogInterface.OnClickListener() {
												public void onClick(
														DialogInterface dialog,
														int whichButton) {
													// Do nothing.
												}
											}).show();
							return false;
						}
					});
		}
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

	public AudioManager getAudioManager() {
		return audioManager;
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
		dialogFragment.show(getFragmentManager(), GAME_FINISHED_DIALOG_TAG);
	}

	public void doNewGameClick() {
		restartGame();
	}

	public void doGoToSettingsClick() {
		startActivity(new Intent(this, SetupActivity.class));
	}

	public void refresh(View view) {
		observableGame.notifyObservers();
		gameController.playCard();
	}

	public void onSharedPreferenceChanged(SharedPreferences sharedPreferences,
			String key) {
		if (key.equals(SettingsCreator.KEY_PLAY_DELAY)) {
			this.gameController.setGameSpeed(Long.valueOf(sharedPreferences
					.getString(key, "1000")));

		}
	}
}
