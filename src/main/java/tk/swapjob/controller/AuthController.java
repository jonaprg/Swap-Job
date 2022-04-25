package tk.swapjob.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tk.swapjob.dao.requests.LoginRequest;
import tk.swapjob.dao.requests.SignupRequest;
import tk.swapjob.dao.responses.JwtResponse;
import tk.swapjob.dao.responses.MessageResponse;
import tk.swapjob.model.User;
import tk.swapjob.repository.UserRepository;
import tk.swapjob.security.jwt.JwtUtils;
import tk.swapjob.security.services.UserDetailsImpl;

import java.sql.Timestamp;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder encoder;
    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getEmail()));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        if (userRepository.existsUserByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

        String email = signUpRequest.getEmail();
        String password = encoder.encode(signUpRequest.getPassword());
        String firstName = signUpRequest.getFirstName();
        String lastName = signUpRequest.getLastName();
        String birtDateString = signUpRequest.getBirthDate();
        String phone = signUpRequest.getPhone();
        Integer postalCode = signUpRequest.getPostalCode();
        String description = signUpRequest.getDescription();

        Timestamp birthDate = Timestamp.valueOf(birtDateString + " 00:00:00");
        User user = new User(email, password, firstName, lastName, postalCode, phone, birthDate, description);

        userRepository.save(user);
        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }
}