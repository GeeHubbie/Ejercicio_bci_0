package ejercicio_bci;

import main.MainApp;
import org.junit.Test;
import static org.junit.Assert.*;


public class AppTest {
    @Test public void appHasAGreeting() {
        MainApp classUnderTest = new MainApp();

        assertNotNull("app should have a greeting", classUnderTest.saluda());
    }
}
