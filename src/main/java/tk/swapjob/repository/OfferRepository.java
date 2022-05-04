package tk.swapjob.repository;

import org.springframework.data.repository.CrudRepository;
import tk.swapjob.model.Offer;

import java.util.List;

public interface OfferRepository extends CrudRepository<Offer, Long> {

    List<Offer> getOffersByCompanyId(int companyId);

    boolean existsById(long id);
}
