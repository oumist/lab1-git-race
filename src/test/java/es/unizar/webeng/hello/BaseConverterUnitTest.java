package es.unizar.webeng.hello;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 *  Test cases:
 *  Convert a String value from decimal to another base |   expected: Error
 *  Convert a String value from binary to another base  |   expected: Error
 *
 *  Convert a no hexadecimal value to another base      |   expected: Error
 *  Convert a no octal value to another base            |   expected: Error
 *  Convert a hexadecimal number to another base        |   expected: Number in target base
 */
@RunWith(Parameterized.class)
@WebMvcTest(BaseConverterController.class)
public class BaseConverterUnitTest {

    private enum Result{
        OK,NOK
    }

    @Parameterized.Parameters
    public static Iterable<Object[]> data() {
        return Arrays.asList(
                new Object[] {"hex", "","dec", "", Result.NOK},
                new Object[] {"dec", "z","oct", "", Result.NOK},
                new Object[] {"oct", "z","bin", "", Result.NOK},
                new Object[] {"bin", "z","hex", "", Result.NOK},

                new Object[] {"hex", "A1", "dec", "161", Result.OK},
                new Object[] {"hex", "A1", "oct", "241", Result.OK},
                new Object[] {"hex", "A1", "bin", "10100001", Result.OK},
                new Object[] {"dec", "17", "oct", "21", Result.OK},
                new Object[] {"dec", "17", "bin", "10001", Result.OK},
                new Object[] {"dec", "17", "hex", "11", Result.OK},
                new Object[] {"oct", "11", "dec", "9", Result.OK},
                new Object[] {"oct", "11", "bin", "1001", Result.OK},
                new Object[] {"oct", "11", "hex", "9", Result.OK},
                new Object[] {"bin", "10", "dec", "2", Result.OK},
                new Object[] {"bin", "10", "oct", "2", Result.OK},
                new Object[] {"bin", "10", "hex", "2", Result.OK});
    }

    @Parameterized.Parameter(0)
    public String originalBase;
    @Parameterized.Parameter(1)
    public String originalNumber;
    @Parameterized.Parameter(2)
    public String targetBase;
    @Parameterized.Parameter(3)
    public String resultNumber;
    @Parameterized.Parameter(4)
    public Result result;

    @Test
    public void createNoteTest() {
        Map<String,Object> model = new HashMap<>();
        String message = "Error";
        String values = null;
        BaseConverterController c = new BaseConverterController();

        String retval = c.converter(originalBase,targetBase,originalNumber, model);

        if(result == Result.OK) {
            message = null;
            values = originalNumber + " in base " + originalBase + " to  " + targetBase + " is " + resultNumber;
        }

        Assert.assertEquals(values, (String)model.get("values"));
        Assert.assertEquals(message, model.get("error_message"));

    }
}