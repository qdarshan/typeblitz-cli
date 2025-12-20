package io.typeblitz.service.provider;

import jakarta.inject.Named;
import jakarta.inject.Singleton;

import java.util.List;

/*
Easy: Basic Vocabulary — sentences with common words. Lowercase only. No punctuation.
 */

@Singleton
@Named("easy")
public class EasyTextProvider implements TextProvider{

    private static final List<String> SENTENCES = List.of(
            "the quick brown fox jumps over the lazy dog",
            "a big red car is in the street",
            "i like to eat apples and bananas",
            "the sun is bright today",
            "we play games in the park",
            "my cat sleeps on the chair",
            "she reads a book every night",
            "he drinks water from a glass",
            "they walk to school together",
            "it is a beautiful day"
    );

    @Override
    public String generateText(long targetLength) {
        return SENTENCES.get((int) (Math.random() * SENTENCES.size()));
    }
}
