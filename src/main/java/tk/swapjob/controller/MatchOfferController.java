package tk.swapjob.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import tk.swapjob.dao.requests.ContractedRequest;
import tk.swapjob.dao.requests.MatchOfferRequest;
import tk.swapjob.dao.requests.RemoveMatchRequest;
import tk.swapjob.model.MatchOffer;
import tk.swapjob.model.Offer;
import tk.swapjob.model.User;
import tk.swapjob.repository.MatchOfferRepository;
import tk.swapjob.repository.OfferRepository;
import tk.swapjob.repository.UserRepository;
import tk.swapjob.security.jwt.JwtUtils;
import tk.swapjob.utils.Utils;

import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
public class MatchOfferController {

    @Autowired
    private OfferRepository offerRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MatchOfferRepository matchOfferRepository;

    @Autowired
    private JwtUtils jwt;

    @PostMapping("/matchOffer")
    public ResponseEntity<?> matchOffer(@RequestBody MatchOfferRequest request) {
        String email = Utils.getUserFromToken(jwt);
        User user = userRepository.findUserByEmail(email);

        if (user == null) {
            return ResponseEntity.badRequest().body("Invalid offer id or user id");
        }

        Optional<Offer> offer = offerRepository.findById(request.getOfferId());

        if (!offer.isPresent()) {
            return ResponseEntity.badRequest().body("Invalid offer id");
        }

        MatchOffer matchOffer = new MatchOffer();
        matchOffer.setOffer(offer.get());
        matchOffer.setContracted(false);
        matchOffer.setFinalized(false);

        if (!matchOfferRepository.existsByOfferIdAndUserId(request.getOfferId(), user.getId())) {
            matchOfferRepository.save(matchOffer);
            user.getMatchOfferList().add(matchOffer);
            userRepository.save(user);
        }

        return ResponseEntity.ok(true);
    }

    @PostMapping("/removeMatchOffer")
    public ResponseEntity<?> removeMatchOffer(@RequestBody MatchOfferRequest request) {
        String email = Utils.getUserFromToken(jwt);
        User user = userRepository.findUserByEmail(email);

        if (user == null) {
            return ResponseEntity.badRequest().body("Invalid user");
        }

        MatchOffer matchOffer = matchOfferRepository.findMatchOfferByOfferIdAndUserId(request.getOfferId(), user.getId());

        if (matchOffer == null) {
            return ResponseEntity.badRequest().body("Invalid offer id or user id");
        }

        user.getMatchOfferList().remove(matchOffer);

        matchOfferRepository.delete(matchOffer);

        userRepository.save(user);

        return ResponseEntity.ok(true);
    }


    @PostMapping("/setContracted")
    public ResponseEntity<?> setContracted(@RequestBody ContractedRequest request) {
        String email = Utils.getUserFromToken(jwt);
        User user = userRepository.findUserByEmail(email);

        if (user == null) {
            return ResponseEntity.badRequest().body("Invalid user");
        }

        Optional<MatchOffer> matchOffer = matchOfferRepository.findById(request.getMatchOfferId());

        if (matchOffer.isEmpty()) {
            return ResponseEntity.badRequest().body("Invalid offer id or user id");
        }

        MatchOffer matchOffer1 = matchOffer.get();
        matchOffer1.setContracted(request.getContracted());

        matchOfferRepository.save(matchOffer1);

        return ResponseEntity.ok(true);
    }


    @PostMapping("/removeMatchCompany")
    public ResponseEntity<?> removeMatchCompany(@RequestBody RemoveMatchRequest request) {
        String email = Utils.getUserFromToken(jwt);
        User user = userRepository.findUserByEmail(email);

        if (user == null) {
            return ResponseEntity.badRequest().body("Invalid user");
        }

        MatchOffer matchOffer = matchOfferRepository.findMatchOfferByOfferIdAndUserId(request.getOfferId(), user.getId());

        matchOfferRepository.delete(matchOffer);

        return ResponseEntity.ok(true);
    }
}
