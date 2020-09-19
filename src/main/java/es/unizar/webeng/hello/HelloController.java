package es.unizar.webeng.hello;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Date;
import java.util.Map;

/**
 * Controller class that contains the handler functions for the different requests.
 */
@Controller
public class HelloController {
    @Value("${app.message:Hello World}")
    private String message;

    @Value("${app.name:Hello World}")
    private String defName;

    /**
     * Handler for GET requests to "/" path.
     * <p>
     * This function sets the time, message and name to be displayed on the web
     * 
     * @param model  Object used to share data with the Thymeleaf template
     * @return the name of the template used
     */
    @GetMapping("/")
    public String welcome(Map<String, Object> model) {
        model.put("time", new Date());
        model.put("message", message);
        model.put("name", defName);

        return "wellcome";
    }

    /**
     * Handler for GET requests to "/{name}" path, where "name" can be any string.
     * <p>
     * The "name" parameter is taken directly from the path
     * This function sets the time and message to be displayed on the web
     * 
     * @param model  Object used to share data with the Thymeleaf template
     * @param name  String that contains the name used to build the message
     * @return the name of the template used
     */
    @GetMapping("/{name}")
    public String welcomeName(Map<String, Object> model, @PathVariable String name) {
        model.put("time", new Date());
        model.put("message", "Hola " + name);

        return "wellcome";
    }
}
