package es.unizar.webeng.hello;

/**
 * PasswordController is the program that returns a safe password from one or
 * more words entered by the user. The secret key is the actual date.
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

import java.text.SimpleDateFormat;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

@Controller
public class PasswordController {

    @GetMapping("/password")
    public String passGen(Map<String, String> model) {
        model.put("password", "");
        return "pass";
    }

    @RequestMapping(value = "/password", method = RequestMethod.POST, consumes =
            MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String passGen(@RequestParam("word") String enc, Map<String, String> model) {

        if(enc.trim().length() == 0) {
            model.put("password", "ERROR");
            return "pass";
        }

        SecretKeySpec secretKey = null;
        byte[] key;

        /**
         * The first step is to define the secret key to encrypt the words
         */

        try {
            key = ((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(new Date())).getBytes("UTF-8");
            MessageDigest sha = MessageDigest.getInstance("SHA-1");
            key = sha.digest(key);
            key = Arrays.copyOf(key, 32); 
            secretKey = new SecretKeySpec(key, "AES");
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            model.put("password", "ERROR");
            return "pass";
        } 

        /**
         * The final step is to encrypt the words entered by the user with the 
         * secret key from the step before and return the encrypted password 
         * using AES as the encryption method
         */
     
        try {
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            model.put("password", Base64.getEncoder().encodeToString(cipher.doFinal(enc.getBytes("UTF-8"))));
        } catch (Exception e) {
            model.put("password", "ERROR");
        }

        return "pass";
    }

}
