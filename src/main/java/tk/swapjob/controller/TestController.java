package tk.swapjob.controller;

import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import tk.swapjob.security.jwt.JwtUtils;
import tk.swapjob.utils.Utils;

import javax.servlet.http.HttpServletRequest;

@RestController
public class TestController {

    @Autowired
    private JwtUtils jwtUtils;

    @GetMapping("/helloworld")
    public String helloworld() {
        return "Hello World";
    }

    @GetMapping("/getUserFromToken")
    public String getUserFromToken() {
        return Utils.getUserFromToken(jwtUtils);
    }
}
