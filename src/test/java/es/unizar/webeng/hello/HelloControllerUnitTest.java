package es.unizar.webeng.hello;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@WebMvcTest(HelloController.class)
public class HelloControllerUnitTest {

    @Value("${app.message:Press F5 to roll the dice}")
    private String message;

    @Autowired
    private HelloController controller;

    /**
     * Testing doesn't make much sense in this web service, but we'll do a dumb test
     */

    @Test
    public void testMessage() throws Exception {
        HashMap<String, Object> map = new HashMap<>();
        controller.rollTheDice(map);
        assertTrue((map.containsKey("dice")));
        assertTrue(map.containsKey("message"));
        assertEquals(map.get("message"), message);
    }
}
