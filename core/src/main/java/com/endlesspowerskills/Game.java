package com.endlesspowerskills;

public interface Game {
    int[] getDice();
    int getDiceQuantity();
    int getMinNumberOfSpots();
    int getMaxNumberOfSpots();
    int getNumber();
    int getGuess();
    void setGuess(int guess);
    int getSmallest();
    int getBiggest();
    int getRemainingGuesses();
    int getGuessCount();
    void reset();
    void check();
    boolean isValidNumberRange();
    boolean isGameWon();
    boolean isGameLost();

}
