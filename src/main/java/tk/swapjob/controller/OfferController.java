package tk.swapjob.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import tk.swapjob.dao.requests.PythonRequest;
import tk.swapjob.model.Offer;
import tk.swapjob.model.User;
import tk.swapjob.repository.OfferRepository;
import tk.swapjob.repository.UserRepository;
import tk.swapjob.security.jwt.JwtUtils;
import tk.swapjob.utils.Utils;

import java.util.ArrayList;
import java.util.List;

@RestController
public class OfferController {

    @Autowired
    private OfferRepository offerRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtils jwt;

    @GetMapping("/offer/all")
    public ResponseEntity<?> getAllOffers() {
        List<Offer> offers = offerRepository.findByIsVisibleIsTrue();

        return ResponseEntity.ok(offers);
    }

    @GetMapping("/offer/recommended")
    public ResponseEntity<?> getRecommendedOffers() {
        //TODO: implement web socket communication to python
        String username = Utils.getUserFromToken(jwt);

        PythonRequest request = new PythonRequest();

        User user = userRepository.findUserByEmail(username);

        request.setUser(new PythonRequest.User(user));

        List<Offer> offers = offerRepository.findByIsVisibleIsTrue();

        List<PythonRequest.Offer> pythonOffers = new ArrayList<>();

        for (Offer offer : offers) {
            pythonOffers.add(new PythonRequest.Offer(offer));
        }

        request.setOffers(pythonOffers);

        //WebSocketClient client = new WebSocketClient(request);

        return ResponseEntity.ok(request);
    }
}
