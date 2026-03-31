package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import main.keyhandler;
import java.awt.event.KeyEvent;

public class KeyHandlerTest1{

    private KeyEvent createKey(int keyCode) {
        return new KeyEvent(
            new java.awt.Canvas(),
            KeyEvent.KEY_PRESSED,
            System.currentTimeMillis(),
            0,
            keyCode,
            KeyEvent.CHAR_UNDEFINED
        );
    }

    @Test
    void testAllMovementKeys() {
        keyhandler kh = new keyhandler();

        kh.keyPressed(createKey(KeyEvent.VK_W));
        assertTrue(kh.upPressed);

        kh.keyPressed(createKey(KeyEvent.VK_S));
        assertTrue(kh.downPressed);

        kh.keyPressed(createKey(KeyEvent.VK_A));
        assertTrue(kh.leftPressed);

        kh.keyPressed(createKey(KeyEvent.VK_D));
        assertTrue(kh.rightPressed);
    }

    @Test
    void testKeyRelease() {
        keyhandler kh = new keyhandler();

        KeyEvent w = createKey(KeyEvent.VK_W);

        kh.keyPressed(w);
        assertTrue(kh.upPressed);

        kh.keyReleased(w);
        assertFalse(kh.upPressed);
    }
}
