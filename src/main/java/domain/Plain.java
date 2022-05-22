package domain;

public class Plain extends Content {

    @Override
    protected boolean isOccupied() {
        return false;
    }

    @Override
    public char getType() {
        return ' ';
    }
}
