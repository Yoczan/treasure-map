package org.example;

import domain.Adventurer;
import domain.Content;
import domain.Map;
import domain.Mountain;
import domain.Movement;
import domain.Orientation;
import domain.Position;
import domain.Treasures;
import exceptions.OutOfMapException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class AppWithFiles {

    public static void main(String[] args) throws IOException, OutOfMapException {
        System.out.println(System.getProperty("user.dir"));
        List<String> mapSpecifications = Files.readAllLines(Paths.get("src/main/resources/map.txt"));
        List<String> adventurers = Files.readAllLines(Paths.get("src/main/resources/adventurers.txt"));

        int width = 0;
        int height = 0;
        java.util.Map<Content, Position> contentAndPositions = new HashMap<>();

        for (String line : mapSpecifications) {
            String[] parameters = line.split(" ");
            switch (parameters[0]) {
                case "C" -> {
                    width = Integer.parseInt(parameters[1]);
                    height = Integer.parseInt(parameters[2]);
                }
                case "T" -> {
                    String[] positions = parameters[1].split("-");
                    Treasures treasures = new Treasures(Integer.parseInt(parameters[2]));
                    Position position = new Position(Integer.parseInt(positions[0]), Integer.parseInt(positions[1]));
                    contentAndPositions.put(treasures, position);
                }
                case "M" -> {
                    String[] mPositions = parameters[1].split("-");
                    Position mPosition = new Position(Integer.parseInt(mPositions[0]), Integer.parseInt(mPositions[1]));
                    contentAndPositions.put(new Mountain(), mPosition);
                }
            }
        }
        Map map = new Map(width, height);

        List<AdventurerMoving> adventurersMoves = new ArrayList<>();
        for (String adventurer : adventurers) {
            String[] parameters = adventurer.split(" ");
            Orientation orientation = Orientation.valueOf(parameters[2]);
            String[] positions = parameters[1].split("-");
            Position position = new Position(Integer.parseInt(positions[0]), Integer.parseInt(positions[1]));
            Adventurer key = new Adventurer(orientation, position, map, parameters[0]);
            contentAndPositions.put(key, position);
            List<Movement> moves = Arrays.stream(parameters[3].split("")).map(Movement::valueOf).collect(Collectors.toList());
            adventurersMoves.add(new AdventurerMoving(key, moves));
        }

        for (java.util.Map.Entry<Content, Position> contentPositionEntry : contentAndPositions.entrySet()) {
            Position position = contentPositionEntry.getValue();
            Content content = contentPositionEntry.getKey();
            map.populate(position, content);
        }

        for (AdventurerMoving adventurersMove : adventurersMoves) {
            new Thread(adventurersMove).start();
        }
    }
}
