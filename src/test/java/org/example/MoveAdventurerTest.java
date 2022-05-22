package org.example;

import domain.Adventurer;
import domain.Map;
import domain.Mountain;
import domain.Movement;
import domain.Orientation;
import domain.Position;
import domain.Treasures;
import exceptions.OutOfMapException;
import org.junit.Test;

import java.util.List;

public class MoveAdventurerTest {

    @Test
    public void positionShouldBeS23WhenMovementIsAADADAGA() throws InterruptedException, OutOfMapException {
        Map map = new Map(6, 5);
        Adventurer adventurer = new Adventurer(Orientation.E, new Position(1, 1), map, "Yohan");
        map.populate(adventurer.getCurrentPosition(), adventurer);

        System.out.println(adventurer);
        move(adventurer, List.of(Movement.A, Movement.A, Movement.D, Movement.A, Movement.D, Movement.A, Movement.G, Movement.A));
    }

    private void move(Adventurer adventurer, List<Movement> movements) throws InterruptedException {
        for (Movement movement : movements) {
            adventurer.move(movement);
            System.out.println(adventurer);
        }
    }

    @Test
    public void postionShouldBeS23WhenMovementIsAADADAGA() throws InterruptedException, OutOfMapException {
        Map map = new Map(6, 5);
        Adventurer adventurer = new Adventurer(Orientation.E, new Position(1, 1), map, "Yohan");
        map.populate(adventurer.getCurrentPosition(), adventurer);
        map.populate(new Position(3, 2), new Treasures(2));
        map.populate(new Position(2, 3), new Treasures(3));

        move(adventurer, List.of(Movement.A, Movement.A, Movement.D, Movement.A, Movement.D, Movement.A, Movement.G, Movement.A));
    }

    @Test
    public void mountains() throws InterruptedException, OutOfMapException {
        Map map = new Map(6, 5);
        Adventurer adventurer = new Adventurer(Orientation.E, new Position(1, 1), map, "Yohan");
        map.populate(adventurer.getCurrentPosition(), adventurer);
        map.populate(new Position(2, 3), new Treasures(3));
        map.populate(new Position(3, 2), new Mountain());

        move(adventurer, List.of(Movement.A, Movement.A, Movement.D, Movement.A, Movement.D, Movement.A, Movement.G, Movement.A));
    }
}
