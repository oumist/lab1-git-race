package es.unizar.webeng.hello;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.MalformedURLException;
import java.net.ProtocolException;

public class GetRequest {

    private URL urlForGetRequest;
    private String readLine;
    private HttpURLConnection conection;
    private StringBuffer response;

    public GetRequest() throws IOException {
        super();
        urlForGetRequest = new URL("https://official-joke-api.appspot.com/jokes/programming/random");
        readLine = null;
        conection = (HttpURLConnection) urlForGetRequest.openConnection();
        conection.setRequestMethod("GET");
        conection.setRequestProperty("userId", "a1bcdef"); // set userId its a sample here
        int responseCode = conection.getResponseCode();
        response = null;
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(new InputStreamReader(conection.getInputStream()));
            response = new StringBuffer();
            while ((readLine = in .readLine()) != null) {
                response.append(readLine);
            } in .close();
        }
    }

    public String getJSON() {
        if (response != null) return response.toString();
        else return null;
    }
}