package tk.swapjob.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import tk.swapjob.repository.SkillRepository;

@RestController
public class SkillController {
    @Autowired
    private SkillRepository skillRepository;

    @GetMapping("/skill/all")
    public ResponseEntity<?> getAllSkills() {
        return ResponseEntity.ok(skillRepository.findAll());
    }

}
