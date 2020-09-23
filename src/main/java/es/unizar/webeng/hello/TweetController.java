package es.unizar.webeng.hello;

import com.google.gson.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.ArrayList;
import java.util.List;

/**
 * Controller for Twitter Timeline.
 * It uses WebClient to make synchronous requests to the Twitter API.
 *
 * @see <a href="https://www.arquitecturajava.com/flux-vs-mono-spring-y-la-programacion-reactiva">Flux vs Mono, Spring y Programacion Reactiva</a>
 * @see <a href="https://www.callicoder.com/reactive-rest-apis-spring-webflux-reactive-mongo/">Reactive REST APIs</a>
 */
@Controller
@Api(value = "Twitter Timeline")
public class TweetController {

    @Value("${app.twitter_api_key}")
    private String twitter_api_key;

    @Value("${app.twitter_api_secret_key}")
    private String twitter_api_secret_key;

    @Value("${app.twitter_bearer}")
    private String twitter_bearer;

    /**
     * This method is called when requested by GET petition to
     * the twitter endpoint of this webpage (/twitter/{user}).
     * It prints the last 5 tweets of the user if Twitter API
     * credentials are correct and user exists. If not, it displays
     * the error.
     *
     * @param model Interface for holding attributes and
     *              accessing them from Thymeleaf templates
     * @param user  twitter user whose tweets will be retrieved
     * @return Thymeleaf name view corresponding to this controller
     */
    @GetMapping("/twitter/{user}")
    @ApiOperation(value = "Shows last 5 tweets of a user", response = String.class)
    public String tweet(Model model, @PathVariable String user) {

        WebClient client = WebClient
                .builder()
                .baseUrl("https://api.twitter.com/1.1/statuses/user_timeline.json")
                .build();

        try {
            String res = client.get()
                    //.uri("/users/by?usernames="+user)
                    .uri("?screen_name=" + user + "&count=5")
                    .header("authorization", "Bearer " + twitter_bearer)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();

            model.addAttribute("user", user);
            model.addAttribute("tweets", transformResponse(res, "text"));

        } catch (WebClientResponseException wcre) {
            if (wcre.getRawStatusCode() == 404) {
                model.addAttribute("error", "Could not find the user: " + user);
            } else if (wcre.getRawStatusCode() == 401) {
                model.addAttribute("error", "Unauthorized access to the Twitter API. Check the credentials");
            } else {
                model.addAttribute("error", "wcre.getStatusText()");
            }
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
        }

        return "tweet";
    }

    /**
     * Transforms the response of Twitter API into a list of tweets.
     * A tweet is composed of the text of the tweet.
     *
     * @param res   Twitter API response in JSON format
     * @param field the json field to retrieve from the response
     * @return list of the tweets
     */
    private List<Tweet> transformResponse(String res, String field) {
        Gson gson = new Gson();
        JsonArray jsonArray = gson.fromJson(res, JsonArray.class);
        List<Tweet> tweetList = new ArrayList<Tweet>();

        for (JsonElement jsonElement : jsonArray) {
            tweetList.add(new Tweet(jsonElement.getAsJsonObject().get(field).getAsString()));
        }

        return tweetList;
    }

    /**
     * Representation of a tweet.
     * It contains only the text of a tweet.
     */
    class Tweet {
        private String text;

        public Tweet(String text) {
            this.text = text;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }
    }
}

