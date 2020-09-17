package es.unizar.webeng.hello;

/**
 * The HelloControler program implements an application that
 * simply displays current time and a static welcome message
 * when requested.
 *
 */

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Date;
import java.util.Map;

@Controller
public class HelloController {
    @Value("${app.message:Hello World}")
    private String message;

    /**
     * This method is called when requested by GET petition to
     * main webpage (/). Puts current time and a message on a
     * Map so it can be read by view (JSP)
     *
     * @param model This is the map where we put the data
     * @return JSP name view.
     */

    @GetMapping("/")
    public String welcome(Map<String, Object> model) {
        model.put("time", new Date());
        model.put("message", message);
        return "wellcome";
    }
}
