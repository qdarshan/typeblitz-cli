package io.typeblitz.commands;

import io.typeblitz.service.GameEngine;
import jakarta.inject.Inject;
import picocli.CommandLine;

@CommandLine.Command(name = "start", description = "start type-blitz session")
public class StartCommand implements Runnable {

    @Inject
    GameEngine gameEngine;

    @CommandLine.Option(names = {"-t", "--time"}, description = "Duration in seconds", defaultValue = "60")
    int timeSeconds;

    @Override
    public void run() {

        String sampleText = "The quick brown fox jumps over the lazy dog";
        gameEngine.startSession(sampleText, timeSeconds * 1000L);
    }
}