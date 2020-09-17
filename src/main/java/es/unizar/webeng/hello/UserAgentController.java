package es.unizar.webeng.hello;

/**
 * The UserAgentController program implements an application that
 * simply returns current time and the user's browser name with its
 * version
 *
 * @author Daniel Huici Meseguer (758635)
 * @version 1.0
 * @since 09/17/2020
 *
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;

@Controller
public class UserAgentController {

    /**
     * This method is called when requested by GET petition to
     * the user-agent webpage (/user-agent). It simply puts
     * current time and user browser to a Map so iit can be read
     * by view (JSP).
     *
     * @param model This is the map where we put the data
     * @return JSP name view.
     */

    @GetMapping("/user-agent")
    public String welcome(@RequestHeader(value = "User-Agent") String userAgent, Map<String, Object> model) {
        model.put("time", new Date());
        model.put("message", userAgent);
        return "wellcome";
    }

}
