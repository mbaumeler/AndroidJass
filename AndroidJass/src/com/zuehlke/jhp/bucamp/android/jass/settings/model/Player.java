package com.zuehlke.jhp.bucamp.android.jass.settings.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Player implements Parcelable {
	
	private String name;
	private boolean isAi;
	private String strategy;
	
	public Player(String name, boolean isAi, String strategy) {
		super();
		this.name = name;
		this.isAi = isAi;
		this.strategy = strategy;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isAi() {
		return isAi;
	}
	public void setAi(boolean isAi) {
		this.isAi = isAi;
	}
	public String getStrategy() {
		return strategy;
	}
	public void setStrategy(String strategy) {
		this.strategy = strategy;
	}

	public Player(Parcel p) {
		name = p.readString();
		isAi = Boolean.valueOf(p.readString());
		strategy = p.readString();
	}
	
	public int describeContents() {
		return 0;
	}

	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(name);
		dest.writeString(Boolean.toString(isAi));
		dest.writeString(strategy);
	}
	
    public static final Parcelable.Creator<Player> CREATOR =
        	new Parcelable.Creator<Player>() {
                public Player createFromParcel(Parcel in) {
                    return new Player(in);
                }
     
                public Player[] newArray(int size) {
                    return new Player[size];
                }
            };	

}
