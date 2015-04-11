package org.fsdev.roulette;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

@Component
@DependsOn("loader")
public class Announcer implements Runnable {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${winning.pocket.announcement.period.sec}")
    private long announcePeriod;

    @Autowired
    private ScheduledExecutorService executorService;

    @PostConstruct
    public void scheduleAnnouncer() {
        executorService.scheduleWithFixedDelay(this, announcePeriod, announcePeriod, TimeUnit.SECONDS);
    }

    @Override
    public void run() {
        logger.info("# winning number is " + (int) (Math.random() * 36 + 1));
    }
}
