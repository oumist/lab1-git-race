package es.unizar.webeng.hello;
import es.unizar.webeng.hello.GetRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Date;
import java.util.Map;


@Controller
public class HelloController {
    @Value("${app.message:Hello World}")
    private String message;
    @Value("${app.joke_const:Hello World}")
    private String joke_const;

    @GetMapping("/")
    public String welcome(Map<String, Object> model) {
        model.put("time", new Date());
        model.put("message", message);
        model.put("joke_const", joke_const);
        GetRequest joke = null;
        // Getting programming joke from external api
        try {
            joke = new GetRequest();
        } catch (Exception e) {
            //efe
        }
        if (joke != null){
            String[] parts = joke.getJSON().split(",");
            String plus = null, minus = null;
            for (String i: parts){
                if(i.startsWith("\"setup\"") && plus == null) plus = i.substring(9,i.length()-1);
                if(i.startsWith("\"punchline\"") && minus == null) minus = i.substring(13,i.length()-3);
            }
            // Parse failed??
            if (plus == null || minus == null){
                model.put("joke_plus", "ERROR");
                model.put("joke_mins", "ERROR");
            } else {
                model.put("joke_plus", plus);
                model.put("joke_mins", minus);
            }
        }else{
            model.put("joke_plus", "ERROR");
            model.put("joke_mins", "ERROR");
        }
        return "wellcome";
    }
}
