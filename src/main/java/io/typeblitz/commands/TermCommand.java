package io.typeblitz.commands;

import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;
import picocli.CommandLine;

import java.io.IOException;
import java.util.List;

@CommandLine.Command(name = "term")
public class TermCommand implements Runnable {
    @Override
    public void run() {
        try (Terminal terminal = TerminalBuilder.builder().system(true).build()) {


            List<String> sentences = List.of(
                    "The quick brown fox jumps over the lazy dog",
                    "Pack my box with five dozen liquor jugs",
                    "Sphinx of black quartz judge my vow"
            );

            for (String sentence : sentences) {
                terminal.writer().print("\u001B[90m" + sentence + "\u001B[0m");
                terminal.writer().print("\r");
                terminal.flush();

                terminal.enterRawMode();
                int index = 0;
                while (index < sentence.length()) {
                    int input = terminal.reader().read();

                    if (input == 127) {
                        continue;
                    }

                    char typedChar = (char) input;
                    char expectedChar = sentence.charAt(index);

                    if (typedChar == expectedChar) {
                        terminal.writer().print("\u001B[32m" + typedChar + "\u001B[0m");
                    } else {
                        terminal.writer().print("\u001B[31m" + typedChar + "\u001B[0m");
                    }
                    terminal.flush();
                    index++;
                }
                terminal.writer().println();
            }

            terminal.writer().println("Goodbye!");
        } catch (IOException e) {
            System.err.println("Error creating terminal: " + e.getMessage());
        }
    }
}
