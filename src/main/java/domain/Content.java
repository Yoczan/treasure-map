package domain;

import java.util.StringJoiner;

public abstract class Content {

    protected Position position;

    protected Content(Position position) {
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }

    protected abstract String getType();

    @Override
    public String toString() {
        return new StringJoiner(", ", getType() + "[", "]")
                .add("position=" + position)
                .toString();
    }
}
