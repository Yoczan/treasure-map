package org.example;

import domain.Map;
import domain.Mountain;
import domain.Position;
import domain.Treasures;
import exceptions.OutOfMapException;
import org.junit.Assert;
import org.junit.Test;

public class CreateMapTest {

    @Test
    public void mapShouldContainsExpectedCells() throws OutOfMapException {
        Map map = new Map(6, 5);
        Treasures treasures = new Treasures(1);
        map.populate(new Position(4, 2), treasures);
        Mountain mountain = new Mountain();
        map.populate(new Position(5, 3), mountain);
        Assert.assertEquals(treasures, map.getCell(new Position(4, 2)).getContent());
        Assert.assertEquals(mountain, map.getCell(new Position(5, 3)).getContent());
    }
}
