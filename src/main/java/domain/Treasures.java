package domain;

import java.util.Objects;

public class Treasures extends Content {

    private final int size;

    public Treasures(int size) {
        this.size = size;
    }

    public int getSize() {
        return size;
    }

    @Override
    protected boolean isOccupied() {
        return false;
    }

    @Override
    public char getType() {
        return 'T';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Treasures treasures = (Treasures) o;
        return size == treasures.size;
    }

    @Override
    public int hashCode() {
        return Objects.hash(size);
    }
}
