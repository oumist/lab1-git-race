package es.unizar.webeng.hello;

/**
 * PasswordController is the program that returns a safe password from one or
 * more words entered by the user. The secret key is the actual date. After 
 * the password is created, you can modify it adding characters at random 
 * positions
 *
 * @author Alvaro Garcia Diaz (760704)
 *
 */

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Map;
import java.util.Arrays;
import java.util.Base64;
import java.util.concurrent.ThreadLocalRandom;

import java.text.SimpleDateFormat;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

@Controller
public class PasswordController {

    /**
     * Calculates the number of occurrences of special characters, numbers and
     * uppercase and lowercase letters of the password passed by parameter
     * 
     * @param model The map with the data
     * @param pass The password 
     * @param len Length of the password
     */

    private void stadistics(Map<String, Object> model, String pass, int len) {
        int num = 0, upp = 0, spe = 0, low = 0;
        for(int i = 0; i < len; ++i) {
            if(Character.isUpperCase(pass.charAt(i))) ++upp;
            else if(Character.isLowerCase(pass.charAt(i))) ++low;
            else if(Character.isDigit(pass.charAt(i))) ++num;
            else ++spe;
        }
        model.put("len", len);
        model.put("upp", upp);
        model.put("low", low);
        model.put("spe", spe);
        model.put("num", num);
    }

    /**
     * Returns the name of the JSP when requested by a GET petition to /password
     * 
     * @return JSP name
     */

    @GetMapping("/password")
    public String passGen() {
        return "pass";
    }

    /**
     * Generates a password from the words entered by the user and a secret key
     * which is the actual date. It uses AES as the encryption method
     * Source of AES encryption: https://howtodoinjava.com/java/java-security/java-aes-encryption-example/
     * 
     * @param enc The word or words entered by the user
     * @param model The map with the data
     * @return JSP name (pass if incorrect, otherwise showpass)
     */

    @RequestMapping(value="/showpassword", method=RequestMethod.POST, 
            consumes=MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String passGen(@RequestParam("word") String enc, Map<String, Object> model) {

        /**
         * If the word entered is empty, the password can't be generated
         */
        if(enc.trim().length() == 0) {
            return "pass";
        }

        SecretKeySpec secretKey = null;

        /**
         * The first step is to define the secret key to encrypt the words
         */

        try {
            MessageDigest sha = MessageDigest.getInstance("SHA-1");
            byte[] key = sha.digest(((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(new Date())).getBytes("UTF-8"));
            key = Arrays.copyOf(key, 32); 
            secretKey = new SecretKeySpec(key, "AES");
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            return "pass";
        } 

        /**
         * The last step is to encrypt the words entered by the user with the 
         * secret key from the step before and return the encrypted password 
         * using AES as the encryption method
         */
        
        String pass = null;
        try {
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            pass = Base64.getEncoder().encodeToString(cipher.doFinal(enc.getBytes("UTF-8")));
            model.put("password", pass);
        } catch (Exception e) {
            return "pass";
        }
        stadistics(model, pass, pass.length());
        return "showpass";
    }

    /**
     * Modify the password adding at random positions a number of characters
     * of different types (uppercase, lowercase, digits, special) 
     * 
     * @param add Number of random characters
     * @param spe Number of special characters
     * @param low Number of lowercase letters
     * @param upp Number of uppercase letters
     * @param num Number of digits
     * @param pass The password to modify
     * @param model The map with the data
     * @return JSP name
     */

    @RequestMapping(value = "/modPass", method = RequestMethod.POST, consumes =
            MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String modifyPass(@RequestParam("add") String add, 
            @RequestParam("special") String spe, @RequestParam("lower") String low, 
            @RequestParam("upper") String upp, @RequestParam("numb") String num,
            @RequestParam("password") String pass, Map<String, Object> model) {
        int ran = 0, str = 0, min = 0, may = 0, dig = 0, len = pass.length();
        try {
            ran = Integer.parseInt(add);
            str = Integer.parseInt(spe);
            min = Integer.parseInt(low);
            may = Integer.parseInt(upp);
            dig = Integer.parseInt(num);
        } catch (NumberFormatException e) {
            model.put("password", pass);
            stadistics(model, pass, len);
            return "showpass";
        }

        if(ran < 0) ran = 0;
        if(str < 0) str = 0;
        if(min < 0) min = 0;
        if(may < 0) may = 0;
        if(dig < 0) dig = 0;

        int tot = ran + str + min + may + dig;
        while(tot > 0) {
            int pos = ThreadLocalRandom.current().nextInt(0, len);
            if(str > 0) {
                int choose = ThreadLocalRandom.current().nextInt(0, 4);
                if(pos == 0) choose = ThreadLocalRandom.current().nextInt(32, 48);
                else if(pos == 1) choose = ThreadLocalRandom.current().nextInt(58, 65);
                else if(pos == 2) choose = ThreadLocalRandom.current().nextInt(91, 97);
                else choose = ThreadLocalRandom.current().nextInt(123, 127);
                pass = pass.substring(0, pos) + (char)choose + pass.substring(pos);
                --str;
            } else if(min > 0) {
                pass = pass.substring(0, pos) + (char)ThreadLocalRandom.current().nextInt(97, 123) + pass.substring(pos);
                --min;
            } else if(may > 0) {
                pass = pass.substring(0, pos) + (char)ThreadLocalRandom.current().nextInt(65, 91) + pass.substring(pos);
                --may;
            } else if(dig > 0) {
                pass = pass.substring(0, pos) + (char)ThreadLocalRandom.current().nextInt(48, 58) + pass.substring(pos);
                --dig;
            } else {
                pos = pos % 4;
                if(pos == 0) ++str;
                else if(pos == 1) ++min;
                else if(pos == 2) ++may;
                else ++dig;
                --ran;
                continue;
            }
            ++len; --tot;
        }

        model.put("password", pass);
        stadistics(model, pass, len);
        return "showpass";
    }

}
