package com.endlesspowerskills.impl;

import com.endlesspowerskills.Game;
import com.endlesspowerskills.MessageGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MessageGeneratorImpl implements MessageGenerator {

    // -- constants
    private static final String MAIN_MESSAGE = "game.main.message";
    private static final String GAME_WIN = "game.win";
    private static final String GAME_LOSE = "game.lose";
    private static final String GAME_INVALID_RANGE = "game.invalid.range";
    private static final String GAME_FIRST_GUESS = "game.first.guess";
    private static final String LOWER = "game.lower";
    private static final String HIGHER = "game.higher";
    private static final String GAME_REMAINING = "game.remaining";
    private static final String GAME_RESTART = "game.restart";

    // -- fields
    private final Game game;
    private final MessageSource messageSource;

    // -- constructors
    public MessageGeneratorImpl(Game game, MessageSource messageSource) {
        this.game = game;
        this.messageSource = messageSource;
    }

    // -- init
    public void init(){
        log.info("Game = {}", game) ;
    }

    // -- public methods
    @Override
    public String getMainMessage() {
        return getMessage(MAIN_MESSAGE, game.getDiceQuantity(), game.getMinNumberOfSpots(), game.getMaxNumberOfSpots(), game.getSmallest(), game.getBiggest());
    }

    @Override
    public String getResultMessage() {
        if(game.isGameWon()){
            return getMessage(GAME_WIN, game.getNumber());
        } else if(game.isGameLost()){
            return getMessage(GAME_LOSE, game.getNumber());
        } else if(!game.isValidNumberRange()){
            return getMessage(GAME_INVALID_RANGE, game.getSmallest(), game.getBiggest());
        } else if(game.getRemainingGuesses() == game.getGuessCount()){
            return getMessage(GAME_FIRST_GUESS);
        } else {
            String direction = getMessage(LOWER);
            if(game.getGuess() < game.getNumber()){
                direction = getMessage(HIGHER);
            }
            return getMessage(GAME_REMAINING, direction, game.getRemainingGuesses());
        }
    }

    @Override
    public String getPlayAgainMessage() {
        return getMessage(GAME_RESTART);
    }

    // -- private methods
    private String getMessage(String code, Object... args){
        return messageSource.getMessage(code, args, LocaleContextHolder.getLocale());
    }
}
