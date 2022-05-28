package tk.swapjob.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tk.swapjob.model.Preference;
import tk.swapjob.model.User;
import tk.swapjob.repository.PreferenceRepository;
import tk.swapjob.repository.UserRepository;
import tk.swapjob.security.jwt.JwtUtils;
import tk.swapjob.utils.Utils;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class PreferenceController {
    @Autowired
    private PreferenceRepository preferenceRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtils jwt;

    @GetMapping("/preferences/all")
    public ResponseEntity<?> getAllPreferences() {
        return ResponseEntity.ok(preferenceRepository.findAll());
    }

    @PostMapping("/preference/update")
    public ResponseEntity<?> updateSkill(@RequestBody List<Preference> preferenceList) {
        String username = Utils.getUserFromToken(jwt);

        User user = userRepository.findUserByEmail(username);
        if (user == null) {
            return ResponseEntity.badRequest().body("Invalid user id");
        }

        user.getPreferenceList().clear();

        for (Preference preference : preferenceList) {
            preferenceRepository.save(preference);
            user.addPreference(preference);
        }

        userRepository.save(user);

        return ResponseEntity.ok(true);
    }

}
