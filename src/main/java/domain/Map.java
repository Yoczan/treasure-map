package domain;

import exceptions.OutOfMapException;

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

        try {
            Cell nextExpectedCell = getCell(nextExpectedPosition);
            Content contentOfNextExpectedCell = nextExpectedCell.getContent();

            System.out.println(adventurer.getName() + " se dirige vers la case " + nextExpectedPosition);

            if (contentOfNextExpectedCell instanceof Treasures treasures) {
                adventurer.pickUpTreasures(treasures);
                Thread.sleep(1_000);
                moveToNextCell(adventurer);
                System.out.println(adventurer.getName() + " a trouvé " + treasures.getSize() + " trésors");
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
                System.out.println(adventurer.getName() + " rencontre un autre aventurier");
            }
        } catch (OutOfMapException ignored) {

        }

        System.out.println("Le mouvement de " + adventurer.getName() + " a été ignoré");
        return adventurer.getCurrentPosition();
    }

    private void moveToNextCell(Adventurer adventurer) throws OutOfMapException {
        populate(adventurer.getCurrentPosition(), new Plain());
        populate(adventurer.getNextPosition(), adventurer);
        System.out.println(adventurer.getName() + " est maintenant sur la case " + adventurer.getNextPosition());
    }

    public void populate(Position position, Content content) throws OutOfMapException {
        getCell(position).updateContent(content);
    }

    private void waitForNextCellToBeReleased(Adventurer adventurer) throws InterruptedException, OutOfMapException {
        Cell nextExpectedCell = getCell(adventurer.getNextPosition());
        while (nextExpectedCell.isOccupied()) {
            System.out.println(adventurer.getName() + " attend que la case " + nextExpectedCell.getPosition() + " soit libre");
            adventurer.waitToMove();
            wait();
        }
    }

    public Cell getCell(Position position) throws OutOfMapException {
        int x = position.getX();
        int y = position.getY();
        if (x > getWidth() || x < 1 || y < 0 || y > getHeight()) {
            System.out.println("La case " + x + ", " + y + " est inaccessible");
            throw new OutOfMapException();
        }

        return cells[x - 1][position.getY() - 1];
    }

    public int getWidth() {
        return cells.length;
    }

    public int getHeight() {
        return cells[0].length;
    }

    @Override
    public String toString() {
        StringBuilder map = new StringBuilder();

        for (int column = 0; column <= cells.length; column++) {
            map.append("|").append(column);
        }
        map.append("|").append("\n");
        for (int r = 0; r < cells[0].length; r++) {
            map.append("|").append(r + 1);
            for (Cell[] cell : cells) {
                map.append("|").append(cell[r].getContent().getType());
            }
            map.append("|").append("\n");
        }
        return map.toString();
    }
}
