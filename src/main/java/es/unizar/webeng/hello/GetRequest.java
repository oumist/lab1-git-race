package es.unizar.webeng.hello;

import java.io.Serializable;
import java.util.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/*
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.MalformedURLException;
import java.net.ProtocolException;*/

/**
 * This class is used to zip all about HTTP protocol and GET request
 */
public class GetRequest {

    private JokeResponse res;
    public GetRequest() {
        try {
            RestTemplate rt = new RestTemplate();
            String url = "https://official-joke-api.appspot.com/jokes/programming/random";
            ResponseEntity<JokeResponse[]> entity = rt.getForEntity(url, JokeResponse[].class);
            res = entity.getBody() != null ? Arrays.asList(entity.getBody()).get(0) : null;
        } catch (Exception e) {
            //System.out.println("ERROR CATCH: " + e.toString());
            res = null;
        }
    }

    /**
     * Getter  simplified
     * @return res.toString();
     */
    public String AllJoke(){
        if (res == null) return null;
        return res.toString();
    }

    /**
     * Returns if this joke contains question+response
     * @return boolean
     */
    public boolean isSuccess(){
        if (res == null) return false;
        return res.getSetup() != null && res.getPunchline() != null;
    }

    /**
     * Getter  simplified
     * @return res.getSetup();
     */
    public String plus(){
        if (res == null) return null;
        return res.getSetup();
    }

    /**
     * Getter  simplified
     * @return res.getPunchline();
     */
    public String minus(){
        if (res == null) return null;
        return res.getPunchline();
    }
}