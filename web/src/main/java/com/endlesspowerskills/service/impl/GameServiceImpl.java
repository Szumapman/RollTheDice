package com.endlesspowerskills.service.impl;

import com.endlesspowerskills.Game;
import com.endlesspowerskills.MessageGenerator;
import com.endlesspowerskills.service.GameService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class GameServiceImpl implements GameService {

    // -- fields
    private final Game game;
    private final MessageGenerator messageGenerator;

    // -- constructors
    @Autowired
    public GameServiceImpl(Game game, MessageGenerator messageGenerator) {
        this.game = game;
        this.messageGenerator = messageGenerator;
    }

    // -- init
//    @PostConstruct
//    public void init(){
//        int i = 1;
//        for(int diceNumber : game.getDice()){
//            log.info("Dice number {} = {}", i++, diceNumber);
//        }
//        log.info("Number = {}", game.getNumber());
//    }

    @Override
    public boolean isGameOver() {
        return game.isGameWon() || game.isGameLost();
    }

    @Override
    public String getMainMessage() {
        return messageGenerator.getMainMessage();
    }

    @Override
    public String getResultMessage() {
        return messageGenerator.getResultMessage();
    }

    @Override
    public int[] getDice() {
        return game.getDice();
    }

    @Override
    public void checkGuess(int guess) {
        game.setGuess(guess);
        game.check();
    }

    @Override
    public void reset() {
        game.reset();
    }
}
