package tk.swapjob.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import tk.swapjob.dao.requests.OfferRequest;
import tk.swapjob.dao.requests.PythonRequest;
import tk.swapjob.dao.responses.OfferResponse;
import tk.swapjob.model.Offer;
import tk.swapjob.model.User;
import tk.swapjob.repository.OfferRepository;
import tk.swapjob.repository.SkillRepository;
import tk.swapjob.repository.UserRepository;
import tk.swapjob.security.jwt.JwtUtils;
import tk.swapjob.utils.Utils;

import javax.validation.Valid;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
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
    private SkillRepository skillRepository;

    @Autowired
    private JwtUtils jwt;

    @GetMapping("/offer/all")
    public ResponseEntity<?> getAllOffers() {
        List<Offer> offers = offerRepository.findByIsVisibleIsTrue();

        return ResponseEntity.ok(offers);
    }

    @GetMapping("/offer/recommended")
    public ResponseEntity<?> getRecommendedOffers() throws URISyntaxException {
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

        Gson gson = new Gson();
        HttpResponse<String> response = null;

        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest clientRequest = HttpRequest.newBuilder()
                    .uri(URI.create("http://localhost:4321/expert"))
                    .POST(HttpRequest.BodyPublishers.ofString(gson.toJson(request)))
                    .build();

            response = client.send(clientRequest,
                    HttpResponse.BodyHandlers.ofString());

        } catch (IOException | InterruptedException e) {
            System.out.println("Python server not connected, filling with random offers");
        }


        if (response != null) {
            List<Long> pythonResponse = gson.fromJson(response.body(), new TypeToken<List<Long>>() {
            }.getType());
            final List<OfferResponse> recommendedOffers = new ArrayList<>();

            for (Long id : pythonResponse) {
                offerRepository.findById(id).ifPresent(offer -> recommendedOffers.add(new OfferResponse(offer)));
            }
            return ResponseEntity.ok(recommendedOffers);
        }

        List<OfferResponse> recommendedOffersFailed = new ArrayList<>();
        for (Offer offer : offers) {
            recommendedOffersFailed.add(new OfferResponse(offer));
        }
        return ResponseEntity.ok(recommendedOffersFailed);
    }

    @PostMapping("/offer/new")
    public ResponseEntity<?> addOffer(@Valid @RequestBody OfferRequest offerRequest) {
        String username = Utils.getUserFromToken(jwt);

        User user = userRepository.findUserByEmail(username);
        if (user == null || user.getCompany() == null
                || offerRequest == null || offerRequest.isInvalid()) {
            return ResponseEntity.badRequest().body("Invalid user id");
        }

        try {
            Offer newOffer = new Offer(offerRequest);
            for (Long skillId : offerRequest.getSkillList()) {
                skillRepository.findById(skillId).ifPresent(newOffer::addSkill);
            }

            offerRepository.save(newOffer);
            user.getCompany().getOfferList().add(newOffer);
            userRepository.save(user);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Invalid offer");
        }

        return ResponseEntity.ok(true);
    }
}
