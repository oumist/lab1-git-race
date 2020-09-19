package es.unizar.webeng.hello.lotr;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;

/**
 *
 * @author Luis García Garcés
 * @nia 739202
 *
 */
@Controller
public class RingsQuote {

    /*Classes to contact with the quotes provider API */
    private  HttpClient cliente =HttpClient.newHttpClient();
    private HttpRequest request;
    private HttpResponse<String> response;

    //URI
    private  String API_URI="https://the-one-api.dev/v2/quote";
    //API Key
    private  String API_KEY;

    private  String quoteChosen="";


    /***
     * 
     * @param This method does not need parameters
     * @return "lordrings"
     */
    @GetMapping("/rings-quote")
    public String welcome(Map<String, Object> model) {
        request = HttpRequest.newBuilder()
                .GET()
                .header("Authorization", "Bearer COd1dmY6BQA2Pujr05Bv")
                .uri(URI.create(API_URI))
                .build();
        try {

            response = cliente.send(request,
                    HttpResponse.BodyHandlers.ofString());

            //json parse
            String text=response.body();
            String requiredString = text.substring(text.indexOf("[") + 1, text.indexOf("]"));
            String[] parts = requiredString.split("\\{");

            
            int random_int = (int)(Math.random() * ((parts.length-1)));

            parts[random_int]=parts[random_int].substring(parts[random_int].indexOf("dialog") + 1, parts[random_int].indexOf("movie"));
            parts[random_int]=parts[random_int].substring(parts[random_int].indexOf(":") + 1, parts[random_int].indexOf(","));

            parts[random_int]=parts[random_int].replaceAll("\"","");

            quoteChosen=parts[random_int];

            model.put("prhaseoftherings", quoteChosen);

        }
        //In case it's not possible to establish a connection with the API
        // A default value is set
        catch(Exception error){

            quoteChosen="The API is not working anymore!";

        }

        model.put("prhaseoftherings", quoteChosen);

        return "lordrings";
    }

}
