package org.example;

import domain.Map;
import domain.Mountain;
import domain.Position;
import domain.Treasures;
import org.junit.Assert;
import org.junit.Test;

public class CreateMapTest {

    @Test
    public void mapShouldContainsExpectedCells() {
        Map map = new Map(6, 5);
        Treasures treasures = new Treasures(1, new Position(4, 2));
        map.populate(treasures);
        Mountain mountain = new Mountain(new Position(5, 3));
        map.populate(mountain);
        Assert.assertEquals(treasures, map.getCell(new Position(4, 2)).getContent());
        Assert.assertEquals(mountain, map.getCell(new Position(5, 3)).getContent());
    }
}
