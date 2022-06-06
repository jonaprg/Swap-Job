package tk.swapjob.repository;

import org.springframework.data.repository.CrudRepository;
import tk.swapjob.model.MatchOffer;

import java.util.List;

public interface MatchOfferRepository extends CrudRepository<MatchOffer, Long> {
    List<MatchOffer> findMatchOffersByOfferId(Long offerId);

    MatchOffer findMatchOfferByOfferIdAndUserId(Long offerId, Integer userId);

    Boolean existsByOfferIdAndUserId(Long offerId, Integer userId);

}
