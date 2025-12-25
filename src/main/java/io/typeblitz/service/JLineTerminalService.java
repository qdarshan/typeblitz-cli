package io.typeblitz.service;

import jakarta.inject.Singleton;
import org.jline.terminal.Attributes;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;
import org.jline.utils.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

@Singleton
public class JLineTerminalService implements TerminalService {

    private static final Logger log = LoggerFactory.getLogger(JLineTerminalService.class);
    private Terminal terminal;
    private NonBlockingReader reader;

    public static final int READ_EXPIRED = -2;

    @Override
    public void initialize() {
        try {
            this.terminal = TerminalBuilder.builder()
                    .system(true)
                    .nativeSignals(true)
                    .build();

            this.terminal.enterRawMode();

            Attributes attributes = terminal.getAttributes();
            attributes.setLocalFlag(Attributes.LocalFlag.ISIG, false);
            this.terminal.setAttributes(attributes);

            this.reader = terminal.reader();
            this.terminal.puts(InfoCmp.Capability.cursor_invisible);

        } catch (IOException e) {
            throw new RuntimeException("Failed to initialize the terminal.", e);
        }

    }

    @Override
    public int readInput(long timeoutMillis) {
        try {
            return reader.read(timeoutMillis);
        } catch (IOException e) {
            throw new RuntimeException("Error reading the input.", e);
        }
    }
    @Override
    public void renderFrame(TypingSession session) {
        terminal.puts(InfoCmp.Capability.cursor_home);

        String targetText = session.getTargetText();
        int currentIndex = session.getCurrentIndex();
        boolean[] correctOnFirstAttempt = session.getCorrectOnFirstAttempt();
        int[] errorsPerPosition = session.getErrorsPerPosition();

        long timeLeft = session.getTimeLimitInMillis() / 1000;
        int wpm = session.calculateWPM();
        int accuracy = session.calculateAccuracy();

        terminal.writer().print(String.format("[Time: %ds] [WPM: %d] [Accuracy: %d%%]", timeLeft, wpm, accuracy));
        terminal.puts(InfoCmp.Capability.clr_eol);
        terminal.writer().print("\n");

        AttributedStringBuilder attributedStringBuilder = new AttributedStringBuilder();

        for (int i = 0; i < targetText.length(); i++) {
            char expectedChar = targetText.charAt(i);
            if (i < currentIndex) {
                if (correctOnFirstAttempt[i]) {
                    attributedStringBuilder.style(AttributedStyle.DEFAULT.foreground(AttributedStyle.GREEN));
                } else {
                    attributedStringBuilder.style(AttributedStyle.DEFAULT.foreground(AttributedStyle.RED));
                }
            } else if (i == currentIndex) {
                if (errorsPerPosition[i] > 0) {
                    attributedStringBuilder.style(AttributedStyle.BOLD.foreground(AttributedStyle.YELLOW).underline().blink());
                } else {
                    attributedStringBuilder.style(AttributedStyle.DEFAULT.foreground(AttributedStyle.WHITE).underline());
                }
            } else {
                attributedStringBuilder.style(AttributedStyle.DEFAULT.foreground(AttributedStyle.BRIGHT).faint());
            }
            attributedStringBuilder.append(expectedChar);
        }

        terminal.writer().print(attributedStringBuilder.toAnsi(terminal));
        terminal.puts(InfoCmp.Capability.clr_eos);
        terminal.flush();
    }

    @Override
    public void printLine(String message) {
        terminal.writer().println(message);
        terminal.flush();
    }

    @Override
    public void close() {
        if (terminal != null) {
            terminal.puts(InfoCmp.Capability.cursor_normal);
            terminal.flush();
            try {
                terminal.close();
            } catch (IOException e) {
                log.error(e.getMessage());
            }
        }
    }
}
