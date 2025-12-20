package io.typeblitz.commands;

import io.typeblitz.convertors.DifficultyConverter;
import io.typeblitz.model.enums.Difficulty;
import io.typeblitz.service.GameEngine;
import io.typeblitz.service.provider.TextProvider;
import io.typeblitz.service.provider.TextProviderFactory;
import jakarta.inject.Singleton;
import picocli.CommandLine;

@Singleton
@CommandLine.Command(
        name = "start",
        description = "start type-blitz session",
        mixinStandardHelpOptions = true,
        showDefaultValues = true)
public class StartCommand implements Runnable {

    @CommandLine.Option(
            names = {"-t", "--time"},
            description = "Duration in seconds",
            defaultValue = "60")
    int timeSeconds;

    @CommandLine.Option(
            names = {"-d", "--difficulty"},
            description = "Difficulty level: ${COMPLETION-CANDIDATES}",
            defaultValue = "EASY",
            converter = DifficultyConverter.class)
    Difficulty difficulty;

    private final GameEngine gameEngine;
    private final TextProviderFactory textProviderFactory;

    public StartCommand(GameEngine gameEngine, TextProviderFactory textProviderFactory) {
        this.gameEngine = gameEngine;
        this.textProviderFactory = textProviderFactory;
    }

    @Override
    public void run() {

        TextProvider textProvider = textProviderFactory.getTextProvider(difficulty);

        String sampleText = textProvider.generateText(100);
        gameEngine.startSession(sampleText, timeSeconds * 1000L);
    }
}