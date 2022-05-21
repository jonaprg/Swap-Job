package tk.swapjob.repository;

import org.springframework.data.repository.CrudRepository;
import tk.swapjob.model.Status;

public interface StatusRepository extends CrudRepository<Status, Long> {

    Status getStatusById(int id);
}
