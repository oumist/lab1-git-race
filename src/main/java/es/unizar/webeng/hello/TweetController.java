package es.unizar.webeng.hello;

import com.fasterxml.jackson.databind.JsonNode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import org.thymeleaf.spring5.context.webflux.ReactiveDataDriverContextVariable;
import reactor.core.publisher.Flux;

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
            Flux<Tweet> res = client.get()
                    .uri("?screen_name=" + user + "&count=5")
                    .header("authorization", "Bearer " + twitter_bearer)
                    .retrieve()
                    .bodyToFlux(Tweet.class);

            model.addAttribute("user", user);
            model.addAttribute("tweets", new ReactiveDataDriverContextVariable(res, 5));

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
     * Representation of a tweet.
     * It contains only the text of a tweet.
     * <p>
     * It could be improved with annotations from <a href="https://projectlombok.org/">Project Lombok</a> not to have to
     * write constructors, getters and setters.
     */
    public static class Tweet {

        private String created_at;
        private String id;
        private String id_str;
        public String text;
        private Boolean truncated;
        private JsonNode entities;
        private String source;
        private String in_reply_to_status_id;
        private String in_reply_to_status_id_str;
        private String in_reply_to_user_id;
        private String in_reply_to_user_id_str;
        private String in_reply_to_screen_name;
        private JsonNode user;
        private String geo;
        private String coordinates;
        private String place;
        private String contributors;
        private Boolean is_quote_status;
        private String quoted_status_id;
        private String quoted_status_id_str;
        private JsonNode quoted_status;
        private String retweet_count;
        private String favorite_count;
        private String favorited;
        private String retweeted;
        private String possibly_sensitive;
        private String lang;

        public Tweet() {
        }

        public Tweet(String created_at, String id, String id_str, String text, Boolean truncated, JsonNode entities,
                     String source, String in_reply_to_status_id, String in_reply_to_status_id_str,
                     String in_reply_to_user_id, String in_reply_to_user_id_str, String in_reply_to_screen_name,
                     JsonNode user, String geo, String coordinates, String place, String contributors,
                     Boolean is_quote_status, String quoted_status_id, String quoted_status_id_str,
                     JsonNode quoted_status, String retweet_count, String favorite_count, String favorited,
                     String retweeted, String possibly_sensitive, String lang) {
            this.created_at = created_at;
            this.id = id;
            this.id_str = id_str;
            this.text = text;
            this.truncated = truncated;
            this.entities = entities;
            this.source = source;
            this.in_reply_to_status_id = in_reply_to_status_id;
            this.in_reply_to_status_id_str = in_reply_to_status_id_str;
            this.in_reply_to_user_id = in_reply_to_user_id;
            this.in_reply_to_user_id_str = in_reply_to_user_id_str;
            this.in_reply_to_screen_name = in_reply_to_screen_name;
            this.user = user;
            this.geo = geo;
            this.coordinates = coordinates;
            this.place = place;
            this.contributors = contributors;
            this.is_quote_status = is_quote_status;
            this.quoted_status_id = quoted_status_id;
            this.quoted_status_id_str = quoted_status_id_str;
            this.quoted_status = quoted_status;
            this.retweet_count = retweet_count;
            this.favorite_count = favorite_count;
            this.favorited = favorited;
            this.retweeted = retweeted;
            this.possibly_sensitive = possibly_sensitive;
            this.lang = lang;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getId_str() {
            return id_str;
        }

        public void setId_str(String id_str) {
            this.id_str = id_str;
        }

        public Boolean getTruncated() {
            return truncated;
        }

        public void setTruncated(Boolean truncated) {
            this.truncated = truncated;
        }

        public JsonNode getEntities() {
            return entities;
        }

        public void setEntities(JsonNode entities) {
            this.entities = entities;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getIn_reply_to_status_id() {
            return in_reply_to_status_id;
        }

        public void setIn_reply_to_status_id(String in_reply_to_status_id) {
            this.in_reply_to_status_id = in_reply_to_status_id;
        }

        public String getIn_reply_to_status_id_str() {
            return in_reply_to_status_id_str;
        }

        public void setIn_reply_to_status_id_str(String in_reply_to_status_id_str) {
            this.in_reply_to_status_id_str = in_reply_to_status_id_str;
        }

        public String getIn_reply_to_user_id() {
            return in_reply_to_user_id;
        }

        public void setIn_reply_to_user_id(String in_reply_to_user_id) {
            this.in_reply_to_user_id = in_reply_to_user_id;
        }

        public String getIn_reply_to_user_id_str() {
            return in_reply_to_user_id_str;
        }

        public void setIn_reply_to_user_id_str(String in_reply_to_user_id_str) {
            this.in_reply_to_user_id_str = in_reply_to_user_id_str;
        }

        public String getIn_reply_to_screen_name() {
            return in_reply_to_screen_name;
        }

        public void setIn_reply_to_screen_name(String in_reply_to_screen_name) {
            this.in_reply_to_screen_name = in_reply_to_screen_name;
        }

        public JsonNode getUser() {
            return user;
        }

        public void setUser(JsonNode user) {
            this.user = user;
        }

        public String getGeo() {
            return geo;
        }

        public void setGeo(String geo) {
            this.geo = geo;
        }

        public String getCoordinates() {
            return coordinates;
        }

        public void setCoordinates(String coordinates) {
            this.coordinates = coordinates;
        }

        public String getPlace() {
            return place;
        }

        public void setPlace(String place) {
            this.place = place;
        }

        public String getContributors() {
            return contributors;
        }

        public void setContributors(String contributors) {
            this.contributors = contributors;
        }

        public Boolean getIs_quote_status() {
            return is_quote_status;
        }

        public void setIs_quote_status(Boolean is_quote_status) {
            this.is_quote_status = is_quote_status;
        }

        public String getQuoted_status_id() {
            return quoted_status_id;
        }

        public void setQuoted_status_id(String quoted_status_id) {
            this.quoted_status_id = quoted_status_id;
        }

        public String getQuoted_status_id_str() {
            return quoted_status_id_str;
        }

        public void setQuoted_status_id_str(String quoted_status_id_str) {
            this.quoted_status_id_str = quoted_status_id_str;
        }

        public JsonNode getQuoted_status() {
            return quoted_status;
        }

        public void setQuoted_status(JsonNode quoted_status) {
            this.quoted_status = quoted_status;
        }

        public String getRetweet_count() {
            return retweet_count;
        }

        public void setRetweet_count(String retweet_count) {
            this.retweet_count = retweet_count;
        }

        public String getFavorite_count() {
            return favorite_count;
        }

        public void setFavorite_count(String favorite_count) {
            this.favorite_count = favorite_count;
        }

        public String getFavorited() {
            return favorited;
        }

        public void setFavorited(String favorited) {
            this.favorited = favorited;
        }

        public String getRetweeted() {
            return retweeted;
        }

        public void setRetweeted(String retweeted) {
            this.retweeted = retweeted;
        }

        public String getPossibly_sensitive() {
            return possibly_sensitive;
        }

        public void setPossibly_sensitive(String possibly_sensitive) {
            this.possibly_sensitive = possibly_sensitive;
        }

        public String getLang() {
            return lang;
        }

        public void setLang(String lang) {
            this.lang = lang;
        }
    }
}

