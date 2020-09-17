package es.unizar.webeng.hello;

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

    @GetMapping("/user-agent")
    public String welcome(@RequestHeader(value = "User-Agent") String userAgent, Map<String, Object> model) {
        model.put("time", new Date());
        model.put("message", userAgent);
        return "wellcome";
    }

}
