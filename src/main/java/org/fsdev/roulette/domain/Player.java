package org.fsdev.roulette.domain;

import java.math.BigInteger;

public class Player {
    private String name;
    private BigInteger totalWin;
    private BigInteger totalBets;

    public Player(String name, BigInteger totalWin, BigInteger totalBets) {
        this.name = name;
        this.totalWin = totalWin;
        this.totalBets = totalBets;
    }

    @Override
    public String toString() {
        return name;
    }

    public void wins(BigInteger amount) {
        totalBets.add(BigInteger.ONE);
        totalWin.add(amount);
    }
}
