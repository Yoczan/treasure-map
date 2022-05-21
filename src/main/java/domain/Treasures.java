package domain;

public class Treasures extends Content {

    private final int size;

    public Treasures(int size, Position position) {
        super(position);
        this.size = size;
    }

    public int getSize() {
        return size;
    }

    @Override
    protected String getType() {
        return "treasures";
    }
}
