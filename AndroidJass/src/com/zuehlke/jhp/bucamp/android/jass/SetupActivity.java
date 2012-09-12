package com.zuehlke.jhp.bucamp.android.jass;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.zuehlke.jhp.bucamp.android.jass.settings.model.JassSettings;
import com.zuehlke.jhp.bucamp.android.jass.settings.model.ModelFactory;

public class SetupActivity extends Activity {

	public static final String KEY_SETTINGS = "settings";
	public static Context appContext;

	private JassSettings settings;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.setup_activity);
		
		if (savedInstanceState != null
				&& savedInstanceState.containsKey(KEY_SETTINGS)) {
			settings = (JassSettings) savedInstanceState.get(KEY_SETTINGS);
		} else {
			settings = ModelFactory.createDefaultModel();
		}

		// ActionBar gets initiated
		ActionBar actionbar = getActionBar();

		// Tell the ActionBar we want to use Tabs.
		actionbar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		// initiating all tabs and set text to it.
		ActionBar.Tab tabSettings = actionbar.newTab().setText(
				getResources().getString(R.string.setup_actionbar_settings));
		ActionBar.Tab tabTeam1 = actionbar.newTab().setText(
				getResources().getString(R.string.setup_actionbar_team1));
		ActionBar.Tab tabTeam2 = actionbar.newTab().setText(
				getResources().getString(R.string.setup_actionbar_team2));

		// create the two fragments we want to use for display content
		Fragment fragmentSettings = new SetupSettingsFragment();
		fragmentSettings.setArguments(SetupSettingsFragment
				.createArguments(settings));
		Fragment fragmentTeam1 = new SetupTeamFragment();
		fragmentTeam1.setArguments(SetupTeamFragment.createArguments(settings
				.getTeam1()));
		Fragment fragmentTeam2 = new SetupTeamFragment();
		fragmentTeam2.setArguments(SetupTeamFragment.createArguments(settings
				.getTeam2()));

		// set the Tab listener. Now we can listen for clicks.
		tabSettings.setTabListener(new SetupTabsListener(fragmentSettings));
		tabTeam1.setTabListener(new SetupTabsListener(fragmentTeam1));
		tabTeam2.setTabListener(new SetupTabsListener(fragmentTeam2));

		// add the all tabs to the actionbar
		actionbar.addTab(tabSettings);
		actionbar.addTab(tabTeam1);
		actionbar.addTab(tabTeam2);

		// start game when play-button is being pressed
		Button closeButton = (Button) this.findViewById(R.id.setup_btn_play);
		closeButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				startGame();
			}
		});

	}

	protected void startGame() {
		Intent myIntent = new Intent(this, MainActivity.class);
		myIntent.putExtra(KEY_SETTINGS, settings);
		this.startActivity(myIntent);
	}

	class SetupTabsListener implements ActionBar.TabListener {
		public Fragment fragment;

		public SetupTabsListener(Fragment fragment) {
			this.fragment = fragment;
		}

		public void onTabReselected(Tab tab, FragmentTransaction ft) {
			// do nothing
		}

		public void onTabSelected(Tab tab, FragmentTransaction ft) {
			ft.replace(R.id.fragment_container, fragment);
		}

		public void onTabUnselected(Tab tab, FragmentTransaction ft) {
			ft.remove(fragment);
		}

	}
	
}
