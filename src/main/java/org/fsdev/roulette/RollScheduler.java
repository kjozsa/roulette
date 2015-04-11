package org.fsdev.roulette;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.annotation.PostConstruct;

import org.fsdev.roulette.domain.RouletteBoard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

@Component
@DependsOn("playerLoader")
public class RollScheduler implements Runnable {
    @Value("${winning.pocket.announcement.period.sec}")
    private long announcePeriod;

    @Autowired
    private ScheduledExecutorService executorService;

    @Autowired
    private RouletteBoard rouletteBoard;

    @PostConstruct
    public void scheduleAnnouncer() {
        executorService.scheduleWithFixedDelay(this, announcePeriod, announcePeriod, TimeUnit.SECONDS);
    }

    @Override
    public void run() {
        rouletteBoard.roll();
    }
}
