package es.unizar.webeng.hello;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Martín Gascón - 764429
 *
 * @version 1.0 (9/2020) Martín Gascón
 *  The service is able to convert form binary, hexadecimal, decimal and octal base to a binary, hexadecimal, decimal and octal base.
 *
 * BaseConverterController convert numbers between different bases
 *
 */
@Controller
public class BaseConverterController {

    Map bases;
    BaseConverterController() {
        bases = new HashMap<String, Integer>();
        bases.put("hex", 16);
        bases.put("dec", 10);
        bases.put("oct", 8);
        bases.put("bin", 2);

    }

    /**
     *  Receive the get petitions, in this case it attend
     *  from the request of the main page (/base-calculator).
     * @param model The return data container.
     * @return  Name of the JSP
     */
    @GetMapping("/base-converter")
    public String calculator(Map<String, Object> model) {
        model.put("values", "");
        model.put("error_message", "");
        return "converter";
    }

    /**
     *  Receive the post petitions, in this case it attend
     *  from the conversion requests.
     *  Converts the value passed as parameter into a target base
     * @param originalBase Base of the number to transform.
     * @param targetBase Desired base of the number to transform.
     * @param originalNumber Number to transform
     * @param model The return data container.
     * @return Name of the JSP
     */
  @PostMapping(value = "/base-converter",  consumes =
            MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String converter(@RequestParam("original_base") String originalBase, @RequestParam("target_base") String targetBase,
                             @RequestParam("original_number") String originalNumber,
                             Map<String, Object> model) {
        String retval = null;
        try {

            int decimalNum = Integer.parseInt(originalNumber, (Integer) bases.get(originalBase));

            switch (targetBase){
                case "hex":
                    retval = Integer.toHexString(decimalNum);
                    break;
                case "oct":
                    retval = Integer.toOctalString(decimalNum);
                    break;
                case "dec":
                    retval = String.valueOf(decimalNum);
                    break;
                case "bin":
                    retval = Integer.toBinaryString(decimalNum);
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + targetBase);
            }
             model.put("values", originalNumber + " in base " + originalBase + " to  " + targetBase + " is " + retval);
        } catch (NumberFormatException e) {
            model.put("error_message", "Error");
        }

        return "converter";
    }
}