package org.fsdev.roulette.domain;

import java.math.BigInteger;

import org.springframework.util.Assert;

public class ParityBet extends Bet {
    private Parity parity;

    public ParityBet(Player player, BigInteger amount, Parity parity) {
        super(player, amount);
        Assert.state(parity != Parity.ZERO, "Can't bet on zero!");
        this.parity = parity;
    }

    @Override
    public BigInteger amountWon(Field rolledField) {
        return rolledField.getParity().equals(parity) ? amount.multiply(BigInteger.valueOf(2)) : BigInteger.ZERO;
    }
}
