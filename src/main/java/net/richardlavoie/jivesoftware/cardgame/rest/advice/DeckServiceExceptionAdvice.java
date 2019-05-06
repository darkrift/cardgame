package net.richardlavoie.jivesoftware.cardgame.rest.advice;

import net.richardlavoie.jivesoftware.cardgame.service.deck.exception.DeckNotFoundException;
import net.richardlavoie.jivesoftware.cardgame.service.game.exception.GameNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Class which is responsible to translate deck service exceptions into REST error response.
 */
@ControllerAdvice
public class DeckServiceExceptionAdvice {

    @ResponseBody
    @ExceptionHandler(GameNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String gameNotFoundHandler(DeckNotFoundException ex) {
        return ex.getMessage();
    }

}
