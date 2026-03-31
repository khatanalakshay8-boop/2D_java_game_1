package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import entity.player;

public class PlayerTest {

    player p;

    @BeforeEach
    void setup() {
        p = new player(null, null);
        p.speed = 5;
    }

    @Test
    void testMoveUp() {
        int startY = p.worldY;
        p.direction = "up";
        p.update();
        assertTrue(p.worldY < startY);
    }

    @Test
    void testMoveDown() {
        int startY = p.worldY;
        p.direction = "down";
        p.update();
        assertTrue(p.worldY > startY);
    }

    @Test
    void testMoveLeft() {
        int startX = p.worldX;
        p.direction = "left";
        p.update();
        assertTrue(p.worldX < startX);
    }

    @Test
    void testMoveRight() {
        int startX = p.worldX;
        p.direction = "right";
        p.update();
        assertTrue(p.worldX > startX);
    }

    @Test
    void testNoMovementWhenNoDirection() {
        int startX = p.worldX;
        int startY = p.worldY;

        p.direction = "";
        p.update();

        assertEquals(startX, p.worldX);
        assertEquals(startY, p.worldY);
    }
}
