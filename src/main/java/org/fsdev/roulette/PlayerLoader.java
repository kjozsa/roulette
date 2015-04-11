package org.fsdev.roulette;

import java.math.BigInteger;
import java.util.Scanner;
import javax.annotation.PostConstruct;

import org.fsdev.roulette.domain.Player;
import org.fsdev.roulette.domain.RouletteBoard;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

@Component
public class PlayerLoader {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("classpath:${player.definition.file}")
    private Resource playersFile;

    @Autowired
    private RouletteBoard rouletteBoard;

    @PostConstruct
    public void loadPlayersFile() {
        logger.info("loading players from {}", playersFile.getFilename());

        try (Scanner scanner = new Scanner(playersFile.getInputStream())) {
            while (scanner.hasNextLine()) {
                String[] tokens = scanner.nextLine().split(",");
                Player player = new Player(tokens[0], new BigInteger(tokens[1]), new BigInteger(tokens[2]));
                rouletteBoard.playerArrived(player);
            }
        } catch (Exception e) {
            throw new RuntimeException("failed to load players file " + playersFile.getFilename(), e);
        }
    }
}
