package org.fsdev.roulette.domain;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.locks.ReentrantLock;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RouletteBoard {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private Map<String, Player> players = Maps.newHashMap();
    private Map<Integer, Field> fields = Maps.newHashMap();
    private Set<Bet> bets = Sets.newHashSet();

    private ReentrantLock lock;

    public RouletteBoard() {
        fields.put(0, new Field(0, Parity.ZERO));
        for (int i = 1; i <= 36; i++) {
            fields.put(i, new Field(i, i % 2 == 0 ? Parity.EVEN : Parity.ODD));
        }
    }

    public void playerArrived(Player player) {
        players.put(player.name, player);
        logger.info("player arrived " + player);
    }

    public void placeBet(Bet bet) {
        bet.player.loses(bet.amount);
        bets.add(bet);
        logger.info("Placed {}", bet);
    }

    public void roll() {
        Integer winningNumber = (int) (Math.random() * 36 + 1);
        logger.info("winning number is {}", winningNumber);

        bets.stream().forEach((bet) -> bet.player.wins(bet.amountWon(fields.get(winningNumber))));
        bets.clear();
    }

    public Player getPlayer(String name) {
        Player player = players.get(name);
        if (player == null) {
            throw new IllegalArgumentException("No such player");
        }
        return player;
    }

    public Field getField(Integer fieldNumber) {
        Field field = fields.get(fieldNumber);
        if (field == null) {
            throw new IllegalArgumentException("No such field");
        }
        return field;
    }
}
