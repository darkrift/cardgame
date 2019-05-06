package net.richardlavoie.jivesoftware.cardgame.rest.advice;

import net.richardlavoie.jivesoftware.cardgame.service.game.exception.GameDeletionErrorException;
import net.richardlavoie.jivesoftware.cardgame.service.game.exception.GameNotFoundException;
import net.richardlavoie.jivesoftware.cardgame.service.game.exception.PlayerNotFoundException;
import net.richardlavoie.jivesoftware.cardgame.service.game.exception.TooManyGamesFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Class which is responsible to translate game service exceptions into REST error response.
 */
@ControllerAdvice
public class GameServiceExceptionAdvice {

    @ResponseBody
    @ExceptionHandler(GameNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String gameNotFoundHandler(GameNotFoundException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(PlayerNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String gameNotFoundHandler(PlayerNotFoundException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(TooManyGamesFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String tooManyGamesFoundHandler(TooManyGamesFoundException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(GameDeletionErrorException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String gameNotFoundHandler(GameDeletionErrorException ex) {
        return ex.getMessage();
    }

}
