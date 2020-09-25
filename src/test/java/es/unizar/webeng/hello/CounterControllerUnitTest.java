package es.unizar.webeng.hello;

/**
 * Performs unit test to the CounterController
 *
 * The private methods are tested using the
 * public methods because they are called by them.
 *
 * Test cases:
 * -------------------------------------------
 * | counter | button           | result     |
 * -------------------------------------------
 * | 0      | "increase"        | 1         |
 * | 1      | "increase"        | 2         |
 * | 0      | "decrease"        | 0         |
 * | -1     | "decrease"        | 0         |
 * | 2      | "decrease"        | 1         |
 * | 10     | "init"            | 0         |
 * | "a"    | "init"            | "ERROR"   |
 * | 0      | "another_option"  | "ERROR"   |
 * ------------------------------------------
 *
 * @author Eduardo Ruiz Cordón (764539)
 * @version 1.0
 * @since 09/24/2020
 *
 */

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@WebMvcTest(CounterController.class)
public class CounterControllerUnitTest {

    private final static String ERROR = "ERROR";
    private final static String INCREASE = "increase";
    private final static String DECREASE = "decrease";
    private final static String INIT = "init";

    private final static int INIT_VALUE = 0;

    @Autowired
    private CounterController controller;

    /**
     * Performs test of increase the counter with the value 0
     */
    @Test
    public void testIncreaseValue0(){
        int expectCounter = 0;
        HashMap<String, Object> map = new HashMap<>();
        String view = controller.actionCounter(String.valueOf(expectCounter), INCREASE, map);
        expectCounter++;
        assertThat(view, is("counter"));
        assertTrue(map.containsKey("counter"));
        assertEquals(map.get("counter"), expectCounter);
    }

    /**
     * Performs test of increase the counter with the value greater than 0
     */
    @Test
    public void testIncreaseValueGreaterThan0(){
        int expectCounter = 1;
        HashMap<String, Object> map = new HashMap<>();
        String view = controller.actionCounter(String.valueOf(expectCounter), INCREASE, map);
        expectCounter++;
        assertThat(view, is("counter"));
        assertTrue(map.containsKey("counter"));
        assertEquals(map.get("counter"), expectCounter);
    }

    /**
     * Performs test of decrease the counter with the value 0
     */
    @Test
    public void testDecreaseValue0(){
        int expectCounter = 0;
        HashMap<String, Object> map = new HashMap<>();
        String view = controller.actionCounter(String.valueOf(expectCounter), DECREASE, map);
        assertThat(view, is("counter"));
        assertTrue(map.containsKey("counter"));
        assertEquals(map.get("counter"), expectCounter);
    }

    /**
     * Performs test of decrease the counter with the value less than 0
     */
    @Test
    public void testDecreaseValueLessThan0(){
        int expectCounter = -1;
        HashMap<String, Object> map = new HashMap<>();
        String view = controller.actionCounter(String.valueOf(expectCounter), INCREASE, map);
        expectCounter = 0;

        assertThat(view, is("counter"));
        assertTrue(map.containsKey("counter"));
        assertEquals(map.get("counter"), expectCounter);
    }

    /**
     * Performs test of decrease the counter with the value greater than 0
     */
    @Test
    public void testDecreaseValueGreaterThan0(){
        int expectCounter = 2;
        HashMap<String, Object> map = new HashMap<>();
        String view = controller.actionCounter(String.valueOf(expectCounter), DECREASE, map);
        expectCounter--;
        assertThat(view, is("counter"));
        assertTrue(map.containsKey("counter"));
        assertEquals(map.get("counter"), expectCounter);
    }

    /**
     * Performs test of init the counter
     */
    @Test
    public void testInitCounter(){
        int expectCounter = 10;
        HashMap<String, Object> map = new HashMap<>();
        String view = controller.actionCounter(String.valueOf(expectCounter), INIT, map);
        expectCounter = INIT_VALUE;
        assertThat(view, is("counter"));
        assertTrue(map.containsKey("counter"));
        assertEquals(map.get("counter"), expectCounter);
    }

    /**
     * Performs test of realize one action on the counter with the type date of counter isn't Integer
     */
    @Test
    public void testCounterNotInteger(){
        HashMap<String, Object> map = new HashMap<>();
        String view = controller.actionCounter("a", INIT, map);
        assertThat(view, is("counter"));
        assertTrue(map.containsKey("counter"));
        assertEquals(map.get("counter"), ERROR);
    }

    /**
     * Performs test of try realized a option not register in CounterController
     */
    @Test
    public void testTryActionNotRegister(){
        HashMap<String, Object> map = new HashMap<>();
        String view = controller.actionCounter(String.valueOf(0), "another_option", map);
        assertThat(view, is("counter"));
        assertTrue(map.containsKey("counter"));
        assertEquals(map.get("counter"), ERROR);
    }
}