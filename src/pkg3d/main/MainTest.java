package pkg3d.main;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pkg3d.main.gfx.Display;
import pkg3d.main.input.KeyManager;
import pkg3d.main.states.MainState;
import pkg3d.main.states.State;

import static org.junit.jupiter.api.Assertions.*;

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
    }

    @Test
    void getMouseManager() {
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