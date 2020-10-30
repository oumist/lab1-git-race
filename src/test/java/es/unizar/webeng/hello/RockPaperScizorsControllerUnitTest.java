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
 * @author Jose Daniel Barcelo Labuena - 737070
 * RockPaperScizors is a program that simulates a game of
 * Rock Paper or Scizors between the user and the computer
 *
 * @version 1.0
 * @since 10/20/2020
 *
 */
public class RockPaperScizorsControllerUnitTest {
    private final static String ROCK_RESULT = "ROCK(CPU) <--> ";
    private final static String PAPER_RESULT = "PAPER(CPU) <--> ";
    private final static String SCIZORS_RESULT = "SCIZORS(CPU) <--> ";
    private final static String WINNING_MESSAGE = "YOU WON!";
    private final static String LOSING_MESSAGE = "YOU LOST :(";
    private final static String TIE_MESSAGE = "IT'S A TIE!";
    private final static String RESULT = "The result goes here";
    private final static String MESSAGE = "Waiting for the result";
    private final static String ERROR = "ERROR";

    private RockPaperScizorsController rpsController = new RockPaperScizorsController();

    /**
    * Checks that the default values of the controller are the ones expected
     */

    @Test
    public void startRockPaperScizors() {
        HashMap<String, Object> model = new HashMap<>();
        String view = rpsController.startRockPaperScizors(model);
        assertThat(view, is("rockpaperscizors"));
        assertTrue(model.containsKey("result"));
        assertTrue(model.containsKey("message"));
        assertThat(model.get("result"), is(RESULT));
        assertThat(model.get("message"), is(MESSAGE));
    }
    /**
     * POSSIBLE TEST CASES:
     * User choice: ROCK , computer choice: ROCK
     * User choice: ROCK , computer choice: PAPER
     * User choice: ROCK , computer choice: SCIZORS
     * User choice: PAPER , computer choice: ROCK
     * User choice: PAPER , computer choice: PAPER
     * User choice: PAPER , computer choice: SCIZORS
     * User choice: SCIZORS , computer choice: ROCK
     * User choice: SCIZORS , computer choice: PAPER
     * User choice: SCIZORS , computer choice: SCIZORS
     *
     * Checks if the messages are the ones expected
     *
     */
    @Test
    public void computerChooseWeaponROCK() {
        String candidate = "0"; // ROCK = 0 , PAPER = 1, SCIZORS = 2
        HashMap<String, Object> model = new HashMap<>();
        rpsController.computerChooseWeaponForTest(candidate, model);
        if(model.get("result").equals(ROCK_RESULT)){
            assertEquals(model.get("message"), TIE_MESSAGE);
        }
        else if(model.get("result").equals(PAPER_RESULT)){
            assertEquals(model.get("message"), LOSING_MESSAGE);
        }
        else{
            assertEquals(model.get("message"), WINNING_MESSAGE);
        }
    }

    @Test
    public void computerChooseWeaponPAPER() {
        String candidate = "1"; // ROCK = 0 , PAPER = 1, SCIZORS = 2
        HashMap<String, Object> model = new HashMap<>();
        rpsController.computerChooseWeaponForTest(candidate, model);
        if(model.get("result").equals(ROCK_RESULT)){
            assertEquals(model.get("message"), WINNING_MESSAGE);
        }
        else if(model.get("result").equals(PAPER_RESULT)){
            assertEquals(model.get("message"), TIE_MESSAGE);
        }
        else{
            assertEquals(model.get("message"), LOSING_MESSAGE);
        }
    }

    @Test
    public void computerChooseWeaponSCIZORS() {
        String candidate = "2"; // ROCK = 0 , PAPER = 1, SCIZORS = 2
        HashMap<String, Object> model = new HashMap<>();
        rpsController.computerChooseWeaponForTest(candidate, model);
        if(model.get("result").equals(ROCK_RESULT)){
            assertEquals(model.get("message"), LOSING_MESSAGE);
        }
        else if(model.get("result").equals(PAPER_RESULT)){
            assertEquals(model.get("message"), WINNING_MESSAGE);
        }
        else{
            assertEquals(model.get("message"), TIE_MESSAGE);
        }
    }
}
