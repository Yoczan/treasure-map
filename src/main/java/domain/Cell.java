package domain;

import java.util.StringJoiner;

public class Cell {

    private Content content;
    private final Position position;

    public Cell(Plain content, Position position) {
        this.content = content;
        this.position = position;
    }

    public Content getContent() {
        return content;
    }

    public boolean isOccupied() {
        return content.isOccupied();
    }

    public Position getPosition() {
        return position;
    }

    public void updateContent(Content content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Cell.class.getSimpleName() + "[", "]")
                .add("content=" + content)
                .add("position=" + position)
                .toString();
    }
}
