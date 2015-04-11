package org.fsdev.roulette.domain;

import java.util.Map;
import java.util.Set;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RouletteBoard {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private Set<Player> players = Sets.newHashSet();

    private Map<Integer, Field> fields = Maps.newHashMap();
    private Set<Bet> bets = Sets.newHashSet();

    public RouletteBoard() {
        fields.put(0, new Field(0, Parity.ZERO));
        for (int i = 1; i <= 36; i++) {
            fields.put(i, new Field(i, i % 2 == 0 ? Parity.EVEN : Parity.ODD));
        }
    }

    public void playerArrived(Player player) {
        players.add(player);
        logger.info("player arrived " + player);
    }

    public void placeBet(Bet bet) {
        bets.add(bet);
    }

    public void clearBets() {
        bets.clear();
    }

    public void roll() {
        Integer winningNumber = (int) (Math.random() * 36 + 1);
        bets.stream().forEach((bet) -> bet.player.wins(bet.amountWon(fields.get(winningNumber))));
    }
}
