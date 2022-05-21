package org.example;

import domain.Adventurer;
import domain.Map;
import domain.Mountain;
import domain.Movement;
import domain.Orientation;
import domain.Position;
import domain.Treasures;

import java.util.List;

public class App {
    public static void main(String[] args) {
        Map map = MapFactory.createMap(6, 5);
        map.populate(new Treasures(1, new Position(3, 2)));
        map.populate(new Treasures(3, new Position(1, 3)));
        map.populate(new Mountain(new Position(5, 3)));

        List<Movement> movementsYohan = List.of(Movement.A, Movement.A, Movement.D, Movement.A, Movement.D, Movement.A, Movement.G, Movement.A);
        Position position11 = new Position(1, 1);
        AdventurerMoving adventurerMoving = createAdventurer(map, Orientation.E, position11, "Yohan", movementsYohan);

        List<Movement> movementsAziza = List.of(Movement.A, Movement.D, Movement.A, Movement.D, Movement.A, Movement.G);
        Position position42 = new Position(4, 2);
        AdventurerMoving aziza = createAdventurer(map, Orientation.O, position42, "Aziza", movementsAziza);

        new Thread(adventurerMoving).start();
        new Thread(aziza).start();
    }

    private static AdventurerMoving createAdventurer(Map map, Orientation orientation, Position position, String name, List<Movement> movements) {
        Adventurer adventurer = new Adventurer(orientation, position, map, name);
        map.populate(adventurer);
        return new AdventurerMoving(adventurer, movements);
    }
}
