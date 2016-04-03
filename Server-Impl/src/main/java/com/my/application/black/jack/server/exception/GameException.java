package com.my.application.black.jack.server.exception;

/**
 * Created: Shostak Roman
 * Date: 03.04.2016.
 */
public class GameException extends RuntimeException {
    public GameException(String description) {
        super(description);
    }
}
