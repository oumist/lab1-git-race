package es.unizar.webeng.hello;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Date;
import java.util.Map;

@Controller
public class HelloController {
    @Value("${app.message:Hello World}")
    private String message;

    @Value("${app.name:Hello World}")
    private String defName;

    @GetMapping("/")
    public String welcome(Map<String, Object> model) {
        model.put("time", new Date());
        model.put("message", message);
        model.put("name", defName);

        return "wellcome";
    }

    @GetMapping("/{name}")
    public String welcomeName(Map<String, Object> model, @PathVariable String name) {
        model.put("time", new Date());
        model.put("message", "Hola " + name);

        return "wellcome";
    }
}
