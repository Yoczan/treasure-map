package domain;

public class Plain extends Content {
    public Plain(Position position) {
        super(position);
    }

    @Override
    protected String getType() {
        return "plain";
    }
}
