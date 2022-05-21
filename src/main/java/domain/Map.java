package domain;

public class Map {

    private final Cell[][] cells;

    public Map(int width, int height) {
        cells = new Cell[width][height];
        for (int column = 0; column < width; column++) {
            for (int row = 0; row < height; row++) {
                Position position = new Position(column + 1, row + 1);
                cells[column][row] = new Cell(new Plain(), position);
            }
        }
    }

    public synchronized Position handleMovement(Adventurer adventurer) throws InterruptedException {
        Position nextExpectedPosition = adventurer.getNextPosition();
        Cell nextExpectedCell = getCell(nextExpectedPosition);
        Content contentOfNextExpectedCell = nextExpectedCell.getContent();

        System.out.println("Cell : " + nextExpectedCell + " Adventurer :" + adventurer);

        if (contentOfNextExpectedCell instanceof Treasures treasures) {
            adventurer.pickUpTreasures(treasures);
            Thread.sleep(1_000);
            moveToNextCell(adventurer);
            notifyAll();
            return nextExpectedPosition;
        } else if (contentOfNextExpectedCell instanceof Plain) {
            Thread.sleep(1_000);
            moveToNextCell(adventurer);
            notifyAll();
            return nextExpectedPosition;
        } else if (contentOfNextExpectedCell instanceof Adventurer && contentOfNextExpectedCell != adventurer) {
            if (!((Adventurer) contentOfNextExpectedCell).isWaitingToMove()) {
                waitForNextCellToBeReleased(adventurer);
                moveToNextCell(adventurer);
                return nextExpectedPosition;
            }
        }

        return adventurer.getCurrentPosition();
    }

    private void moveToNextCell(Adventurer adventurer) {
        populate(adventurer.getCurrentPosition(), new Plain());
        populate(adventurer.getNextPosition(), adventurer);
    }

    public void populate(Position position, Content content) {
        getCell(position).updateContent(content);
    }

    private void waitForNextCellToBeReleased(Adventurer adventurer) throws InterruptedException {
        Cell nextExpectedCell = getCell(adventurer.getNextPosition());
        while (nextExpectedCell.isOccupied()) {
            System.out.println("wait for " + nextExpectedCell.getContent() + " to move");
            adventurer.waitToMove();
            wait();
        }
    }

    public Cell getCell(Position position) {
        return cells[position.getX() - 1][position.getY() - 1];
    }
}
