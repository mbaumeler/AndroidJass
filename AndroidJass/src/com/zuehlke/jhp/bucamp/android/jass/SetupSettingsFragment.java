package com.zuehlke.jhp.bucamp.android.jass;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.zuehlke.jhp.bucamp.android.jass.settings.model.JassSettings;

public class SetupSettingsFragment extends Fragment {
	
	private static final String ARG_SETTINGS = "argument.settings";

	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.setup_settings_fragment, container, false);
		
		JassSettings settings = (JassSettings)getArguments().get(ARG_SETTINGS);
		
		EditText etPoints = (EditText)view.findViewById(R.id.setup_settings_et_points);
		etPoints.setText("" + settings.getTargetPoints());
		
		return view;
	}
	
	public static Bundle createArguments(JassSettings settings) {
		Bundle b = new Bundle();
		b.putParcelable(ARG_SETTINGS, settings);
		return b;
	}
}
