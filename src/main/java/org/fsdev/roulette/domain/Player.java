package org.fsdev.roulette.domain;

import java.math.BigInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Player {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public final String name;
    private BigInteger money;
    private BigInteger betCount;

    public Player(String name, BigInteger money, BigInteger betCount) {
        this.name = name;
        this.money = money;
        this.betCount = betCount;
    }

    @Override
    public String toString() {
        return name;
    }

    public void wins(BigInteger amount) {
        betCount.add(BigInteger.ONE);
        money.add(amount);

        logger.debug("{} won {}", name, amount);
    }

    public void loses(BigInteger amount) {
        if (money.compareTo(amount) < 0) {
            throw new IllegalArgumentException("Player can't place bet, would ran out of money");
        }

        money.subtract(amount);
        logger.debug("{} lost {}", name, amount);
    }
}
