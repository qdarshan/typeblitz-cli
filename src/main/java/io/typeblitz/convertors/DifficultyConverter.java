package io.typeblitz.convertors;

import io.typeblitz.model.enums.Difficulty;
import picocli.CommandLine;

public class DifficultyConverter implements CommandLine.ITypeConverter<Difficulty> {
    @Override
    public Difficulty convert(String value) {
        return Difficulty.valueOf(value.toUpperCase());
    }
}