package io.typeblitz.service;

public interface TerminalService extends AutoCloseable {

    void initialize();

    int readInput(long timeoutMillis);

    void renderFrame(TypingSession session);

    void printLine(String message);

}
