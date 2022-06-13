package ru.dark.stream.model;

public enum Separator {
    SEPARATOR("#@#");

    private String value;

    private Separator(String v) {
        value = v;
    }

    @Override
    public String toString() {
        return value;
    }
}
