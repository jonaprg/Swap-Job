package tk.swapjob.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import tk.swapjob.dao.requests.NewCompanyRequest;
import tk.swapjob.dao.requests.SignupRequest;
import tk.swapjob.model.*;
import tk.swapjob.repository.*;
import tk.swapjob.security.jwt.JwtUtils;
import tk.swapjob.utils.Utils;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class CompanyController {

    @Autowired
    private OfferRepository offerRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private MatchOfferRepository matchOfferRepository;

    @Autowired
    private StatusRepository statusRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtils jwt;

    @GetMapping("/company")
    public ResponseEntity<?> getCompany() {
        String companyEmail = Utils.getUserFromToken(jwt);
        User user = userRepository.findUserByEmail(companyEmail);

        if (user == null || user.getCompany() == null) {
            return ResponseEntity.badRequest().body("Company not found");
        }
        Company company = user.getCompany();

        return ResponseEntity.ok(company);
    }

    @GetMapping("/company/offers")
    public ResponseEntity<?> getPublishedOffers() {
        String companyEmail = Utils.getUserFromToken(jwt);
        User user = userRepository.findUserByEmail(companyEmail);

        if (user == null || user.getCompany() == null) {
            return ResponseEntity.badRequest().body("Company not found");
        }
        Company company = user.getCompany();

        return ResponseEntity.ok(company.getOfferList());
    }

    @GetMapping("/company/matches")
    public ResponseEntity<?> getMatches() {
        String companyEmail = Utils.getUserFromToken(jwt);
        User user = userRepository.findUserByEmail(companyEmail);

        if (user == null || user.getCompany() == null) {
            return ResponseEntity.badRequest().body("Company not found");
        }
        Company company = user.getCompany();
        List<Offer> offerList = offerRepository.getOffersByCompanyId(company.getId());
        List<MatchOffer> matchOfferList = new ArrayList<>();
        for (Offer offer : offerList) {
            matchOfferList.addAll(matchOfferRepository.findMatchOffersByOfferId(offer.getId()));
        }

        return ResponseEntity.ok(matchOfferList);
    }

    @PostMapping("/company/new")
    public ResponseEntity<?> createNewCompany(@RequestBody NewCompanyRequest companyRequest) {
        if (companyRequest.isInvalid()) {
            return ResponseEntity.badRequest().body("Invalid request");
        }

        if (companyRepository.findCompanyByEmail(companyRequest.getEmail()) != null) {
            return ResponseEntity.badRequest().body("Company already exists");
        }

        Company company = new Company(companyRequest);
        companyRepository.save(company);

        SignupRequest signupRequest = new SignupRequest();
        signupRequest.setEmail(companyRequest.getEmail());
        signupRequest.setPassword(companyRequest.getPassword());
        signupRequest.setBirthDate("2000-01-01");
        signupRequest.setFirstName("Company " + company.getId());
        signupRequest.setLastName("Company " + company.getId());
        signupRequest.setPhone("+34123456");
        signupRequest.setPostalCode(companyRequest.getPostalCode());
        signupRequest.setDescription("Company " + company.getId());
        signupRequest.setCompanyUser(true);

        User user = registerUser(signupRequest);

        user.setCompany(company);
        userRepository.save(user);

        return ResponseEntity.ok(true);
    }

    public User registerUser(SignupRequest signUpRequest) {
        if (signUpRequest.isInvalid()) {
            return null;
        }
        if (userRepository.existsUserByEmail(signUpRequest.getEmail())) {
            return null;
        }

        if (!Utils.isEmailValid(signUpRequest.getEmail())) {
            return null;
        }
        String email = signUpRequest.getEmail();
        String password = encoder.encode(signUpRequest.getPassword());
        String firstName = signUpRequest.getFirstName();
        String lastName = signUpRequest.getLastName();
        String birtDateString = signUpRequest.getBirthDate();
        String phone = signUpRequest.getPhone();
        Integer postalCode = signUpRequest.getPostalCode();
        String description = signUpRequest.getDescription();
        boolean isCompanyUser = signUpRequest.isCompanyUser();

        Timestamp birthDate;
        try {
            birthDate = Timestamp.valueOf(birtDateString + " 00:00:00");
        } catch (Exception e) {
            return null;
        }

        Status status = statusRepository.getStatusById(1);

        User user = new User(email, password, firstName, lastName, postalCode, phone, birthDate, description, isCompanyUser, status);

        userRepository.save(user);
        return user;
    }
}
