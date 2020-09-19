package es.unizar.webeng.hello;

/**
 * Performs unit test to the Wellcome JSP template
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
@WebMvcTest(HelloController.class)
public class HelloControllerUnitTest {

    @Value("${app.message:Hello World}")
    private String message;

    @Autowired
    private HelloController controller;

    /**
     * Performs static unit content test to the
     * main JSP template wellcome (message)
     * <p>
     * This function tests whether the welcome function sets the message info and the message is correct
     */
    @Test
    public void testMessage() throws Exception {
        HashMap<String, Object> map = new HashMap<>();
        String view = controller.welcome(map);
        assertThat(view, is("wellcome"));
        assertThat(map.containsKey("message"), is(true));
        assertThat(map.get("message"), is(message));
    }

    /**
     * Test for the message returned when calling the welcomeName function.
     * <p>
     * This function tests whether the welcomeName function sets the message info 
     *  and the message corresponds with the name sent to the function
     */
    @Test
    public void testName() throws Exception {
        String name = "user";
        HashMap<String, Object> map = new HashMap<>();
        String view = controller.welcomeName(map, name);
        assertThat(view, is("wellcome"));

        assertThat(map.containsKey("message"), is(true));
        assertThat(map.get("message"), is("Hola " + name));
    }
}
