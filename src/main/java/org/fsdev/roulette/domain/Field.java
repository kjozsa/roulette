package org.fsdev.roulette.domain;

public class Field {
    private final int number;
    private final Parity parity;

    public Field(int number, Parity parity) {
        this.number = number;
        this.parity = parity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Field field = (Field) o;

        return number == field.number;
    }

    @Override
    public int hashCode() {
        return number;
    }

    public Parity getParity() {
        return parity;
    }
}
