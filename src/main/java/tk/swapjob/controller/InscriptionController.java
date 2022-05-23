package tk.swapjob.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import tk.swapjob.dao.requests.MatchOfferRequest;
import tk.swapjob.dao.requests.NewOfferRequest;
import tk.swapjob.dao.requests.PythonRequest;
import tk.swapjob.model.MatchOffer;
import tk.swapjob.model.Offer;
import tk.swapjob.model.User;
import tk.swapjob.model.Company;
import tk.swapjob.repository.CompanyRepository;
import tk.swapjob.repository.MatchOfferRepository;
import tk.swapjob.repository.OfferRepository;
import tk.swapjob.repository.UserRepository;
import tk.swapjob.security.jwt.JwtUtils;
import tk.swapjob.utils.Utils;

public class InscriptionController {

    @Autowired
    private OfferRepository offerRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtils jwt;

    @Autowired
    private CompanyRepository companyRepository;

    public ResponseEntity<?> inscriptionOffer(@RequestBody Offer off)
    {
        String username = Utils.getUserFromToken(jwt);

        User user = userRepository.findUserByEmail(username);
        if (user == null || user.getCompany() == null) {
            return ResponseEntity.badRequest().body("Invalid user id");
        }
        if(validOffer(off))
        {
            Company company = user.getCompany();
            offerRepository.save(off);
            company.getOfferList().add(off);
            companyRepository.save(company);
            return ResponseEntity.ok(true);
        }
        else return ResponseEntity.badRequest().body("Invalid offer");
    }
    public boolean validOffer(Offer off)
    {
        return off.getDescription() != null && off.getSalary() != 0.0f && off.getTitle() != null
                && off.getRemote() != null;
    }
}

