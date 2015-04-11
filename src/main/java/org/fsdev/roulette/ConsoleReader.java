package org.fsdev.roulette;

import java.io.InputStream;
import java.io.PrintStream;
import java.math.BigInteger;
import java.util.Scanner;
import javax.annotation.PostConstruct;

import org.fsdev.roulette.domain.Bet;
import org.fsdev.roulette.domain.FieldBet;
import org.fsdev.roulette.domain.Parity;
import org.fsdev.roulette.domain.ParityBet;
import org.fsdev.roulette.domain.Player;
import org.fsdev.roulette.domain.RouletteBoard;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

@Component
@DependsOn("playerLoader")
public class ConsoleReader {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RouletteBoard rouletteBoard;

    @Autowired
    private InputStream consoleInput;

    @Autowired
    private PrintStream consoleOutput;

    @PostConstruct
    public void parseBets() {
        consoleOutput.println("Place a bet in the format: '<name> <field> <amount>'");
        try (Scanner scanner = new Scanner(consoleInput)) {
            while (scanner.hasNextLine()) {
                try {
                    rouletteBoard.placeBet(parseBet(scanner.nextLine()));
                } catch (Exception e) {
                    logger.warn("invalid user input", e);
                    consoleOutput.println("Place a bet in the format: '<name> <field> <amount>'");
                }
            }
        }
    }

    private Bet parseBet(String input) {
        String[] parts = input.split("\\s");
        Player player = rouletteBoard.getPlayer(parts[0]);
        BigInteger amount = new BigInteger(parts[2]);

        if (parts[1].equals(Parity.EVEN.name())) {
            return new ParityBet(player, amount, Parity.EVEN);

        } else if (parts[1].equals(Parity.ODD.name())) {
            return new ParityBet(player, amount, Parity.ODD);

        } else {
            int fieldNumber = Integer.parseInt(parts[1]);
            return new FieldBet(player, amount, rouletteBoard.getField(fieldNumber));
        }
    }
}
