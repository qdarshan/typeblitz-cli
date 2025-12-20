package io.typeblitz.service;

public class TypingSession {
    private final String targetText;
    private final boolean[] correctOnFirstAttempt;
    private final int[] errorsPerPosition;
    private final StringBuilder inputBuffer;
    private final long startTime;
    private final long timeLimitInMillis;

    private int currentIndex = 0;
    private int errorCount;

    public TypingSession(String targetText, long timeLimitInMillis) {
        this.targetText = targetText;
        this.correctOnFirstAttempt = new boolean[targetText.length()];
        this.errorsPerPosition = new int[targetText.length()];
        this.inputBuffer = new StringBuilder();
        this.startTime = System.currentTimeMillis();
        this.timeLimitInMillis = timeLimitInMillis;
    }

    public void handleInput(int input) {
        if (input == 127 || input == 8 || (Character.isISOControl(input) && input != '\n')) {
            return;
        }

        if (currentIndex < targetText.length()) {
            char typedChar = (char) input;
            char expectedChar = targetText.charAt(currentIndex);

            if (typedChar == expectedChar) {
                if (errorsPerPosition[currentIndex] == 0) {
                    correctOnFirstAttempt[currentIndex] = true;
                }
                inputBuffer.append(typedChar);
                currentIndex++;
            } else {
                correctOnFirstAttempt[currentIndex] = false;
                errorsPerPosition[currentIndex]++;
                errorCount++;
            }
        }
    }

    public boolean isFinished() {
        return inputBuffer.length() == targetText.length() || getTimeLimitInMillis() <= 0;
    }

    public int calculateAccuracy() {
        int totalAttempts = inputBuffer.length() + errorCount;
        if (totalAttempts == 0) {
            return 100;
        }
        return (int) ((inputBuffer.length() / (double) totalAttempts) * 100);
    }

    public int calculateWPM() {
        long elapsedTime = System.currentTimeMillis() - startTime;
        if (elapsedTime == 0) {
            return 0;
        }
        double minutes = elapsedTime / 60000.0;
        double words = inputBuffer.length() / 5.0;
        return (int) (words / minutes);
    }

    public long getTimeLimitInMillis() {
        long elapsedTime = System.currentTimeMillis() - startTime;
        return Math.max(0, timeLimitInMillis - elapsedTime);
    }

    public int getCurrentIndex() {
        return currentIndex;
    }

    public boolean[] getCorrectOnFirstAttempt() {
        return correctOnFirstAttempt;
    }

    public int[] getErrorsPerPosition() {
        return errorsPerPosition;
    }

    public String getTargetText() {
        return targetText;
    }
}
