package org.example;

import domain.Content;
import domain.Map;
import domain.Mountain;
import domain.Position;
import domain.Treasures;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;

public class ShouldHave {

    public static void main(String[] args) {
        List<String> lines = Files.readAllLines();

        int width = 0;
        int height = 0;
        java.util.Map<Content, Position> contentAndPositions = new HashMap<>();

        for (String line : lines) {
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
        map.populate(new Position(3, 2), new Treasures(1));
        map.populate(new Position(1, 3), new Treasures(3));
        map.populate(new Position(5, 3), new Mountain());

        for (java.util.Map.Entry<Content, Position> contentPositionEntry : contentAndPositions.entrySet()) {
            map.populate(contentPositionEntry.getValue(), contentPositionEntry.getKey());
        }
    }
}
