package org.example;

import domain.Adventurer;
import domain.Movement;

import java.util.List;

public class AdventurerMoving implements Runnable {

    private final Adventurer adventurer;
    private final List<Movement> movements;

    public AdventurerMoving(Adventurer adventurer, List<Movement> movements) {
        this.adventurer = adventurer;
        this.movements = movements;
    }

    @Override
    public void run() {
        for (Movement movement : movements) {
            try {
                adventurer.move(movement);
                System.out.println(adventurer);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
