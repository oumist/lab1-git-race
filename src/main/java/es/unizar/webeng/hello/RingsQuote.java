package es.unizar.webeng.hello;

import org.springframework.beans.factory.annotation.Value;
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
    @Value("${app.api_key}")
    private  String api_key;

    private  String quoteChosen="";


    /***
     * 
     * @param This method does not need parameters
     * @return "lordrings"
     */
    @GetMapping("/rings-quote")
    public String findLOTRquote(Map<String, Object> model) {
        request = HttpRequest.newBuilder()
                .GET()
                .header("Authorization", api_key)
                .uri(URI.create(API_URI))
                .build();
        try {

            response = cliente.send(request,
                    HttpResponse.BodyHandlers.ofString());

            //json parse
            String text=response.body();
            String requiredString = text.substring(text.indexOf("[") + 1, text.indexOf("]"));
            String[] phrases = requiredString.split("\\{");

            
            int random = (int)(Math.random() * ((phrases.length-1)));

            phrases[random]=phrases[random].substring(phrases[random].indexOf("dialog") + 1, phrases[random].indexOf("movie"));
            phrases[random]=phrases[random].substring(phrases[random].indexOf(":") + 1, phrases[random].indexOf(","));

            phrases[random]=phrases[random].replaceAll("\"","");

            quoteChosen=phrases[random];

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
