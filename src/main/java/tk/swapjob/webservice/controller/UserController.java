package tk.swapjob.webservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tk.swapjob.webservice.model.User;
import tk.swapjob.webservice.repository.UserRepository;

import java.util.Optional;

@RestController
public class UserController{

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/user")
    public User getUser(@RequestParam String email) {
        return userRepository.findUserByEmail(email);
    }
}
