package com.example.osads.mlcplus;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import java.io.Serializable;

@SuppressLint("ParcelCreator")
public class Player implements Parcelable {

    private String playerName;
    private Gender playerGender;
    private int playerLevel;
    private int playerBonus;

    public Player(String playerName, Gender playerGender) {
        this.playerName = playerName;
        this.playerGender = playerGender;
        this.playerLevel = 1;
        this.playerBonus = 0;

    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public Gender getPlayerGender() {
        return playerGender;
    }

    public void setPlayerGender(Gender playerGender) {
        this.playerGender = playerGender;
    }

    public int getPlayerLevel() {
        return playerLevel;
    }

    public void setPlayerLevel(int playerLevel) {
        this.playerLevel = playerLevel;
    }

    public int getPlayerBonus() {
        return playerBonus;
    }

    public void setPlayerBonus(int playerBonus) {
        this.playerBonus = playerBonus;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        Bundle bundle = new Bundle();

        parcel.writeString(playerName);
        parcel.writeSerializable(playerGender);
        parcel.writeInt(playerLevel);
        parcel.writeInt(playerBonus);
    }
    public static final Parcelable.Creator<Player> CREATOR = new Parcelable.Creator<Player>(){

        @Override
        public Player createFromParcel(Parcel parcel) {
            return new Player(parcel);
        }

        @Override
        public Player[] newArray(int i) {
            return new Player[i];
        }
    };
    private Player (Parcel parcel) {
        playerName = parcel.readString();
        playerGender = (Gender) parcel.readSerializable();
        playerLevel = parcel.readInt();
        playerBonus = parcel.readInt();
    }

}

