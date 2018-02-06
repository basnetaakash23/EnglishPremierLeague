package com.example.aakashbasnet.englishpremierleague;

/**
 * Created by aakashbasnet on 2/5/18.
 */

public class PremierLeague {
    private String nTeam;
    private int nGames;
    private int nWin;
    private int nLoss;
    private int nTotal;

    public PremierLeague(String mTeam, int mGames, int mWin, int mLoss, int mTotal){
        nTeam = mTeam;
        nGames = mGames;
        nWin = mWin;
        nLoss = mLoss;
        nTotal = mTotal;

    }

    public String getTeam(){
        return nTeam;
    }

    public int getGames(){
        return nGames;
    }

    public int getWin(){
        return nWin;
    }

    public int getLoss(){
        return nLoss;
    }

    public int getTotal(){
        return nTotal;
    }

}
