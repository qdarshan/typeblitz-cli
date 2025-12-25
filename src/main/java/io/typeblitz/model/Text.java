package io.typeblitz.model;

import io.micronaut.serde.annotation.Serdeable;
import io.typeblitz.model.enums.Difficulty;

@Serdeable
public class Text {
    private String sentence;
    private long length;
    private Difficulty difficulty;

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public long getLength() {
        return length;
    }

    public void setLength(long length) {
        this.length = length;
    }

    public String getSentence() {
        return sentence;
    }

    public void setSentence(String sentence) {
        this.sentence = sentence;
    }

}
