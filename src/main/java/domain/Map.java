package domain;

public class Map {

    private final Cell[][] cells;

    public Map(int width, int height) {
        cells = new Cell[width][height];
        for (int column = 0; column < width; column++) {
            for (int row = 0; row < height; row++) {
                cells[column][row] = new Cell(new Position(column + 1, row + 1));
            }
        }
    }

    public void populate(Content content) {
        Position position = content.getPosition();
        cells[position.getX() - 1][position.getY() - 1].setContent(content);
    }

    public synchronized Position move(Adventurer adventurer) throws InterruptedException {
        Position currentAdventurerPosition = adventurer.getPosition();
        Position nextExpectedPosition = currentAdventurerPosition.move(adventurer.getOrientation());
        Cell nextExpectedCell = cells[nextExpectedPosition.getX() - 1][nextExpectedPosition.getY() - 1];
        Content contentOfNextExpectedCell = nextExpectedCell.getContent();

        System.out.println("Cell : " + nextExpectedCell + " Adventurer :" + adventurer);

        if (contentOfNextExpectedCell instanceof Treasures treasures) {
            adventurer.pickUpTreasures(treasures);
            Thread.sleep(1_000);
            cells[currentAdventurerPosition.getX() - 1][currentAdventurerPosition.getY() - 1].clean(currentAdventurerPosition);
            nextExpectedCell.setContent(adventurer);
            notifyAll();
            return nextExpectedPosition;
        } else if (contentOfNextExpectedCell instanceof Plain) {
            Thread.sleep(1_000);
            cells[currentAdventurerPosition.getX() - 1][currentAdventurerPosition.getY() - 1].clean(currentAdventurerPosition);
            nextExpectedCell.setContent(adventurer);
            notifyAll();
            return nextExpectedPosition;
        } else if (contentOfNextExpectedCell instanceof Adventurer && contentOfNextExpectedCell != adventurer) {

            if (!((Adventurer) contentOfNextExpectedCell).isWaitingToMove()) {

                while (nextExpectedCell.isOccupied()) {
                    System.out.println("wait for " + contentOfNextExpectedCell + " to move");
                    adventurer.waitToMove();
                    wait();
                }

                cells[currentAdventurerPosition.getX() - 1][currentAdventurerPosition.getY() - 1].clean(currentAdventurerPosition);
                nextExpectedCell.setContent(adventurer);
                return nextExpectedPosition;
            }
        }

        return currentAdventurerPosition;
    }

    public Cell getCell(Position position) {
        return cells[position.getX() - 1][position.getY() - 1];
    }
}
