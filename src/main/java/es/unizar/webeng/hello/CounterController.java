package es.unizar.webeng.hello;

/**
 * CounterController class implements the functionality of init, increase and decrease a counter.
 *
 * @author Eduardo Ruiz Cordón (764539)
 * @version 1.0
 * @since 09/24/2020
 */

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;


@Controller
public class CounterController {
    private final static int INITIAL_VALUE = 0;
    private final static String ERROR = "ERROR";

    private final static String INITIAL = "init";
    private final static String INCREASE = "increase";
    private final static String DECREASE = "decrease";


    /**
     * initCounter is a method who is requested by a GET petition from the wellcome page. The method return the
     * counter started to the INITIAL_VALUE.
     * @param model The Map where the method introduce the initial value of the counter
     * @return JSP to the page viewCounter.html
     */
    @GetMapping("/init-counter")
    public String initCounter(Map<String, Object> model){
        model.put("counter", INITIAL_VALUE);
        return "counter";
    }

    /**
     * increaseCounter is a method that increase the counter by 1 and put the value in the map pass by parameter.
     * @param counter current value of the counter
     * @param model The Map where is saved the value of the counter
     */
    private void increaseCounter(int counter, Map<String, Object> model) throws NumberFormatException{
        counter++;
        model.put("counter", counter);
    }

    /**
     * decreaseCounter is a method that decrease the counter by 1 and put the value in the map pass by parameter.
     * If the value of the parameter counter is equal or less than INITIAL_VALUE return put the counter
     * to the INITIAL_VALUE
     * @param counter current value of the counter
     * @param model the Map where is saved the value of the counter
     */
    private void decreaseCounter(int counter, Map<String, Object> model) throws NumberFormatException{
        if(counter > INITIAL_VALUE){
            counter--;
        } else {
            counter = INITIAL_VALUE;
        }
        model.put("counter", counter);
    }

    /**
     * actionCounter is a method which is invoked by a POST petition. This method change the value of the counter
     * based on the button pushed by the user and saved the value in a Map.
     * @param counter current value of the counter
     * @param button show the action which is selected by the user
     * @param model the Map where is saved the value of the counter
     * @return JSP to the page viewCounter.html
     */
    @RequestMapping(value = "/action-counter", method = RequestMethod.POST, consumes =
            MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String actionCounter(@RequestParam("counter") String counter, @RequestParam("button") String button,
                                Map<String, Object> model){
        try {
            switch (button){
                case INITIAL:
                    int counter1 = Integer.parseInt(counter);
                    initCounter(model);
                    break;
                case INCREASE:
                    increaseCounter(Integer.parseInt(counter), model);
                    break;
                case DECREASE:
                    decreaseCounter(Integer.parseInt(counter), model);
                    break;
                default:
                    model.put("counter", ERROR);
                    break;
            }
        } catch (NumberFormatException e) {
            model.put("counter", ERROR);
        }
        return "counter";
    }
}
