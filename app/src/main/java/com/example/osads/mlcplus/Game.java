package com.example.osads.mlcplus;


public class Game {

    private Player Player;
    private Rival[] rivals;
    int maxLevel;

    public Game(Player mPlayer, int maxLevel) {
        this.Player = mPlayer;
        this.maxLevel = maxLevel;
    }

    public Player getPlayer() {
        return Player;
    }

    public void setmPlayer(Player mPlayer) {
        this.Player = mPlayer;
    }

    public int getMaxLevel() {
        return maxLevel;
    }

    public void setMaxLevel(int maxLevel) {
        this.maxLevel = maxLevel;
    }
}
