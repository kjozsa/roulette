package org.fsdev.roulette.domain;

import java.util.Set;

import com.google.common.collect.Sets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RouletteBoard {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private Set<Player> players = Sets.newHashSet();

    public void playerArrived(Player player) {
        players.add(player);
        logger.info("player arrived " + player);
    }
}
