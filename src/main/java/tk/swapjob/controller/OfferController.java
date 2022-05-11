package tk.swapjob.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import tk.swapjob.dao.requests.PythonRequest;
import tk.swapjob.model.Offer;
import tk.swapjob.model.User;
import tk.swapjob.repository.OfferRepository;
import tk.swapjob.repository.UserRepository;
import tk.swapjob.security.jwt.JwtUtils;
import tk.swapjob.utils.Utils;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
@CrossOrigin(origins = "*")
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
    public ResponseEntity<?> getRecommendedOffers() throws URISyntaxException {
        //TODO: implement web socket communication to python
        String username = Utils.getUserFromToken(jwt);

        PythonRequest request = new PythonRequest();

        User user = userRepository.findUserByEmail(username);

        request.setUser(new PythonRequest.User(user));

        List<Offer> offers = offerRepository.findByIsVisibleIsTrue();

        List<PythonRequest.Offer> pythonOffers = new ArrayList<>();

        Random rd = new Random();
        int seed = (int) (rd.nextDouble() * (20));

        if (seed == 0) {
            seed = 2;
        }

        for (Offer offer : offers) {
            if (offer.getId() % seed == 0) {
                pythonOffers.add(new PythonRequest.Offer(offer));
            }
        }

        request.setOffers(pythonOffers);

        WebsocketClientEndpoint client = new WebsocketClientEndpoint(new URI("ws://localhost:4321/getExpert_offers"));

        final String[] messageA = {null};
        client.addMessageHandler(message -> messageA[0] = message);

        Gson gson = new Gson();
        client.sendMessage(gson.toJson(request));

        while (messageA[0] == null) {
        }

        try {
            client.userSession.close();
        } catch (IOException e) {
            System.out.println("Socket closing error");
        }

        List<Long> socketResponse = gson.fromJson(messageA[0], new TypeToken<List<Long>>() {
        }.getType());
        List<Offer> recommendedOffers = new ArrayList<>();

        for (Long id : socketResponse) {
            offerRepository.findById(id).ifPresent(recommendedOffers::add);
        }

        if (recommendedOffers.isEmpty()) {
            recommendedOffers = offers;
        }

        return ResponseEntity.ok(recommendedOffers);
    }
}
