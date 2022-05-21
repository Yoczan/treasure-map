package domain;

public record Position(int x, int y) {

    public Position getNextPosition(Orientation orientation) {
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
        return "x = " + x + ", y = " + y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
