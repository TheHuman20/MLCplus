package com.example.osads.mlcplus;

/**
 * Created by osads on 08.04.2018.
 */

public class Rival {

    private String playerName;
    private Gender playerGender;
    private int playerLevel;
    private int playerBonus;
    private int playerStrength = playerLevel+playerBonus;

    public String getPlayerName() {
        return playerName;
    }

    public Gender getPlayerGender() {
        return playerGender;
    }

    public int getPlayerLevel() {
        return playerLevel;
    }

    public int getPlayerBonus() {
        return playerBonus;
    }

    public int getPlayerStrength() {
        return playerStrength;
    }

}
