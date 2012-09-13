package com.zuehlke.jhp.bucamp.android.jass;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class SetupActivity extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		getFragmentManager().beginTransaction()
				.replace(android.R.id.content, new SettingsFragment()).commit();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuItem menuItem = menu.add(Menu.NONE, 0, 0, "Play");
		menuItem.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
		menuItem.setIcon(R.drawable.start_game);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case 0:
			startActivity(new Intent(this, MainActivity.class));
			return true;
		}
		return false;
	}

}
