package org.example;

import domain.Adventurer;
import domain.Map;
import domain.Mountain;
import domain.Movement;
import domain.Orientation;
import domain.Position;
import domain.Treasures;
import org.junit.Test;

import java.util.List;

public class MoveAdventurerTest {

    @Test
    public void positionShouldBeS23WhenMovementIsAADADAGA() throws InterruptedException {
        Map map = MapFactory.createMap(6, 5);
        Adventurer adventurer = new Adventurer(Orientation.E, new Position(1, 1), map, "Yohan");
        map.populate(adventurer);

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
    public void postionShouldBeS23WhenMovementIsAADADAGA() throws InterruptedException {
        Map map = MapFactory.createMap(6, 5);
        Adventurer adventurer = new Adventurer(Orientation.E, new Position(1, 1), map, "Yohan");
        map.populate(adventurer);
        map.populate(new Treasures(2, new Position(3, 2)));
        map.populate(new Treasures(3, new Position(2, 3)));

        move(adventurer, List.of(Movement.A, Movement.A, Movement.D, Movement.A, Movement.D, Movement.A, Movement.G, Movement.A));
    }

    @Test
    public void mountains() throws InterruptedException {
        Map map = MapFactory.createMap(6, 5);
        Adventurer adventurer = new Adventurer(Orientation.E, new Position(1, 1), map, "Yohan");
        map.populate(adventurer);
        map.populate(new Treasures(3, new Position(2, 3)));
        map.populate(new Mountain(new Position(3, 2)));

        move(adventurer, List.of(Movement.A, Movement.A, Movement.D, Movement.A, Movement.D, Movement.A, Movement.G, Movement.A));
    }
}
