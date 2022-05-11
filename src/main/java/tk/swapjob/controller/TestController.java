package tk.swapjob.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import tk.swapjob.security.jwt.JwtUtils;
import tk.swapjob.utils.Utils;

@RestController
@CrossOrigin(origins = "*")
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
