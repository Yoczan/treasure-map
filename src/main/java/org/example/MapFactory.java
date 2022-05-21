package org.example;

import domain.Map;

public class MapFactory {
    public static Map createMap(int width, int height) {
        return new Map(width, height);
    }
}
