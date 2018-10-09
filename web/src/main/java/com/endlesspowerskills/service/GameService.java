package com.endlesspowerskills.service;

public interface GameService {
    boolean isGameOver();
    String getMainMessage();
    String getResultMessage();
    int[] getDice();
    void checkGuess(int guess);
    void reset();
}
