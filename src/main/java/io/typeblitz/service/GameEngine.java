package io.typeblitz.service;

import jakarta.inject.Singleton;

@Singleton
public class GameEngine {

    private final TerminalService terminalService;

    public GameEngine(TerminalService terminalService) {
        this.terminalService = terminalService;
    }

    public void startSession(String targetText, long timeLimitInMillis) {
        terminalService.initialize();
        TypingSession session = new TypingSession(targetText, timeLimitInMillis);

        try {
            while (!session.isFinished()) {
                terminalService.renderFrame(session);

                int input = terminalService.readInput(10);

                if (input == JLineTerminalService.READ_EXPIRED) {
                    continue;
                }

                if (input == 3) {
                    break;
                }
                session.handleInput(input);
            }
            terminalService.renderFrame(session);
            showResults(session);
        } catch (Exception e) {
            terminalService.printLine("An error occurred: " + e.getMessage());
        } finally {
            try {
                terminalService.close();
            } catch (Exception ignored) {
            }
        }
    }

    private void showResults(TypingSession session) {
        terminalService.printLine("\n\n--- Session Finished ---");
        if (session.getTimeLimitInMillis() <= 0) {
            terminalService.printLine("Time's Up!");
        } else {
            terminalService.printLine("Completed!");
        }

        terminalService.printLine("Accuracy: " + session.calculateAccuracy() + "%");
    }
}
