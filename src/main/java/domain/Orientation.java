package domain;

public enum Orientation {
    E, S, N, O;

    public Orientation getNextOrientation(Movement movement) {
        switch (movement) {
            case A -> {
                return this;
            }
            case D -> {
                switch (this) {
                    case E -> {
                        return S;
                    }
                    case S -> {
                        return O;
                    }
                    case N -> {
                        return E;
                    }
                    case O -> {
                        return N;
                    }
                }
            }
            case G -> {
                switch (this) {
                    case E -> {
                        return N;
                    }
                    case S -> {
                        return E;
                    }
                    case N -> {
                        return O;
                    }
                    case O -> {
                        return S;
                    }
                }
            }
        }

        throw new IllegalArgumentException();
    }
}
