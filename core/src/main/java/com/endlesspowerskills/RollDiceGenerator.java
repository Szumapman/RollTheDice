package com.endlesspowerskills;

public interface RollDiceGenerator {
    int getDiceQuantity();
    int getMaxNumberOfSpots();
    int getMinNumberOfSpots();
    int[] rollTheDice();

}
