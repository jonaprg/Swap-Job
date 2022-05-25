package tk.swapjob.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tk.swapjob.model.User;
import tk.swapjob.repository.SkillRepository;
import tk.swapjob.repository.UserRepository;
import tk.swapjob.security.jwt.JwtUtils;
import tk.swapjob.utils.Utils;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class SkillController {
    @Autowired
    private SkillRepository skillRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtils jwt;

    @GetMapping("/skill/all")
    public ResponseEntity<?> getAllSkills() {
        return ResponseEntity.ok(skillRepository.findAll());
    }

    @PostMapping("/skill/update")
    public ResponseEntity<?> updateSkill(@RequestBody List<Integer> skillIds) {
        String username = Utils.getUserFromToken(jwt);

        User user = userRepository.findUserByEmail(username);
        if (user == null) {
            return ResponseEntity.badRequest().body("Invalid user id");
        }

        user.getSkillList().clear();

        for (Integer skillId : skillIds) {
            skillRepository.findById(skillId).ifPresent(user::addSkill);
        }

        userRepository.save(user);

        return ResponseEntity.ok(true);
    }

}
