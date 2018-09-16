package ru.bogdanov.learner.exception;

/**
 * Denis, 16.09.2018
 */
public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }
}
