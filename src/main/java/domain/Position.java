package domain;

import java.util.StringJoiner;

public record Position(int x, int y) {

    public Position move(Orientation orientation) {
        switch (orientation) {
            case E -> {
                return new Position(x + 1, y);
            }
            case S -> {
                return new Position(x, y + 1);
            }
            case N -> {
                return new Position(x, y - 1);
            }
            case O -> {
                return new Position(x - 1, y);
            }
        }

        throw new IllegalArgumentException();
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Position.class.getSimpleName() + "[", "]")
                .add("x=" + x)
                .add("y=" + y)
                .toString();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
