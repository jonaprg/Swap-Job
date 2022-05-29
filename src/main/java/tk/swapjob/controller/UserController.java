package tk.swapjob.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tk.swapjob.dao.requests.EditProfileRequest;
import tk.swapjob.dao.responses.MatchOfferResponse;
import tk.swapjob.dao.responses.MessageResponse;
import tk.swapjob.model.Preference;
import tk.swapjob.model.Skill;
import tk.swapjob.model.User;
import tk.swapjob.repository.MatchOfferRepository;
import tk.swapjob.repository.UserRepository;
import tk.swapjob.security.jwt.JwtUtils;
import tk.swapjob.utils.Utils;

import javax.validation.Valid;
import java.sql.Timestamp;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MatchOfferRepository matchOfferRepository;

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
        boolean visibility = request.getVisible();

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
        user.setVisible(visibility);

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

        return ResponseEntity.ok(user.getMatchOfferList().stream().map(MatchOfferResponse::new).collect(Collectors.toList()));
    }

    @GetMapping("/user/delete")
    public ResponseEntity<?> deleteUser() {
        String companyEmail = Utils.getUserFromToken(jwt);
        User user = userRepository.findUserByEmail(companyEmail);

        if (user == null) {
            return ResponseEntity.badRequest().body("User not found");
        }

        user.getSkillList().clear();
        user.getPreferenceList().clear();
        matchOfferRepository.deleteAll(user.getMatchOfferList());

        userRepository.delete(user);
        return ResponseEntity.ok(true);
    }
}
