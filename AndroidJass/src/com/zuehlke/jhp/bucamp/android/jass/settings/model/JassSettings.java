package com.zuehlke.jhp.bucamp.android.jass.settings.model;

import android.os.Parcel;
import android.os.Parcelable;

public class JassSettings implements Parcelable {

	private long targetPoints;
	private long playDelay;
	private Team team1;
	private Team team2;

	public JassSettings(long targetPoints, long playDelay, Team team1, Team team2) {
		super();
		this.targetPoints = targetPoints;
		this.playDelay = playDelay;
		this.team1 = team1;
		this.team2 = team2;
	}

	public long getTargetPoints() {
		return targetPoints;
	}

	public void setTargetPoints(long targetPoints) {
		this.targetPoints = targetPoints;
	}

	public long getPlayDelay() {
		return playDelay;
	}

	public void setPlayDelay(long playDelay) {
		this.playDelay = playDelay;
	}

	public Team getTeam1() {
		return team1;
	}

	public void setTeam1(Team team1) {
		this.team1 = team1;
	}

	public Team getTeam2() {
		return team2;
	}

	public void setTeam2(Team team2) {
		this.team2 = team2;
	}

	public JassSettings(Parcel p) {
		targetPoints = p.readLong();
		playDelay = p.readLong();
		team1 = new Team(p);
		team2 = new Team(p);
	}

	public int describeContents() {
		return 0;
	}

	public void writeToParcel(Parcel dest, int flags) {
		dest.writeLong(targetPoints);
		dest.writeLong(playDelay);
		team1.writeToParcel(dest, flags);
		team2.writeToParcel(dest, flags);
	}

	public static final Parcelable.Creator<JassSettings> CREATOR = new Parcelable.Creator<JassSettings>() {
		public JassSettings createFromParcel(Parcel in) {
			return new JassSettings(in);
		}

		public JassSettings[] newArray(int size) {
			return new JassSettings[size];
		}
	};

}
