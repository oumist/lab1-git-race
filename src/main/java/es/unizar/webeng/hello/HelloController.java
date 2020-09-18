package es.unizar.webeng.hello;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Date;
import java.util.Map;
import java.util.Random;

@Controller
public class HelloController {

    @Value("${app.message:Press F5 to roll the dice}")
    private String message;

    /**
     * This function act as the sole controller of the web service.
     * Every petition to the website is answered with 'wellcome.jsp' page
     * showing the after rolling dice result.
     *
     * @param model Data model to be passed to the view
     * @return String
     */

    @GetMapping("/")
    public String rollTheDice(Map<String, Object> model) {

        // random is needed to generate the seed
        Random random = new Random();

        // Dice result is calculated between 1 and 6 and storeged in dice
        int dice = random.nextInt(6) + 1;

        // Both params are put in the model to pass them to the view
        model.put("message", message);
        model.put("dice", dice);
        return "wellcome";
    }
}
