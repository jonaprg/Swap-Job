package tk.swapjob.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import tk.swapjob.dao.requests.NewOfferRequest;
import tk.swapjob.dao.responses.MessageResponse;
import tk.swapjob.model.Company;
import tk.swapjob.model.MatchOffer;
import tk.swapjob.model.Offer;
import tk.swapjob.model.User;
import tk.swapjob.repository.CompanyRepository;
import tk.swapjob.repository.MatchOfferRepository;
import tk.swapjob.repository.OfferRepository;
import tk.swapjob.repository.UserRepository;
import tk.swapjob.security.jwt.JwtUtils;
import tk.swapjob.utils.Utils;

import javax.validation.Valid;
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
    private UserRepository userRepository;

    @Autowired
    private JwtUtils jwt;

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

    @GetMapping("/offer/new")
    public ResponseEntity<?> newOffer(@Valid @RequestBody NewOfferRequest request) {
        if (request.isInvalid()) {
            return ResponseEntity.badRequest().body("Invalid request");
        }

        String title = request.getTitle();
        String description = request.getDescription();
        boolean is_remote = request.getIs_remote();
        float salary = request.getSalary();

        String companyEmail = Utils.getUserFromToken(jwt);
        User user = userRepository.findUserByEmail(companyEmail);

        Offer offer = new Offer();

        if (user == null || user.getCompany() == null) {
            return ResponseEntity.badRequest().body("Company not found");
        }
        Company company = user.getCompany();

        offer.setTitle(title);
        offer.setDescription(description);
        offer.setVisible(true);
        offer.setRemote(is_remote);
        offer.setSalary(salary);
        offerRepository.save(offer);
        company.getOfferList().add(offer);
        companyRepository.save(company);

        return ResponseEntity.ok(true);
    }

}
