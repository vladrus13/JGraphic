package ru.vladrus13.jgraphic.exception;

/**
 * @author vladkuznetsov
 * Exception class for game
 */
public class AppException extends Exception {
    /**
     * Constructor for exception
     *
     * @param s message
     */
    public AppException(String s) {
        super(s);
    }

    /**
     * Constructor with exception
     *
     * @param s message
     * @param e suppressed exception
     */
    public AppException(String s, Exception e) {
        super(s, e);
    }

    /**
     * Constructor with just exception
     *
     * @param e suppressed exception
     */
    public AppException(Exception e) {
        super(e);
    }
}
