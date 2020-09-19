package es.unizar.webeng.hello;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Date;
import java.util.Map;
import java.text.SimpleDateFormat;

/**
 * The HelloControler class contains the handler functions for the different requests.
 * <p>
 * The program implements an application that
 * simply displays current time and a static welcome message
 * when requested.
 *
 */
@Controller
public class HelloController {
    @Value("${app.message:Hello World}")
    private String message;

    private String luckyColor(){
        
        /* Current day in integer form */
        Integer num = Integer.parseInt(currentDay); 

        if(num % 5 == 0 ){
            return "black";
        }
        
        if(num % 2 == 0){
            return "blue";
        }
        
        if(num % 3 == 0) {
            return "white";
        }
            
        return "red";
    }

    /* Current day in string form */
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private String currentDay = sdf.format(new Date()).substring(0,2);

    /**
     * A controller method which is called when the root endpoint is ordered by a client.
     *
     * It modifies the model, setting into the key "time" the actual date and into the key "message" the
     * hardcoded value assigned to the attribute "message".
     *
     * @param model the MVC model
     * @return "wellcome", hardcoded
     */
    @GetMapping("/")
    public String welcome(Map<String, Object> model) {

        model.put("luckyColor", luckyColor());
        model.put("time", new Date());
        model.put("message", message);

        return "wellcome";
    }

    /**
     * Handler for GET requests to "/{name}" path, where "name" can be any string.
     * <p>
     * The "name" parameter is taken directly from the path
     * This function sets the time and message to be displayed on the web
     * 
     * @param model  The MVC model
     * @param name  String that contains the name used to build the message
     * @return the name of the template used
     */
    @GetMapping("/{name}")
    public String welcomeName(Map<String, Object> model, @PathVariable String name) {

        model.put("luckyColor", luckyColor());
        model.put("time", new Date());
        model.put("message", "Hola " + name);

        return "wellcome";
    }
}
