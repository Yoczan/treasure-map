package org.example;

import domain.Adventurer;
import domain.Movement;

import java.util.List;

public record AdventurerMoving(Adventurer adventurer, List<Movement> movements) implements Runnable {

    @Override
    public void run() {
        for (Movement movement : movements) {
            try {
                adventurer.move(movement);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
