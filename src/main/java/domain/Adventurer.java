package domain;

public class Adventurer extends Content {
    private final Map map;
    private final String name;
    private Orientation orientation;
    private int treasureCount = 0;
    private boolean isWaitingToMove;
    private Position currentPosition;

    public Adventurer(Orientation orientation, Position initialPosition, Map map, String name) {
        this.map = map;
        this.orientation = orientation;
        this.name = name;
        this.currentPosition = initialPosition;
    }

    public void move(Movement movement) throws InterruptedException {
        if (movement == Movement.A) {
            currentPosition = map.handleMovement(this);
        } else {
            System.out.println(name + " : Je tourne à " + (movement == Movement.D ? "droite" : "gauche"));
            orientation = orientation.getNextOrientation(movement);
        }
    }

    public Position getNextPosition() {
        return currentPosition.getNextPosition(orientation);
    }

    public Position getCurrentPosition() {
        return currentPosition;
    }

    public boolean isWaitingToMove() {
        return isWaitingToMove;
    }

    public void waitToMove() {
        isWaitingToMove = true;
    }

    public void pickUpTreasures(Treasures treasures) throws InterruptedException {
        treasureCount += treasures.getSize();
        Thread.sleep(1_000);
    }

    @Override
    protected boolean isOccupied() {
        return true;
    }

    @Override
    public char getType() {
        return 'X';
    }

    @Override
    public String toString() {
        return "Je suis " + name + ", j'ai ramassé " + treasureCount + " trésors. Je suis actuellement dans la case " + currentPosition + " et je me dirige vers le " + orientation;
    }

    public String getName() {
        return name;
    }
}
