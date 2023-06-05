package com.bol.mancala.exceptions;

import com.bol.mancala.exceptions.types.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GameExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(GameNotFoundException.class)
    public ResponseEntity<Object> handleGameNotFoundException() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(EmptyPitException.class)
    public ResponseEntity<FailureResponse> handleEmptyPitException() {
        return ResponseEntity.badRequest()
                .body(new FailureResponse("You can't choose a pit with no stone."));
    }

    @ExceptionHandler(IllegalGameStateException.class)
    public ResponseEntity<FailureResponse> handleIllegalGameStateException(IllegalGameStateException e) {
        return ResponseEntity.badRequest()
                .body(new FailureResponse("You can't do any action in this state: %s".formatted(e.getState())));
    }

    @ExceptionHandler(IncorrectTurnException.class)
    public ResponseEntity<FailureResponse> handleIncorrectTurnException(IncorrectTurnException e) {
        return ResponseEntity.badRequest()
                .body(new FailureResponse("It is %s's turn.".formatted(e.getTurn())));
    }

    @ExceptionHandler(InvalidPitException.class)
    public ResponseEntity<FailureResponse> handleInvalidPitException() {
        return ResponseEntity.badRequest()
                .body(new FailureResponse("Start from big pit is illegal."));
    }
}
