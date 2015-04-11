package org.fsdev.roulette.domain;

import java.math.BigInteger;

public class FieldBet extends Bet {
    private Player player;
    private Field field;

    public FieldBet(Player player, BigInteger amount, Field field) {
        super(player, amount);
        this.field = field;
    }

    @Override
    public BigInteger amountWon(Field rolledField) {
        return field.equals(rolledField) ? amount.multiply(BigInteger.valueOf(36)) : BigInteger.ZERO;
    }
}
