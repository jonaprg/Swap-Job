package tk.swapjob.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tk.swapjob.dao.requests.EditProfileRequest;
import tk.swapjob.dao.responses.MessageResponse;
import tk.swapjob.model.Preference;
import tk.swapjob.model.Skill;
import tk.swapjob.model.User;
import tk.swapjob.repository.UserRepository;
import tk.swapjob.security.jwt.JwtUtils;
import tk.swapjob.utils.Utils;

import javax.validation.Valid;
import java.sql.Timestamp;

@RestController
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtils jwt;

    @GetMapping("/user")
    public User getUser(@RequestParam String email) {
        return userRepository.findUserByEmail(email);
    }

    @GetMapping("/user/all")
    public ResponseEntity<?> getAllUsers() {
        return ResponseEntity.ok(userRepository.findAll());
    }

    @PostMapping("/user/edit")
    public ResponseEntity<?> editUser(@Valid @RequestBody EditProfileRequest request) {
        if (request.isInvalid()) {
            return ResponseEntity.badRequest().body("Invalid request");
        }

        if (!Utils.isEmailValid(request.getEmail())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Invalid email"));
        }
        String email = request.getEmail();
        String firstName = request.getFirstName();
        String lastName = request.getLastName();
        String birtDateString = request.getBirthDate();
        String phone = request.getPhone();
        Integer postalCode = request.getPostalCode();
        String description = request.getDescription();

        Timestamp birthDate;
        try {
            birthDate = Timestamp.valueOf(birtDateString + " 00:00:00");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Invalid birth date"));
        }

        String companyEmail = Utils.getUserFromToken(jwt);
        User user = userRepository.findUserByEmail(companyEmail);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setBirthDate(birthDate);
        user.setPhone(phone);
        user.setPostalCode(postalCode);
        user.setDescription(description);
        user.setEmail(email);
        user.getPreferenceList().clear();
        user.getSkillList().clear();

        for (Skill skill : request.getSkillList()) {
            user.getSkillList().add(skill);
        }

        for (Preference preference : request.getPreferenceList()) {
            user.getPreferenceList().add(preference);
        }

        userRepository.save(user);

        return ResponseEntity.ok(true);
    }

    @GetMapping("/user/getProfile")
    public ResponseEntity<?> getProfile() {
        String email = Utils.getUserFromToken(jwt);
        User user = userRepository.findUserByEmail(email);

        if (user == null) {
            return ResponseEntity.badRequest().body("User not found");
        }

        return ResponseEntity.ok(user.getProfile());
    }

    @GetMapping("/user/matches")
    public ResponseEntity<?> getMatches() {
        String email = Utils.getUserFromToken(jwt);
        User user = userRepository.findUserByEmail(email);

        if (user == null) {
            return ResponseEntity.badRequest().body("User not found");
        }

        return ResponseEntity.ok(user.getMatchOfferList());
    }
}
