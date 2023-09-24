package com.example.trainstation.entities;

import java.util.Arrays;
import java.util.Optional;

/**
 * Типы вагонов
 */
public enum Type {
    AUTOTRACK("AUTOTRACK"),
    BOXCAR("BOXCAR"),
    CENTERBEAM("CENTERBEAM"),
    COVEREDHOPPER("COVEREDHOPPER"),
    COILCAR("COILCAR"),
    FLATCAR("FLATCAR"),
    GONDOLA("GONDOLA"),
    INTERMODALEQUIMENT("INTERMODALEQUIMENT"),
    OPENTOPHOPPER("OPENTOPHOPPER"),
    REFRIGERATEDBOXCAR("REFRIGERATEDBOXCAR"),
    TANKCAR("TANKCAR");


    private final String name;

    private Type(String s) {
        name = s;
    }

    public boolean equalsName(String otherName) {
        return name.equals(otherName);
    }

    public String toString() {
        return this.name;
    }
    public static Optional<Type> fromText(String text) {
        return Arrays.stream(values())
                .filter(bl -> bl.name.equalsIgnoreCase(text))
                .findFirst();
    }
}
