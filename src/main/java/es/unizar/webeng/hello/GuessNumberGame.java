package es.unizar.webeng.hello;

import org.springframework.stereotype.Controller;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Random;


class ValueDTO {
    public Integer getUserNumber() { return userNumber; }
    public void setUserNumber(Integer userNumber) { this.userNumber = userNumber; }
    private Integer userNumber;
  }

/**
 * The GuessNumberGame program implements a game application that
 * consists of guessing the random number.
 *
 * @author Eduardo Díaz Larraga (735041)
 * @version 1.0
 */

//Game to guess the number between 1 and 100

@Controller
public class GuessNumberGame{
    private static final String ERROR_FORMAT_NUMBER = "Error in format number";
    private int numberToGuess;
    private int counter=0;

    @GetMapping("/guessNumberGame-init")
    public String initGuessNumber(Map<String, Object> model){
        
        model.put("saludo", "Press the button to start the game"); //asocia a ${saludo} del html el valor "hola"
        Random random = new Random();
        this.numberToGuess = random.nextInt(100);
        return "guessNumberGame"; //devuelve la vista del html
    }

    /**
     * Method which is called by a POST petition and implements the logic
     * of the game.
     * @param guess 
     * @param model the Map where the actual result and message are stored
     * @return JSP to the page guessNumberGame.html
     */
  
    @RequestMapping(value = "/guessAction", method = RequestMethod.POST, consumes =
    MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String guessNumberAction(ValueDTO value, Map<String, Object> model){
        try{
            //Get userNumber
            Integer userNumber = value.getUserNumber();
            model.put("userNumberText", userNumber);
            System.out.println("UserNumber:" + userNumber);
            if(userNumber<numberToGuess){
                model.put("message","Failed! Your number is smaller"); 
                model.put("counter",++counter); 
            }
            else if(userNumber>numberToGuess){
                model.put("message","Failed! Your number is higher");
                model.put("counter",++counter); 
            } 
            else{ model.put("message","YOU WON!");}
       }catch(Exception e){
            model.put("userNumberText",e);
        }
        return "guessNumberGame"; //devuelve la vista del html

    }
}