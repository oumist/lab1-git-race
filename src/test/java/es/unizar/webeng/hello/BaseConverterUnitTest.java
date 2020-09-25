package es.unizar.webeng.hello;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.*;
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
@RunWith(SpringRunner.class)
@WebMvcTest(BaseConverterController.class)
public class BaseConverterUnitTest {

    @Autowired
    private BaseConverterController controller;

    @Value("${app.messageError:Error}")
    private String messageError;

    enum Result {
        OK,NOK
    }

    @Test
    public void illegalStringHexToDecTest(){
        defaultTest("hex", "","dec", "", Result.NOK);;
    }
    @Test
    public void illegalStringDecToOctTest(){
        defaultTest("dec", "z","oct", "", Result.NOK);;
    }
    @Test
    public void illegalStringOctToBinTest(){
        defaultTest("oct", "z","bin", "", Result.NOK);
    }
    @Test
    public void illegalStringBinToHexTest(){
        defaultTest("bin", "z","hex", "", Result.NOK);
    }
    @Test
    public void HexToDecTest(){
        defaultTest("hex", "A1", "dec", "161", Result.OK);;
    }
    @Test
    public void hexToOctTest(){
        defaultTest("hex", "A1", "oct", "241", Result.OK);;
    }
    @Test
    public void hexToBinTest(){
        defaultTest("hex", "A1", "bin", "10100001", Result.OK);
    }
    @Test
    public void decToOctTest(){
        defaultTest("dec", "17", "oct", "21", Result.OK);
    }
    @Test
    public void decToBinTest(){
        defaultTest("dec", "17", "bin", "10001", Result.OK);
    }
    @Test
    public void decToHexTest(){
        defaultTest( "dec", "17", "hex", "11", Result.OK);
    }
    @Test
    public void octToDecTest(){
        defaultTest("oct", "11", "dec", "9", Result.OK);
    }
    @Test
    public void octToBinTest(){
        defaultTest("oct", "11", "bin", "1001", Result.OK);;
    }
    @Test
    public void octToHexTest(){
        defaultTest("oct", "11", "hex", "9", Result.OK);;
    }
    @Test
    public void binToDecTest(){
        defaultTest("bin", "10", "dec", "2", Result.OK);;
    }
    @Test
    public void binToOctTest(){
        defaultTest("bin", "10", "oct", "2", Result.OK);;
    }
    @Test
    public void binToHexTest(){
        defaultTest("bin", "10", "hex", "2", Result.OK);;
    }

    public void defaultTest(String originalBase, String originalNumber, String targetBase, String resultNumber, Result result) {
        Map<String,Object> model = new HashMap<>();
        String message = messageError;
        String values = null;
        controller.converter(originalBase,targetBase,originalNumber, model);

        if(result == Result.OK) {
            message = null;
            values = originalNumber + " in base " + originalBase + " to  " + targetBase + " is " + resultNumber;
        }

        assertEquals(values, (String)model.get("values"));
        assertEquals(message, model.get("error_message"));

    }
}