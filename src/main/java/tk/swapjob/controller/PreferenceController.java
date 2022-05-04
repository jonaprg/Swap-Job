package tk.swapjob.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import tk.swapjob.repository.PreferenceRepository;

@RestController
public class PreferenceController {
    @Autowired
    private PreferenceRepository preferenceRepository;

    @GetMapping("/preferences/all")
    public ResponseEntity<?> getAllPreferences() {
        return ResponseEntity.ok(preferenceRepository.findAll());
    }

}
