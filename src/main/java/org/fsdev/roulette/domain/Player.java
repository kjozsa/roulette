package org.fsdev.roulette.domain;

import java.math.BigDecimal;

public class Player {
    private String name;
    private BigDecimal totalWin;
    private BigDecimal totalBets;

    public Player(String name, BigDecimal totalWin, BigDecimal totalBets) {
        this.name = name;
        this.totalWin = totalWin;
        this.totalBets = totalBets;
    }

    @Override
    public String toString() {
        return name;
    }
}
