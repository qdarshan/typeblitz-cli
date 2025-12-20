package io.typeblitz.service.provider;

import jakarta.inject.Named;
import jakarta.inject.Singleton;

import java.util.List;

/*
Medium: Standard Prose — Complete sentences with capitalization and punctuation.
 */

@Singleton
@Named("medium")
public class MediumTextProvider implements TextProvider {

    private static final List<String> SENTENCES = List.of(
            "The quick brown fox jumps over the lazy dog.",
            "Never underestimate the power of a good book.",
            "The early bird catches the worm, or so they say.",
            "Technology has revolutionized the way we live and work.",
            "The sun always shines brightest after the rain.",
            "A journey of a thousand miles begins with a single step.",
            "Innovation is the key to progress in any field.",
            "The mountains are calling, and I must go.",
            "Kindness is a language that everyone can understand.",
            "The future belongs to those who believe in the beauty of their dreams."
    );

    @Override
    public String generateText(long targetLength) {
        return SENTENCES.get((int) (Math.random() * SENTENCES.size()));
    }
}
