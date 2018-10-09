package com.endlesspowerskills.console;

import com.endlesspowerskills.Game;
import com.endlesspowerskills.MessageGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Slf4j
@Component
public class ConsoleRollTheDiceGame {

    // -- fields
    private final Game game;
    private final MessageGenerator messageGenerator;

    // constructors
    public ConsoleRollTheDiceGame(Game game, MessageGenerator messageGenerator) {
        this.game = game;
        this.messageGenerator = messageGenerator;
    }

    // events
    @EventListener(ContextRefreshedEvent.class)
    public void start(){
        log.info("-- start() -> container ready to use --");

        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.println(messageGenerator.getMainMessage());
            System.out.println(messageGenerator.getResultMessage());

            int guess = scanner.nextInt();
            scanner.nextLine();
            game.setGuess(guess);
            game.check();

            if(game.isGameWon() || game.isGameLost()){
                System.out.println(messageGenerator.getResultMessage());
                System.out.println(messageGenerator.getPlayAgainMessage() + " y/n?");

                String playAgain = scanner.nextLine().trim();
                if(!playAgain.equalsIgnoreCase("y")){
                    break;
                }
                game.reset();
            }
        }
    }
}
