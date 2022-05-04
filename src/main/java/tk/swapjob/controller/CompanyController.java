package tk.swapjob.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import tk.swapjob.model.Company;
import tk.swapjob.repository.CompanyRepository;
import tk.swapjob.repository.OfferRepository;
import tk.swapjob.security.jwt.JwtUtils;
import tk.swapjob.utils.Utils;

@RestController
public class CompanyController {

    @Autowired
    private OfferRepository offerRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private JwtUtils jwt;

    @GetMapping("/getPublishedOffers")
    public ResponseEntity<?> getPublishedOffers() {
        String companyEmail = Utils.getUserFromToken(jwt);
        Company company = companyRepository.findCompanyByEmail(companyEmail);

        if (company == null) {
            return ResponseEntity.badRequest().body("Company not found");
        }

        return ResponseEntity.ok(company.getOfferList());
    }

}
