package es.unizar.webeng.hello;

/**
 * The CalculatorController program implements an application that
 * simply performs the sum of two integer values when requested
 *
 * @author Daniel Huici Meseguer (758635)
 * @version 1.0
 * @since 09/17/2020
 *
 */

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Map;

@Controller
public class UltraCalculatorController {

    /**
     * This method is called when requested by GET petition to
     * the calculator webpage (/ultra-calculator). It simply puts
     * default values to a Map so it can be read by view (JSP).
     *
     * @param model This is the map where we put the data
     * @return JSP name view.
     */

    @GetMapping("/ultra-calculator")
    public String calculator(Map<String, Object> model) {
        model.put("values", String.valueOf(0));
        model.put("message", String.valueOf(0));
        return "calc";
    }

    /**
     * This method is called when requested by POST petition to
     * the calculator webpage (/ultra-calculator) with two values.
     * Performs a sum of the values provided if possible and returns
     * the result so it can be read by view (JSP).
     *
     * @param parameter1 First value of the sum
     * @param parameter2 Second value of the sum
     * @param model This is the map where we put the data
     * @return JSP name view with the result of the sum or
     *         "ERROR" in case of non numerical characters
     */

    @RequestMapping(value = "/ultra-calculator", method = RequestMethod.POST, consumes =
            MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String calculator(@RequestParam("value1") String parameter1, @RequestParam("value2") String parameter2,
                             Map<String, Object> model) {
        model.put("values", parameter1 + " + " + parameter2);

        try {
            int first = Integer.parseInt(parameter1);
            int second = Integer.parseInt(parameter2);

            model.put("message", String.valueOf(first + second));
        } catch (NumberFormatException e) {
            model.put("message", "ERROR");
        }

        return "calc";
    }

}
