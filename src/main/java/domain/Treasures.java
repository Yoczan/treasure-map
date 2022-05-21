package domain;

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
}
