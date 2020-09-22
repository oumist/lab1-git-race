package es.unizar.webeng.hello;
import es.unizar.webeng.hello.GetRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Date;
import java.util.Map;
import java.util.Random;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


/**
 * The HelloControler class contains the handler functions for the different requests.
 * <p>
 * The program implements an application that
 * simply displays current time and a static welcome message
 * when requested.
 *
 */
@Controller
@Api(value = "Ingeniería Web", description = "API creada para el funcionamento de la web para la práctica 1 de IW")
public class HelloController {

    @Value("${app.message:Press F5 to roll the dice}")
    private String message;
    @Value("${app.joke_const:Hello World}")
    private String joke_const;
    private String link = "Abre este enlace";

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
     * hardcoded value assigned to the attribute "message". Also includes a rolling dice result.
     *
     * @param model the MVC model
     * @return "wellcome", hardcoded
     */
    @GetMapping("/")
    @ApiOperation(value = "Operacion que muestra la hora actual y dos mensajes por pantalla", response = String.class)
    public String rollTheDice(Map<String, Object> model) {

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

        // random is needed to generate the seed
        Random random = new Random();

        // Dice result is calculated between 1 and 6 and storeged in dice
        int dice = random.nextInt(6) + 1;

        model.put("luckyColor", luckyColor());
        model.put("time", new Date());
        model.put("message", message);
        model.put("joke_const", joke_const);
        // Getting jokes with external api
        GetRequest joke = new GetRequest();
        if (joke.isSuccess()){
            model.put("joke_plus", joke.plus());
            model.put("joke_minus", joke.minus());
        } else {
            model.put("joke_plus", "ERROR LOADING JOKES");
            model.put("joke_minus", "ERROR LOADING JOKES");
        }
        model.put("link", link);
        model.put("extra_message","This is an extra message. Im original enough to not make a new funtionality and just add a new message to the typical HELLO WORLD, come on guys amp it up!");
        model.put("dice", dice);
        model.put("java", System.getProperty("java.version"));

        try {
            InetAddress ip = InetAddress.getLocalHost();
            model.put("host", ip.getHostName());
            model.put("ip", ip.getHostAddress());
        } catch (UnknownHostException e) {
            model.put("host", "Cannot get the host name");
            model.put("ip", "Cannot get the host address or IP");
        }
        
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
