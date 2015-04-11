package org.fsdev.roulette.domain;

import java.math.BigInteger;

public abstract class Bet {
    public final Player player;
    protected BigInteger amount;

    public Bet(Player player, BigInteger amount) {
        this.player = player;
        this.amount = amount;
    }

    public abstract BigInteger amountWon(Field rolledField);
}
