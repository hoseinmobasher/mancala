package com.bol.mancala.exceptions;

import com.bol.mancala.exceptions.types.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Centralized controller raised exceptions
 */
@RestControllerAdvice
public class GameExceptionHandler extends ResponseEntityExceptionHandler {
    /**
     * When a game is not found.
     * @return a response entity with status of 404
     */
    @ExceptionHandler(GameNotFoundException.class)
    public ResponseEntity<Object> handleGameNotFoundException() {
        return ResponseEntity.notFound().build();
    }

    /**
     * When an empty pit is selected.
     * @return a response entity with status of 400 with desired message.
     */
    @ExceptionHandler(EmptyPitException.class)
    public ResponseEntity<FailureResponse> handleEmptyPitException() {
        return ResponseEntity.badRequest()
                .body(new FailureResponse("You can't choose a pit with no stone."));
    }

    /**
     * When game status is not valid.
     * @param e exception which is thrown.
     * @return a response entity with status of 400 with a message contains current state.
     */
    @ExceptionHandler(IllegalGameStateException.class)
    public ResponseEntity<FailureResponse> handleIllegalGameStateException(IllegalGameStateException e) {
        return ResponseEntity.badRequest()
                .body(new FailureResponse("You can't do any action in this state: %s".formatted(e.getState())));
    }

    /**
     * In case of playing in invalid turn.
     * @param e exception which is thrown.
     * @return a response entity with status of 400 with a message contains current turn.
     */
    @ExceptionHandler(IncorrectTurnException.class)
    public ResponseEntity<FailureResponse> handleIncorrectTurnException(IncorrectTurnException e) {
        return ResponseEntity.badRequest()
                .body(new FailureResponse("It is %s's turn.".formatted(e.getTurn())));
    }

    /**
     * Player pick the big pit.
     * @return a response entity with status of 400 with desired message.
     */
    @ExceptionHandler(InvalidPitException.class)
    public ResponseEntity<FailureResponse> handleInvalidPitException() {
        return ResponseEntity.badRequest()
                .body(new FailureResponse("Start from big pit is illegal."));
    }
}
