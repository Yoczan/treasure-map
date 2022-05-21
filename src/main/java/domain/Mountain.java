package domain;

public class Mountain extends Content {
    public Mountain(Position position) {
        super(position);
    }

    @Override
    protected String getType() {
        return "mountain";
    }
}
