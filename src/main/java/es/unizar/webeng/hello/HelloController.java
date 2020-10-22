package es.unizar.webeng.hello;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.util.Date;
import java.util.Map;
import java.util.Random;
import java.text.SimpleDateFormat;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.client.RestTemplate;


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

    @Value("${app.weather_api_key}")
    private String weather_api_key;
    private String weather_API = "http://api.openweathermap.org/data/2.5/forecast?id=3104324&appid=";
    private String weather_options = "&lang=es&units=metric";
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
     * hardcoded value assigned to the attribute "message". Includes a rolling dice result, and the
     * current weather for Zaragoza in Celsius.
     *
     *
     * @param model the MVC model
     * @return "wellcome", hardcoded
     */
    @GetMapping("/")
    @ApiOperation(value = "Operacion que muestra la hora actual y dos mensajes por pantalla", response = String.class)
    public String rollTheDice(Map<String, Object> model) throws JsonProcessingException {

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
        // Random class is part of java.util package and creates a class that will be used later to choose randomly a number 
        // that will be used to get the variable dice 
        Random random = new Random();


        // Dice result is calculated between 1 and 6 and storeged in dice
        int dice = random.nextInt(6) + 1;

        // Adding to the interface model the following parameters
        model.put("luckyColor", luckyColor());
        model.put("time", new Date());
        model.put("message", message);
        model.put("joke_const", joke_const);
        // Getting jokes with external api
        // If joke is success, we do the following implementation to the object model
        GetRequest joke = new GetRequest();
        if (joke.isSuccess()){
            model.put("joke_plus", joke.plus());
            model.put("joke_minus", joke.minus());
        } else {
            // If the joke does not success, we add an error message to the object model
            model.put("joke_plus", "ERROR LOADING JOKES");
            model.put("joke_minus", "ERROR LOADING JOKES");
        }
        model.put("link", link);
        model.put("extra_message","This is an extra message. Im original enough to not make a new funtionality and just add a new message to the typical HELLO WORLD, come on guys amp it up!");
        model.put("dice", dice);
        model.put("java", System.getProperty("java.version"));      

        try {
            // The variable ip gets the IP of the router that executes the action, to take the information of the
            // name and the address of the IP. In case the information is incorrect or is not possible to get the results, an exception will be launched.
            InetAddress ip = InetAddress.getLocalHost();
            model.put("host", ip.getHostName());
            model.put("ip", ip.getHostAddress());
        } catch (UnknownHostException e) {
            model.put("host", "Cannot get the host name");
            model.put("ip", "Cannot get the host address or IP");
        }


        //OpenWeather API
        if(weather_api_key.equals("NO_KEY")) {
            System.out.println("Give a valid API key for the weather information");
        } else {
            weather_API = weather_API + weather_api_key + weather_options;
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> response
                    = restTemplate.getForEntity(weather_API, String.class);
            if (response.getStatusCode() == HttpStatus.OK) {
                ObjectMapper mapper = new ObjectMapper();
                JsonNode actualObj = mapper.readTree(response.getBody());

                JsonNode temp = actualObj.path("list").get(1).path("main").path("temp");
                model.put("temperature", temp.asText());
            } else {
                model.put("temperature", "APIDoesNotRespond");
            }
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
        //return the string Wellcome
    }
}
