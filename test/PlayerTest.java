package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import entity.player;
import main.gamepanel;
import main.keyhandler;
import main.CollisionChecker; // adjust if name differs

public class PlayerTest {

    player p;
    gamepanel gp;
    keyhandler keyH;

    @BeforeEach
    void setup() {
        gp = new gamepanel();
        keyH = new keyhandler();

        // Ensure required dependencies exist
        gp.cChecker = new CollisionChecker(gp);

        p = new player(gp, keyH);
        p.speed = 5;

        // reset keys
        resetKeys();
    }

    void resetKeys() {
        keyH.upPressed = false;
        keyH.downPressed = false;
        keyH.leftPressed = false;
        keyH.rightPressed = false;
    }

    @Test
    void testMoveUp() {
        int startY = p.worldY;

        keyH.upPressed = true;
        p.update();

        assertTrue(p.worldY < startY);
    }

    @Test
    void testMoveDown() {
        int startY = p.worldY;

        keyH.downPressed = true;
        p.update();

        assertTrue(p.worldY > startY);
    }

    @Test
    void testMoveLeft() {
        int startX = p.worldX;

        keyH.leftPressed = true;
        p.update();

        assertTrue(p.worldX < startX);
    }

    @Test
    void testMoveRight() {
        int startX = p.worldX;

        keyH.rightPressed = true;
        p.update();

        assertTrue(p.worldX > startX);
    }

    @Test
    void testNoMovementWhenNoDirection() {
        int startX = p.worldX;
        int startY = p.worldY;

        resetKeys(); // no keys pressed
        p.update();

        assertEquals(startX, p.worldX);
        assertEquals(startY, p.worldY);
    }
}
