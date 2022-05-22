package org.example;

import domain.Adventurer;
import domain.Content;
import domain.Map;
import domain.Mountain;
import domain.Movement;
import domain.Orientation;
import domain.Position;
import domain.Treasures;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws InterruptedException {

        boolean shouldExit = false;

        while (!shouldExit) {
            System.out.println("Choisir une action :");
            System.out.println("1. Jouer");
            System.out.println("0. Quitter");

            Scanner scanner = new Scanner(System.in);
            switch (scanner.nextInt()) {
                case 0 -> shouldExit = true;
                case 1 -> {
                    System.out.println("=== Créer une carte ===");
                    System.out.println("Largeur de la carte ?");
                    int width = scanner.nextInt();
                    System.out.println("Hauteur de la carte ?");
                    int height = scanner.nextInt();
                    Map map = new Map(width, height);
                    System.out.println(map);
                    Position position = new Position(1, 1);
                    System.out.println("Renseigner le nom de votre aventurier");
                    String name = scanner.next();
                    Adventurer adventurer = new Adventurer(Orientation.E, position, map, name);
                    map.populate(position, adventurer);
                    final int treasureCount = new Random().nextInt(1, 10);
                    populateMap(width, height, map, new Treasures(treasureCount));
                    populateMap(width, height, map, new Mountain());
                    System.out.println(adventurer);
                    while (!shouldExit) {

                        System.out.println("Choisissez une action");
                        System.out.println("Appuyez sur A pour avancer, D pour tourner à droite et G pour tourner à gauche");
                        System.out.println("Appuyez sur W pour connaître la position des aventuriers");
                        System.out.println("Appuyez sur H pour voir la carte");
                        System.out.println("Q pour quitter");
                        char move = scanner.next().charAt(0);

                        switch (move) {
                            case 'A', 'D', 'G' -> adventurer.move(Movement.valueOf(Character.toString(move)));
                            case 'W' -> System.out.println(adventurer);
                            case 'H' -> System.out.println(map);
                            case 'Q' -> shouldExit = true;
                        }
                    }
                }
            }
        }
    }

    private static void populateMap(int width, int height, Map map, Content content) {
        int x = new Random().nextInt(2, width);
        int y = new Random().nextInt(2, height);
        map.populate(new Position(x, y), content);
    }

    private static AdventurerMoving createAdventurer(Map map, Orientation orientation, Position position, String name, List<Movement> movements) {
        Adventurer adventurer = new Adventurer(orientation, position, map, name);
        map.populate(adventurer.getCurrentPosition(), adventurer);
        return new AdventurerMoving(adventurer, movements);
    }
}
