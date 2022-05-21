package domain;

import java.util.StringJoiner;

public class Cell {

    private Content content;

    public Cell(Position position) {
        this.content = new Plain(position);
    }

    public Content getContent() {
        return content;
    }

    public void setContent(Content content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Cell.class.getSimpleName() + "[", "]")
                .add("content=" + content)
                .toString();
    }

    public boolean isOccupied() {
        return content.getType().equals("Adventurer");
    }

    public void clean(Position position) {
        content = new Plain(position);
    }
}
