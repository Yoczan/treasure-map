package domain;

public class Mountain extends Content {

    @Override
    protected boolean isOccupied() {
        return true;
    }

    @Override
    public char getType() {
        return 'M';
    }
}
