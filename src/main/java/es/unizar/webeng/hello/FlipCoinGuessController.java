package es.unizar.webeng.hello;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;
import java.util.Random;

/**
 * FlipCoinGuess program implements an simple game that
 * allow the user to guess the result of flipping
 * a coin.
 *
 * @author Jose Ignacio Hernandez (740491)
 * @version 1.0
 * @since 09/25/2020
 *
 */

@Controller
public class FlipCoinGuessController {
    private final static String DEFAULT_RESULT = "NOT RESULT YET | ";
    private final static String DEFAULT_MESSAGE = "WAITING FOR RESULT";
    private final static String HEADS_RESULT = "HEADS | ";
    private final static String TAILS_RESULT = "TAILS | ";
    private final static String WINNING_MESSAGE = "YOU GUESSED IT RIGHT!";
    private final static String LOSING_MESSAGE = "SORRY BETTER LUCK NEXT TIME!";
    private final static String ERROR = "ERROR";

    @GetMapping("/flipCoinGuess-init")
    public String initFlipCoin(Map<String, Object> model) {
        model.put("result", DEFAULT_RESULT);
        model.put("message", DEFAULT_MESSAGE);
        return "flipCoinGuess";
    }

    /**
     * Method which is called by a POST petition and implements the logic
     * of the game.
     * @param guess user choice of guessing the result of tossing a coin
     * @param model the Map where the actual result and message are stored
     * @return JSP to the page flipCoinGuess.html
     */
    @RequestMapping(value = "/flipAction", method = RequestMethod.POST, consumes =
            MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String flipAndGuess(@RequestParam("guess") String guess,
                                Map<String, Object> model){
        try {
            int userGuess = Integer.parseInt(guess); // HEADS = 0 , TAILS = 1
            int actualResult = toss();
            if (actualResult == 0){
                model.put("result", HEADS_RESULT);
            }
            else if (actualResult == 1){
                model.put("result", TAILS_RESULT);
            }
            if (guessResult(userGuess, actualResult)){
                model.put("message", WINNING_MESSAGE);
            }
            else{
                model.put("message", LOSING_MESSAGE);
            }

        } catch (NumberFormatException e) {
            model.put("guess", ERROR);
        }
        return "flipCoinGuess";
    }

    private int toss(){
        Random random = new Random();
        int sideUp = random.nextInt(2);
        return sideUp;
    }
    private boolean guessResult(int guess, int sideUp) {
        return guess == sideUp;
    }
}
