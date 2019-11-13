package de.albert.drinkordie;

import java.util.Arrays;
import java.util.Random;

public class Strike {
    private String strike;
    private String solution;
    private String[] players;
    private int rounds = 0;
    private int index;
    private int maxRounds;


    public Strike(String strike, String solution, String[] players, int index)
    {
        this.strike = strike;
        this.solution = solution;
        this.players = Arrays.copyOf(players, players.length);
        this.index = index;
        maxRounds = new Random().nextInt((19 - 9)+ 1) + 9;
    }

    public String getStrike()
    {
        String randomPlayer = players[0];
        String randomPlayer2 = players[1];
        String randomPlayer3 = players[2];
        return String.format(strike, randomPlayer, randomPlayer2, randomPlayer3, getRandomSipCount());
    }

    public String getSolution()
    {
        String randomPlayer = players[0];
        String randomPlayer2 = players[1];
        String randomPlayer3 = players[2];
        return String.format(solution, randomPlayer, randomPlayer2, randomPlayer3, getRandomSipCount());
    }

    public int getIndex()
    {
        return index;
    }

    public boolean round()
    {
        rounds++;
        return rounds >= maxRounds;
    }

    public boolean roundEnd()
    {
        return true;
    }

    private String getRandomSipCount()
    {
        Random random = new Random();
        int randomSipNumber = random.nextInt(4) + 2;
        String randomSipCount = "" + randomSipNumber;
        return randomSipCount;
    }

}
