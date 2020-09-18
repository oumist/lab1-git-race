package es.unizar.webeng.hello;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Date;
import java.util.Map;

@Controller
public class HelloController {
    @Value("${app.message:Hello World}")
    private String message;

    @GetMapping("/")
    public String welcome(Map<String, Object> model) {
        model.put("time", new Date());
        model.put("message", message);
        model.put("extra_message","This is an extra message. Im original enough to not make a new funtionality and just add a new message to the typical HELLO WORLD, come on guys amp it up!");
        return "wellcome";
    }
}
