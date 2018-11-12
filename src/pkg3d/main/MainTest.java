package pkg3d.main;

import org.junit.jupiter.api.Test;
import pkg3d.main.gfx.Display;
import pkg3d.main.input.MouseManager;

import javax.swing.*;
import java.awt.event.KeyEvent;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;


class MainTest {

    @Test
    void startup() {
        Main main = new Main();
        main.startup();
        assertEquals(1080, main.getWidth());
        assertEquals(720, main.getHeight());
        assertEquals(new Display("3d", main.getWidth(), main.getHeight()), main.getDisplay());
        assertNotNull(main.getKeyManager());
    }

    @Test
    void update() {
    }

    @Test
    void render() {
    }

    @Test
    void getState() {
    }

    @Test
    void getKeyManager() {
        Main main = new Main();
        main.startup();
        JPanel instance = new JPanel();
        KeyEvent key = new KeyEvent(instance, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0,  KeyEvent.VK_D,'d');
        main.getKeyManager().keyPressed(key);
        assertTrue(main.getKeyManager().getDPressed());
    }

    @Test
    void getMouseManager() {
        Main main = new Main();
        main.startup();
        assertEquals(new MouseManager(main.getWidth(), main.getHeight()), main.getMouseManager());
    }

    @Test
    void getWidth() {
    }

    @Test
    void getHeight() {
    }

    @Test
    void getDisplay() {
    }
}