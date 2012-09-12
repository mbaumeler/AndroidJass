package com.zuehlke.jhp.bucamp.android.jass;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;

import com.zuehlke.jhp.bucamp.android.jass.settings.model.Team;

public class SetupTeamFragment extends Fragment {

	public static final String ARG_TEAM = "arg.team";

	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.setup_team_fragment, container, false);
		
		Team team = (Team)getArguments().get(ARG_TEAM);
		
		{
			EditText etName = (EditText)view.findViewById(R.id.setup_team_et_player1_name);
			etName.setText(team.getPlayer1().getName());
			CheckBox chkbAi = (CheckBox)view.findViewById(R.id.setup_team_chkb_player1_type);
			chkbAi.setChecked(!team.getPlayer1().isHuman());
		}
		
		{
			EditText etName = (EditText)view.findViewById(R.id.setup_team_et_player2_name);
			etName.setText(team.getPlayer2().getName());
			CheckBox chkbAi = (CheckBox)view.findViewById(R.id.setup_team_chkb_player2_type);
			chkbAi.setChecked(!team.getPlayer2().isHuman());
		}

		return view;
	}

	public static Bundle createArguments(Team team) {
		Bundle b = new Bundle();
		b.putParcelable(ARG_TEAM, team);
		return b;
	}
	
}
