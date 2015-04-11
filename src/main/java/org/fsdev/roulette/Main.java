package org.fsdev.roulette;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import org.fsdev.roulette.domain.RouletteBoard;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = "org.fsdev.roulette")
public class Main {

    @Bean
    public ScheduledExecutorService scheduledExecutorService() {
        return Executors.newScheduledThreadPool(1);
    }

    @Bean
    public InputStream consoleInput() {
        return System.in;
    }

    @Bean
    public PrintStream consoleOutput() {
        return System.out;
    }

    @Bean
    public RouletteBoard rouletteBoard() {
        return new RouletteBoard();
    }

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}
