package com.endlesspowerskills.impl;

import com.endlesspowerskills.RollDiceGenerator;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Random;

@Slf4j
@Getter
@Component
public class RollDiceGeneratorImpl implements RollDiceGenerator {

    // -- fields
    @Getter(AccessLevel.NONE)
    private final Random random = new Random();

    private final int diceQuantity;
    private final int maxNumberOfSpots;
    private final int minNumberOfSpots;

    @Getter(AccessLevel.NONE)
    private final int[] diceRollResults;

    // -- constructors
    @Autowired
    public RollDiceGeneratorImpl(int diceQuantity, int maxNumberOfSpots, int minNumberOfSpots) {
        this.diceQuantity = diceQuantity;
        this.maxNumberOfSpots = maxNumberOfSpots;
        this.minNumberOfSpots = minNumberOfSpots;
        this.diceRollResults = new int[this.diceQuantity];
    }

    @Override
    public int[] rollTheDice() {
        for (int i = 0; i < diceRollResults.length; i++){
            diceRollResults[i] = random.nextInt(maxNumberOfSpots - minNumberOfSpots) + minNumberOfSpots;
            log.info("Dice number {} = {}", (i+1), diceRollResults[i]);
        }
        return diceRollResults;
    }
}
