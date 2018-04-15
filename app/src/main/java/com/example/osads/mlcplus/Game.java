package com.example.osads.mlcplus;


import java.util.ArrayList;

public class Game {

    private Player Player;
    private ArrayList<Rival> rivals;
    int maxLevel;

    public void setPlayer(com.example.osads.mlcplus.Player player) {
        Player = player;
    }

    public ArrayList<Rival> getRivals() {
        return rivals;
    }

    public void setRivals(ArrayList<Rival> rivals) {
        this.rivals = rivals;
    }

    public Game(Player mPlayer, int maxLevel) {
        this.Player = mPlayer;
        this.maxLevel = maxLevel;
    }

    public Player getPlayer() {
        return Player;
    }

    public int getMaxLevel() {
        return maxLevel;
    }

    public void setMaxLevel(int maxLevel) {
        this.maxLevel = maxLevel;
    }
}
