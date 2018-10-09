package com.endlesspowerskills.impl;

import com.endlesspowerskills.Game;
import com.endlesspowerskills.GuessCount;
import com.endlesspowerskills.RollDiceGenerator;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Slf4j
@Getter
@Component
public class GameImpl implements Game {

    // -- fields
    @Getter(AccessLevel.NONE)
    private final RollDiceGenerator rollDiceGenerator;

    private final int guessCount;

    private int[] dice;
    private int diceQuantity;
    private int minNumberOfSpots;
    private int maxNumberOfSpots;
    private int number;
    private int smallest;
    private int biggest;
    private int remainingGuesses;
    private boolean validNumberRange = true;

    @Setter
    private int guess;

    // -- constructors
    @Autowired
    public GameImpl(RollDiceGenerator rollDiceGenerator, @GuessCount int guessCount) {
        this.rollDiceGenerator = rollDiceGenerator;
        this.guessCount = guessCount;
    }

    // -- init
    @PostConstruct
    @Override
    public void reset() {
        dice = rollDiceGenerator.rollTheDice();
        number = 0;
        for (int die : dice){
            number += die;
        }
        diceQuantity = rollDiceGenerator.getDiceQuantity();
        minNumberOfSpots = rollDiceGenerator.getMinNumberOfSpots();
        maxNumberOfSpots = rollDiceGenerator.getMaxNumberOfSpots();
        smallest = minNumberOfSpots * diceQuantity;
        biggest = maxNumberOfSpots * diceQuantity;
        remainingGuesses = guessCount;
        log.debug("-- The number is: {} --", number);
    }

    // -- public methods
    @Override
    public void check() {
        checkValidNumberRange();
        if(validNumberRange){
            if(guess > number){
                biggest = guess - 1;
            }
            if(guess < number){
                smallest = guess + 1;
            }
            remainingGuesses--;
        }
    }

    @Override
    public boolean isGameWon() {
        return guess == number;
    }

    @Override
    public boolean isGameLost() {
        return !isGameWon() && remainingGuesses <= 0;
    }

    // -- private methods
    private void checkValidNumberRange() {
        validNumberRange = (guess >= smallest) && (guess <= biggest);
    }
}
