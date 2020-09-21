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
        assertThat(map.containsKey("len"), is(true));
        assertThat(map.containsKey("upp"), is(true));
        assertThat(map.containsKey("low"), is(true));
        assertThat(map.containsKey("spe"), is(true));
        assertThat(map.containsKey("num"), is(true));
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
        assertThat(map.containsKey("len"), is(true));
        assertThat(map.containsKey("upp"), is(true));
        assertThat(map.containsKey("low"), is(true));
        assertThat(map.containsKey("spe"), is(true));
        assertThat(map.containsKey("num"), is(true));
        String aux = (String)map.get("password");
        assert aux.length() > 0;
    }

    /**
     * Performs a test with negative numbers
     */

    @Test
    public void testModifyPasswordNegative() throws Exception {
        HashMap<String, Object> map = new HashMap<>();
        String view = controller.modifyPass("0", "-5", "4", "3", "0", "82466", map);
        assertThat(view, is("showpass"));
        assertThat(map.containsKey("password"), is(true));
        assertThat(map.containsKey("len"), is(true));
        assertThat(map.containsKey("upp"), is(true));
        assertThat(map.containsKey("low"), is(true));
        assertThat(map.containsKey("spe"), is(true));
        assertThat(map.containsKey("num"), is(true));
        String aux = (String)map.get("password");
        int len = aux.length(), low = 4, upp = 3, spe = 0, num = 5;
        assert len > 0;
        for(int i = 0; i < len; ++i) {
            if(Character.isUpperCase(aux.charAt(i))) --upp;
            else if(Character.isLowerCase(aux.charAt(i))) --low;
            else if(Character.isDigit(aux.charAt(i))) --num;
            else --spe;
        }
        assert low == 0;
        assert upp == 0;
        assert spe == 0;
        assert num == 0;
    }

    /**
     * Performs a test with a valid case
     */

    @Test
    public void testModifyPasswordCorrect() throws Exception {
        HashMap<String, Object> map = new HashMap<>();
        String view = controller.modifyPass("0", "2", "2", "2", "2", "123456", map);
        assertThat(view, is("showpass"));
        assertThat(map.containsKey("password"), is(true));
        assertThat(map.containsKey("len"), is(true));
        assertThat(map.containsKey("upp"), is(true));
        assertThat(map.containsKey("low"), is(true));
        assertThat(map.containsKey("spe"), is(true));
        assertThat(map.containsKey("num"), is(true));
        String aux = (String)map.get("password");
        int len = aux.length(), low = 2, upp = 2, spe = 2, num = 8;
        assert len > 0;
        for(int i = 0; i < len; ++i) {
            if(Character.isUpperCase(aux.charAt(i))) --upp;
            else if(Character.isLowerCase(aux.charAt(i))) --low;
            else if(Character.isDigit(aux.charAt(i))) --num;
            else --spe;
        }
        assert low == 0;
        assert upp == 0;
        assert spe == 0;
        assert num == 0;
    }

    /**
     * Performs a test adding random characters
     */

    @Test
    public void testModifyPasswordRandom() throws Exception {
        HashMap<String, Object> map = new HashMap<>();
        String view = controller.modifyPass("5", "0", "0", "0", "0", "123456", map);
        assertThat(view, is("showpass"));
        assertThat(map.containsKey("password"), is(true));
        assertThat(map.containsKey("len"), is(true));
        assertThat(map.containsKey("upp"), is(true));
        assertThat(map.containsKey("low"), is(true));
        assertThat(map.containsKey("spe"), is(true));
        assertThat(map.containsKey("num"), is(true));
        String aux = (String)map.get("password");
        int len = aux.length(), low = 0, upp = 0, spe = 0, num = 6;
        assert len > 0;
        for(int i = 0; i < len; ++i) {
            if(Character.isUpperCase(aux.charAt(i))) --upp;
            else if(Character.isLowerCase(aux.charAt(i))) --low;
            else if(Character.isDigit(aux.charAt(i))) --num;
            else --spe;
        }
        assert low <= 0;
        assert upp <= 0;
        assert spe <= 0;
        assert num <= 0;
    }

}
