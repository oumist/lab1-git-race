package es.unizar.webeng.hello;

import org.springframework.stereotype.Controller;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
import java.util.Scanner;
import java.util.StringTokenizer;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * The TextCounterController program implements an application that
 * simply counts the number of words of an input text.
 *
 * @author David Alloza Tejero (761754)
 * @version 1.0
 * @since 09/24/2020
 */

@Controller
public class TextCounterController {
    
    /**
     * This method is called when requested by GET petition to
     * the "text word counter" webpage (/textCounter). It simply put
     * a default value to a Map so it can be read by view (the jsp file).
     *
     * @param model The MVC model
     * @return JSP name view.
     */
    
    @GetMapping("/textCounter")
    public String textCounter(Map<String, Object> model) {
        model.put("numPalabras", String.valueOf(0));
        return "textc";
    }

    /**
     * This method is called when requested by POST petition to
     * the "text word counter" webpage (/textCounter) with a String value.
     * Calculates the total words of the string provided if possible and
     * reutrns the result so it can be read by view (the jsp file).
     *
     * @param text The String that represents the input text
     * @param model The MVC model
     * @return JSP name view.
     */
    
    @ApiOperation(value = "Operation that calculates the total words of a string", response = String.class)
    @RequestMapping(value = "/textCounter", method = RequestMethod.POST, consumes =
                    MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String textCounter(@RequestParam("text") String text, Map<String, Object> model) {
        try{
            StringTokenizer s = new StringTokenizer(text);
            int numPalabras = s.countTokens();
            model.put("numPalabras", String.valueOf(numPalabras));
            
        }catch (NumberFormatException e) {
            model.put("numPalabras", "ERROR");
        }
        return "textc";
    }
}
