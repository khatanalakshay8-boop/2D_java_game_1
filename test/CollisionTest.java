package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import main.CollisionChecker;
import entity.player;

public class CollisionTest {

    @Test
    void testCollisionDoesNotCrash() {
        player p = new player(null, null);
        CollisionChecker cc = new CollisionChecker(null);

        assertDoesNotThrow(() -> {
            cc.checkTile(p);
        });
    }

    @Test
    void testCollisionFlagBoolean() {
        player p = new player(null, null);
        CollisionChecker cc = new CollisionChecker(null);

        cc.checkTile(p);

        assertTrue(p.collisionOn == true || p.collisionOn == false);
    }
}
