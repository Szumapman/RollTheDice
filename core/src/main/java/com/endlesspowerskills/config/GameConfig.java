package com.endlesspowerskills.config;

import com.endlesspowerskills.DiceQuantity;
import com.endlesspowerskills.GuessCount;
import com.endlesspowerskills.MaxNumberOfSpots;
import com.endlesspowerskills.MinNumberOfSpots;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan(basePackages = "com.endlesspowerskills")
@PropertySource("classpath:config/game.properties")
public class GameConfig {

    // -- fields
    @Value("${game.maxNumberOfSpots:12}")
    private int maxNumberOfSpots;

    @Value("${game.minNumberOfSpots:2}")
    private int minNumberOfSpots;

    @Value("${game.diceQuantity:2}")
    private int diceQuantity;

    @Value("${game.guessCount:4}")
    private int guessCount;

    // -- bean methods
    @Bean
    @MaxNumberOfSpots
    public int maxNumberOfSpots(){
        return maxNumberOfSpots;
    }

    @Bean
    @MinNumberOfSpots
    public int minNumberOfSpots(){
        return minNumberOfSpots;
    }

    @Bean
    @DiceQuantity
    public int diceQuantity(){
        return diceQuantity;
    }

    @Bean
    @GuessCount
    public int guessCount(){
        return guessCount;
    }
}
