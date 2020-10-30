package es.unizar.webeng.hello;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * FlipCoinGuess test program implements all the
 * possible unit test cases to check the right execution
 * of FlipCoinGuessController
 *
 * @author Jose Ignacio Hernandez (740491)
 * @version 1.0
 * @since 09/25/2020
 *
 */
//@WebMvcTest(FlipCoinGuessController.class)
public class FlipCoinGuessControllerTest {
    private final static String DEFAULT_RESULT = "NOT RESULT YET | ";
    private final static String DEFAULT_MESSAGE = "WAITING FOR RESULT";
    private final static String WINNING_MESSAGE = "YOU GUESSED IT RIGHT!";
    private final static String LOSING_MESSAGE = "SORRY BETTER LUCK NEXT TIME!";
    private final static String HEADS_RESULT = "HEADS | ";
    private final static String TAILS_RESULT = "TAILS | ";
    private final static String ERROR = "ERROR";

    //@Autowired
    private FlipCoinGuessController flipController = new FlipCoinGuessController();

    /**
    * Checks that the default values of the controller are the ones expected
     */

    @Test
    public void initFlipCoin() {
        HashMap<String, Object> model = new HashMap<>();
        String view = flipController.initFlipCoin(model);
        assertThat(view, is("flipCoinGuess"));
        assertTrue(model.containsKey("result"));
        assertTrue(model.containsKey("message"));
        assertThat(model.get("message"), is(DEFAULT_MESSAGE));
    }
    /**
     * POSSIBLE TEST CASES:
     * User guesses: HEADS , actual result: HEADS
     * User guesses: HEADS , actual result: TAILS
     * User guesses: TAILS , actual result: HEADS
     * User guesses: TAILS , actual result: TAILS
     *
     * Checks if the messages are the winning or losing expected
     *
     */
    @Test
    public void flipAndGuessHEADS() {
        String candidate = "0"; // HEADS = 0 , TAILS = 1
        HashMap<String, Object> model = new HashMap<>();
        flipController.flipAndGuess(candidate, model);
        if(model.get("result").equals(HEADS_RESULT)){
            assertEquals(model.get("message"), WINNING_MESSAGE);
        }
        else{
            assertEquals(model.get("message"), LOSING_MESSAGE);
        }
    }

    @Test
    public void flipAndGuessTAILS() {
        String candidate = "1"; // HEADS = 0 , TAILS = 1
        HashMap<String, Object> model = new HashMap<>();
        flipController.flipAndGuess(candidate, model);

        if(model.get("result").equals(TAILS_RESULT)){
            assertEquals(model.get("message"), WINNING_MESSAGE);
        }
        else{
            assertEquals(model.get("message"), LOSING_MESSAGE);
        }
    }
}
