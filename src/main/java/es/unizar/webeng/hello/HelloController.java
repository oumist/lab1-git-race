package es.unizar.webeng.hello;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Date;
import java.util.Map;
import java.text.SimpleDateFormat;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * The HelloControler program implements an application that
 * simply displays current time and a static welcome message
 * when requested.
 *
 */
@Api(value = "Ingeniería Web", description = "API creada para el funcionamento de la web para la práctica 1 de IW")
@Controller
public class HelloController {
    @Value("${app.message:Hello World}")
    private String message;

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
    @ApiOperation(value = "Operacion que muestra la hora actual y dos mensajes por pantalla", response = String.class)
    @GetMapping("/")
    public String welcome(Map<String, Object> model) {

        /* Current day in integer form */
        Integer num = Integer.parseInt(currentDay); 
        if(num % 5 == 0 ){
            model.put("luckyColor", "black");
        } else if(num % 2 == 0){
            model.put("luckyColor", "blue");
        } else if(num % 3 == 0) {
            model.put("luckyColor", "white");
        } else {
            model.put("luckyColor", "red");
        }

        model.put("time", new Date());
        model.put("message", message);
        model.put("extra_message","This is an extra message. Im original enough to not make a new funtionality and just add a new message to the typical HELLO WORLD, come on guys amp it up!");
        return "wellcome";
    }
}