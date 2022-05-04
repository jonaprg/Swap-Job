package tk.swapjob.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
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

import java.util.ArrayList;
import java.util.List;

@RestController
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

}
