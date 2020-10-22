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
 * @author Jose Daniel Barcelo Labuena - 737070
 * RockPaperScizors is a program that simulates a game of
 * Rock Paper or Scizors between the user and the computer
 *
 * @version 1.0
 * @since 10/20/2020
 *
 */

@Controller
public class RockPaperScizorsController {
    private final static String ROCK_RESULT = "ROCK(CPU) <--> ";
    private final static String PAPER_RESULT = "PAPER(CPU) <--> ";
    private final static String SCIZORS_RESULT = "SCIZORS(CPU) <--> ";
    private final static String WINNING_MESSAGE = "YOU WON!";
    private final static String LOSING_MESSAGE = "YOU LOST :(";
    private final static String TIE_MESSAGE = "IT'S A TIE!";
    private final static String RESULT = "The result goes here";
    private final static String MESSAGE = "Waiting for the result";
    private final static String ERROR = "ERROR";

    @GetMapping("/rockPaperScizors_start")
    public String startRockPaperScizors(Map<String, Object> model) {
        model.put("result", RESULT);
        model.put("message", MESSAGE);
        return "rockpaperscizors";
    }

    /**
     * Method which is called by a POST petition and implements the logic
     * of the game.
     * @param guess user choice of one of the allowed weapons on the Rock Paper or Scizors game
     * @param model the Map where the actual result and message are stored
     * @return JSP to the page rockpaperscizors.html
     */
    @RequestMapping(value = "/rpsAction", consumes =
            MediaType.APPLICATION_FORM_URLENCODED_VALUE)

    class ValueDTO {
        public Integer getUserChoice() { return userChoice; }
        public void setUserChoice(Integer userChoice) { this.userChoice = userChoice; }
        private Integer userChoice;
    }

    public String computerChooseWeapon(ValueDTO value,
                                Map<String, Object> model){
        try {
            int userChoice = value.getUserChoice(); 
            int computerChoice = computerChoice();
            if (computerChoice == 0){
                model.put("result", ROCK_RESULT);
            }
            else if (computerChoice == 1){
                model.put("result", PAPER_RESULT);
            }
            else if (computerChoice == 2){
                model.put("result", SCIZORS_RESULT);
            }
            if (selectWinner(userChoice, computerChoice)==0){
                model.put("message", TIE_MESSAGE);
            }
            else if (selectWinner(userChoice, computerChoice)==1){
                model.put("message", WINNING_MESSAGE);
            }
            else if (selectWinner(userChoice, computerChoice)==-1){
                model.put("message", LOSING_MESSAGE);
            }

        } catch (NumberFormatException e) {
            model.put("choice", ERROR);
        }
        return "rockpaperscizors";
    }

    public String computerChooseWeaponForTest(@RequestParam("choice") String choice,
                                Map<String, Object> model){
        try {
            int userChoice = Integer.parseInt(choice); // ROCK = 0 , PAPER = 1, SCIZORS = 2
            int computerChoice = computerChoice();
            if (computerChoice == 0){
                model.put("result", ROCK_RESULT);
            }
            else if (computerChoice == 1){
                model.put("result", PAPER_RESULT);
            }
            else if (computerChoice == 2){
                model.put("result", SCIZORS_RESULT);
            }
            if (selectWinner(userChoice, computerChoice)==0){
                model.put("message", TIE_MESSAGE);
            }
            else if (selectWinner(userChoice, computerChoice)==1){
                model.put("message", WINNING_MESSAGE);
            }
            else if (selectWinner(userChoice, computerChoice)==-1){
                model.put("message", LOSING_MESSAGE);
            }

        } catch (NumberFormatException e) {
            model.put("choice", ERROR);
        }
        return "rockpaperscizors";
    }

    private int computerChoice(){
        Random random = new Random();
        int choice = random.nextInt(3);
        return choice;
    }
    private int selectWinner(int userChoice, int computerChoice) {
        if(userChoice==computerChoice){
            return 0;
        }
        else if ((userChoice==0 && computerChoice==1) || (userChoice==1 && computerChoice==2) || (userChoice==2 && computerChoice==0)){
            return -1;
        }
        else{
            return 1;
        }
    }
}
