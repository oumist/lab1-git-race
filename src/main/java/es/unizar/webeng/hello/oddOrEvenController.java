package es.unizar.webeng.hello;

/**
 * oddOrEvenController class implements the functionality of get a number, choose randomly an other number and see if the sum
 * of both is odd or even.
 *
 * @author Alejandro Omist (737791)
 * @version 1.0
 * @since 21/10/2020
 */

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;
import java.util.Random;

@Controller
public class oddOrEvenController {


	 public String GetEvenOdd(int numero){

	 	if (summatory(numero) % 2 == 0 ) {
	 		return "Even";
	 	}

	 	else {
	 		return "Odd";
	 	}
	 }

	int summatory(int number) {
		Random random = new Random();
		return number + random;
	}


}
