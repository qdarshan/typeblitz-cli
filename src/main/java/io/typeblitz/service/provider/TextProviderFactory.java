package io.typeblitz.service.provider;

import io.typeblitz.model.enums.Difficulty;
import jakarta.inject.Named;
import jakarta.inject.Singleton;

/*
Easy: Basic Vocabulary — Top 200 common words. Lowercase only. No punctuation.
Medium: Standard Prose — Complete sentences with capitalization and punctuation.
Hard: Technical Mode — Complex strings with numbers, symbols, and mixed case.
 */

@Singleton
public class TextProviderFactory {

    private final TextProvider easyProvider;
    private final TextProvider mediumProvider;
    private final TextProvider hardProvider;

    public TextProviderFactory(
            @Named("easy") TextProvider easyProvider,
            @Named("medium") TextProvider mediumProvider,
            @Named("hard") TextProvider hardProvider) {
        this.easyProvider = easyProvider;
        this.mediumProvider = mediumProvider;
        this.hardProvider = hardProvider;
    }

    public TextProvider getTextProvider(Difficulty difficulty) {
        return switch (difficulty) {
            case EASY -> easyProvider;
            case MEDIUM -> mediumProvider;
            case HARD -> hardProvider;
        };
    }
}
