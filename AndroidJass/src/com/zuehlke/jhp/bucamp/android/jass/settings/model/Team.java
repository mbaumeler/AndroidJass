package com.zuehlke.jhp.bucamp.android.jass.settings.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Team implements Parcelable {
	
	private Player player1;
	private Player player2;
	public Player getPlayer1() {
		return player1;
	}
	public Team(Player player1, Player player2) {
		super();
		this.player1 = player1;
		this.player2 = player2;
	}
	public void setPlayer1(Player player1) {
		this.player1 = player1;
	}
	public Player getPlayer2() {
		return player2;
	}
	public void setPlayer2(Player player2) {
		this.player2 = player2;
	}
	
	public Team(Parcel p) {
		player1 = new Player(p);
		player2 = new Player(p);
	}
	
	public int describeContents() {
		return 0;
	}

	public void writeToParcel(Parcel dest, int flags) {
		player1.writeToParcel(dest, flags);
		player2.writeToParcel(dest, flags);
	}
	
    public static final Parcelable.Creator<Team> CREATOR =
        	new Parcelable.Creator<Team>() {
                public Team createFromParcel(Parcel in) {
                    return new Team(in);
                }
     
                public Team[] newArray(int size) {
                    return new Team[size];
                }
            };		

}
