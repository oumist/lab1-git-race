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

    private class JokeResponse implements Serializable{
        private int id;
        private String minus;
        private String plus;

        public JokeResponse(int id, String pl, String min){
            this.id = id;
            this.plus = pl;
            this.minus = min;
        }

        public int getId(){
            return id;
        }
        public String getPlus(){
            return plus;
        }
        public String getMinus(){
            return minus;
        }
        @Override
        public int hashCode(){
            return Objects.hash(id, plus, minus);
        }
        @Override
        public String toString(){
            return "[" + id + "]:(+ " + plus + ", - " + minus + ")";
        }
        @Override
        public boolean equals(Object obj){
            if (this == obj) return true;
            if (obj == null) return false;
            JokeResponse other = (JokeResponse) obj;
            if (id != other.id) return false;
            return true;
        }
    }

    private JokeResponse res;
    public GetRequest() {
        RestTemplate rt = new RestTemplate();
        String url = "https://official-joke-api.appspot.com/jokes/programming/random";
        ResponseEntity<JokeResponse[]> entity = rt.getForEntity(url, JokeResponse[].class);
        res = entity.getBody() != null ? Arrays.asList(entity.getBody()).get(0) : null;
    }

    public String AllJoke(){
        if (res == null) return null;
        return res.toString();
    }
    public boolean isSuccess(){
        if (res == null) return false;
        return res.getPlus() != null && res.getMinus() != null;
    }
    public String plus(){
        if (res == null) return null;
        return res.getPlus();
    }
    public String minus(){
        if (res == null) return null;
        return res.getMinus();
    }
}