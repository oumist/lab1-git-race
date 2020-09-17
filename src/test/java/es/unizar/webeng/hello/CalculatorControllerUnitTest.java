package es.unizar.webeng.hello;

/**
 * Performs unit test to the CalculatorController
 *
 * Test cases:
 * ----------------------------
 * | value1 | value2 | result |
 * ----------------------------
 * | "1"    | "3"   | "4"     |
 * | -1     | "-3"  | "-4"    |
 * | "a"    | "b"   | "ERROR" |
 * | ""     | ""    | "ERROR" |
 * | NULL   | NULL  | "ERROR" |
 * ----------------------------
 *
 * @author Daniel Huici Meseguer (758635)
 * @version 1.0
 * @since 09/17/2020
 *
 */

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@WebMvcTest(UltraCalculatorController.class)
public class CalculatorControllerUnitTest {

    @Value("${app.error}")
    private String error;

    @Autowired
    private UltraCalculatorController controller;

    /**
     * Performs test with integer positive values
     */

    @Test
    public void testCalculatorPositiveValues() throws Exception {
        String value1 = "1";
        String value2 = "3";
        int result = Integer.parseInt(value1) + Integer.parseInt(value2);

        HashMap<String, Object> map = new HashMap<>();
        String view = controller.calculator(value1, value2, map);
        assertThat(view, is("calc"));
        assertThat(map.containsKey("values"), is(true));
        assertThat(map.containsKey("message"), is(true));
        assertThat(map.get("values"), is(value1 + " + " + value2));
        assertThat(map.get("message"), is(String.valueOf(result)));
    }

    /**
     * Performs test with integer negative values
     */

    @Test
    public void testCalculatorNegativeValues() throws Exception {
        String value1 = "-1";
        String value2 = "-3";
        int result = Integer.parseInt(value1) + Integer.parseInt(value2);

        HashMap<String, Object> map = new HashMap<>();
        String view = controller.calculator(value1, value2, map);
        assertThat(view, is("calc"));
        assertThat(map.containsKey("values"), is(true));
        assertThat(map.containsKey("message"), is(true));
        assertThat(map.get("values"), is(value1 + " + " + value2));
        assertThat(map.get("message"), is(String.valueOf(result)));
    }

    /**
     * Performs test with decimal values
     */

    @Test
    public void testCalculatorDecimalValues() throws Exception {
        String value1 = "1.4";
        String value2 = "3.1";

        HashMap<String, Object> map = new HashMap<>();
        String view = controller.calculator(value1, value2, map);
        assertThat(view, is("calc"));
        assertThat(map.containsKey("values"), is(true));
        assertThat(map.containsKey("message"), is(true));
        assertThat(map.get("values"), is(value1 + " + " + value2));
        assertThat(map.get("message"), is(error));
    }

    /**
     * Performs test with non numerical values
     */

    @Test
    public void testCalculatorNonNumericalValues() throws Exception {
        String value1 = "a";
        String value2 = "b";

        HashMap<String, Object> map = new HashMap<>();
        String view = controller.calculator(value1, value2, map);
        assertThat(view, is("calc"));
        assertThat(map.containsKey("values"), is(true));
        assertThat(map.containsKey("message"), is(true));
        assertThat(map.get("values"), is(value1 + " + " + value2));
        assertThat(map.get("message"), is(error));
    }

    /**
     * Performs test with null values
     */

    @Test
    public void testCalculatorNullValues() throws Exception {
        String value1 = null;
        String value2 = null;

        HashMap<String, Object> map = new HashMap<>();
        String view = controller.calculator(value1, value2, map);
        assertThat(view, is("calc"));
        assertThat(map.containsKey("values"), is(true));
        assertThat(map.containsKey("message"), is(true));
        assertThat(map.get("values"), is(value1 + " + " + value2));
        assertThat(map.get("message"), is(error));
    }

    /**
     * Performs test with empty values
     */

    @Test
    public void testCalculatorEmptyValues() throws Exception {
        String value1 = "";
        String value2 = "";

        HashMap<String, Object> map = new HashMap<>();
        String view = controller.calculator(value1, value2, map);
        assertThat(view, is("calc"));
        assertThat(map.containsKey("values"), is(true));
        assertThat(map.containsKey("message"), is(true));
        assertThat(map.get("values"), is(value1 + " + " + value2));
        assertThat(map.get("message"), is(error));
    }
}
