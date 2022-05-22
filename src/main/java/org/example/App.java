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
            System.out.println("=== La carte au trésor ===");
            System.out.println("1. Jouer");
            System.out.println("0. Quitter");

            Scanner clavier = new Scanner(System.in);

            switch (clavier.nextInt()) {
                case 0 -> shouldExit = true;
                case 1 -> play(clavier);
            }
        }
    }

    private static void play(Scanner clavier) throws InterruptedException {
        Map map = createMap(clavier);
        Adventurer adventurer = createAdventurer(clavier, map);

        boolean shouldExit = false;
        while (!shouldExit) {
            System.out.println("Appuyez sur A pour avancer, D pour tourner à droite et G pour tourner à gauche");
            System.out.println("Appuyez sur W pour connaître la position de l'aventurier");
            System.out.println("Appuyez sur H pour obtenir de l'aide");
            System.out.println("Q pour retourner au menu principal");

            char move = clavier.next().charAt(0);

            switch (move) {
                case 'A', 'D', 'G' -> adventurer.move(Movement.valueOf(Character.toString(move)));
                case 'W' -> System.out.println(adventurer);
                case 'H' -> System.out.println(map);
                case 'Q' -> shouldExit = true;
            }
        }
    }

    private static Adventurer createAdventurer(Scanner clavier, Map map) {
        System.out.println("Renseigner le nom de votre aventurier : ");
        Position position = new Position(1, 1);
        Adventurer adventurer = new Adventurer(Orientation.E, position, map, clavier.next());
        System.out.println(adventurer);
        map.populate(position, adventurer);
        return adventurer;
    }

    private static Map createMap(Scanner clavier) {
        System.out.println("=== Créer une carte ===");
        int width = askInt(clavier, "Largeur de la carte ?");
        int height = askInt(clavier, "Hauteur de la carte ?");
        Map map = new Map(width, height);
        System.out.println(map);
        populateMap(map, new Treasures(new Random().nextInt(1, 10)));
        populateMap(map, new Mountain());
        return map;
    }

    private static int askInt(Scanner scanner, String s) {
        System.out.println(s);
        return scanner.nextInt();
    }

    private static void populateMap(Map map, Content content) {
        int x = new Random().nextInt(2, map.getWidth());
        int y = new Random().nextInt(2, map.getHeight());
        map.populate(new Position(x, y), content);
    }
}
