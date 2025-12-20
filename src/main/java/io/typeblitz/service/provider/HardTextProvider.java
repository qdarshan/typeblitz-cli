package io.typeblitz.service.provider;

import jakarta.inject.Named;
import jakarta.inject.Singleton;

import java.util.List;

/*
Hard: Technical Mode — Complex strings with numbers, symbols, and mixed case.
 */

@Singleton
@Named("hard")
public class HardTextProvider implements TextProvider{

    private static final List<String> SENTENCES = List.of(
            "The quick brown fox jumps over the lazy dog 5 times a day!",
            "Pack my box with five dozen liquor jugs, and don't forget the 12 bottles of wine.",
            "How vexingly quick daft zebras jump over the big fjords, especially at 3 AM.",
            "Bright vixens jump; do crazy frogs quack with glee? Yes, 7 times!",
            "The 19th century saw a revolution in technology, including steam engines & telegraphs.",
            "\"Sphinx of black quartz, judge my vow!\" - a classic pangram with 29 letters.",
            "J. K. Rowling's 'Harry Potter' series sold over 500 million copies worldwide by 2018.",
            "The ISBN-13 for 'The Lord of the Rings' is 978-0-618-05326-7, published by Houghton Mifflin Harcourt.",
            "Can you solve this: (2 + 3) * 4 - 10 / 2 = ? The answer is 15.",
            "The IPv6 address '2001:0db8:85a3:0000:0000:8a2e:0370:7334' is a common example."

    );

    @Override
    public String generateText(long targetLength) {
//        return SENTENCES.get((int) (Math.random() * SENTENCES.size()));
        return SENTENCES.get(5);
    }
}
