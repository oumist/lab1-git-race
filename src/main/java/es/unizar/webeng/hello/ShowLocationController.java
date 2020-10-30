package es.unizar.webeng.hello;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Date;
import java.util.Map;
import java.util.Random;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * The ShowLocationController program implements an application that
 * shows your current locations based on your IP address and also it 
 * allows you to search for places.
 *
 * @author Elena Morón Vidal (739324)
 * @version 1.0
 * @since 10/07/2020
 */

@Controller
@Api(value="Show Location", description="API that uses Leaflet API map to show a fixed and to look for places")
public class ShowLocationController{
    
    @GetMapping("/ShowLocation")
    @ApiOperation(value="It shows EINA location and you can look for places too")
    public String showLocation(){
        return "showLocation";
    }
}