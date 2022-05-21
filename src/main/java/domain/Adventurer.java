package domain;

import java.util.StringJoiner;

public class Adventurer extends Content implements Movable {
    private final Map map;
    private final String name;
    private Orientation orientation;
    private int treasureCount = 0;
    private boolean isWaitingToMove;

    public Adventurer(Orientation orientation, Position position, Map map, String name) {
        super(position);
        this.map = map;
        this.orientation = orientation;
        this.name = name;
    }

    public void move(Movement movement) throws InterruptedException {
        if (movement == Movement.A) {
            position = map.move(this);
        } else {
            System.out.println(this + " movement : " + movement);
            orientation = orientation.move(movement);
        }
    }

    public void pickUpTreasures(Treasures treasures) throws InterruptedException {
        treasureCount += treasures.getSize();
        Thread.sleep(1_000);
    }

    @Override
    protected String getType() {
        return "Adventurer";
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Adventurer.class.getSimpleName() + "[", "]")
                .add("map=" + map)
                .add("name='" + name + "'")
                .add("orientation=" + orientation)
                .add("treasureCount=" + treasureCount)
                .add("position=" + position)
                .toString();
    }

    public Orientation getOrientation() {
        return orientation;
    }

    public boolean isWaitingToMove() {
        return isWaitingToMove;
    }

    public void waitToMove() {
        isWaitingToMove = true;
    }
}
