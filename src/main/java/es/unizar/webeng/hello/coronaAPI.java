package es.unizar.webeng.hello;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
 * This class is used to zip all about HTTP protocol and GET request
 */
public class coronaAPI {

    private coronaDTO res;
    public coronaAPI() {
        try {
            RestTemplate rt = new RestTemplate();
            String url = "https://api.covid19api.com/world/total";
            ResponseEntity<String> response = rt.getForEntity(url, String.class);
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(response.getBody());
            int totalConfirmed = root.path("TotalConfirmed").asInt();
            int totalDeaths = root.path("TotalDeaths").asInt();
            int totalRecovered = root.path("TotalRecovered").asInt();
            res = new coronaDTO();
            res.setTotalConfirmed(totalConfirmed);
            res.setTotalDeaths(totalDeaths);
            res.setTotalRecovered(totalRecovered);
        } catch (Exception e) {
            //System.out.println("ERROR CATCH: " + e.toString());
            res = null;
        }
    }


    /**
     * Returns if the information received is ok
     * @return boolean
     */
    public boolean isSuccess(){
        if (res == null) return false;
        return res.getTotalConfirmed() != 0 && res.getTotalRecovered() != 0 && res.getTotalDeaths() != 0;
    }


    /**
     * Getter  simplified
     * @return res.getTotalConfirmed();
     */
    public int totalConfirmed(){
        if (res == null) return 0;
        return res.getTotalConfirmed();
    }

    /**
     * Getter  simplified
     * @return res.gettotalDeaths();
     */
    public int totalDeaths(){
        if (res == null) return 0;
        return res.getTotalDeaths();
    }

    /**
     * Getter  simplified
     * @return res.getTotalRecovered();
     */
    public int totalRecovered(){
        if (res == null) return 0;
        return res.getTotalRecovered();
    }
}