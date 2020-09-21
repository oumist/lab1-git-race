package es.unizar.webeng.hello;

/**
 * Performs unit test to the PasswordController
 *
 * @author Alvaro Garcia Diaz (760704)
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
@WebMvcTest(PasswordController.class)
public class PasswordControllerUnitTest {

    @Value("${app.error}")
    private String error;

    @Autowired
    private PasswordController controller;

    /**
     * Performs a test with an empty string
     */

    @Test
    public void testPasswordEmpty() throws Exception {
        HashMap<String, Object> map = new HashMap<>();
        String view = controller.passGen("", map);
        assertThat(view, is("pass"));
        assertThat(map.containsKey("password"), is(false));
    }

    /**
     * Performs a test with a valid string
     */

    @Test
    public void testPasswordCorrect() throws Exception {
        HashMap<String, Object> map = new HashMap<>();
        String view = controller.passGen("prove", map);
        assertThat(view, is("showpass"));
        assertThat(map.containsKey("password"), is(true));
        String aux = (String)map.get("password");
        assert aux.length() > 0;
    }

    /**
     * Performs a test with strange characters
     */

     @Test
     public void testPasswordStrange() throws Exception {
         HashMap<String, Object> map = new HashMap<>();
         String view = controller.passGen("キターȓ̷͓͉͈̺̿e̷͖̔̂̀̚", map);
         assertThat(view, is("showpass"));
         assertThat(map.containsKey("password"), is(true));
         String aux = (String)map.get("password");
         assert aux.length() > 0;
     }

}
