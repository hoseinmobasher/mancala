package com.bol.mancala.exceptions;

/**
 * In case of failure this response-style should be sent.
 *
 * @param message the failure response should include this message.
 */
public record FailureResponse(Object message) {

}
